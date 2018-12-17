package com.itrainasia.async;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button button;
    String result = "Executed";
    ProgressDialog progressDialog;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button1);
        button.setOnClickListener(this);
        context = this;
    }

    public void onClick(View v){
        switch(v.getId()){
            case R.id.button1:
                new LongOperation().execute();
                break;
        }
    }
    private class LongOperation extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... params) {
            for(int i=0; i<5; i++){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return result;

            }


        @Override
        protected void onPostExecute(String result) {
            TextView txt= findViewById(R.id.output);
            txt.setText(result);
            if (progressDialog.isShowing()){
                progressDialog.dismiss();
            }
        }

        @Override
        protected void onPreExecute() {
           progressDialog=new ProgressDialog(context);
           progressDialog.setMessage("Processing...");
           progressDialog.show();


            }
        }




}
