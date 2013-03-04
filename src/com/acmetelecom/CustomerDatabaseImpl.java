package com.acmetelecom;
import java.util.List;

import com.acmetelecom.customer.*;

public class CustomerDatabaseImpl implements ICustomerDatabase {

	
	
	
	public List<Customer> getCustomers()
	{
		return CentralCustomerDatabase.getInstance().getCustomers();
	}
	
	
	 
	
}