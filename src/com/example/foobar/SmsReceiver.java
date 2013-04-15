package com.example.foobar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.foobar.mail.AsyncEmailSender;

public class SmsReceiver extends BroadcastReceiver {
	private static final String LOG_TAG = "SMSApp";

	private static final String ACTION = "android.provider.Telephony.SMS_RECEIVED";

	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equals(ACTION)) {
			StringBuilder buf = new StringBuilder();
			// Bundle bundle = intent.getExtras();
			// if (bundle != null) {
			// SmsMessage[] messages = Telephony.Sms.Intents
			// .getMessagesFromIntent(intent);
			// for (int i = 0; i < messages.length; i++) {
			// SmsMessage message = messages[i];
			// buf.append("Received SMS from  ");
			// buf.append(message.getDisplayOriginatingAddress());
			// buf.append(" - ");
			// buf.append(message.getDisplayMessageBody());
			// }
			// }
			Log.i(LOG_TAG, "[SMSApp] onReceiveIntent: " + buf);
			System.out.println("received text: " + buf);

			// TODO load emails from file (user populates on another screen)
			new AsyncEmailSender().execute("conorgilsenan@gmail.com");
		}
	}
}