package com.bafoly.stockchart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by basarb on 6/12/2016.
 */
public class ActivityContent extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_content);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.content, getFragment(getIntent().getExtras())).commit();

    }

    private Fragment getFragment(Bundle bundle){
        int listItem = bundle.getInt("item", 0);
        Fragment fragment;
        switch (listItem){
//            case ActivityMain.CANDLE_CHART:
//            case ActivityMain.OHLC_CHART:
//            case ActivityMain.LINE_CHART:
//            case ActivityMain.CANDLE_WITH_SMA_CHART:
//            case ActivityMain.OHLC_WITH_BOLLINGER:
//                fragment = new FragmentSingleChart();
//                break;
            case ActivityMain.CANDLE_WITH_MACD_CHART:
            case ActivityMain.CANDLE_STOCHASTIC_OSCILLATOR:
                fragment = new FragmentTwoCharts();
                break;
            default:
                fragment = new FragmentSingleChart();
                break;
        }
        fragment.setArguments(bundle);
        return fragment;
    }
}
