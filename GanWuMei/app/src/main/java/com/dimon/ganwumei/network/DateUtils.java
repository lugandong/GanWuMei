package com.dimon.ganwumei.network;

import android.support.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * Created by Dimon on 2016/3/11.
 */
public class DateUtils {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);

    private static final Pattern DATE_PATTERN = Pattern.compile("\\d{4}\\-\\d{2}\\-\\d{2}");

    public static String[] format(Date date) {
        return format(DATE_FORMAT.format(date));
    }

    @Nullable
    public static String[] format(String date) {
        if (!DATE_PATTERN.matcher(date).matches()) {
            return null;
        }

        return date.split("-");
    }
}
