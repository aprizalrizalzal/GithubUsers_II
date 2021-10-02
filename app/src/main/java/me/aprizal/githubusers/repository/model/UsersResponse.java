package me.aprizal.githubusers.repository.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class UsersResponse{

	@SerializedName("items")
	private final List<UsersResponseItem> items;

	public UsersResponse(List<UsersResponseItem> items) {
		this.items = items;
	}

	public List<UsersResponseItem> getItems() {
		return items;
	}
}