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

        Bundle bundle = getIntent().getExtras();

        int listItem = bundle.getInt("item", 0);


        fragmentTransaction.replace(R.id.content, getFragment(listItem)).commit();

    }

    private Fragment getFragment(int idx){

        switch (idx){
            case 0:
                return new FragmentChartCandle();
            case 1:
                return new FragmentChartOHLC();
            default:
                return new FragmentChartLine();
        }


    }
}
