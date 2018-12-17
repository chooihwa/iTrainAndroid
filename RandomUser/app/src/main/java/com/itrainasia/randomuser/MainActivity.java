package com.itrainasia.randomuser;

import android.app.DownloadManager;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.BreakIterator;

import static com.itrainasia.randomuser.R.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button button;
    ImageView image;
    TextView name,email,phone,dob,address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        image = (ImageView) findViewById(R.id.image);
        name = (TextView) findViewById(R.id.name);
        email = (TextView) findViewById(R.id.email);
        phone = (TextView) findViewById(R.id.phone);
        dob = (TextView) findViewById(R.id.dob);
        address = (TextView) findViewById(R.id.address);

        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://randomuser.me/api/";

        // Request a string response from the provided URL.
        JsonObjectRequest JsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject shorte = response.getJSONArray("results").getJSONObject(0);

                            String picURL = shorte.getJSONObject("picture").getString("large");
                            Picasso.with(getApplicationContext()).load(picURL).into(image);

                            String firstName = shorte.getJSONObject("name").getString("first");
                            String title = shorte.getJSONObject("name").getString("title");
                            String lastName = shorte.getJSONObject("name").getString("last");
                            name.setText(title + ". " + firstName + " " +lastName);
                            email.setText(shorte.getString("email"));
                            phone.setText(shorte.getString("phone"));
                            dob.setText(shorte.getString("dob"));

                            String street = shorte.getJSONObject("location").getString("street");
                            String city = shorte.getJSONObject("location").getString("city");
                            String state = shorte.getJSONObject("location").getString("state");
                            String postCode = shorte.getJSONObject("location").getString("postcode");
                            address.setText(street + ", " + city + ", " + state + ", " +postCode);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                name.setText("That didn't work!");
            }
        });
        // Add the request to the RequestQueue.
        queue.add(JsonObjectRequest);
    }
}
