package com.alekso.planner.ui.accounts;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alekso.planner.R;
import com.alekso.planner.model.Account;

import java.util.ArrayList;

public class AccountsFragment extends Fragment implements BaseAccountsView {

    public static final String TAG = AccountsFragment.class.getSimpleName();
    private static final boolean DEBUG = true;

    @NonNull
    private BaseAccountsPresenter presenter;

    private AccountsAdapter adapter;

    public static AccountsFragment newInstance() {
        return new AccountsFragment();
    }

    public AccountsFragment() {
        if (DEBUG) Log.d(TAG, "constructor()");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (DEBUG) Log.d(TAG, "onCreate(savedInstanceState: " + savedInstanceState + ")");

        super.onCreate(savedInstanceState);
        adapter = new AccountsAdapter(new AccountsAdapter.ItemClickHandler() {
            @Override
            public void onItemClicked(long id) {
                if (DEBUG) Log.d(TAG, "account #" + id + " has clicked");
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_accounts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if (DEBUG)
            Log.d(TAG, "onViewCreated(view: " + view + "; savedInstanceState: " + savedInstanceState + ")");

        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.accounts_recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(adapter);

        presenter = ViewModelProviders.of(this).get(AccountsPresenter.class);
        presenter.setView(this);
        presenter.init();
        presenter.load();
    }

    @Override
    public void setData(ArrayList<Account> data) {
        adapter.setItems(data);
    }
}
