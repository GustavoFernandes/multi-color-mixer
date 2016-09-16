package com.github.gustavofernandes.multicolormixer;

import android.graphics.Color;
import android.util.Log;

import java.util.List;

public class Utils {

    public static int getLegibleForegroundColor(int backgroundColor) {

        // http://stackoverflow.com/questions/3942878/how-to-decide-font-color-in-white-or-black-depending-on-background-color

        double r = Color.red(backgroundColor) / 255;
        double g = Color.green(backgroundColor) / 255;
        double b = Color.blue(backgroundColor) / 255;

        r = (r <= 0.03928) ? r / 12.92 : Math.pow((r + 0.055 / 1.055), 2.4);
        g = (g <= 0.03928) ? g / 12.92 : Math.pow((g + 0.055 / 1.055), 2.4);
        b = (b <= 0.03928) ? b / 12.92 : Math.pow((b + 0.055 / 1.055), 2.4);

        double l = 0.2126 * r + 0.7152 * g + 0.0722 * b;

        return l > 0.179 ? Color.BLACK : Color.WHITE;
    }

    public static int getMix(List<Integer> colors) {

        int numColors = colors.size();

        if(numColors < 1) {
            return Color.WHITE;
        }

        int red = 0;
        int green = 0;
        int blue = 0;

        for (int color : colors) {

            red += Color.red(color);
            green += Color.green(color);
            blue += Color.blue(color);
        }

        red /= numColors;
        green /= numColors;
        blue /= numColors;

        int mixedColor = (red << 16) | (green << 8) | blue;

        String mixedColorHexString = Integer.toHexString(mixedColor);
        mixedColorHexString = "#" + ("000000" + mixedColorHexString).substring(mixedColorHexString.length());

        return Color.parseColor(mixedColorHexString);
    }
}
