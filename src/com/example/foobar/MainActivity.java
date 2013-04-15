package com.example.foobar;

import android.app.Activity;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	private static final String THIS_NAME = MainActivity.class.getName();
	public static final String EXTRA_MESSAGE = THIS_NAME + ".EXTRA_MESSAGE";

	private boolean isSmsReceiverEnabled = false;

	// TODO probably don't create static final instance - need to pass emails to
	// this somehow
	private static final SmsReceiver SMS_RECEIVER = new SmsReceiver();
	private static final IntentFilter SMS_RECEIVED_INTENT_FILTER = new IntentFilter(
			"android.provider.Telephony.SMS_RECEIVED");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.println("MainActivity onCreate");
		setContentView(R.layout.activity_main);

		// TODO read the enabled/disabled setting from file or the
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
		getApplicationContext().registerReceiver(SMS_RECEIVER,
				SMS_RECEIVED_INTENT_FILTER);
		// TODO would be cool to put an icon in the notification bar to show
		// that forwarding is on
	}

	private void unregisterSmsReceiver() {
		System.out.println("unregisterSmsReceiver()");
		getApplicationContext().unregisterReceiver(SMS_RECEIVER);
	}

	public void btn_addEmail_onClick(View view) {
		System.out.println("btn_addEmail_onClick()");
		EditText addEmailBox = (EditText) findViewById(R.id.btn_addEmail);
		addEmailBox.getText();

	}

	// Functions just for random curiosity/debug/exploration
	@Override
	public void onPause() {
		super.onPause();
		System.out.println("MainActivity pause");
	}

	@Override
	public void onStart() {
		super.onStart();
		System.out.println("MainActivity start");
	}

	@Override
	public void onResume() {
		super.onResume();
		System.out.println("MainActivity resume");
	}

	@Override
	public void onStop() {
		super.onStop();
		System.out.println("MainActivity stop");
	}

	@Override
	public void onRestart() {
		super.onRestart();
		System.out.println("MainActivity restart");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		System.out.println("MainActivity destroy");
	}

	// public void btn_sendMessage_onClick(View view) {
	// System.out.println("clicked the button!");
	// Intent intent = new Intent(this, PublishMessageActivity.class);
	// EditText editText = (EditText) findViewById(R.id.edit_message);
	// intent.putExtra(EXTRA_MESSAGE, editText.getText().toString());
	// startActivity(intent);
	// }
}