package com.guser.service;

import com.guser.service.Messages.Messages;
import com.guser.service.Messages.Notify;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;
import android.util.Log;

public class CallsContentObserver extends ContentObserver {

		private Context context;
		
        public CallsContentObserver(android.os.Handler handler, Context context) {
            super(handler);
            this.context = context;
        }

        @Override public boolean deliverSelfNotifications() { 
            return false; 
        }

        public void logCallLog() {
            String columns[]=new String[] {
                    CallLog.Calls._ID, 
                    CallLog.Calls.CACHED_NAME,
                    CallLog.Calls.CACHED_NUMBER_LABEL,
                    CallLog.Calls.NUMBER, 
                    CallLog.Calls.DATE, 
                    CallLog.Calls.DURATION, 
                    CallLog.Calls.TYPE};

                        
            Cursor c;
            c =  this.context.getContentResolver().query(Uri.parse("content://call_log/calls"),
                    columns, null, null, "Calls._ID DESC"); //last record first
           
            c.moveToFirst();
            
            Messages msg = new Messages(this.context);
            
            msg.setDuration(c.getLong(c.getColumnIndex(CallLog.Calls.DURATION)));
            msg.setDialed(c.getLong(c.getColumnIndex(CallLog.Calls.DATE)));
            msg.setNumber(c.getString(c.getColumnIndex(CallLog.Calls.NUMBER)));
            msg.setName(c.getString(c.getColumnIndex(CallLog.Calls.CACHED_NAME)));            
            msg.setLabel(c.getString(c.getColumnIndex(CallLog.Calls.CACHED_NUMBER_LABEL)));
            msg.setCallType(c.getLong(c.getColumnIndex(CallLog.Calls.TYPE)));

            c.close();
            
            String message = msg.getRandomMessage();
            Log.i("Message", message);
            //Log.i("CallLog","type: " + c.getString(4) + ", Call to number: "+ number +", registered at: "+ new Date(dialed).toString() + ", duration:" + duration);
            
            Notify notify = new Notify(this.context);
            notify.showNotify("Guser message", message);
               
        }

        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            Log.d("PhoneService", "StringsContentObserver.onChange( " + selfChange + ")");
            logCallLog();
        }
        

}