package com.heisenberg.waterutil;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by ssaim on 14-03-2018.
 */

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context)
    {
        this.context = context;
    }

    //image for sliders
    public int[] slide_images = {
            R.drawable.hey,
            R.drawable.ic_water,
            R.drawable.ic_daily,
            R.drawable.ic_month,
            R.drawable.ic_graph,
            R.drawable.ic_team,
            R.drawable.ic_bill
    };

    public String[] slide_headings = {
            "Hi, There!!!",
            "WATER LEVEL",
            "DAILY USAGE",
            "MONTHLY USAGE",
            "GRAPHICAL VIEW",
            "TOTAL CONSUMPTION",
            "BILLING"
    };

    public String[] slide_descs = {
            "Let us take you on a tour around the app's features. Just sit back and swipe!",
            "Shows you how much water is left in your tank. Turn the pump on before it runs dry!",
            "Wouldn't it be fascinating to know how much water you've been wasting (Just kidding, Using) everyday?",
            "Monthly report of your wastage (The same joke again, Usage) of water!",
            "Your usage, plotted on the cartesian plane.",
            "Usage of every neighbour of yours and your own, summed up",
            "Sad that water ain't free. Pay for what you've used!"

    };

    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (RelativeLayout)object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout,container,false);

        ImageView slideImagView = view.findViewById(R.id.slideImage);
        TextView slideHeading = view.findViewById(R.id.side_heading);
        TextView slideDescription = view.findViewById(R.id.slide_desc);

        slideImagView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_headings[position]);
        slideDescription.setText(slide_descs[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((RelativeLayout)object);

    }
}
