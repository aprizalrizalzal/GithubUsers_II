package me.aprizal.githubusers.main;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayoutMediator;
import me.aprizal.githubusers.R;
import me.aprizal.githubusers.adapter.SectionsPagerAdapter;
import me.aprizal.githubusers.databinding.ActivityDetailBinding;
import me.aprizal.githubusers.repository.model.UsersResponseItem;
import me.aprizal.githubusers.viewmodel.activity.DetailViewModel;

public class DetailActivity extends AppCompatActivity {

    private ActivityDetailBinding binding;

    @StringRes
    private final int[] TAB_TITLES = new int[]{
            R.string.followers,
            R.string.following
    };

    public static final String EXTRA_USERS = "extra_users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this);
        binding.viewPager.setAdapter(sectionsPagerAdapter);
        new TabLayoutMediator(binding.tabs, binding.viewPager,
                (tab, position) -> tab.setText(getResources().getString(TAB_TITLES[position]))
        ).attach();

        if(getSupportActionBar() != null) {
            getSupportActionBar().setElevation(0);
        }

        UsersResponseItem usersResponseItem = getIntent().getParcelableExtra(EXTRA_USERS);
        String login = usersResponseItem.getLogin();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(login);
        }

        DetailViewModel detailViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(DetailViewModel.class);

        detailViewModel.setDetailUser(login);
        detailViewModel.getDetailUsersResponseItem().observe(this, item -> {
            Glide.with(DetailActivity.this).load(item.getAvatarUrl()).centerCrop().placeholder(R.drawable.ic_baseline_account_circle_24).into(binding.imgAvatar);
            binding.tvName.setText(item.getName());
            binding.tvUsername.setText(item.getLogin());
            binding.tvCompany.setText(item.getCompany());
            binding.tvLocation.setText(item.getLocation());
            binding.tvRepository.setText(String.valueOf(item.getPublicRepos()));
        });

        detailViewModel.getShowLoading().observe(this, this::showLoading);
        detailViewModel.getShowToast().observe(this, toast -> Toast.makeText(this, toast, Toast.LENGTH_SHORT).show());
    }

    private void showLoading(Boolean state) {
        if (state) {
            binding.progressBar.setVisibility(View.VISIBLE);
        } else {
            binding.progressBar.setVisibility(View.GONE);
        }
    }
}

