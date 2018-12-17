package com.itrainasia.mymaterial;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 */
public class CardFragment extends Fragment {

    public CardFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RecyclerView recyclerView =  (RecyclerView) inflater.inflate(R.layout.fragment_card, container, false);

        ContentAdapter adapter = new ContentAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return recyclerView;
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView picture;
        public TextView name;
        public TextView description;
        public Button button;

        public CustomViewHolder(LayoutInflater inflater, ViewGroup parent)
        {
            super(inflater.inflate(R.layout.custom_row, parent, false));

            picture = itemView.findViewById(R.id.card_image);
            name = itemView.findViewById(R.id.card_title);
            description = itemView.findViewById(R.id.card_text);
            button = itemView.findViewById(R.id.action_button);
        }
    }

    public class ContentAdapter extends RecyclerView.Adapter<CustomViewHolder>
    {
        private final String[] mPlaces;
        private final String[] mPlaceDesc;
        private final Drawable[] mPlacePictures;

        public ContentAdapter(Context context) {
            Resources resources = context.getResources();
            mPlaces = resources.getStringArray(R.array.places);
            mPlaceDesc = resources.getStringArray(R.array.place_desc);
            TypedArray a = resources.obtainTypedArray(R.array.places_picture);
            mPlacePictures = new Drawable[a.length()];
            for(int i=0; i < mPlacePictures.length; i++)
            {
                mPlacePictures[i] = a.getDrawable(i);
            }

            a.recycle();
        }

        @Override
        public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new CustomViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(CustomViewHolder holder, final int position) {
            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "Reach " + mPlaces[position], Toast.LENGTH_SHORT).show();
                    Log.d("Debug", "Reach " + mPlaces[position]);
                }
            });
            holder.description.setText(mPlaceDesc[position]);
            holder.name.setText(mPlaces[position]);
            holder.picture.setImageDrawable(mPlacePictures[position]);
        }

        @Override
        public int getItemCount() {
            return mPlaces.length;
        }
    }
}
