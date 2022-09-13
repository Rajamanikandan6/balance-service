package com.maveric.balanceservice.service;

import com.maveric.balanceservice.exception.AccountIdMismatchException;
import com.maveric.balanceservice.exception.BalanceAlreadyExistException;
import com.maveric.balanceservice.exception.BalanceNotFoundException;
import com.maveric.balanceservice.model.Account;
import com.maveric.balanceservice.model.Balance;
import com.maveric.balanceservice.repository.BalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import com.maveric.balanceservice.converter.ModelDtoConverter;
import com.maveric.balanceservice.dto.BalanceDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import com.maveric.balanceservice.constant.SuccessMessageConstant;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class BalanceService {
    @Autowired
    BalanceRepository balanceRepository;

    @Autowired
    ModelDtoConverter modelDtoConverter;

    public String getBalance(String balanceId,String accountId) {
        Optional<Balance> bal = balanceRepository.findByAccountIdAndBalanceId(balanceId, accountId);
        if (bal.isPresent()) {
            Balance acctBalance = bal.get();
            return acctBalance.getAmount();
        } else {
            throw new BalanceNotFoundException(balanceId);
        }
    }

    public List<BalanceDto> getAllBalance(String accountId, int page, int pageSize){

        Page<Balance> balances = balanceRepository.findAllByAccountId(PageRequest.of(page, pageSize),accountId);
        List<Balance> listBalance = balances.getContent();
        return modelDtoConverter.entityToDtoList(listBalance);
    }


    public String deleteBalance(String balanceId,String accountId){
        balanceRepository.findByAccountIdAndBalanceId(balanceId,accountId).orElseThrow(() -> new BalanceNotFoundException(balanceId));
        balanceRepository.deleteById(balanceId);
        return SuccessMessageConstant.DELETE_SUCCESS_MESSAGE;
    }

    public BalanceDto updateBalance(Balance balance, String balanceId){
        Optional<Balance> balanceFromDb = balanceRepository.findById(balanceId);
            if(balanceFromDb.isPresent()) {
            Balance newBal = balanceFromDb.get();
            newBal.setAccountId(balance.getAccountId());
            newBal.setCurrency(balance.getCurrency());
            newBal.setAmount(balance.getAmount());


            return modelDtoConverter.entityToDto(balanceRepository.save(newBal));
        }else{
                throw  new BalanceNotFoundException(balanceId);
            }
    }

    public BalanceDto createBalance(Balance balance){

       return modelDtoConverter.entityToDto(balanceRepository.save(balance));
    }

    public void findAccountIdBelongsToCurrentUser(List<Account> account,String account_id){
        AtomicInteger count = new AtomicInteger(0);
        account.forEach(singleAccount -> {
            if (singleAccount.get_id().equals(account_id)){
                count.getAndIncrement();
            }
        });
        if(count.get() == 0){
            throw new AccountIdMismatchException(account_id);
        }
    }
    public BalanceDto getBalanceForParticularAccount(String accountId){
        Balance balances = balanceRepository.findByAccountId(accountId);
        return modelDtoConverter.entityToDto(balances);

    }

    public BalanceDto createBalanceForAccount(Balance balance){
        Balance balance1=balanceRepository.findByAccountId(balance.getAccountId());
        if(balance1 == null)
            return modelDtoConverter.entityToDto(balanceRepository.save(balance));

        throw new BalanceAlreadyExistException(balance.getAccountId());
    }
}
