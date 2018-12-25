package com.heisenberg.waterutil;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ssaim on 20-03-2018.
 */

public class AdapterDaily extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    List<DataDaily> data= Collections.emptyList();
    DataDaily current;
    public AdapterDaily(Context context, List<DataDaily> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.content_daily_usage, parent,false);
        AdapterDaily.MyHolder holder=new AdapterDaily.MyHolder(view);
        return holder;
    }
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        AdapterDaily.MyHolder myHolder= (AdapterDaily.MyHolder) holder;
        final DataDaily current=data.get(position);
        myHolder.daily_usage.setText(current.amt_daily+" L");
        int timeval = Integer.parseInt(current.time_upd);

        switch (timeval)
        {
            case 1 : myHolder.daily_time.setText("12 AM - 01 AM");
                break;
            case 2 : myHolder.daily_time.setText("01 AM - 02 AM");
                break;
            case 3 : myHolder.daily_time.setText("02 AM - 03 AM");
                break;
            case 4 : myHolder.daily_time.setText("03 AM - 04 AM");
                break;
            case 5 : myHolder.daily_time.setText("04 AM - 05 AM");
                break;
            case 6 : myHolder.daily_time.setText("05 AM - 06 AM");
                break;
            case 7 : myHolder.daily_time.setText("06 AM - 07 AM");
                break;
            case 8 : myHolder.daily_time.setText("07 AM - 08 AM");
                break;
            case 9 : myHolder.daily_time.setText("08 AM - 09 AM");
                break;
            case 10 : myHolder.daily_time.setText("09 AM - 10 AM");
                break;
            case 11 : myHolder.daily_time.setText("10 AM - 11 AM");
                break;
            case 12 : myHolder.daily_time.setText("11 AM - 12 PM");
                break;
            case 13 : myHolder.daily_time.setText("12 PM - 01 PM");
                break;
            case 14 : myHolder.daily_time.setText("01 PM - 02 PM");
                break;
            case 15 : myHolder.daily_time.setText("02 PM - 03 PM");
                break;
            case 16 : myHolder.daily_time.setText("03 PM - 04 PM");
                break;
            case 17 : myHolder.daily_time.setText("04 PM - 05 PM");
                break;
            case 18 : myHolder.daily_time.setText("05 PM - 06 PM");
                break;
            case 19 : myHolder.daily_time.setText("06 PM - 07 PM");
                break;
            case 20 : myHolder.daily_time.setText("07 PM - 08 PM");
                break;
            case 21 : myHolder.daily_time.setText("08 PM - 09 PM");
                break;
            case 22 : myHolder.daily_time.setText("09 PM - 10 PM");
                break;
            case 23 : myHolder.daily_time.setText("10 PM - 11 PM");
                break;
            case 24 : myHolder.daily_time.setText("11 PM - 12 AM");
                break;
            default: myHolder.daily_time.setText("00 AM - 00 AM");
        }



        int time12 = Integer.parseInt(current.time_upd)%12;
        switch (time12)
        {
            case 1 : myHolder.clock.setImageResource(R.drawable.one);
                     break;
            case 2 : myHolder.clock.setImageResource(R.drawable.two);
                break;
            case 3 : myHolder.clock.setImageResource(R.drawable.three);
                break;
            case 4 : myHolder.clock.setImageResource(R.drawable.four);
                break;
            case 5 : myHolder.clock.setImageResource(R.drawable.five);
                break;
            case 6 : myHolder.clock.setImageResource(R.drawable.six);
                break;
            case 7 : myHolder.clock.setImageResource(R.drawable.seven);
                break;
            case 8 : myHolder.clock.setImageResource(R.drawable.eight);
                break;
            case 9 : myHolder.clock.setImageResource(R.drawable.nine);
                break;
            case 10 : myHolder.clock.setImageResource(R.drawable.ten);
                break;
            case 11 : myHolder.clock.setImageResource(R.drawable.eleven);
                break;
            case 12 : myHolder.clock.setImageResource(R.drawable.twelve);
                break;
        }
    }
    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyHolder extends RecyclerView.ViewHolder{

        TextView daily_usage;
        TextView daily_time;
        CircleImageView clock;

        // create constructor to get widget reference
        public MyHolder(View itemView) {
            super(itemView);
            daily_usage= itemView.findViewById(R.id.amt);
            daily_time= itemView.findViewById(R.id.datatime);
            clock = itemView.findViewById(R.id.clock);
        }

    }
}
