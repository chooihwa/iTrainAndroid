package com.itrainasia.myweather;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class weLayout extends Fragment {

    public weLayout() {
        // Required empty public constructor
    }

    public static List<String> dt = new ArrayList<String>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_we_layout, container, false);

        RequestQueue queue = Volley.newRequestQueue(getActivity());
        String url ="https://api.openweathermap.org/data/2.5/forecast/daily?lat=35&lon=139&cnt=16&appid=8131be7e3e6b2014b3af931e011bd730";

        // Request a string response from the provided URL.
        com.android.volley.toolbox.JsonObjectRequest JsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            for(int i = 0; i < response.getJSONArray("list").length(); i++)
                            {
                                String get_dt = response.getJSONArray("list").getJSONObject(i).getString("dt");
                                dt.add(get_dt);
                            }
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

        ContentAdapter adapter = new ContentAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return recyclerView;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView celcius;

        public ViewHolder(LayoutInflater inflater, ViewGroup parent)
        {
            super(inflater.inflate(R.layout.weather, parent, false));

            celcius = itemView.findViewById(R.id.celcius);
        }
    }

    public static class ContentAdapter extends RecyclerView.Adapter<ViewHolder>
    {
        List<String> mCelcius;

        public ContentAdapter(Context context)
        {
            mCelcius = dt;
        }

        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        public void onBindViewHolder(ViewHolder holder, int position)
        {
            holder.celcius.setText(dt.get(position));
        }

        public int getItemCount()
        {
            return 16;
        }


    }

}
