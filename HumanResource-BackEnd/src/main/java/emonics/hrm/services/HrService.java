package emonics.hrm.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import emonics.hrm.dao.DepartmentRepo;
import emonics.hrm.dao.EmployeeInsuranceRepo;
import emonics.hrm.dao.EmployeeRepo;
import emonics.hrm.entities.Department;
import emonics.hrm.entities.Employee;
import emonics.hrm.entities.EmployeeInsurance;
import emonics.hrm.util.Message;

@Service
public class HrService {
	@Autowired
	private EmployeeRepo repo;
	
	@Autowired
	private DepartmentRepo deptRepo;
	
	@Autowired
	private EmployeeInsuranceRepo insRepo;
	
	public Employee findById(int id) {
		
			Employee e=null;
			Optional<Employee> opt=repo.findById(id);
			if(opt.isPresent()) {
				e=opt.get();
			}else {
				e=new Employee();
			}
			return e;
			
	}
		
		
	@Transactional(propagation=Propagation.REQUIRED)	
	public Message save(Employee e, EmployeeInsurance ins) {
			Message m=new Message();
			try {
				repo.save(e);
				insRepo.save(ins);
				m.setMsg("Employee "+e.getId() + " is addedd successfully");
			}catch(Exception ex) {
				m.setMsg("Error while adding Employee "+e.getId()+ ":"+ex.getMessage());
			}
			
			return m;
		}
	
	public Department findDeptById(int id) {
		Department d=null;
		Optional<Department> opt=deptRepo.findById(id);
		if(opt.isPresent()) {
			d=opt.get();
		}
		return d;
	}
}
