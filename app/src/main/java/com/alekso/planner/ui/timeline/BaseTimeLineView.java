package com.alekso.planner.ui.timeline;

import com.alekso.planner.model.decorators.TimeLineItem;
import com.alekso.planner.ui.BaseView;

import java.util.ArrayList;

public interface BaseTimeLineView extends BaseView {
    void setData(ArrayList<TimeLineItem> data);
}
