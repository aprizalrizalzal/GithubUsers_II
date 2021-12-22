package me.aprizal.githubusers.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import me.aprizal.githubusers.R;
import me.aprizal.githubusers.adapter.OnItemClickCallback;
import me.aprizal.githubusers.adapter.UsersAdapter;
import me.aprizal.githubusers.databinding.ActivityMainBinding;
import me.aprizal.githubusers.repository.model.UsersResponseItem;
import me.aprizal.githubusers.viewmodel.activity.MainViewModel;

public class MainActivity extends AppCompatActivity implements OnItemClickCallback {

    private ActivityMainBinding binding;
    private MainViewModel mainViewModel;
    private UsersAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mainViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MainViewModel.class);

        binding.searchView.setIconifiedByDefault(false);
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                setListSearchUsers(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()){
                    setListUsers();
                } else {
                    setListSearchUsers(newText);
                }
                return false;
            }
        });

        setListUsers();

        binding.rvUsers.setHasFixedSize(true);
        binding.rvUsers.setLayoutManager(new LinearLayoutManager(this));

        adapter = new UsersAdapter(this);
        binding.rvUsers.setAdapter(adapter);

        mainViewModel.getShowLoading().observe(this, this::showLoading);
        mainViewModel.getShowToast().observe(this, toast -> Toast.makeText(this, toast, Toast.LENGTH_SHORT).show());

    }

    private void setListUsers() {
        mainViewModel.setListUsers();
        mainViewModel.getUsersResponseItem().observe(this, usersResponseItems -> {
            if (usersResponseItems !=null){
                adapter.setUsers(usersResponseItems);
            }
        });
    }

    private void setListSearchUsers(String login) {
        mainViewModel.setListSearchUser(login);
        mainViewModel.getUsersResponseItem().observe(this, usersResponseItems -> {
            if (usersResponseItems !=null){
                adapter.setUsers(usersResponseItems);
            }
        });
    }

    private void showLoading(Boolean state) {
        if (state) {
            binding.progressBar.setVisibility(View.VISIBLE);
        } else {
            binding.progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onItemClicked(UsersResponseItem usersResponseItem) {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        UsersResponseItem model = new UsersResponseItem();
        model.setLogin(usersResponseItem.getLogin());
        intent.putExtra(DetailActivity.EXTRA_USERS,model);
        startActivity(intent);
    }

    @Override
    public void onLinkClicked(UsersResponseItem usersResponseItem) {
        Toast.makeText(this, getString(R.string.open_browser), Toast.LENGTH_SHORT).show();
        String url = usersResponseItem.getHtmlUrl();
        Intent link = new Intent(Intent.ACTION_VIEW);
        link.setData(Uri.parse(url));
        startActivity(link);
    }
}