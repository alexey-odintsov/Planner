package com.alekso.planner.ui.timeline;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.alekso.planner.R;
import com.alekso.planner.model.Transaction;
import com.alekso.planner.model.TransactionStatus;
import com.alekso.planner.model.TransactionType;
import com.alekso.planner.source.Repository;
import com.alekso.planner.source.local.LocalDataSourceImpl;
import com.alekso.planner.source.remote.RemoteDataSourceImpl;

import java.util.Date;

public class TransactionDialog extends DialogFragment {
    public static final String TAG = TransactionDialog.class.getSimpleName();

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_transaction, null))
                .setPositiveButton("Add", (dialog, which) -> {
                    Transaction transaction = new Transaction.Builder()
                            .setAccountId(1)
                            .setAmount(Math.random() * 10000.0 - 5000.0)
                            .setBalance(0)
                            .setDt(new Date().getTime())
                            .setComment("test")
                            .setType(TransactionType.EXPENSE_INCOME)
                            .setStatus(TransactionStatus.PLANNED)
                            .setVital(false)
                            .build();

                    // TODO: 2019-06-25 move to bg thread
                    long transactionId = Repository.getInstance(LocalDataSourceImpl.getInstance(getContext()),
                            RemoteDataSourceImpl.getInstance()).addTransaction(transaction);

                    final Intent intent = new Intent();
                    intent.putExtra(TimeLineFragment.PARAM_TRANSACTION, transaction);
                    getTargetFragment().onActivityResult(getTargetRequestCode(), 1, intent);
                })
                .setNegativeButton("Cancel", (dialog, which) -> {
                    // do nothing
                })
                .setTitle("Add new transaction");
        return builder.create();
    }


}
