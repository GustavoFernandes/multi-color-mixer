package com.github.gustavofernandes.multicolormixer;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ColorTile extends RelativeLayout {

    private List<OnMixChangeListener> mOnMixChangeListeners;

    private int mCount;
    private int mColor;
    private TextView countTextView;

    public ColorTile(Context context, int color) {
        super(context);

        inflate(context, R.layout.tile_color, this);

        mOnMixChangeListeners = new ArrayList<>();

        mCount = 0;
        mColor = color;
        setBackgroundColor(mColor);

        int textColor = Utils.getLegibleForegroundColor(mColor);

        countTextView = (TextView) findViewById(R.id.textview_count);
        countTextView.setTextColor(textColor);

        Button plusButton = (Button) findViewById(R.id.button_plus);
        plusButton.setTextColor(textColor);
        plusButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                plusButtonOnClick();
            }
        });

        Button minusButton = (Button) findViewById(R.id.button_minus);
        minusButton.setTextColor(textColor);
        minusButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                minusButtonOnClick();
            }
        });
    }

    public void setOnMixChangeListener(OnMixChangeListener listener) {
        mOnMixChangeListeners.add(listener);
    }

    private void plusButtonOnClick() {
        mCount += 1;

        countTextView.setText(Integer.toString(mCount));

        for(OnMixChangeListener listener : mOnMixChangeListeners) {
            listener.colorAdded(mColor);
        }
    }

    private void minusButtonOnClick() {
        if(mCount < 1) {
            return;
        }

        mCount -= 1;

        countTextView.setText(Integer.toString(mCount));

        for(OnMixChangeListener listener : mOnMixChangeListeners) {
            listener.colorRemoved(mColor);
        }
    }
}

