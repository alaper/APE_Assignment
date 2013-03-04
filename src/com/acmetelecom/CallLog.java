package com.acmetelecom;

import java.util.ArrayList;
import java.util.List;

import com.acmetelecom.customer.Customer;

public class CallLog {

	private List<CallEvent> callLog = new ArrayList<CallEvent>();
	
	public void initiateCall(String caller, String callee, long time)
	{
		callLog.add(new CallStart(caller, callee, time));
	}
	
	public void initiateCall(String caller, String callee)
	{
		initiateCall(caller, callee, System.currentTimeMillis());
	}
	
	 public void callCompleted(String caller, String callee) {
	     callCompleted(caller, callee, System.currentTimeMillis());
	 }
	 
	 public void callCompleted(String caller, String callee, long time)
	 {
		 
		 callLog.add(new CallEnd(caller, callee, time));
	 }
	 
	 public List<CallEvent> getCallLog()
	 {
		 return callLog;
	 }
	        
	 public void clear()
	 {
		 callLog.clear();
	 }
	 
	 public List<Call> getCallLog(Customer customer)
	 {
	       List<CallEvent> customerEvents = new ArrayList<CallEvent>();
	        for (CallEvent callEvent : callLog) {
	            if (callEvent.getCaller().equals(customer.getPhoneNumber())) {
	                customerEvents.add(callEvent);
	            }
	        }

	        List<Call> calls = new ArrayList<Call>();

	        CallEvent start = null;
	        for (CallEvent event : customerEvents) {
	            if (event instanceof CallStart) {
	                start = event;
	            }
	            if (event instanceof CallEnd && start != null) {
	                calls.add(new Call(start, event));
	                start = null;
	            }
	        }
	        
	        return calls;
	 }
}
