package com.priyanshu.PTInvest.repository;

import com.priyanshu.PTInvest.entity.Holding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HoldingRepository extends JpaRepository<Holding, Long> {

}
