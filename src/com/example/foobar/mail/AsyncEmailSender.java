package com.example.foobar.mail;

import android.os.AsyncTask;
import android.util.Log;

public class AsyncEmailSender extends AsyncTask<String, Void, Void> {
	private static final GMailSender GMAIL_SENDER = new GMailSender(
			"virtru.test.123@gmail.com", "password123!");

	@Override
	protected Void doInBackground(String... params) {
		System.out.println("doInBackground()");
		if (params.length == 0) {
			throw new IllegalArgumentException("params cannot be empty");
		}

		for (String toAddress : params) {
			System.out.println("attempting to send to <" + toAddress + ">");
			try {
				GMAIL_SENDER
						.sendMail(
								"From my phone!",
								"Emails being sent with a button click. Progress has been made :) W00t.",
								"virtru.test.123@gmail.com", toAddress);
			} catch (Exception e) {
				Log.e("SendMail", e.getMessage(), e);
				throw new RuntimeException(e);
			}
			System.out.println("email sent to <" + toAddress + ">");
		}
		return null;
	}

}
