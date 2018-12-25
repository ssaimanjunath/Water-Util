package com.heisenberg.waterutil;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MonthlyUsage extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    View rootView;
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    private RecyclerView cart;
    private AdapterMonth mAdapter_month;
    private SwipeRefreshLayout swipeRefreshWaterLevel;
    public MonthlyUsage() {
        // Required empty public constructor
    }

    public static WaterLevel newInstance() {
        return new WaterLevel();
    }
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView =  inflater.inflate(R.layout.fragment_monthly_usage, container, false);
        swipeRefreshWaterLevel = rootView.findViewById(R.id.swipe_refresh_monthly);
        swipeRefreshWaterLevel.setOnRefreshListener(this);
        /**
         * Showing Swipe Refresh animation on activity create
         * As animation won't start on onCreate, post runnable is used
         */
        swipeRefreshWaterLevel.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            swipeRefreshWaterLevel.setRefreshing(true);
                                            new AsyncFetch().execute();
                                        }
                                    }
        );
        return rootView;
    }

    @Override
    public void onRefresh() {
        new AsyncFetch().execute();
    }
    private class AsyncFetch extends AsyncTask<String, String, String> {
        //ProgressDialog pdLoading = new ProgressDialog(rootView.getContext());
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
            /*pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();*/

        }

        @Override
        protected String doInBackground(String... params) {
            try {

                // Enter URL address where your json file resides
                // Even you can make call to php file which returns json data
                url = new URL("https://water-util.herokuapp.com/app-monthly");

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return e.toString();
            }
            try {

                // Setup HttpURLConnection class to send and receive data from php and mysql
                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("POST");

                // setDoOutput to true as we recieve data from json file
                conn.setDoOutput(true);

            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return e1.toString();
            }

            try {

                int response_code = conn.getResponseCode();

                // Check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {

                    // Read data sent from server
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    // Pass data to onPostExecute method
                    return (result.toString());

                } else {

                    return ("unsuccessful");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            } finally {
                conn.disconnect();
            }


        }

        @Override
        protected void onPostExecute(String result) {

            //this method will be running on UI thread

            //pdLoading.dismiss();
            List<DataMonth> data = new ArrayList<>();

            swipeRefreshWaterLevel.setRefreshing(false);
            try {

                JSONArray jArray = new JSONArray(result);

                // Extract data from json and store into ArrayList as class objects
                for (int i = 0; i < jArray.length(); i++) {
                    JSONObject json_data = jArray.getJSONObject(i);
                    DataMonth schemeData = new DataMonth();
                    schemeData.amt_month = json_data.getString("amount");
                    schemeData.date_upd = json_data.getString("date");
                    data.add(schemeData);
                }

                // Setup and Handover data to recyclerview
                cart = rootView.findViewById(R.id.month_list);
                mAdapter_month = new AdapterMonth(rootView.getContext(), data);
                cart.setAdapter(mAdapter_month);
                cart.setLayoutManager(new LinearLayoutManager(rootView.getContext()));

            } catch (JSONException e) {
                //Toast.makeText(Cart.this, e.toString(), Toast.LENGTH_LONG).show();
                LinearLayout empty = rootView.findViewById(R.id.ifnodata_mon);
                empty.setVisibility(View.VISIBLE);
            }

        }
    }
}
