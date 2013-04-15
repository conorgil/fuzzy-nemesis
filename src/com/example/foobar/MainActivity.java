package com.example.foobar;

import java.util.List;

import android.app.Activity;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;

public class MainActivity extends Activity {
	private static final String THIS_NAME = MainActivity.class.getName();
	public static final String EXTRA_MESSAGE = THIS_NAME + ".EXTRA_MESSAGE";

	private static final IntentFilter SMS_RECEIVED_INTENT_FILTER = new IntentFilter(
			"android.provider.Telephony.SMS_RECEIVED");
	private static final String EMAIL_REGEX = "[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})";

	private boolean isSmsReceiverEnabled = false;
	private final List<String> registeredEmails = Lists.newArrayList();
	private final SmsReceiver smsReceiver;

	public MainActivity() {
		smsReceiver = new SmsReceiver(this, registeredEmails);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.println("MainActivity onCreate");
		setContentView(R.layout.activity_main);

		// TODO read the enabled/disabled setting from the
		// savedInstanceState

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void btn_registerSmsReceiver_onClick(View view) {
		System.out.println("btn_registerSmsReceiver_onClick");
		Button btn = (Button) findViewById(R.id.btn_registerSmsReceiver);
		if (isSmsReceiverEnabled) {
			unregisterSmsReceiver();
			btn.setText(R.string.btn_registerSmsReceiver_unregisteredText);
		} else {
			registerSmsReceiver();
			btn.setText(R.string.btn_registerSmsReceiver_registeredText);
		}
		isSmsReceiverEnabled = !isSmsReceiverEnabled;
	}

	private void registerSmsReceiver() {
		System.out.println("registerSmsReceiver()");
		getApplicationContext().registerReceiver(smsReceiver,
				SMS_RECEIVED_INTENT_FILTER);
		// TODO would be cool to put an icon in the notification bar to show
		// that forwarding is on
	}

	private void unregisterSmsReceiver() {
		System.out.println("unregisterSmsReceiver()");
		getApplicationContext().unregisterReceiver(smsReceiver);
	}

	public void btn_addEmail_onClick(View view) {
		System.out.println("btn_addEmail_onClick()");
		EditText addEmailBox = (EditText) findViewById(R.id.editTxt_addEmail);
		String email = addEmailBox.getText().toString();

		if (email.length() == 0 || !email.matches(EMAIL_REGEX)) {
			Toast.makeText(
					this,
					String.format("\"%s\" is not a valid email address", email),
					Toast.LENGTH_SHORT).show();
		} else {
			registeredEmails.add(email);
			Toast.makeText(
					this,
					String.format("\"%s\" registered!", email),
					Toast.LENGTH_SHORT).show();
			addEmailBox.setText("");
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		String debug = Objects.toStringHelper(this)
				.add("isSmsReceierEnabled", isSmsReceiverEnabled)
				.add("registeredEmails", registeredEmails).toString();
		System.out.println(debug);
	}
}