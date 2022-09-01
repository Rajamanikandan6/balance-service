package com.maveric.balanceservice.repository;

import com.maveric.balanceservice.model.Balance;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BalanceRepository extends MongoRepository<Balance,String> {
    @Query("{Id:?0,accountId:?1}")
    Optional<Balance> findByAccountIdAndBalanceId(String balanceId,String accountId);
}
