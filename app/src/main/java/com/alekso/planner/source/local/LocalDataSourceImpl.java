package com.alekso.planner.source.local;

import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;

import com.alekso.planner.model.Account;
import com.alekso.planner.source.local.readers.AccountsReader;

import java.util.ArrayList;

public class LocalDataSourceImpl implements LocalDataSource {

    private static LocalDataSourceImpl instance;
    private DbHelper dbHelper;

    private LocalDataSourceImpl(Context context) {
        dbHelper = new DbHelper(context);
    }

    public static LocalDataSourceImpl getInstance(Context context) {
        if (instance == null) {
            instance = new LocalDataSourceImpl(context);
        }
        return instance;
    }

    @Override
    public long addAccount(Account account) {
        return 0;
    }

    @Override
    public boolean deleteAccount(long accountId) {
        return false;
    }

    @Override
    public ArrayList<Account> getAccounts() {
        final Cursor c = dbHelper.getReadableDatabase().query(
                DbContract.AccountEntry.TABLE,
                new String[]{
                        DbContract.AccountEntry._ID,
                        DbContract.AccountEntry.C_NAME,
                        DbContract.AccountEntry.C_CURRENCY_ID,
                        DbContract.AccountEntry.C_TYPE
                },
                null,
                null,
                null,
                null,
                null
        );

        return AccountsReader.fromCursor(c);
    }
}
