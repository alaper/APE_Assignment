package test;
import java.math.BigDecimal;

import com.acmetelecom.*;
import com.acmetelecom.customer.Tariff;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.joda.time.*;

public class testBillGenerator {

	private FakeCustomerDatabase fkCustomerDatabase;
	private FakeTariffDatabase fkTariffDatabase;
	private CallLog callLog;
	private FakePrinter fkPrinter;
	
	
	@Before
	public void setUp() throws Exception {
	
		callLog = new CallLog();
		fkCustomerDatabase = new FakeCustomerDatabase();
		fkTariffDatabase = new FakeTariffDatabase();
		DateTime callStart = new DateTime(2013,2,15,15,0,0,0);
		DateTime callEnd = new DateTime(2013,2,15,19,0,0,0);
		fkCustomerDatabase.addCustomer("John", "12345", "Business");
		fkCustomerDatabase.addCustomer("Fred",  "54321", "Business");
		
		callLog.initiateCall("12345", "54321", callStart.getMillis());
		callLog.callCompleted("12345", "54321", callEnd.getMillis());
		
		callStart = new DateTime(2013,2,15,19,0,0,0);
		callEnd = new DateTime(2013,2,15,22,0,0,0);
		
		callLog.initiateCall("12345", "54321", callStart.getMillis());
		callLog.callCompleted("12345", "54321", callEnd.getMillis());
	
	}

	@Test
	public void testTotalBillCost() {
		
		BillGenerator billGenerator = new BillGenerator(fkCustomerDatabase, fkTariffDatabase, callLog, fkPrinter);
		
		Bill testBill = billGenerator.getBillFor(fkCustomerDatabase.findCustomer("12345"));
		
		BigDecimal calculatedPeakCharge = Tariff.Business.peakRate().multiply(new BigDecimal(7200));
		BigDecimal calculatedOffPeakCharge = Tariff.Business.offPeakRate().multiply(new BigDecimal(18000));
		
		BigDecimal calculatedCharge = calculatedPeakCharge.add(calculatedOffPeakCharge); 
		
		
		assertEquals(testBill.getTotal().compareTo(calculatedCharge),0);
		
	}

}
