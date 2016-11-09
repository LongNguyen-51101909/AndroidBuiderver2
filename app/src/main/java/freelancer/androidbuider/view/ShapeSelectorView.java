package freelancer.androidbuider.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import freelancer.androidbuider.R;

/**
 * Created by nqlong on 09-Nov-16.
 */

public class ShapeSelectorView extends View {
    private int mShapeColor;
    private boolean mDisplayShapeName;

    // Draw the Canvas
    private int mShapeWidth = 100;
    private int mShapeHeight = 100;
    private int mTextXOffset = 0;
    private int mTextYOffset = 30;
    private Paint paintShape;

    // we must provide a contructor that takes a context and an AttributeSet.
    //This constructor allows the UI to create and edit an instance of your view.
    public ShapeSelectorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupAttributes(attrs);
        setupPaint();
    }

    private void setupAttributes(AttributeSet attrs) {
        // Obtain a typed array of attributes
        TypedArray a = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.ShapeSelectorView, 0, 0);
        // Extract custom attributes into member variables
        try {
            mShapeColor = a.getColor(R.styleable.ShapeSelectorView_shapeColor, Color.BLACK);
            mDisplayShapeName = a.getBoolean(R.styleable.ShapeSelectorView_displayShapeName, false);
        } finally {
            // TypeArray objects are shared and must be recycled...
            a.recycle();
        }
    }

    public boolean setDisplayingShapeName() {
        return mDisplayShapeName;
    }

    public void setDisplayShapeName(boolean state) {
        this.mDisplayShapeName = state;
        // Update the appearance
        invalidate();
        requestLayout();
    }

    public int getmShapeColor() {
        return mShapeColor;
    }

    public void setmShapeColor (int color) {
        this.mShapeColor = color;
        // Update the appearance
        invalidate();
        requestLayout();
    }

    private void setupPaint(){
        paintShape = new Paint();
        paintShape.setStyle(Paint.Style.FILL);
        paintShape.setColor(mShapeColor);
        paintShape.setTextSize(30);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0, 0, mShapeWidth, mShapeHeight, paintShape);
        if (mDisplayShapeName) {
            canvas.drawText("Square", mShapeWidth + mTextXOffset, mShapeHeight + mTextXOffset, paintShape);
        }
    }
}
