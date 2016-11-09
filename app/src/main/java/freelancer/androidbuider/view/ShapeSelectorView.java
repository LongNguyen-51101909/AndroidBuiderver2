package freelancer.androidbuider.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
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
    private String[] shapeValue = {"square", "circle", "triangle"};
    private static int currentShapeIndex = 0;

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
        String shapeSelected = shapeValue[currentShapeIndex];
        if ("square".equals(shapeSelected)) {
            canvas.drawRect(0, 0, mShapeWidth, mShapeHeight, paintShape);
            mTextXOffset = 0;
        } else if ("circle".equals(shapeSelected)) {
            canvas.drawCircle(mShapeWidth / 2, mShapeHeight / 2, mShapeWidth / 2, paintShape);
            mTextXOffset = 12;
        } else if ("triangle".equals(shapeSelected)) {
            canvas.drawPath(getTrianglePath(), paintShape);
            mTextXOffset = 0;
        }
        if (mDisplayShapeName) {
            canvas.drawText(shapeSelected, mShapeWidth + mTextXOffset, mShapeHeight + mTextXOffset, paintShape);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // Defines the extra padding for the shape name text
        int textPadding = 10;
        int contentWidth = mShapeWidth;

        // Resolve the width based on our minium and the measure spec
        int minw = contentWidth + getPaddingLeft() + getPaddingRight();
        int w = resolveSizeAndState(minw, widthMeasureSpec, 0);

        // Ask for a height that would let the view get as big as it can
        int minh = mShapeHeight + getPaddingBottom() + getPaddingTop();
        if (mDisplayShapeName) {
            minh += mTextYOffset + textPadding;
        }
        int h = resolveSizeAndState(minh, heightMeasureSpec, 0);
        // Calling this method determines the measured width and height
        // Retrieve with getMeasuredWidth or getMeasureHeight method later
        setMeasuredDimension(w, h);
    }



    //Change the currentShapeIndex whenever the shape is clicked

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean result = super.onTouchEvent(event);
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//            currentShapeIndex = (currentShapeIndex++) % shapeValue.length;
            currentShapeIndex = (++currentShapeIndex) % shapeValue.length;
            postInvalidate();
            Log.d("TAG", "currentShapeIndex: " + currentShapeIndex);
            Log.d("TAG", "currentShapeIndex: " + currentShapeIndex);
            return true;
        }
        return result;
    }

    protected Path getTrianglePath() {
        Point p1 = new Point(0, mShapeHeight), p2 = null, p3 = null;
        p2 = new Point(p1.x + mShapeWidth, p1.y);
        p3 = new Point(p1.x + (mShapeWidth / 2), p1.y - mShapeHeight);
        Path path = new Path();
        path.moveTo(p1.x, p1.y);
        path.lineTo(p2.x, p2.y);
        path.lineTo(p3.x, p3.y);
        return path;
    }
    // Returns selected shape name
    public String getSelectedShape() {
        return shapeValue[currentShapeIndex];
    }

}
