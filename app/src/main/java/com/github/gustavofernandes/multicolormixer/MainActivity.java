package com.github.gustavofernandes.multicolormixer;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements OnMixChangeListener {

    private static final String TAG = MainActivity.class.getName();

    private List<Integer> mColors;

    private Palette mPalette;
    private View mMixView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPalette = (Palette) findViewById(R.id.palette);
        mPalette.addOnMixChangeListener(this);
        mPalette.addColors(getResources().getIntArray(R.array.initial_colors));

        mMixView = findViewById(R.id.view_mix);

        mColors = new ArrayList<>();
    }

    @Override
    public void colorAdded(int colorAdded) {
        mColors.add(colorAdded);
        mMixView.setBackgroundColor(Utils.getMix(mColors));
    }

    @Override
    public void colorRemoved(int colorRemoved) {
        mColors.remove((Integer) colorRemoved);
        mMixView.setBackgroundColor(Utils.getMix(mColors));
    }
}
