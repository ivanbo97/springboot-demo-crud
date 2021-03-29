package com.luv2code.springboot.cruddemo.dao;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springboot.cruddemo.entity.Employee;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {

	private EntityManager entityManager;
	
	@Autowired
	public EmployeeDAOJpaImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	@Override
	public List<Employee> findAll() {
		
		Query theQuery = 
				entityManager.createQuery("from Employee");
		
		List<Employee> employees = theQuery.getResultList();
		
		return employees;
	}

	@Override
	public Employee findById(int employeeId) {
		
		Employee employee = 
				entityManager.find(Employee.class, employeeId);
		
		return employee;
	}

	@Override
	public void save(Employee employee) {
		
		Employee dbEmployee = entityManager.merge(employee);
		
		// update with id from db ... so we can get generated id for save/insert
		employee.setId(dbEmployee.getId());

	}

	@Override
	public void delete(int employeeId) {
		
		Query theQuery = 
				entityManager.createQuery(
						"delete from Employee where id=:employeeId");
		 theQuery.setParameter("employeeId", employeeId);
		 
		 theQuery.executeUpdate();
	}

}
