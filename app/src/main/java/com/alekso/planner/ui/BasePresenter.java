package com.alekso.planner.ui;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.alekso.planner.source.Repository;
import com.alekso.planner.source.local.LocalDataSourceImpl;
import com.alekso.planner.source.remote.RemoteDataSourceImpl;

public abstract class BasePresenter<V extends BaseView> extends AndroidViewModel {
    @NonNull
    protected V view;
    @NonNull
    protected final Repository repository;

    public BasePresenter(@NonNull Application application) {
        super(application);
        repository = Repository.getInstance(
                LocalDataSourceImpl.getInstance(application),
                RemoteDataSourceImpl.getInstance());
    }

    public abstract void init();

    public void setView(@NonNull V view) {
        this.view = view;
        Log.d("=====", "setView " + view);
    }
}
