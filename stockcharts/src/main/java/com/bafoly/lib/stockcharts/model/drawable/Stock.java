package com.bafoly.lib.stockcharts.model.drawable;

import com.bafoly.lib.stockcharts.android.DefaultPainter;
import com.bafoly.lib.stockcharts.draw.DrawCandleStick;
import com.bafoly.lib.stockcharts.draw.DrawGrid;
import com.bafoly.lib.stockcharts.draw.DrawStrategy;
import com.bafoly.lib.stockcharts.model.Environment;
import com.bafoly.lib.stockcharts.model.adapter.Painter;
import com.bafoly.lib.stockcharts.model.axis.Axis;
import com.bafoly.lib.stockcharts.model.data.SingleData;

import java.util.ArrayList;
import java.util.List;

/**
 * Stock object has it's own Chart Data.<br>
 * Stock may have indicators and technical analyses<br>
 * When the draw method is called from the framework, this instance will be responsible for<br>
 * drawing the sub instances.<br>
 * It's more convenient to instantiate the Stock object with the Builder.
 */
public class Stock<X, Y extends Number> extends ChartModel<X, Y> {

    private List<Indicator> indicators = new ArrayList<>();

    private List<TechnicalAnalysis> technicalAnalyses = new ArrayList<>();

    private Stock(Axis xAxis, Axis yAxis) {
        super(xAxis, yAxis);
    }

    public void addIndicator(Indicator indicator){
        if(indicator==null)
            return;
        indicator.setParent(this);
        indicators.add(indicator);
    }

    public List<TechnicalAnalysis> getTechnicalAnalyses() {
        return technicalAnalyses;
    }

    public void setTechnicalAnalyses(List<TechnicalAnalysis> technicalAnalyses) {
        this.technicalAnalyses = technicalAnalyses;
    }

    public void addTechnicalAnalyses(TechnicalAnalysis technicalAnalysis){
        this.technicalAnalyses.add(technicalAnalysis);
    }

    @Override
    public void draw(Environment environment) {
        super.draw(null);

        for(Indicator indicator:indicators){
            if(indicator.isOverlay())
                indicator.draw(getEnvironment());
        }

        for(TechnicalAnalysis technicalAnalysis: technicalAnalyses){
            technicalAnalysis.draw(getEnvironment());
        }
    }

    public static class Builder<X, Y extends Number>{

        private List<? extends SingleData<X, Y>> data;

        private Axis<X> x;

        private Axis<Y> y;

        private DrawStrategy drawStrategy = new DrawCandleStick();

        private Painter painter = new DefaultPainter();

        public Builder<X,Y> setData(List<? extends SingleData<X, Y>> data){
            this.data = data;
            return this;
        }

        public Builder<X,Y> setAxisX(Axis<X> x){
            this.x = x;
            return this;
        }

        public Builder<X,Y> setAxisY(Axis<Y> y){
            this.y = y;
            return this;
        }

        public Builder<X,Y> setDrawStrategy(DrawStrategy drawStrategy){
            this.drawStrategy = drawStrategy;
            return this;
        }

        public Builder<X,Y> setPainter(Painter painter) {
            this.painter = painter;
            return this;
        }

        public Stock<X, Y> build(){
            Stock<X,Y> s = new Stock(null, null);
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
