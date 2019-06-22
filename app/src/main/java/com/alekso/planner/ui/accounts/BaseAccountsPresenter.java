package com.alekso.planner.ui.accounts;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.alekso.planner.model.Account;
import com.alekso.planner.ui.BasePresenter;

import java.util.ArrayList;

public abstract class BaseAccountsPresenter extends BasePresenter<BaseAccountsView> {

    public BaseAccountsPresenter(@NonNull Application application) {
        super(application);
    }

    public abstract void load();

    public abstract MutableLiveData<ArrayList<Account>> getAccounts();
}
