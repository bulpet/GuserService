package com.guser.service.common;

import android.app.Application;

public class GuserApplication extends Application {

	String CallState = "";

	/**
	 * @return the callState
	 */
	public String getCallState() {
		return CallState;
	}

	/**
	 * @param callState the callState to set
	 */
	public void setCallState(String callState) {
		CallState = callState;
	}
}
