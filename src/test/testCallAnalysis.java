package test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.joda.*;
import org.joda.time.DateTime;
import org.joda.time.LocalTime;

import com.acmetelecom.*;

public class testCallAnalysis {

	@Test
	public void testSingleDayPeakSeconds()
	{
		CallAnalysis callAnalysis = new CallAnalysis();
		
		//set peak start and end times
		callAnalysis.setPeakStart(new LocalTime(9,0));
		callAnalysis.setPeakEnd(new LocalTime(17,0));
			
		//create a test call using callStart at 15:00 and callEnd at 19:00
		CallStart callStart = new CallStart("12345","54321", new DateTime(2013,2,15,15,0,0,0).getMillis());
		CallEnd callEnd = new CallEnd("12345","54321", new DateTime(2013,2,15,19,0,0,0).getMillis());
		
		Call call = new Call(callStart,callEnd);
		
		try{callAnalysis.calculate(call);}
		catch(Exception e){}
		
		//Peak seconds should be from 15:00 to 17:00 = 2 * 60 * 60 = 7200
		assertEquals(7200,callAnalysis.getPeakSeconds());
	}



	@Test
	public void testSingleDayOffPeakSeconds()
	{
		CallAnalysis callAnalysis = new CallAnalysis();
		
		//set peak start and end times
		callAnalysis.setPeakStart(new LocalTime(9,0));
		callAnalysis.setPeakEnd(new LocalTime(17,0));
			
		//create a test call using callStart at 15:00 and callEnd at 19:00
		CallStart callStart = new CallStart("12345","54321", new DateTime(2013,2,15,15,0,0,0).getMillis());
		CallEnd callEnd = new CallEnd("12345","54321", new DateTime(2013,2,15,19,0,0,0).getMillis());
		
		Call call = new Call(callStart,callEnd);
		
		try{callAnalysis.calculate(call);}
		catch(Exception e){}
		
		//OffPeak seconds should be from 17:00 to 19:00 = 2 * 60 * 60 = 7200
		assertEquals(7200,callAnalysis.getOffPeakSeconds());
	}
	
	@Test
	public void testCallOver24Hours()
	{
		CallAnalysis callAnalysis = new CallAnalysis();
		
		callAnalysis.setPeakStart(new LocalTime(9,0));
		callAnalysis.setPeakEnd(new LocalTime(17,0));
		
		//create a test call using callStart at 15:00 and callEnd at 19:00
		CallStart callStart = new CallStart("12345","54321", new DateTime(2013,2,15,15,0,0,0).getMillis());
		CallEnd callEnd = new CallEnd("12345","54321", new DateTime(2013,2,16,19,0,0,0).getMillis());
	
		Call call = new Call(callStart, callEnd);
		
		try{callAnalysis.calculate(call);}
		catch(Exception e)
		{ assertEquals("Call exceeds 24 hours",e.getMessage());
		}
		
	}
	
	@Test
	public void testMultiDayPeakSeconds()
	{
		CallAnalysis callAnalysis = new CallAnalysis();
		
		callAnalysis.setPeakStart(new LocalTime(9,0));
		callAnalysis.setPeakEnd(new LocalTime(17,0));
		
		//	create a test call using callStart at 15:00 and callEnd at 19:00
		CallStart callStart = new CallStart("12345","54321", new DateTime(2013,2,15,15,0,0,0).getMillis());
		CallEnd callEnd = new CallEnd("12345","54321", new DateTime(2013,2,16,10,0,0,0).getMillis());
	
		Call call = new Call(callStart, callEnd);
		
		try{callAnalysis.calculate(call);}
		catch(Exception e){}
				
		assertEquals(10800,callAnalysis.getPeakSeconds());
		
	}
	
	@Test
	public void testMultiDayOffPeakSeconds()
	{
		CallAnalysis callAnalysis = new CallAnalysis();
		
		callAnalysis.setPeakStart(new LocalTime(9,0));
		callAnalysis.setPeakEnd(new LocalTime(17,0));
		
		//create a test call using callStart at 15:00 and callEnd at 19:00
		CallStart callStart = new CallStart("12345","54321", new DateTime(2013,2,15,15,0,0,0).getMillis());
		CallEnd callEnd = new CallEnd("12345","54321", new DateTime(2013,2,16,10,0,0,0).getMillis());
	
		Call call = new Call(callStart, callEnd);
		
		try{callAnalysis.calculate(call);}
		catch(Exception e){}
				
		assertEquals(57600,callAnalysis.getOffPeakSeconds());
		
	}
	
}

	
	
