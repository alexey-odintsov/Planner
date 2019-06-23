package com.alekso.planner.source;

import com.alekso.planner.model.Account;
import com.alekso.planner.model.decorators.TimeLineItem;
import com.alekso.planner.source.local.LocalDataSource;
import com.alekso.planner.source.remote.RemoteDataSource;

import java.util.ArrayList;

public class Repository {
    private static Repository instance;
    private final LocalDataSource localDataSource;
    private final RemoteDataSource remoteDataSource;

    private Repository(LocalDataSource localDataSource, RemoteDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    public static Repository getInstance(LocalDataSource localDataSource, RemoteDataSource remoteDataSource) {
        if (instance == null) {
            instance = new Repository(localDataSource, remoteDataSource);
        }

        return instance;
    }

    public ArrayList<Account> getAccounts() {
        return localDataSource.getAccounts();
    }

    public ArrayList<TimeLineItem> getTimeLine(long dt) {
        return localDataSource.getTimeLine(dt);
    }
}
