package com.heisenberg.waterutil;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TourScreen extends AppCompatActivity {

    private ViewPager mSlideViewPager;
    private LinearLayout mDotLayout;
    private SliderAdapter sliderAdapter;

    private TextView[] mdots;

    private Button mNextBtn,mBackBtn;
    private int currPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_screen);

        mSlideViewPager = findViewById(R.id.slideViewPager);
        mDotLayout = findViewById(R.id.dotsLayout);
        mNextBtn = findViewById(R.id.nextBtn);
        mBackBtn = findViewById(R.id.prevBtn);

        sliderAdapter = new SliderAdapter(this);
        mSlideViewPager.setAdapter(sliderAdapter);

        addDotsIndicator(0);

        mSlideViewPager.addOnPageChangeListener(viewListener);

        mNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currPage==6)
                {
                    Intent i = new Intent(getBaseContext(), Login.class);
                    startActivity(i);
                    finish();
                }
                else
                {
                    mSlideViewPager.setCurrentItem(currPage+1);
                }
            }
        });
        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currPage==0)
                {
                    Intent i = new Intent(getBaseContext(), Login.class);
                    startActivity(i);
                    finish();
                }
                else
                {
                    mSlideViewPager.setCurrentItem(currPage-1);
                }
            }
        });
    }

    public void addDotsIndicator(int position)
    {
        mdots = new TextView[7];
        mDotLayout.removeAllViews();
        for(int i = 0;i<mdots.length;i++)
        {
            mdots[i] = new TextView(this);
            mdots[i].setText(Html.fromHtml("&#8226;"));
            mdots[i].setTextSize(35);
            mdots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));

            mDotLayout.addView(mdots[i]);
        }
        if(mdots.length > 0)
        {
            mdots[position].setTextColor(getResources().getColor(R.color.white));
        }

    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int i) {
            addDotsIndicator(i);
            currPage = i;
            if(i==0)
            {
                mNextBtn.setEnabled(true);
                mBackBtn.setEnabled(true);
                mBackBtn.setVisibility(View.INVISIBLE);
                mNextBtn.setText("Next");
                mBackBtn.setText("Skip");
            }
            else if (i == mdots.length-1)
            {
                mNextBtn.setEnabled(true);
                mBackBtn.setEnabled(true);
                mBackBtn.setVisibility(View.VISIBLE);
                mNextBtn.setText("Finish");
                mBackBtn.setText("Back");
            }
            else
            {
                mNextBtn.setEnabled(true);
                mBackBtn.setEnabled(true);
                mBackBtn.setVisibility(View.VISIBLE);
                mNextBtn.setText("Next");
                mBackBtn.setText("Back");
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
