package com.bafoly.lib.stockcharts.uc;

import java.util.List;

/**
 * Created by basarb on 6/20/2016.
 */
public interface Timeline<T> {

    List<T> getTimeLine();

    void setFormat(String format);

    String format(T t);
}
