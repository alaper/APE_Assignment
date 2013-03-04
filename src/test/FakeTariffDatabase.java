package test;
import com.acmetelecom.ITariffDatabase;
import com.acmetelecom.customer.Tariff;
import com.acmetelecom.customer.Customer;


public class FakeTariffDatabase implements ITariffDatabase {
	
	public Tariff getTariff(Customer customer)
	{
		switch(customer.getPricePlan())
		{
		case "Business" : return Tariff.Business;
		case "Leisure" : return Tariff.Leisure;
		case "Standard" : return Tariff.Standard;
		default: return Tariff.Business;
		}
	}
	
}
