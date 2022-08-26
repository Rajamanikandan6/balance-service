package com.maveric.balanceservice.repository;

import com.maveric.balanceservice.model.Balance;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BalanceRepository extends MongoRepository<Balance,String> {

    @Query("{accountId:?0}")
    List<Balance> findAllByAccountId(String accountId);
}
