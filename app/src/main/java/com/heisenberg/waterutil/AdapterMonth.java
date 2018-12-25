package com.heisenberg.waterutil;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by ssaim on 20-03-2018.
 */

public class AdapterMonth extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    List<DataMonth> data= Collections.emptyList();
    DataMonth current;
    public AdapterMonth(Context context, List<DataMonth> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.content_monthly_usage, parent,false);
        AdapterMonth.MyHolder holder=new AdapterMonth.MyHolder(view);
        return holder;
    }
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        AdapterMonth.MyHolder myHolder= (AdapterMonth.MyHolder) holder;
        final DataMonth current=data.get(position);
        myHolder.month_usage.setText(current.amt_month+" L");
        if(current.date_upd.substring(0,2).contains("-"))
            myHolder.month_date.setText("0"+current.date_upd);
        else
            myHolder.month_date.setText(current.date_upd);
        if(current.date_upd.substring(0,2).contains("-"))
            myHolder.noofday.setText(current.date_upd.substring(0,1));
        else
            myHolder.noofday.setText(current.date_upd.substring(0,2));
    }
    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyHolder extends RecyclerView.ViewHolder{

        TextView month_usage;
        TextView month_date;
        TextView noofday;

        // create constructor to get widget reference
        public MyHolder(View itemView) {
            super(itemView);
            month_usage= itemView.findViewById(R.id.amt_month);
            month_date = itemView.findViewById(R.id.dateofmon);
            noofday = itemView.findViewById(R.id.noofday);
        }

    }
}
