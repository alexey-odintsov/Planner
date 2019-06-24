package com.alekso.planner.ui.timeline;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.alekso.planner.model.Transaction;
import com.alekso.planner.model.decorators.TimeLineItem;

import java.util.ArrayList;

public class TimeLinePresenter extends BaseTimeLinePresenter {

    @NonNull
    private MutableLiveData<ArrayList<TimeLineItem>> data = new MutableLiveData<>();


    public TimeLinePresenter(@NonNull Application application) {
        super(application);
    }

    @Override
    public void load() {
        new Thread(new Runnable() {// TODO: 2019-06-22 Use executor
            @Override
            public void run() {
                data.postValue(repository.getTimeLine(0L));
            }
        }).start();
    }

    @Override
    public MutableLiveData<ArrayList<TimeLineItem>> getItems() {
        return data;
    }

    @Override
    public void onTransactionAdded(Transaction transaction) {
        data.postValue(repository.getTimeLine(transaction.getDt()));
    }

    @Override
    public void init() {
        data.observe(view, new Observer<ArrayList<TimeLineItem>>() {
            @Override
            public void onChanged(ArrayList<TimeLineItem> items) {
                view.setData(items);
            }
        });
    }
}
