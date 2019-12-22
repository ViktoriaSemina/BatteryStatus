package com.example.viktoriasemina.batterystatus;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mBatteryLevelText;
    private ProgressBar mBatteryLevelProgress;
    private BroadcastReceiver mReceiver;

    /*private TextView tv2;
    private EditText editMessage;
    private Button cancel;
    private Button ok;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBatteryLevelText = (TextView) findViewById(R.id.textView);
        mBatteryLevelProgress = (ProgressBar) findViewById(R.id.progressBar);
        /*tv2 = (TextView) findViewById(R.id.tv2);
        editMessage = (EditText) findViewById(R.id.editMsg);
        cancel = (Button) findViewById(R.id.btnCancel);
        ok = (Button) findViewById(R.id.btnOk);*/

        mReceiver = new BatteryBroadcastReceiver();

    }

    @Override
    protected void onStart() {
        registerReceiver(mReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(mReceiver);
        super.onStop();
    }

    /*
    public void btnOkOnClick(View view) {
        editMessage.setText("");
        view = this.getCurrentFocus();
        try{
            SmsHelper.sendSms("02041534646", "");
        }
        catch (Exception e) {
            cancel.setText("Message have not been sent");
        }
        if (view != null){
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }*/


    /*private BroadcastReceiver BatteryBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context c, Intent intent) {
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
            mBatteryLevelText.setText("Battery Status: " + String.valueOf(level) + "%");
            mBatteryLevelProgress.setProgress(level);

        }
    };*/

    //get battery status and show progress bar
    private class BatteryBroadcastReceiver extends BroadcastReceiver {
        private final static String BATTERY_LEVEL = "level";
        @Override
        public void onReceive(Context context, Intent intent) {
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
            mBatteryLevelText.setText(getString(R.string.battery_level) + " " + level + "%");
            mBatteryLevelProgress.setProgress(level);
        }
    }
}
