package emonics.hrm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import emonics.hrm.entities.Department;



@Repository
public interface DepartmentRepo extends JpaRepository<Department, Integer>{
	
}