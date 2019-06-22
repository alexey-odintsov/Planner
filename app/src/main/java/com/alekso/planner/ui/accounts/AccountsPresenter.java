package com.alekso.planner.ui.accounts;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.alekso.planner.model.Account;

import java.util.ArrayList;

public final class AccountsPresenter extends BaseAccountsPresenter {

    @NonNull
    private MutableLiveData<ArrayList<Account>> data = new MutableLiveData<>();

    public AccountsPresenter(@NonNull Application application) {
        super(application);
    }

    @Override
    public void load() {
        new Thread(new Runnable() {// TODO: 2019-06-22 Use executor
            @Override
            public void run() {
                data.postValue(repository.getAccounts());
            }
        }).start();
    }

    @Override
    public void init() {
        Log.d("=====", "init " + this);
        data.observe(view, new Observer<ArrayList<Account>>() {
            @Override
            public void onChanged(ArrayList<Account> accounts) {
                view.setData(accounts);
            }
        });
    }

    @NonNull
    public MutableLiveData<ArrayList<Account>> getAccounts() {
        return data;
    }

}