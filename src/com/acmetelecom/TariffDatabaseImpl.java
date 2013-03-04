package com.acmetelecom;

import com.acmetelecom.customer.CentralTariffDatabase;
import com.acmetelecom.customer.Customer;
import com.acmetelecom.customer.Tariff;

public class TariffDatabaseImpl implements ITariffDatabase{

	public Tariff getTariff(Customer customer)
	{
		return CentralTariffDatabase.getInstance().tarriffFor(customer);

	}
	
	
}