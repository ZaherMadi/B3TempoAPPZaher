package com.example.b3tempoappzaher;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

/**
 * TODO: document your custom view class.
 */
public class DayColorView extends View {
    private String captionText;
    private int captionTextColor = Color.BLACK;
    private float captionTextSize = 0;
    private int dayCircleColor = Color.GRAY;

    private TextPaint textPaint;
    private float textWidth;
    private float textHeight;

    private Paint circlePaint;

    public DayColorView(Context context) {
        super(context);
        init(context,null, 0);

    }

    public DayColorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);


    }

    public DayColorView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);


    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.DayColorView, defStyle, 0);

        String text = a.getString(R.styleable.DayColorView_captionText);
        captionText = text == null ? "Preview Text" : text;

        captionTextColor = a.getColor(R.styleable.DayColorView_captionTextColor, captionTextColor);
        // Use getDimensionPixelSize or getDimensionPixelOffset when dealing with
        // values that should fall on pixel boundaries.
        captionTextSize = a.getDimension(R.styleable.DayColorView_captionTextSize, captionTextSize);

        dayCircleColor = a.getColor(R.styleable.DayColorView_dayCircleColor, ContextCompat.getColor(context, R.color.tempo_undecided_day_bg));

        a.recycle();
        // Init the circle
        circlePaint = new Paint();
        //set up the circle
        setCirclePaint();
        // Set up a default TextPaint object
        textPaint = new TextPaint();
        setTextPaintAndMeasurements();
    }

    private void setTextPaintAndMeasurements() {
        // setup a default TextPaint object
        textPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextAlign(Paint.Align.LEFT);
        textPaint.setTextSize(captionTextSize);
        textPaint.setColor(captionTextColor);

        textWidth = textPaint.measureText(captionText);
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        textHeight = fontMetrics.bottom;
    }

    private void setCirclePaint() {
        //init
        circlePaint = new Paint();
        // set up a paint object to draw circle
        circlePaint.setStyle(Paint.Style.FILL);
        circlePaint.setColor(dayCircleColor);
    }
    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);

        // TODO: consider storing these as member variables to reduce
        // allocations per draw cycle.
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int contentWidth = getWidth() - paddingLeft - paddingRight;
        int contentHeight = getHeight() - paddingTop - paddingBottom;

        float cx = (float) contentWidth / 2;
        float cy = (float) contentHeight / 2;
        float radius = Math.min(contentHeight,contentWidth) / 2f * 0.9f;

        canvas.drawCircle(cx,cy,radius, circlePaint);
        // Draw the text.
        canvas.drawText(captionText,
                paddingLeft + (contentWidth - textWidth) / 2,
                paddingTop + (contentHeight + textHeight) / 2,
                textPaint);
    }


    /**
     * Sets the view"s example color attribute value. In the example view, this color
     * is the font color.
     *
     * @param exampleColor The example color attribute value to use.
     */
    public void setExampleColor(int exampleColor) {
        captionTextColor = exampleColor;
        setTextPaintAndMeasurements();
    }



}