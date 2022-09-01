package com.maveric.balanceservice.repository;

import com.maveric.balanceservice.model.Balance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface BalanceRepository extends MongoRepository<Balance,String> {

    @Query("{accountId:?0}")
    Page<Balance> findAllByAccountId(Pageable pageable, String accountId);
}
