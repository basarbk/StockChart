package com.bafoly.stockchart;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.bafoly.lib.stockcharts.draw.DrawCandleStick;
import com.bafoly.lib.stockcharts.draw.DrawLine;
import com.bafoly.lib.stockcharts.draw.DrawOHLC;
import com.bafoly.widget.menu.MenuExtension;

/**
 * Created by basarb on 6/12/2016.
 */
public class ActivityContent extends AppCompatActivity{

    MenuExtension menuExtension;
    BaseFragment fragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_content);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        menuExtension = (MenuExtension) findViewById(R.id.menuExtension);
        menuExtension.setToolbar(toolbar, getResources().getColor(R.color.colorPrimary));
        menuExtension.setMenuExtensionListener(new MenuExtension.MenuExtensionListener() {
            @Override
            public void onMenuExtensionItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.extension_chart_line:
                        fragment.setDrawStrategy(new DrawLine());
                        break;
                    case R.id.extension_chart_ohlc:
                        fragment.setDrawStrategy(new DrawOHLC());
                        break;
                    case R.id.extension_chart_candle:
                        fragment.setDrawStrategy(new DrawCandleStick());
                        break;
                }
            }
        });


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.content, getFragment(getIntent().getExtras())).commit();

    }

    private Fragment getFragment(Bundle bundle){
        int listItem = bundle.getInt("item", 0);
        switch (listItem){
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_chart:
                menuExtension.menuShowHideToggle(R.menu.chartmenu, getResources().getColor(R.color.extension_menu));
                break;
            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
