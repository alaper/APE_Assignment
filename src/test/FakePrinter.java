package test;

import com.acmetelecom.Printer;
import java.util.List;

public class FakePrinter implements Printer {

	public String name;
	public String phoneNumber;
	public String pricePlan;
	public String total;
	
	public List<PrintLine> printLines;
	
	private static Printer instance = new FakePrinter();
	
	private FakePrinter() {
	}
	public static Printer getInstance() {
	return instance;
	}
	
		
	@Override
	public void printHeading(String name, String phoneNumber, String pricePlan) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.pricePlan = pricePlan;

	}

	@Override
	public void printItem(String time, String callee, String duration,
			String cost) {

		printLines.add(new PrintLine(time, callee, duration, cost));
	}

	@Override
	public void printTotal(String total) {
		this.total = total;

	}

	public class PrintLine
	{
		public String time;
		public String callee;
		public String duration;
		public String cost;
		
		public PrintLine(String time, String callee, String duration, String cost)
		{
			this.time = time;
			this.callee = callee;
			this.duration = duration;
			this.cost = cost;
		}
	}
	
	
}
