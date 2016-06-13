package com.bafoly.stockchart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ActivityMain extends AppCompatActivity implements AdapterView.OnItemClickListener{

    public static String[] activities = {"Candle Stick Chart", "OHLC Chart","Line Chart",
            "Candle Chart with SMA", "Candle Chart with MACD", "OHLC Chart with Bollinger",
            "Candle Chart with Stochastic Oscillator"};

    public static final int CANDLE_CHART = 0;
    public static final int OHLC_CHART = 1;
    public static final int LINE_CHART = 2;
    public static final int CANDLE_WITH_SMA_CHART = 3;
    public static final int CANDLE_WITH_MACD_CHART = 4;
    public static final int OHLC_WITH_BOLLINGER = 5;
    public static final int CANDLE_STOCHASTIC_OSCILLATOR = 6;


    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_main);

        intent = new Intent(this, ActivityContent.class);


        ListView listView = (ListView) findViewById(R.id.activityList);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, activities);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        intent.putExtra("item", i);
        startActivity(intent);
    }
}
