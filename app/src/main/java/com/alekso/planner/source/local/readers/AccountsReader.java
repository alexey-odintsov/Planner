package com.alekso.planner.source.local.readers;

import android.database.Cursor;
import android.util.Log;

import com.alekso.planner.model.Account;
import com.alekso.planner.source.local.DbContract.AccountEntry;

import java.util.ArrayList;

public class AccountsReader {
    private static final boolean DEBUG = true;
    private static final String TAG = AccountsReader.class.getSimpleName();

    public static ArrayList<Account> fromCursor(Cursor c) {
        if (DEBUG) Log.d(TAG, "fromCursor(cursor: " + c + ")");

        ArrayList<Account> accounts = new ArrayList<>();

        while (c.moveToNext()) {
            Account account = new Account(
                    c.getLong(c.getColumnIndex(AccountEntry._ID)),
                    c.getLong(c.getColumnIndex(AccountEntry.C_CURRENCY_ID)),
                    c.getString(c.getColumnIndex(AccountEntry.C_NAME)),
                    c.getInt(c.getColumnIndex(AccountEntry.C_TYPE))
            );
            accounts.add(account);
            Log.d(TAG, "account parsed: " + account);
        }
        c.close();

        return accounts;
    }
}
