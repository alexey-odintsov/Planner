package com.alekso.planner.source.remote;

public class RemoteDataSourceImpl implements RemoteDataSource {
    private static RemoteDataSourceImpl instance;

    public static RemoteDataSourceImpl getInstance() {
        if (instance == null) {
            instance = new RemoteDataSourceImpl();
        }
        return instance;
    }

    private RemoteDataSourceImpl() {

    }
}
