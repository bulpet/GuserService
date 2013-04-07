package com.guser.service.quackers.command;

import android.content.Context;
import android.os.Bundle;
import com.guser.service.common.GuserMessage;
import com.guser.service.quackers.CallInformation;

public class call_information implements IQuack {

	@Override
	public GuserMessage execute(Bundle params, Context context) {

		CallInformation caller = new CallInformation(context);
		return caller.Quack();
	}
}
