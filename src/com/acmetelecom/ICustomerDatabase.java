package com.acmetelecom;
import java.util.List;

import com.acmetelecom.customer.Customer;


public interface ICustomerDatabase {

	public List<Customer> getCustomers();
	
}
