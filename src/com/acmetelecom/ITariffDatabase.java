package com.acmetelecom;

import com.acmetelecom.customer.Customer;
import com.acmetelecom.customer.Tariff;

public interface ITariffDatabase {

	
	public Tariff getTariff(Customer customer);
}
