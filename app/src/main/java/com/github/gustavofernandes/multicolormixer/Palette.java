package com.github.gustavofernandes.multicolormixer;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class Palette extends LinearLayout {

    private Context mContext;
    private OnMixChangeListener mOnMixChangeListener;

    public Palette(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;
    }

    public void addOnMixChangeListener(OnMixChangeListener listener) {

        mOnMixChangeListener = listener;

        if(getChildCount() > 0) {
            // TODO: loop through existing children and add listener to them
        }
    }

    public void addColors(int[] colors) {
        for(int color : colors) {
            addColor(color);
        }
    }

    public void addColor(int color) {
        ColorTile colorTile = new ColorTile(mContext, color);
        colorTile.setOnMixChangeListener(mOnMixChangeListener);
        addView(colorTile);
    }
}
