package com.heisenberg.waterutil;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompatSideChannelService;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WaterBill extends Fragment {
    View rootView;
    TextView wat_used,cost_pay,tot_cost;
    Button pay;

    public WaterBill() {
        // Required empty public constructor

    }
    Button btnBottomSheet;
    BottomSheetBehavior sheetBehavior;

    public static WaterLevel newInstance() {
        return new WaterLevel();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView =  inflater.inflate(R.layout.fragment_water_bill, container, false);
        /*wat_used = rootView.findViewById(R.id.wat_cons);
        cost_pay = rootView.findViewById(R.id.cost_to_pay);
        tot_cost = rootView.findViewById(R.id.tot_cost);*/
        pay = rootView.findViewById(R.id.pay);
        ButterKnife.bind(rootView);
        sheetBehavior = BottomSheetBehavior.from(rootView.findViewById(R.id.bottom_sheet_bill));
        btnBottomSheet = rootView.findViewById(R.id.view_picto);
        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_HIDDEN:
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED: {
                        btnBottomSheet.setText("Less Details");
                    }
                    break;
                    case BottomSheetBehavior.STATE_COLLAPSED: {
                        btnBottomSheet.setText("More Details");
                    }
                    break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(rootView.getContext(),"Pay!!! Cheese :)",Toast.LENGTH_LONG).show();
            }
        });
        btnBottomSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleBottomSheet();
            }
        });
        /*StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        InputStream is = null;
        String line = null;
        String result = null;
        String water_usd = "";
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://ickct2018.com/water_util/app/bill_ram.php");
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity entity = httpResponse.getEntity();
            is = entity.getContent();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            result = sb.toString();
            is.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            JSONArray jArray = new JSONArray(result);
            int count = jArray.length();
            for (int i = 0; i < count; i++) {
                JSONObject json_data = jArray.getJSONObject(i);
                water_usd += json_data.getString("0");
            }
            wat_used.setText(water_usd+" L");
            float cost = Math.round(Float.parseFloat(water_usd)*0.01);
            tot_cost.setText("₹"+cost);
            cost_pay.setText(""+cost);

        } catch (Exception e) {
            e.printStackTrace();
        }*/

        return rootView;
    }
    public void toggleBottomSheet() {
        if (sheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
            sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            btnBottomSheet.setText("Less Details");
        } else {
            sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            btnBottomSheet.setText("More Details");
        }
    }
}
