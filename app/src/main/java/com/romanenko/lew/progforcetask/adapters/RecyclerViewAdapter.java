package com.romanenko.lew.progforcetask.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.romanenko.lew.progforcetask.R;
import com.romanenko.lew.progforcetask.entity.WeatherObject;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolders> {

    private List<WeatherObject> dailyWeather = new ArrayList<>();

    protected Context context;

    public RecyclerViewAdapter(Context context) {

        this.context = context;
    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerViewHolders viewHolder = null;
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_daily_list, parent, false);
        viewHolder = new RecyclerViewHolders(layoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, int position) {

            holder.dayOfWeek.setText(dailyWeather.get(position).getDayOfWeek());
            Picasso.get().load( "http://openweathermap.org/img/w/"
                    + dailyWeather.get(position).getWeatherIcon()+".png").into(holder.weatherIcon);
            double mTemp = Double.parseDouble(dailyWeather.get(position).getWeatherResultSmall());
            holder.weatherResult.setText(String.valueOf(Math.round(mTemp)) + "°");

            holder.weatherResultSmall.setText(dailyWeather.get(position).getWeatherResult());
    }

    @Override
    public int getItemCount() {
        return dailyWeather.size();
    }

    public void setList(List<WeatherObject> dailyWeather){
        this.dailyWeather.addAll(dailyWeather);
        notifyDataSetChanged();
    }

}
