package com.programming_calendar.workthrough;

import android.widget.Button;
import android.content.Context;
import android.graphics.Canvas;
import java.util.List;
import java.util.ArrayList;

public class ColorBarButton extends Button
{
		private int mIndex;
	
		public ColorBarDrawable mDrawable;

         private List<ProgHours> mDayProgHoursList;
         private double mProductiveHours;
			private double mTotalHours;

         private String output;

	public ColorBarButton(Context context, List<ProgHours> dayProgHoursList, double productiveHours, double totalHours) {
			super(context);

				mDayProgHoursList = new ArrayList<ProgHours>(dayProgHoursList);
				mProductiveHours = productiveHours;
				mTotalHours = totalHours;
				mDrawable = new ColorBarDrawable(mDayProgHoursList, mProductiveHours, mTotalHours);

		}
		
	public int getIndex(){
		return mIndex;
	}
	
	public void setIndex(int index){
		mIndex = index;
	}

	public List<ProgHours> getDayProgHoursList(){
		return mDayProgHoursList;
	}

	public double getProductiveHours(){
		return mProductiveHours;
	}

	public void setProductiveHours(double productiveHours){
		mProductiveHours = productiveHours;
	}

	public double getTotalHours(){
		return mTotalHours;
	}
 
		public ColorBarDrawable getDrawable(){
			return mDrawable;
		}

		public String getString(){
		output = "";
		for (int i = 0; i < mDayProgHoursList.size(); i++){
			output += "(" + mDayProgHoursList.get(i).getColor() + "," + mDayProgHoursList.get(i).getHours() + ") ";
		}
		return output;
	}

		@Override 
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			mDrawable.draw(canvas);
		}

}
