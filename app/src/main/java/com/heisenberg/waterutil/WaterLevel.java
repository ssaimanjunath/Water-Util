package com.heisenberg.waterutil;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.lzyzsd.circleprogress.ArcProgress;

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

public class WaterLevel extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    View rootView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ArcProgress wat_lvl;
    public WaterLevel() {
        // Required empty public constructor
    }

    public static WaterLevel newInstance() {
        return new WaterLevel();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_water_level, container, false);
        swipeRefreshLayout = rootView.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);
        /**
         * Showing Swipe Refresh animation on activity create
         * As animation won't start on onCreate, post runnable is used
         */
        swipeRefreshLayout.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        swipeRefreshLayout.setRefreshing(true);
                                        getWaterLevel();
                                    }
                                }
        );
        return rootView;
    }

    @Override
    public void onRefresh() {
        getWaterLevel();
    }

    private void getWaterLevel() {
        swipeRefreshLayout.setRefreshing(true);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        InputStream is = null;
        String line = null;
        String result = null;
        String lvl = "";
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("https://water-util.herokuapp.com/app-waterlevel");
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
                lvl += json_data.getString("dist");
            }
            wat_lvl = rootView.findViewById(R.id.water_level);
            wat_lvl.setProgress(Integer.parseInt(lvl));

        } catch (Exception e) {
            e.printStackTrace();
        }
        swipeRefreshLayout.setRefreshing(false);

    }
}

