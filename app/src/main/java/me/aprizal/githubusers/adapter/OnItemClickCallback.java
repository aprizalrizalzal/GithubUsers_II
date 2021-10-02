package me.aprizal.githubusers.adapter;

import me.aprizal.githubusers.repository.model.UsersResponseItem;

public interface OnItemClickCallback {
    void onItemClicked(UsersResponseItem usersResponseItem);
    void onLinkClicked(UsersResponseItem usersResponseItem);
}
