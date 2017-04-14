package com.example.samsung.p0901_asynctaskstatus;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private TextView tvInfo;
    private String message;
    private class MyTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            message = getString(R.string.begin);
            tvInfo.setText(message);
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                for (int i = 0; i < 5; i++) {
                    if (isCancelled()) {
                        return null;
                    }
                    TimeUnit.SECONDS.sleep(1);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            message = getString(R.string.end);
            tvInfo.setText(message);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            message = getString(R.string.cancel);
            tvInfo.setText(message);
        }
    }

    private MyTask myTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvInfo = (TextView) findViewById(R.id.tvInfo);
    }

    public void onClickBtn(View view) {

        switch (view.getId()) {

            case R.id.btnStart :
                startTask();
                break;
            case R.id.btnStatus :
                showStatus();
                break;
            default:
                break;
        }
    }

    private void startTask() {
        myTask = new MyTask();
    }

    private void showStatus() {
        if (myTask != null) {
            message = myTask.getStatus().toString();
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }
}
