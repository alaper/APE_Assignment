package com.acmetelecom;
import org.joda.time.*;

public class CallAnalysis {

	private Call call;
	private LocalTime peakStart;
	private LocalTime peakEnd;
	private int peakSeconds;
	private int offPeakSeconds;
	private DateTime callStart;
	private DateTime callEnd;
	private Interval callTime;
	
	
	public CallAnalysis()
	{
		peakSeconds = 0;
		offPeakSeconds = 0;
	}
	
	public void setPeakStart(LocalTime peakStart)
	{
		this.peakStart = peakStart;
	}
	
	public void setPeakEnd(LocalTime peakEnd)
	{
		this.peakEnd = peakEnd;
	}
		
	public void calculate(Call call) throws Exception
	{
		//check if call duration exceeds 24 hours, and raise exception if so
		if(call.durationSeconds() > 86400)
			throw new Exception("Call exceeds 24 hours");
	
		this.call = call;
		
		//set callStart, callEnd and callTime member variables
		initialiseCallVariables();
		
		peakSeconds += calculatePeakSecondsForDay(false);
		if(multiDayCall()) //check if multiday call and, if so, add to peakSeconds
			peakSeconds += calculatePeakSecondsForDay(true);
		
		calculateOffPeakSeconds();
	}
	
	private void initialiseCallVariables()
	{
		callStart = new DateTime(call.startTime());
		callEnd = new DateTime(call.endTime());
		callTime = new Interval(callStart, callEnd);
	}
	
	private int calculatePeakSecondsForDay(boolean DayTwo)
	{
		DateTime peakStartDateTime = calculatePeakDateTime(peakStart, callStart);
		DateTime peakEndDateTime = calculatePeakDateTime(peakEnd, callStart);
		
		if(DayTwo)
		{
			peakStartDateTime = peakStartDateTime.plusDays(1);
			peakEndDateTime = peakEndDateTime.plusDays(1);
		}
		
		Interval peakTime = new Interval(peakStartDateTime, peakEndDateTime);
		
		if(peakTime.overlap(callTime)!=null)
			return (int)peakTime.overlap(callTime).toDurationMillis()/1000;
		else
			return 0;
			
		
	}
	
	private boolean multiDayCall()
	{
		return callStart.getDayOfMonth() != callEnd.getDayOfMonth();
	}
	
	private DateTime calculatePeakDateTime(LocalTime peakLocalTime, DateTime callStartDateTime)
	{	
		return new DateTime(callStartDateTime.getYear(), callStartDateTime.getMonthOfYear(), callStartDateTime.getDayOfMonth(),
		peakLocalTime.getHourOfDay(), peakLocalTime.getMinuteOfHour(),0,0);
	}

	
	
	private void calculateOffPeakSeconds()
	{
		offPeakSeconds = call.durationSeconds() - peakSeconds;
	}
	
	public int getPeakSeconds()
	{
		return peakSeconds;
	}
	
	public int getOffPeakSeconds() 
	{
		return offPeakSeconds;
	}
	
}
