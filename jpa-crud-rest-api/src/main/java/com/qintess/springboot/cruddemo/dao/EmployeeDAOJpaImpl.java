package com.qintess.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;

import com.qintess.springboot.cruddemo.entity.Employee;

public class EmployeeDAOJpaImpl implements EmployeeDAO {
	
	private EntityManager entityManager;
	
	@Autowired
	public EmployeeDAOJpaImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> findAll() {
		
		// criar busca
		Query theQuery = entityManager.createQuery("from Employee");
		
		// executar busca e obter a lista dos employees
		List<Employee> employees = theQuery.getResultList();
		
		// retorna a lista		
		return employees;
	}

	@Override
	public Employee findById(int theId) {
		
		return null;
	}

	@Override
	public void save(Employee theEmployee) {
		
		// salva ou atualiza o employee
		Employee dbEmployee = entityManager.merge(theEmployee);
		
		// atualiza com id do banco de dados, para poder obter um id gerado para salva/inserir
		theEmployee.setId(dbEmployee.getId());

	}

	@Override
	public void deleteById(int theId) {
		
		// deletar objeto com primary key
		Query theQuery = entityManager.createQuery("delete from Employee where id=:employeeId");
		
		theQuery.setParameter("employeeId", theId);
		
		theQuery.executeUpdate();	

	}

}
