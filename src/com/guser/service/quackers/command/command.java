package com.guser.service.quackers.command;

import android.content.Context;
import android.os.Bundle;

import com.guser.service.common.GuserMessage;

//Command
public interface command
{
  public GuserMessage execute(Bundle params, Context context);
}