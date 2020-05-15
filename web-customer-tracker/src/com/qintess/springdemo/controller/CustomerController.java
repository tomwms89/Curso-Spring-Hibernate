package com.qintess.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.qintess.springdemo.entity.Customer;
import com.qintess.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	// injetar o customer service
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list")
	private String listCustomers(Model theModel) {
		
		// pegar customers do service
		List<Customer> theCustomers = customerService.getCustomers();
		
		// adc customers no model
		theModel.addAttribute("customers", theCustomers);
		
		return "list-customers";
	}
	
	@GetMapping("/showFormAdd")
	public String showFormAdd(Model theModel) {
		// criar atributo model para juntar nos dados
		Customer theCustomer = new Customer();
		
		theModel.addAttribute("customer", theCustomer);
		
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {
		
		customerService.saveCustomer(theCustomer);
		
		return "redirect:/customer/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId, Model theModel) {
		
		// obter the customer do service
		Customer theCustomer = customerService.getCustomer(theId);
		
		// setar customer como model attribute pra pré-preencher form
		theModel.addAttribute("customer", theCustomer);
		
		// mandar para o form
		return "customer-form";
	}
	
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int theId) {
		
		// deletar the customer
		customerService.deleteCustomer(theId);
		
		return "redirect:/customer/list";
	}

}
