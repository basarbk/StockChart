package com.bafoly.lib.stockcharts.model;

import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by basarb on 5/6/2016.
 */
public class AndroidPainter implements PaintAdapter<Paint> {

    Map<String, Paint> map = new HashMap<>();

    public AndroidPainter(){
        setColor(LINE_COLOR, Color.BLACK);
        setColor(HIGH_COLOR, Color.GREEN);
        setColor(LOW_COLOR, Color.RED);
        setColor(FRAME_COLOR, Color.GRAY);
    }

    @Override
    public Paint getPaint(String color) {
        return map.get(color);
    }

    @Override
    public void setPaint(String color, Paint paint) {
        map.put(color, paint);
    }

    @Override
    public void setColor(String color, int c) {
        if(map.containsKey(color)){
            map.get(color).setColor(c);
        } else {
            Paint p = new Paint();
            p.setColor(c);
            if(color.equalsIgnoreCase(HIGH_COLOR) || color.equalsIgnoreCase(LOW_COLOR)){
                p.setStrokeWidth(2);
            } else if (color.equalsIgnoreCase(LINE_COLOR)){
                p.setStyle(Paint.Style.STROKE);
                p.setStrokeWidth(2);
                p.setAntiAlias(true);
                p.setStrokeJoin(Paint.Join.ROUND);
                p.setStrokeCap(Paint.Cap.ROUND);
                p.setPathEffect(new CornerPathEffect(5) );
                p.setShadowLayer(1, 1, 1, Color.BLACK);
            } else if(color.equalsIgnoreCase(FRAME_COLOR)){
                p.setStrokeWidth(1);
                p.setStyle(Paint.Style.STROKE);
            }
            map.put(color, p);
        }
    }
}