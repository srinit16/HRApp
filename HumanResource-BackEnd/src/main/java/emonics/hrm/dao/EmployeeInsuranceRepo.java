package emonics.hrm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import emonics.hrm.entities.EmployeeInsurance;

@Repository
public interface EmployeeInsuranceRepo extends JpaRepository<EmployeeInsurance, Integer> {

}
