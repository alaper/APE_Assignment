package test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.acmetelecom.*;

public class testBillingSystem {

	@Test
	public void BillingSystemCreated() {
		BillingSystem billingSystem = new BillingSystem();
		
		assertNotNull(billingSystem);
		
	}

}
