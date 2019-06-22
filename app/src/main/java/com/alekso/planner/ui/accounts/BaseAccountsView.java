package com.alekso.planner.ui.accounts;

import com.alekso.planner.model.Account;
import com.alekso.planner.ui.BaseView;

import java.util.ArrayList;

public interface BaseAccountsView extends BaseView {
    void setData(ArrayList<Account> data);
}
