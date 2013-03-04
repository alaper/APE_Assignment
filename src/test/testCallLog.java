package test;

import static org.junit.Assert.*;
import com.acmetelecom.*;
import com.acmetelecom.customer.*;
import java.util.*;
import org.joda.time.*;

import org.junit.Before;
import org.junit.Test;

public class testCallLog {

	private CallLog testCallLog;
	private Customer aCustomer;
	private Customer bCustomer;
	private DateTime callStart;
	private DateTime callEnd;
	
	@Before
	public void initialiseCallLog()
	{
		testCallLog = new CallLog();
		aCustomer = new Customer("John", "12345", "Business");
		bCustomer = new Customer("Fred", "54321", "Business");
		
		generateCalls();
		
	}
	
	public void generateCalls()
	{
		callStart = new DateTime(2013,2,15,15,0,0,0);
		callEnd = new DateTime(2013,2,15,17,0,0,0);
		
		testCallLog.initiateCall(aCustomer.getPhoneNumber(), bCustomer.getPhoneNumber(), callStart.getMillis());
		testCallLog.callCompleted(aCustomer.getPhoneNumber(),  bCustomer.getPhoneNumber(), callEnd.getMillis());
	
		callStart = new DateTime(2013,2,15,18,0,0,0);
		callEnd = new DateTime(2013,2,15,18,15,0,0);
		
		testCallLog.initiateCall(aCustomer.getPhoneNumber(),  bCustomer.getPhoneNumber(), callStart.getMillis());
		testCallLog.callCompleted(aCustomer.getPhoneNumber(),  bCustomer.getPhoneNumber(), callEnd.getMillis());
	
		callStart = new DateTime(2013,2,15,19,0,0,0);
		callEnd = new DateTime(2013,2,15,19,30,0,0);
		
		testCallLog.initiateCall(bCustomer.getPhoneNumber(), aCustomer.getPhoneNumber(), callStart.getMillis());
		testCallLog.callCompleted(bCustomer.getPhoneNumber(), aCustomer.getPhoneNumber(), callEnd.getMillis());
		
		
	}
	
	@Test	
	public void testGetCallLogCaller() {
	
		List<Call> callList = testCallLog.getCallLog(aCustomer);
		
		Call aCall = callList.get(0);
	
		assertEquals(aCall.caller(), aCustomer.getPhoneNumber());
	}
	
	@Test
	public void testGetCallLogCallee() {
		
		List<Call> callList = testCallLog.getCallLog(aCustomer);
		
		Call aCall = callList.get(0);
		
		assertEquals(aCall.callee(), bCustomer.getPhoneNumber());
	}
	
	@Test
	public void testNumberOfCallsForCustomerA(){
		
		List<Call> callList = testCallLog.getCallLog(aCustomer);
		
		assertEquals(callList.size(),2);
	}

}
