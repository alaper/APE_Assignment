package com.acmetelecom;

public class CallEnd extends CallEvent {
    public CallEnd(String caller, String callee) {
        super(caller, callee, System.currentTimeMillis());
    }

    public CallEnd(String caller, String callee, long time)
    {
    	super(caller, callee, time);
    }

}
