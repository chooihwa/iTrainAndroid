package com.itrainasia.internet;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOError;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {
//   private ConnectivityManager connectivityManager;
//   private NetworkInfo networkInfo;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main);
//        connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
//        networkInfo = connectivityManager.getActiveNetworkInfo();
//
//        if (networkInfo == null) {
//            Log.v("Connectivity", "internet if off");
//        } else {
//            Log.v("Connectivity", "Internet is On");
//
//            WebView webView = new WebView(this);
//            webView.setWebViewClient(new WebViewClient());
//            webView.getSettings().setJavaScriptEnabled(true);
//            webView.loadUrl("http://www.google.com");
//            setContentView(webView);
//        }

        textView = findViewById(R.id.textview);

        String url = "https://randomuser.me/api/";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            public void onFailure(Call call, IOException e) {
                Log.w("OkHttp", e);
            }

            public void onResponse(Call call, final Response response) throws IOException {
                final String responseString = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            JSONObject person = new JSONObject(responseString).getJSONArray("results").getJSONObject(0);
                            String firstName = person.getJSONObject("name").getString("first");
                            String lastName = person.getJSONObject("name").getString("last");
                            String name = "Name: " + firstName + " " + lastName;
                            textView.setText(name);
                        }
                        catch (JSONException e) {
                            Log.e("JSON", e.toString());
                        }

                    }
                });
            }
        });
    }
}