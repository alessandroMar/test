package com.test.thejournal.utils;

import android.text.format.DateUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

  private final static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

  public static String formatDateToPrint(String dateString) {

    Date date;
    try {
      date = formatter.parse(dateString);
    } catch (ParseException e) {
      e.printStackTrace();
      return dateString;
    }
    return DateUtils.getRelativeTimeSpanString(date.getTime()).toString();
  }
}
