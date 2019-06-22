package com.alekso.planner.ui.accounts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alekso.planner.R;
import com.alekso.planner.model.Account;

import java.util.ArrayList;

public class AccountsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ItemClickHandler itemClickHandler;
    private ArrayList<Account> items = new ArrayList<>();

    public AccountsAdapter(ItemClickHandler itemClickHandler) {
        this.itemClickHandler = itemClickHandler;
    }

    public void setItems(ArrayList<Account> data) {
        this.items.clear();
        this.items.addAll(data);
        notifyDataSetChanged();
    }

    public interface ItemClickHandler {
        void onItemClicked(long id);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.accounts_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder vh = (ViewHolder) holder;
        vh.accountName.setText(items.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView accountName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            accountName = itemView.findViewById(R.id.accounts_item_name);
        }
    }
}
