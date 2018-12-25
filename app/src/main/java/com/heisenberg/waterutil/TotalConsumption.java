package com.heisenberg.waterutil;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class TotalConsumption extends Fragment {
    View rootView;
    RelativeLayout suggest;
    public TotalConsumption() {
        // Required empty public constructor
    }

    public static WaterLevel newInstance() {
        return new WaterLevel();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView =  inflater.inflate(R.layout.fragment_total_consumption, container, false);
        suggest = rootView.findViewById(R.id.suggest_nei);
        suggest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(rootView.getContext(),"We are currently working on it!!!",Toast.LENGTH_SHORT).show();
            }
        });
        return rootView;
    }
}
