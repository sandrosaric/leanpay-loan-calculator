package com.example.loancalculatorapi.repository;

import com.example.loancalculatorapi.model.LoanDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//repository class that is part of DAO layer
@Repository
public interface LoanDetailRepository extends JpaRepository<LoanDetail,Long> {

}
