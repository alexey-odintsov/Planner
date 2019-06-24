package com.alekso.planner.ui.timeline;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alekso.planner.R;
import com.alekso.planner.model.Account;
import com.alekso.planner.model.Transaction;
import com.alekso.planner.model.decorators.TimeLineItem;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TimeLineAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yy H:mm:ss");
    private static DecimalFormat decimalFormat = new DecimalFormat("#,##0.00", new DecimalFormatSymbols());

    ItemClickHandler itemClickHandler;
    private ArrayList<TimeLineItem> items = new ArrayList<>();

    public TimeLineAdapter(ItemClickHandler itemClickHandler) {
        this.itemClickHandler = itemClickHandler;
    }

    public void setItems(ArrayList<TimeLineItem> data) {
        this.items.clear();
        if (data != null) {
            this.items.addAll(data);
        }
        notifyDataSetChanged();
    }

    public interface ItemClickHandler {
        void onItemClicked(long id);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.timeline_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder vh = (ViewHolder) holder;
        final Context context = holder.itemView.getContext();
        final TimeLineItem timeLineItem = items.get(position);
        final Transaction transaction = timeLineItem.getTransaction();
        final Account account = timeLineItem.getAccount();
        vh.id.setText("#" + transaction.getId());
        vh.accountName.setText(account.getName());
        vh.amount.setText(decimalFormat.format(transaction.getAmount()));
        vh.balance.setText(decimalFormat.format(transaction.getBalance()));
        vh.dt.setText(dateFormat.format(new Date(transaction.getDt())));

        vh.amount.setTextColor(transaction.getAmount() > 0.0 ?
                context.getResources().getColor(R.color.colorPrimaryDark) :
                context.getResources().getColor(R.color.colorAccent));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView id;
        final TextView accountName;
        final TextView dt;
        final TextView comment;
        final TextView amount;
        final TextView balance;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.timeline_id);
            accountName = itemView.findViewById(R.id.timeline_account);
            dt = itemView.findViewById(R.id.timeline_dt);
            comment = itemView.findViewById(R.id.timeline_comment);
            amount = itemView.findViewById(R.id.timeline_amount);
            balance = itemView.findViewById(R.id.timeline_balance);
        }
    }
}
