package com.programming_calendar.workthrough;

import android.graphics.drawable.Drawable;
import android.graphics.PixelFormat;
import android.graphics.ColorFilter;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Paint;
import java.util.List;

public class ColorBarDrawable extends Drawable
{

    private List themeColors;
    private List hoursSpent;

    public ColorBarDrawable (List themeColors, List hoursSpent){
        this.themeColors = themeColors;
        this.hoursSpent = hoursSpent;
    }

    @Override
    public void draw(Canvas canvas)
    {
        Rect bounds = getBounds();

        int width = bounds.right - bounds.left;
        int height = bounds.bottom - bounds.top;

        Paint backgroundPaint = new Paint();

        int top = 0;
        int barHeightRemainder = height % themeColors.size() ;

        int totalHours = 12;

        for (int i = 0; i < themeColors.size() ; i++){
            backgroundPaint.setColor(themeColors.get(i));
            canvas.drawRect(0,top,width,
                    top + (int)((1.0 * (int) hoursSpent.get(i) / totalHours) * height),
                    backgroundPaint);
            top += (int)((1.0 * (int) hoursSpent.get(i) / totalHours) * height);
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

}