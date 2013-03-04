package com.acmetelecom;

import com.acmetelecom.customer.Customer;
import com.acmetelecom.customer.Tariff;
import java.math.*;
import java.math.BigDecimal;
import java.util.List;

public class BillGenerator {

	private ICustomerDatabase customerDatabase;
	private ITariffDatabase tariffDatabase;
	private CallLog callLog;
	private Printer printer;
	
	public BillGenerator(ICustomerDatabase customerDatabase, ITariffDatabase tariffDatabase, CallLog callLog, 
	Printer printer)
	{
		this.customerDatabase = customerDatabase;
		this.tariffDatabase = tariffDatabase;
		this.callLog = callLog;
		this.printer = printer;
	}

	public void createCustomerBills()
	{
		List<Customer> customers = customerDatabase.getCustomers();
		for (Customer customer: customers)
		{	createBillFor(customer); }
		
		callLog.clear();
	}
	
	private void createBillFor(Customer customer)
	{
		List<Call> calls = callLog.getCallLog(customer);
		Tariff tariff = tariffDatabase.getTariff(customer);
		Bill bill = new Bill();
		
		for(Call call : calls)
		{
			BigDecimal callCost = calculateCost(tariff, call);
			bill.addLine(call,  callCost);
		}
		
		print(customer, bill);
	}

	public BigDecimal calculateCost(Tariff tariff, Call call)
	{
		BigDecimal cost;
		DaytimePeakPeriod peakPeriod = new DaytimePeakPeriod();
		
		
		if(peakPeriod.offPeak(call.startTime()) && peakPeriod.offPeak(call.endTime()) && call.durationSeconds() < 12 * 60 * 60)
		{
			cost = new BigDecimal(call.durationSeconds()).multiply(tariff.offPeakRate());
		} else
		{
			cost = new BigDecimal(call.durationSeconds()).multiply(tariff.peakRate());
		}
	
		cost = cost.setScale(0, RoundingMode.HALF_UP);
		return cost;
	}

	public void print(Customer customer, Bill bill)
	{
		printer.printHeading(customer.getFullName(), customer.getPhoneNumber(),  customer.getPricePlan());
		
		for (Bill.LineItem call: bill.getLines())
		{
			printer.printItem(call.date(), call.callee(), call.durationMinutes(), MoneyFormatter.penceToPounds(call.cost()));
		}
		
		printer.printTotal(bill.getTotalAsString());
	}

}
