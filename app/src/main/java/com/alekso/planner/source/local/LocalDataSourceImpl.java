package com.alekso.planner.source.local;

import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;

import com.alekso.planner.model.Account;
import com.alekso.planner.model.Transaction;
import com.alekso.planner.model.decorators.TimeLineItem;
import com.alekso.planner.source.local.readers.AccountsReader;
import com.alekso.planner.source.local.readers.TimeLineItemsReader;

import java.util.ArrayList;

import static com.alekso.planner.source.local.DbContract.*;

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
                AccountEntry.TABLE,
                new String[]{
                        AccountEntry._ID,
                        AccountEntry.C_NAME,
                        AccountEntry.C_CURRENCY_ID,
                        AccountEntry.C_TYPE
                },
                null,
                null,
                null,
                null,
                null
        );

        return AccountsReader.fromCursor(c);
    }

    @Override
    public ArrayList<TimeLineItem> getTimeLine(long dt) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        String[] projection = new String[]{
                TransactionEntry.TABLE + "." + TransactionEntry._ID,
                TransactionEntry.C_ACCOUNT_ID,
                TransactionEntry.C_DATETIME,
                TransactionEntry.C_AMOUNT,
                TransactionEntry.C_BALANCE,
                TransactionEntry.C_COMMENT,
                TransactionEntry.C_IS_VITAL,
                TransactionEntry.TABLE + "." + TransactionEntry.C_TYPE,
                TransactionEntry.C_STATUS,
                AccountEntry.TABLE + "." + AccountEntry._ID,
                AccountEntry.TABLE + "." + AccountEntry.C_NAME,
                AccountEntry.TABLE + "." + AccountEntry.C_CURRENCY_ID,
                AccountEntry.TABLE + "." + AccountEntry.C_TYPE,
        };

        queryBuilder.setTables(
                TransactionEntry.TABLE
                        + " LEFT JOIN " +
                        AccountEntry.TABLE + " ON (" +
                        AccountEntry.TABLE + "." + AccountEntry._ID + " = " + TransactionEntry.C_ACCOUNT_ID + ")"
        );
        Cursor c = queryBuilder.query(dbHelper.getReadableDatabase(),
                projection,
                null,
                null,
                null,
                null,
                null
        );
        return TimeLineItemsReader.fromCursor(c);
    }

    @Override
    public long addTransaction(Transaction transaction) {
        ContentValues cv = new ContentValues();
        cv.put(TransactionEntry.C_ACCOUNT_ID, transaction.getAccountId());
        cv.put(TransactionEntry.C_TYPE, transaction.getType().getId());
        cv.put(TransactionEntry.C_STATUS, transaction.getStatus().getId());
        cv.put(TransactionEntry.C_IS_VITAL, transaction.isVital());
        cv.put(TransactionEntry.C_DATETIME, transaction.getDt());
        cv.put(TransactionEntry.C_COMMENT, transaction.getComment());
        cv.put(TransactionEntry.C_AMOUNT, transaction.getAmount());
        cv.put(TransactionEntry.C_BALANCE, transaction.getBalance());

        return dbHelper.getReadableDatabase().insert(TransactionEntry.TABLE, null, cv);
    }
}
