package com.bafoly.lib.stockcharts.bir.chart.draw;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

import com.bafoly.lib.stockcharts.bir.chart.ChartPosition;
import com.bafoly.lib.stockcharts.bir.chart.data.Chart;
import com.bafoly.lib.stockcharts.bir.chart.data.Coordinate;
import com.bafoly.lib.stockcharts.bir.chart.data.MainData;
import com.bafoly.lib.stockcharts.bir.chart.data.userhelper.UserComment;

public class ChartUserInput extends ChartDraw {
	
	

	public ChartUserInput(ChartPosition chartPosition, MainData mainData) {
		super(chartPosition, mainData);
	}
	
	Paint rootObjectPaint;
	Paint highLightPaint;
	boolean paintLoaded = false;
	
	@Override
	public void preChecks() {
		if(!paintLoaded){
			rootObjectPaint = new Paint();
			rootObjectPaint.setStyle(Paint.Style.FILL);
			
			highLightPaint = new Paint();
			highLightPaint.setStyle(Paint.Style.STROKE);
			highLightPaint.setColor(Color.BLACK);
			highLightPaint.setStrokeWidth(4);
			
			paintLoaded=true;
		}
		
	}

	@Override
	public void draw(Canvas canvas) {
		preChecks();
	}
	int sc;

	boolean scinit = false;
	Path p1,p2;
	public void draw(Canvas canvas, UserComment userInput){
		preChecks();
		Coordinate[] coordinates = userInput.getPoints(chartPosition, mainData);
		if(coordinates!=null){
			
			if(!scinit){
				
				sc = canvas.saveLayer(
						chartPosition.getPaddingLeft(), chartPosition.getPaddingTop(), 
						chartPosition.getViewWidth()-chartPosition.getPaddingRight(),
						chartPosition.getViewHeight()-chartPosition.getPaddingBottom(), null,
						Canvas.MATRIX_SAVE_FLAG |
						Canvas.CLIP_SAVE_FLAG |
						Canvas.HAS_ALPHA_LAYER_SAVE_FLAG |
						Canvas.FULL_COLOR_LAYER_SAVE_FLAG |
						Canvas.CLIP_TO_LAYER_SAVE_FLAG);
				scinit = false;
			}
			
			switch(userInput.getExpectation()){
			case Chart.DIRECTION_UP:
				rootObjectPaint.setColor(Color.GREEN);
				break;
			case Chart.DIRECTION_DOWN:
				rootObjectPaint.setColor(Color.RED);
				break;
			default:
				rootObjectPaint.setColor(Color.LTGRAY);
				break;
			}
			
			boolean first = true;
			if(userInput.isSelected()){
				p2 = new Path();
				for(Coordinate c:coordinates){
					if(first){					
						p2.moveTo(c.x1, c.y1);
						first = false;
					}
					p2.lineTo(c.x1, c.y1);
				}
				p2.close();
				canvas.drawPath(p2, highLightPaint);
			}
			
			boolean secondfirst = true;
			p1 = new Path();
			for(Coordinate c:coordinates){
				if(secondfirst){					
					p1.moveTo(c.x1, c.y1);
					secondfirst = false;
				}
				p1.lineTo(c.x1, c.y1);
			}
			p1.close();
			canvas.drawPath(p1, rootObjectPaint);			
			
			canvas.restoreToCount(sc);
		}
	}

}
