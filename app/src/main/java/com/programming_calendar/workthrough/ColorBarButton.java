package com.programming_calendar.workthrough;

import android.support.v7.widget.AppCompatButton;
import android.content.Context;
import android.graphics.Canvas;
import java.util.List;

public class ColorBarButton extends AppCompatButton
{
		public ColorBarDrawable mDrawable;
		
		private List mThemeColors;
		private List mHoursSpent;

		private String output;

	public ColorBarButton(Context context, List themeColors, List hoursSpent) {
			super(context);
			mThemeColors = themeColors;
			mHoursSpent = hoursSpent;
		//	if (themeColors.size() > 1 && hoursSpent.size() > 1)
				mDrawable = new ColorBarDrawable(mThemeColors, mHoursSpent);
/*
			int x = 10;
			int y = 10;
			int width = 300;
			int height = 50;
*/

			// If the color isn't set, the shape uses black as the default.
			//mDrawable.getPaint().setColor(0xff74AC23);
			// If the bounds aren't set, the shape can't be drawn.
		}


	public List getThemeColors(){
		return mThemeColors;
	}
	
	public List getHoursSpent(){
		return mHoursSpent;
	}

	public void updateLists(List themeColors, List hoursSpent){
		mThemeColors = themeColors;
		mHoursSpent = hoursSpent;
		mDrawable = new ColorBarDrawable(mThemeColors, mHoursSpent);
	}

		public ColorBarDrawable getDrawable(){
			return mDrawable;
		}

		public String getString(){
		output = "";
		for (int i = 0; i < mThemeColors.size(); i++){
			output += "(" + mThemeColors.get(i) + "," + mHoursSpent.get(i) + ") ";
		}
		//output =  width + "," + height + " " + top + " " + barHeightRemainder + " " + totalHours;
		return output;
	}

		@Override 
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
		//	mDrawable.setBounds(x, y, x + width, y + height);
		//	if (mThemeColors.size() > 1 && mHoursSpent.size() > 1)
				mDrawable.draw(canvas);
		}

}