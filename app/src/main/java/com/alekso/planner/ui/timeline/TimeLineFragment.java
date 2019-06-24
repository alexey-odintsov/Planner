package com.alekso.planner.ui.timeline;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alekso.planner.R;
import com.alekso.planner.model.decorators.TimeLineItem;

import java.util.ArrayList;

public class TimeLineFragment extends Fragment implements BaseTimeLineView {

    public static final String TAG = TimeLineFragment.class.getSimpleName();
    private static final boolean DEBUG = true;

    @NonNull
    private BaseTimeLinePresenter presenter;

    private TimeLineAdapter adapter;

    public static TimeLineFragment newInstance() {
        return new TimeLineFragment();
    }

    public TimeLineFragment() {
        if (DEBUG) Log.d(TAG, "constructor()");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (DEBUG) Log.d(TAG, "onCreate(savedInstanceState: " + savedInstanceState + ")");

        super.onCreate(savedInstanceState);
        adapter = new TimeLineAdapter(new TimeLineAdapter.ItemClickHandler() {
            @Override
            public void onItemClicked(long id) {
                if (DEBUG) Log.d(TAG, "account #" + id + " has clicked");
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_timeline, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if (DEBUG)
            Log.d(TAG, "onViewCreated(view: " + view + "; savedInstanceState: " + savedInstanceState + ")");

        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.timeline_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(adapter);

        presenter = ViewModelProviders.of(this).get(TimeLinePresenter.class);
        presenter.setView(this);
        presenter.init();
        presenter.load();

        view.findViewById(R.id.fab).setOnClickListener((v) -> {
            TransactionDialog dialog = new TransactionDialog();
            dialog.show(getFragmentManager(), TransactionDialog.TAG);
        });
    }

    @Override
    public void setData(ArrayList<TimeLineItem> data) {
        adapter.setItems(data);
    }
}
