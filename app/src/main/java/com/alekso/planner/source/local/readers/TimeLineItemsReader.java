package com.alekso.planner.source.local.readers;

import android.database.Cursor;
import android.util.Log;

import com.alekso.planner.model.Account;
import com.alekso.planner.model.Transaction;
import com.alekso.planner.model.TransactionStatus;
import com.alekso.planner.model.TransactionType;
import com.alekso.planner.model.decorators.TimeLineItem;

import java.util.ArrayList;

import static com.alekso.planner.source.local.DbContract.AccountEntry;
import static com.alekso.planner.source.local.DbContract.TransactionEntry;

public class TimeLineItemsReader {
    private static final boolean DEBUG = true;
    private static final String TAG = TimeLineItemsReader.class.getSimpleName();

    public static ArrayList<TimeLineItem> fromCursor(Cursor c) {
        if (DEBUG) Log.d(TAG, "fromCursor(cursor: " + c + ")");

        ArrayList<TimeLineItem> items = new ArrayList<>();

        while (c.moveToNext()) {
            final Transaction transaction = new Transaction.Builder()
                    .setId(c.getLong(c.getColumnIndex(TransactionEntry._ID)))
                    .setAccountId(c.getLong(c.getColumnIndex(TransactionEntry.C_ACCOUNT_ID)))
                    .setAmount(c.getDouble(c.getColumnIndex(TransactionEntry.C_AMOUNT)))
                    .setBalance(c.getDouble(c.getColumnIndex(TransactionEntry.C_BALANCE)))
                    .setComment(c.getString(c.getColumnIndex(TransactionEntry.C_COMMENT)))
                    .setDt(c.getLong(c.getColumnIndex(TransactionEntry.C_DATETIME)))
                    .setVital(c.getInt(c.getColumnIndex(TransactionEntry.C_IS_VITAL)) > 0)
                    .setStatus(TransactionStatus.getById(c.getInt(c.getColumnIndex(TransactionEntry.C_STATUS))))
                    .setType(TransactionType.getById(c.getInt(c.getColumnIndex(TransactionEntry.C_TYPE))))
                    .build();

            final Account account = new Account(
                    c.getLong(c.getColumnIndex(AccountEntry._ID)),
                    c.getLong(c.getColumnIndex(AccountEntry.C_CURRENCY_ID)),
                    c.getString(c.getColumnIndex(AccountEntry.C_NAME)),
                    c.getInt(c.getColumnIndex(AccountEntry.C_TYPE))
            );
            items.add(new TimeLineItem(transaction, account));
        }
        c.close();

        return items;
    }
}
