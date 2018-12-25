package com.heisenberg.waterutil;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.heisenberg.waterutil.Login.otp;
import static com.heisenberg.waterutil.Login.user_mob;

public class Otp extends AppCompatActivity {
    Button val_otp;
    TextView et_otp,skip;
    public static final String LOGIN_PREFERENCE = "Login" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        val_otp = findViewById(R.id.val_otp);
        et_otp = findViewById(R.id.user_otp);
        skip = findViewById(R.id.skip);
        val_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.parseInt(et_otp.getText().toString()) == otp) {
                    SharedPreferences sharedpreferences = getSharedPreferences(LOGIN_PREFERENCE, Otp.this.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("mobile", user_mob);
                    editor.commit();
                    Intent intent = new Intent(Otp.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    AlertDialog.Builder builder1;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        builder1 = new AlertDialog.Builder(Otp.this, android.R.style.Theme_Material_Dialog_Alert);
                    } else {
                        builder1 = new AlertDialog.Builder(Otp.this);
                    }
                    builder1.setMessage("Incorrect OTP. Please enter the correct OTP sent to "+user_mob+".");
                    builder1.setCancelable(true);
                    builder1.setPositiveButton(
                            "Close",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert11 = builder1.create();
                    alert11.setIcon(android.R.drawable.ic_dialog_alert);
                    alert11.show();
                }
            }
        });
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Otp.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
