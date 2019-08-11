package com.androidselva.apiconnection;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.androidselva.apiconnection.API.RequestModels.SendTokenReq;
import com.androidselva.apiconnection.API.ResponseModels.SendTokenRes;
import com.androidselva.apiconnection.UTILS.DateFormat;
import com.androidselva.apiconnection.UTILS.HTTPUtils;
import com.androidselva.apiconnection.UTILS.LogTrace;
import com.androidselva.apiconnection.UTILS.NTConstants;

public class MainActivity extends AppCompatActivity {

    static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SendTokenReqFunc();
    }


    private void SendTokenReqFunc(){
        SendTokenReq loginReq = null;
        try {

            loginReq = new SendTokenReq();
            loginReq.setTimeZone(DateFormat.getTimezone());

            if(NTConstants.checkAvailability(this)){
                new LogonTask().execute(loginReq);
            }else{
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            LogTrace.e(TAG, e.getMessage());
        }
    }

    private class LogonTask extends AsyncTask<SendTokenReq, Integer, SendTokenRes> {
        ProgressDialog ringProgressDialog = null;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showWait();
        }
        private void showWait() {
            ringProgressDialog = ProgressDialog.show(MainActivity.this,
                    "", "Loading, ...", true);

        }
        @Override
        protected SendTokenRes doInBackground(SendTokenReq... params) {
            SendTokenRes sendTokenRes = (SendTokenRes) HTTPUtils.getDataFromServer(params[0], SendTokenReq.class,
                    SendTokenRes.class, "tokenlog");
            return sendTokenRes;
        }
        @Override
        protected void onPostExecute(SendTokenRes sendTokenRes) {
            super.onPostExecute(sendTokenRes);
            cancelWait();
            ProcessSendTokenResponse(sendTokenRes);
        }
        private void cancelWait() {
            ringProgressDialog.dismiss();
        }
    }

    public void ProcessSendTokenResponse(SendTokenRes result) {
        try {
            if(result == null){
                return;
            }
            else{
                Log.d(TAG, "ProcessSendTokenResponse: "+result.getTokens());
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogTrace.e(TAG, e.getMessage());
        }

    }
}
