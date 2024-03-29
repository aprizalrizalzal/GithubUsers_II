package me.aprizal.githubusers.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import me.aprizal.githubusers.R;
import me.aprizal.githubusers.databinding.ListRowUsersBinding;
import me.aprizal.githubusers.repository.model.UsersResponseItem;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder>{

    private final List<UsersResponseItem> usersResponseItems = new ArrayList<>();
    private final OnItemClickCallback onItemClickCallback;

    public UsersAdapter(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setUsers(List<UsersResponseItem> items) {
        usersResponseItems.clear();
        usersResponseItems.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UsersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListRowUsersBinding binding = ListRowUsersBinding.inflate(LayoutInflater.from(parent.getContext()),parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersAdapter.ViewHolder holder, int position) {
        holder.bind(usersResponseItems.get(position));
        holder.itemView.setOnClickListener(v -> onItemClickCallback.onItemClicked(usersResponseItems.get(holder.getAdapterPosition())));
        holder.binding.imgBtnLink.setOnClickListener(v -> onItemClickCallback.onLinkClicked(usersResponseItems.get(holder.getAdapterPosition())));
    }

    @Override
    public int getItemCount() {
        return usersResponseItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ListRowUsersBinding binding;
        public ViewHolder(ListRowUsersBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }

        public void bind(UsersResponseItem usersResponseItems) {
            Glide.with(itemView).load(usersResponseItems.getAvatarUrl()).placeholder(R.drawable.ic_baseline_account_circle_24).into(binding.imgUsers);
            binding.tvUsername.setText(usersResponseItems.getLogin());
        }
    }
}
