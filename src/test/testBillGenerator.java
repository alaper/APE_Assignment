package test;
import com.acmetelecom.*;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.joda.time.*;

public class testBillGenerator {

	private FakeCustomerDatabase fkCustomerDatabase;
	private FakeTariffDatabase fkTariffDatabase;
	private CallLog callLog;
	
	
	
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
		
		BillGenerator billGenerator = new BillGenerator(fkCustomerDatabase, fkTariffDatabase);
		
		assertEquals(1,1);
		
	}

}
