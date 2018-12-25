package com.heisenberg.waterutil;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

public class BottomSheetFragment extends BottomSheetDialogFragment {
    View rootView;
    LinearLayout month,daily,rep;
    public BottomSheetFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_bottom_sheet_dialog, container, false);
        daily = rootView.findViewById(R.id.dailygraph_btm);
        month = rootView.findViewById(R.id.monthgraph_btm);
        rep = rootView.findViewById(R.id.report);
        daily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(),DailyLineChartActivity.class);
                startActivity(i);
            }
        });
        month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(rootView.getContext(), MonthLineChartActivity.class);
                startActivity(i);
            }
        });
        rep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(rootView.getContext(),"Currently working",Toast.LENGTH_SHORT).show();
            }
        });
        return rootView;
    }
}
