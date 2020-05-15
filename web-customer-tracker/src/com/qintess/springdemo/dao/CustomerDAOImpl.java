package com.qintess.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qintess.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers() {
		
		// pegar a session atual do hibernate
		Session currentSession = sessionFactory.getCurrentSession();
		
		// criar uma query ordenada pelo last name
		Query<Customer> theQuery = currentSession.createQuery("from Customer order by lastName", Customer.class);
		
		// executar query e pegar result list
		List<Customer> customers = theQuery.getResultList();
		
		// retornar os results
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		
		// obter a sessão atual do hibernate
		Session currentSession = sessionFactory.getCurrentSession();
		
		// salvar ou atualizar o customer
		currentSession.saveOrUpdate(theCustomer);
		
	}

	@Override
	public Customer getCustomer(int theId) {
		
		// obter a sessão atual do hibernate
		Session currentSession = sessionFactory.getCurrentSession();
		
		// recupero/leio do bd usando a primary key
		Customer theCustomer = currentSession.get(Customer.class, theId);
		
		return theCustomer;
	}

	@Override
	public void deleteCustomer(int theId) {
		
		// obter a sessão atual do hibernate
		Session currentSession = sessionFactory.getCurrentSession();
		
		// deletar o objeto com a primary key
		Query theQuery = currentSession.createQuery("delete from Customer where id=:customerId");
		
		theQuery.setParameter("customerId", theId);
		
		theQuery.executeUpdate();		
	}

}
