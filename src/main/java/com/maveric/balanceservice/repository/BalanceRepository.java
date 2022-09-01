package com.maveric.balanceservice.repository;

import com.maveric.balanceservice.model.Balance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;



@Repository
public interface BalanceRepository extends MongoRepository<Balance,String> {

    @Query("{accountId:?0}")
    Page<Balance> findAllByAccountId(Pageable pageable, String accountId);

    @Query("{Id:?0,accountId:?1}")
    Optional<Balance> findByAccountIdAndBalanceId(String balanceId,String accountId);

}
