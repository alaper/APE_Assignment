package com.acmetelecom;

public class CallStart extends CallEvent {
    public CallStart(String caller, String callee) {
        super(caller, callee, System.currentTimeMillis());
    }
    public CallStart(String caller, String callee, long time)
    {
    	super(caller, callee, time);
    }
}
