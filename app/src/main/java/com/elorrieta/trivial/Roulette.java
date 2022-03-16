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
        categories.add("Cultura General");
        categories.add("Videojuegos");
        categories.add("Cine");
        categories.add("Música");
        categories.add("Stackoverflow");

        sweepAngle = (float) (360 / categories.size());
        position = -90;
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        for (int i = 0; i <= categories.size() -1; i++) {
            Paint paint = getRandomPaint();

            float screenX = (float) (getWidth() - getHeight() / 2) / 2;
            float screenY = (float) getHeight() / 4;
            float arcCenterX;
            float arcCenterY;
            float medianAngle;

            int cX;
            int cY;
            int radius;

            RectF oval = new RectF( screenX, screenY, getWidth() - screenX, getHeight() - screenY);

            cX = (int) (oval.left + oval.right) / 2;
            cY = (int) (oval.top + oval.bottom) / 2;
            radius = (int) (oval.right - oval.left) / 2;

            canvas.drawArc(oval, position, sweepAngle,true, paint);

            medianAngle = (position + (sweepAngle / 2f)) * (float) Math.PI / 180f;
            paint.setColor(Color.BLACK);
            paint.setTextSize(30f);

            arcCenterX = (float)(cX + (radius * Math.cos(medianAngle)));
            arcCenterY = (float)(cY + (radius * Math.sin(medianAngle)));

            // A la verga, llevo horas y no consigo centrar y rotar el texto
            canvas.drawText(categories.get(i), arcCenterX, arcCenterY, paint);

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
