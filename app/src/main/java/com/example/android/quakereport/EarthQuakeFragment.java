package com.example.android.quakereport;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class  EarthQuakeFragment   extends Fragment {

    private static final String MY_NUM_KEY = "Image";
    private static final String MY_COLOR_KEY = "News";

    private String mNum;
    private String mColor;

    public static EarthQuakeFragment newInstance(String image, String news) {
        EarthQuakeFragment f = new EarthQuakeFragment();
        Bundle args = new Bundle();
        args.putString(MY_NUM_KEY, image);
        args.putString(MY_COLOR_KEY, news);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNum = getArguments() != null ? getArguments().getString(MY_NUM_KEY) : "";
        mColor = getArguments() != null ? getArguments().getString(MY_COLOR_KEY) : "";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.earthquake_list_item, container, false);


        Picasso.with(getContext()).load(mNum).into((ImageView) rootView.findViewById(R.id.weather_icon));

        TextView textView = rootView.findViewById(R.id.magnitude);
        textView.setText(mColor);

        return rootView;
    }
}