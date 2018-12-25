package com.heisenberg.waterutil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ybq.android.spinkit.SpinKitView;
import com.github.ybq.android.spinkit.style.Circle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import at.markushi.ui.CircleButton;

public class Login extends AppCompatActivity {
    CircleButton sendotp;
    Button tour;
    TextView mobile;
    public static int otp;
    public static String user_mob;
    public static final String LOGIN_PREFERENCE = "Login" ;
    SpinKitView spinKitView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = getSharedPreferences(LOGIN_PREFERENCE, Login.this.MODE_PRIVATE);
        String isLogin = prefs.getString("mobile", "");
        if(isLogin.isEmpty()==false)
        {
            Intent in = new Intent(this,MainActivity.class);
            startActivity(in);
            finish();
        }
        else
        {
            setContentView(R.layout.activity_login);
            sendotp = findViewById(R.id.sendotp);
            mobile = findViewById(R.id.mobile);
            spinKitView = findViewById(R.id.spin_login);
            sendotp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new AsyncLogin().execute(mobile.getText().toString());
                }
            });
            tour = findViewById(R.id.tour);
            tour.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getBaseContext(),TourScreen.class);
                    startActivity(intent);
                    finish();
                }
            });
        }
    }
    private class AsyncLogin extends AsyncTask<String, String, String>
    {
        HttpURLConnection conn;
        URL url = null;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            spinKitView.setVisibility(View.VISIBLE);
        }
        @Override
        protected String doInBackground(String... params)
        {
            String mob = params[0];
            user_mob = mob;
            otp = (int) Math.round(Math.random()*10000);
            try
            {
                url = new URL("http://ickct2018.com/water_util/app/sendsms.php?&phone="+mob+"&OTP="+otp);
            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
                return "exception";
            }
            try
            {
                conn = (HttpURLConnection)url.openConnection();
                conn.connect();

            } catch (IOException e1)
            {
                e1.printStackTrace();
                return "exception";
            }
            try {

                int response_code = conn.getResponseCode();
                if (response_code == HttpURLConnection.HTTP_OK)
                {
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line="";
                    while ((line = reader.readLine()) != null)
                    {
                        result.append(line);
                    }
                    return(result.toString());
                }
                else
                {
                    return("unsuccessful");
                }

            }
            catch (IOException e)
            {
                e.printStackTrace();
                return "exception";
            }
            finally
            {
                conn.disconnect();
            }
        }
        @Override
        protected void onPostExecute(String result)
        {
            spinKitView.setVisibility(View.INVISIBLE);
            Toast.makeText(getBaseContext(),"Result : OTP "+result,Toast.LENGTH_LONG).show();
            Intent i = new Intent(getBaseContext(),Otp.class);
            startActivity(i);
            finish();
        }
    }
}
