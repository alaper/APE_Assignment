package com.acmetelecom;

import com.acmetelecom.customer.CentralCustomerDatabase;
import com.acmetelecom.customer.CentralTariffDatabase;
import com.acmetelecom.customer.Customer;
import com.acmetelecom.customer.Tariff;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class BillingSystem {

	private CallLog callLog;
	private ICustomerDatabase customerDatabase;
    private ITariffDatabase tariffDatabase;
	private Printer printer;
    
	public BillingSystem(CallLog callLog, ICustomerDatabase customerDatabase, ITariffDatabase tariffDbse, Printer printer)
	{
		this.callLog = callLog;
		this.customerDatabase = customerDatabase;
		this.tariffDatabase = tariffDatabase;
		this.printer = printer;
	}
	
	public BillingSystem()
	{
		this.callLog = new CallLog();
		this.customerDatabase = new CustomerDatabaseImpl();
		this.tariffDatabase = new TariffDatabaseImpl();
		this.printer = HtmlPrinter.getInstance();
	}
		
	
	public void callInitiated(String caller, String callee)
	{
		callLog.initiateCall(caller, callee);
	}
	
	public void callCompleted(String caller, String callee)
	{
		callLog.callCompleted(caller, callee);
	}
	
	
    public void createCustomerBills() {
        
    new BillGenerator(customerDatabase, tariffDatabase, callLog, printer).createCustomerBills();
    }
}		
