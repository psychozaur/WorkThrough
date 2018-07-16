
package com.programming_calendar.workthrough;
import android.graphics.drawable.Drawable;
import android.graphics.PixelFormat;
import android.graphics.ColorFilter;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Paint;
import java.util.List;
import java.util.ArrayList;
import android.widget.Toast;
import android.content.Context;

public class ColorBarDrawable extends Drawable
{

	private List mThemeColors;
	private List mHoursSpent;

	private String output;

		Rect bounds;
		
		int width;
		int height;
		
		Paint backgroundPaint;
		
		int top;
		int barHeightRemainder;
		
		int totalHours;
	
	
		public ColorBarDrawable (List themeColors, List hoursSpent){
			mThemeColors = new ArrayList<Integer>(themeColors);
			mHoursSpent = new ArrayList<Integer>(hoursSpent);

			this.backgroundPaint = new Paint();

			this.top = 0;

			this.totalHours = 12;
		}

	@Override
	public void draw(Canvas canvas)
	{
		
		bounds = getBounds();

		width = bounds.right - bounds.left;
		height = bounds.bottom - bounds.top;

		top = 0;
		barHeightRemainder = height % mThemeColors.size() ;

		for (int i = 0; i < mThemeColors.size() ; i++){
			backgroundPaint.setColor((int)mThemeColors.get(i));
			canvas.drawRect(0,top,width,
				top + (int)((1.0 * (double) ((Integer) mHoursSpent.get(i)).intValue() / totalHours) * height),
				backgroundPaint);
			top += (int)((1.0 * (double) ((Integer) mHoursSpent.get(i)).intValue() / totalHours) * height);
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
		for (int i = 0; i < mThemeColors.size(); i++){
			output += "(" + mThemeColors.get(i) + "," + mHoursSpent.get(i) + ") ";
		}
		//output =  width + "," + height + " " + top + " " + barHeightRemainder + " " + totalHours;
		return output;
	}
	
}
