package com.itrainasia.myweathertutorial;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    LinearLayout linear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        final ContentAdapter adapter = new ContentAdapter(this);

        recyclerView.setAdapter(adapter);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://api.openweathermap.org/data/2.5/forecast/daily?lat=35&lon=139&cnt=16&appid=8131be7e3e6b2014b3af931e011bd730";

        // Request a string response from the provided URL.
        JsonObjectRequest JsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            for(int i=0;i<response.getJSONArray("list").length();i++)
                            {
                                adapter.addJSON(response.getJSONArray("list").getJSONObject(i));
                            }
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("debug","That didn't work!");
            }
        });
        // Add the request to the RequestQueue.
        queue.add(JsonObjectRequest);


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder
    {
        public LinearLayout linear;
        public ImageView imageView;
        public TextView tempTextView;
        public TextView weatherTextView;
        public TextView dateTextView;

        public CustomViewHolder(LayoutInflater inflater, ViewGroup parent)
        {
            super(inflater.inflate(R.layout.custom_row, parent, false));

            linear = itemView.findViewById(R.id.linearlayout);
            imageView = itemView.findViewById(R.id.imageView);
            tempTextView = itemView.findViewById(R.id.tempTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            weatherTextView = itemView.findViewById(R.id.weatherTextView);

        }
    }

    public static class ContentAdapter extends RecyclerView.Adapter<CustomViewHolder>
    {
        private final List<JSONObject> objectList = new ArrayList<>();
        Context context;

        public ContentAdapter(Context context)
        {
            this.context = context;
        }
        @Override
        public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new CustomViewHolder(LayoutInflater.from(parent.getContext()),parent);
        }

        @Override
        public void onBindViewHolder(CustomViewHolder holder, int position) {
            final JSONObject obj = objectList.get(position);

            try {
                String imageIcon = obj.getJSONArray("weather").getJSONObject(0).getString("icon");
                String imageUrl = "https://openweathermap.org/img/w/"+imageIcon+".png";
                Picasso.with(context).load(imageUrl).into(holder.imageView);
                holder.weatherTextView.setText(obj.getJSONArray("weather").getJSONObject(0).getString("main"));
                holder.tempTextView.setText((obj.getJSONObject("temp").getInt("morn")-273)+" C");
                Date date = new Date((long)obj.getInt("dt")*1000);
                holder.dateTextView.setText(date.toString());

                final String abc = (obj.getJSONObject("temp").getInt("morn")-273)+" C";

                holder.linear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context,abc,Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public int getItemCount() {
            return objectList.size();
        }

        public void addJSON(JSONObject jsonObject)
        {
            objectList.add(jsonObject);
        }
    }
}
