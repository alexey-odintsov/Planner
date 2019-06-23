package com.alekso.planner.ui.timeline;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.alekso.planner.model.decorators.TimeLineItem;
import com.alekso.planner.ui.BasePresenter;

import java.util.ArrayList;

public abstract class BaseTimeLinePresenter extends BasePresenter<BaseTimeLineView> {

    public BaseTimeLinePresenter(@NonNull Application application) {
        super(application);
    }

    public abstract void load();

    public abstract MutableLiveData<ArrayList<TimeLineItem>> getItems();
}
