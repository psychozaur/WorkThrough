package com.programming_calendar.workthrough;

import android.graphics.drawable.Drawable;
import android.graphics.PixelFormat;
import android.graphics.ColorFilter;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Paint;
import java.util.List;
import android.widget.Toast;
import android.content.Context;

public class ColorBarDrawable extends Drawable
{

		private List <ProgHours> mDayProgHoursList;

		private String output;

		Rect bounds;
		
		int width;
		int height;
		
		Paint backgroundPaint;
		
		int top;
		int barHeightRemainder;
		
		int totalHours;
		double productiveHours;
	
	
		public ColorBarDrawable (List dayProgHoursList, double productiveHours){

			mDayProgHoursList = dayProgHoursList;

			this.backgroundPaint = new Paint();

			this.top = 0;

			this.totalHours = 12;
			this.productiveHours = productiveHours;
		}

	@Override
	public void draw(Canvas canvas)
	{
		
		bounds = getBounds();

		width = bounds.right - bounds.left;
		height = bounds.bottom - bounds.top;

		top = 0;
		barHeightRemainder = height % mDayProgHoursList.size() ;

		backgroundPaint.setColor(0x00000000);
		canvas.drawRect(0,top,width,
		top + (int)(((totalHours - productiveHours) / totalHours) * height),
		backgroundPaint);
		top += (int)(((totalHours - productiveHours) / totalHours) * height);


		for (int i = 0; i < mDayProgHoursList.size() ; i++){
			backgroundPaint.setColor(mDayProgHoursList.get(i).getColor());
			canvas.drawRect(0,top,width,
				top + (int)((mDayProgHoursList.get(i).getHours() / totalHours) * height),
				backgroundPaint);
			top += (int)((mDayProgHoursList.get(i).getHours() / totalHours) * height);
		}
		
		if (barHeightRemainder > 0){
			canvas.drawRect(0,height - barHeightRemainder,width,height,backgroundPaint);
		}

		backgroundPaint.setColor(0xffffffff);
		canvas.drawOval(width/4,
						(height/2 - width/4),
						(width - width/4),
						(height/2 + width/4),
						backgroundPaint);

	}
	
	@Override
	public void setAlpha(int p1)
	{
		
	}
	
	@Override
	public void setColorFilter(ColorFilter p1)
	{
		
	}

	@Override
	public int getOpacity()
	{
		return PixelFormat.OPAQUE;
	}

public String getString(){
		output = "";
		for (int i = 0; i < mDayProgHoursList.size(); i++){
			output += "(" + mDayProgHoursList.get(i).getColor() + "," + mDayProgHoursList.get(i).getHours() + ") ";
		}
		return output;
	}
	
}
