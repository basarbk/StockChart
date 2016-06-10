package com.bafoly.lib.stockcharts.iki.model.drawable;

import com.bafoly.lib.stockcharts.iki.android.DefaultPainter;
import com.bafoly.lib.stockcharts.iki.draw.DrawCandleStick;
import com.bafoly.lib.stockcharts.iki.draw.DrawGrid;
import com.bafoly.lib.stockcharts.iki.draw.DrawStrategy;
import com.bafoly.lib.stockcharts.iki.model.Environment;
import com.bafoly.lib.stockcharts.iki.model.Painter;
import com.bafoly.lib.stockcharts.iki.model.axis.Axis;
import com.bafoly.lib.stockcharts.iki.model.data.SingleData;

import java.util.List;

/**
 * Created by basarb on 6/10/2016.
 */
public class Stock<X, Y extends Number> extends Instrument<X, Y> {

    public static class Builder<X, Y extends Number>{

        private List<? extends SingleData<X, Y>> data;

        private Axis<X> x;

        private Axis<Y> y;

        private DrawStrategy drawStrategy = new DrawCandleStick();

        private Painter painter = new DefaultPainter();

        public Builder setData(List<? extends SingleData<X, Y>> data){
            this.data = data;
            return this;
        }

        public Builder setAxisX(Axis<X> x){
            this.x = x;
            return this;
        }

        public Builder setAxisY(Axis<Y> y){
            this.y = y;
            return this;
        }

        public Builder setDrawStrategy(DrawStrategy drawStrategy){
            this.drawStrategy = drawStrategy;
            return this;
        }

        public Builder setPainter(Painter painter) {
            this.painter = painter;
            return this;
        }

        public Stock build(){
            Stock<X,Y> s = new Stock();
            s.setEnvironment(new Environment());
            s.setAxisDrawStrategy(new DrawGrid());
            s.setData(data);
            s.setxAxis(x);
            s.setyAxis(y);
            s.setPainter(painter);
            s.setDataDrawStrategy(drawStrategy);
            return s;
        }

    }
}
