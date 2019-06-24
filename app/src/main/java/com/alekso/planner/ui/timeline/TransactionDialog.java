package com.alekso.planner.ui.timeline;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.alekso.planner.R;

public class TransactionDialog extends DialogFragment {
    public static final String TAG = TransactionDialog.class.getSimpleName();

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_transaction, null))
                .setPositiveButton("Add", (dialog, which) -> {

                })
                .setNegativeButton("Cancel", (dialog, which) -> {

                })
                .setTitle("Add new transaction");
        return builder.create();
    }
}
