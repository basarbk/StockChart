package com.bafoly.lib.stockcharts.iki.model.drawable;

import com.bafoly.lib.stockcharts.iki.android.DefaultPainter;
import com.bafoly.lib.stockcharts.iki.draw.DrawCandleStick;
import com.bafoly.lib.stockcharts.iki.draw.DrawGrid;
import com.bafoly.lib.stockcharts.iki.draw.DrawStrategy;
import com.bafoly.lib.stockcharts.iki.model.Environment;
import com.bafoly.lib.stockcharts.iki.model.Painter;
import com.bafoly.lib.stockcharts.iki.model.axis.Axis;
import com.bafoly.lib.stockcharts.iki.model.data.SingleData;

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
