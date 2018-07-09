package com.programming_calendar.workthrough;

import android.provider.BaseColumns;

public final class ProgHoursContract {

    private ProgHoursContract() {
    }

    public static class ProgHoursTable implements BaseColumns {
        public static final String TABLE_NAME = "programming_hours";
        public static final String COLUMN_DATE = "day_of_year";
        public static final String COLUMN_COLOR = "color";
        public static final String COLUMN_JOB = "job";
        public static final String COLUMN_HOURS = "hours";
    }
}