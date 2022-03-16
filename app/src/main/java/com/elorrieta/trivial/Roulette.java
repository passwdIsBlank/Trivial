package com.elorrieta.trivial;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Random;

public class Roulette extends View {

    Context context;
    float position;
    float sweepAngle;
    ArrayList<String> categories;

    public Roulette(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;

        categories = new ArrayList<>();
        categories.add("Culture");
        categories.add("Games");
        categories.add("Films");
        categories.add("Music");
        categories.add("Code");

        sweepAngle = (float) (360 / categories.size());
        position = 0;
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        for (int i = 0; i <= categories.size() -1; i++) {
            Paint paint = getRandomPaint();
            float radius = (getWidth() / 2f) - 50;
            float radiusInner = radius / 1.5f;

            float arcCenterX;
            float arcCenterY;
            float medianAngle;

            int cX;
            int cY;

            final RectF oval = new RectF(
                    getWidth() / 2 - radius,
                    getHeight() / 2 - radius,
                    getWidth() / 2 + radius,
                    getHeight() / 2 + radius
            );

            final RectF ovalText = new RectF(
                    getWidth() / 2 - radiusInner,
                    getHeight() / 2 - radiusInner,
                    getWidth() / 2 + radiusInner,
                    getHeight() / 2 + radiusInner
            );

            cX = (int) (ovalText.left + ovalText.right) / 2;
            cY = (int) (ovalText.top + ovalText.bottom) / 2;

            canvas.drawArc(oval, position, sweepAngle,true, paint);

            paint.setAlpha(0);

            canvas.drawArc(ovalText, position, sweepAngle,false, paint);

            medianAngle = (position + (sweepAngle / 2f)) * (float) Math.PI / 180f;
            paint.setColor(Color.BLACK);
            paint.setTextSize(30f);

            arcCenterX = (float)(cX + (radiusInner * Math.cos(medianAngle)));
            arcCenterY = (float)(cY + (radiusInner * Math.sin(medianAngle)));

            canvas.save();
            canvas.drawText(categories.get(i), arcCenterX - (sweepAngle / 1.5f), arcCenterY, paint);
            canvas.rotate(180);
            canvas.restore();

            position += sweepAngle;
        }
    }

    private Paint getRandomPaint() {
        Paint paint = new Paint();
        Random rnd = new Random();

        int min = 140;
        int max = 240;

        paint.setARGB(255, rnd.nextInt( max - min + 1) + min, rnd.nextInt( max - min + 1) + min, rnd.nextInt( max - min + 1) + min);
        paint.setStyle(Paint.Style.FILL);

        return paint;
    }

}
