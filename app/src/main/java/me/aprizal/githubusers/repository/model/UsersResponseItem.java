package me.aprizal.githubusers.repository.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class UsersResponseItem implements Parcelable {

	@SerializedName("avatar_url")
	private String avatarUrl;

	@SerializedName("html_url")
	private String htmlUrl;

	@SerializedName("login")
	private String login;

	@SerializedName("name")
	private String name;

	@SerializedName("company")
	private String company;

	@SerializedName("location")
	private String location;

	@SerializedName("public_repos")
	private int publicRepos;

	public UsersResponseItem() {
	}

	protected UsersResponseItem(Parcel in) {
		avatarUrl = in.readString();
		htmlUrl = in.readString();
		login = in.readString();
		name = in.readString();
		company = in.readString();
		location = in.readString();
		publicRepos = in.readInt();
	}

	public static final Creator<UsersResponseItem> CREATOR = new Creator<UsersResponseItem>() {
		@Override
		public UsersResponseItem createFromParcel(Parcel in) {
			return new UsersResponseItem(in);
		}

		@Override
		public UsersResponseItem[] newArray(int size) {
			return new UsersResponseItem[size];
		}
	};

	public String getAvatarUrl(){
		return avatarUrl;
	}

	public String getHtmlUrl(){
		return htmlUrl;
	}

	public String getLogin(){
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return name;
	}

	public String getCompany() {
		return company;
	}

	public String getLocation() {
		return location;
	}

	public int getPublicRepos() {
		return publicRepos;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(avatarUrl);
		dest.writeString(htmlUrl);
		dest.writeString(login);
		dest.writeString(name);
		dest.writeString(company);
		dest.writeString(location);
		dest.writeInt(publicRepos);
	}
}