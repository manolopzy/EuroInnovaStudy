package com.example.euroinnovastudy.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

/**
 * Vamos a añadir un contador de carácteres a EditText
 * Para implementarlo, tendremos que sobrescribir el
 * evento *onDraw()*, que se llama cada vez que hay que
 * redibujar el control en la pantalla
 *
 * DisplayMetrics
 * is a structure describing general information about a display,
 * such as its size, density, and font scaling.
 * use "context.getResources().getDisplayMetrics();"
 * to access its members
 * Properties: density
 * The logical density of the display.
 * heightPixels
 * The absolute height of the available display size in pixels.
 * densityDpi
 * The screen density expressed as dots-per-inch.
 * xdpi
 * The exact physical pixels per inch of the screen in the X dimension.
 */
public class ExtendedEditText extends androidx.appcompat.widget.AppCompatEditText {
    /**
     * 反鋸齒（英語：anti-aliasing，簡稱AA），也译为抗锯齿或反走样、
     * 消除混叠、抗图像折叠失真等
     * Noun. antialiasing (uncountable) (computer graphics) any technique
     * that reduces the appearance of jagged edges in digital images
     * caused by high-contrast borders between pixels.
     *
     * Creación de diferentes pinceles
     */
    Paint paint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);

    /**
     * The logical density of the display. This is a scaling factor
     * for the Density Independent Pixel unit, where one DIP is one
     * pixel on an approximately 160 dpi screen (for example a 240x320,
     * 1.5"x2" screen), providing the baseline of the system's display.
     * Thus on a 160dpi screen this density value will be 1; on a 120
     * dpi screen it would be .75; etc.
     * https://developer.android.com/reference/android/util/DisplayMetrics
     *
     */
    float scale = getResources().getDisplayMetrics().density;

    public ExtendedEditText(Context context) {
        super(context);
        initialization();
    }

    public ExtendedEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialization();
    }

    public ExtendedEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialization();
    }

    private void initialization(){
        paint1.setStyle(Paint.Style.FILL);
        paint1.setColor(Color.BLACK);

        paint2.setColor(Color.WHITE);
        paint2.setTextSize(50f);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float left = getWidth() - 30 * scale;
        float top = 2 * scale;
        float right = getWidth() - 5 * scale;
        float bottom = 25 * scale;
        //draw a rectangular on the right-top of the EditText
        canvas.drawRect(left, top, right,
                bottom, paint1);
        canvas.drawText(getText().toString().length() + "",
                getWidth() - 28 * scale, 17 * scale, paint2);
    }
}
