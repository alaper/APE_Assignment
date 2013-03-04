package com.acmetelecom;

import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

public class Bill {

	private BigDecimal total;
	private List<LineItem> lines;
	
	public Bill()
	{
		lines = new ArrayList();
		total = new BigDecimal(0);
		
	}
	
	public void setTotal(BigDecimal total)
	{
		this.total = total;
	}
	
	public void addLine(Call call, BigDecimal cost)
	{
		lines.add(new LineItem(call, cost));
		total.add(cost);
	}
	
	public List<LineItem> getLines()
	{
		return lines;
	}
	
	public BigDecimal getTotal()
	{
		return total;
	}
	
	public Boolean isEmpty()
	{
		return lines==null;
	}
	
	public String getTotalAsString()
	{
		return MoneyFormatter.penceToPounds(total);
	}
	
	public class LineItem{
		private Call call;
		private BigDecimal callCost;
		
		public LineItem(Call call, BigDecimal callCost)
		{
			this.call = call;
			this.callCost = callCost;
		}
		
		public String date()
		{
			return call.date();
		}
		
		public String callee()
		{
			return call.callee();
		}
		
		public String durationMinutes()
		{
			return "" + call.durationSeconds()/60 + ":" + String.format("%02d",  call.durationSeconds() % 60);
		}
		
		public BigDecimal cost()
		{
			return callCost;
		}
		
	}
	
}
