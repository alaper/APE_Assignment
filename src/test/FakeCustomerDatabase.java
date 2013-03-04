package test;

import java.util.ArrayList;
import java.util.List;

import com.acmetelecom.ICustomerDatabase;
import com.acmetelecom.customer.Customer;


public class FakeCustomerDatabase implements ICustomerDatabase {

	private List<Customer> customerList;
	
	public FakeCustomerDatabase()
	{
		customerList = new ArrayList<Customer>();
	}
	
	@Override
	public List<Customer> getCustomers() {
		return customerList;
	
	}

	public void addCustomer(String name, String phoneNumber, String pricePlan)
	{
		customerList.add(new Customer(name, phoneNumber, pricePlan));
	}
	
	public Customer findCustomer(String phoneNumber)
	{
		for(Customer customer : customerList)
		{
			if(customer.getPhoneNumber().compareTo(phoneNumber)==0)
				{return customer;}
		}
		
		return null;
	}
}
