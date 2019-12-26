package com.takeaway.views.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.takeaway.R;
import com.takeaway.databinding.ActivityMainBinding;
import com.takeaway.viewmodel.MainViewModel;
import com.takeaway.views.fragment.MainScreenFragment;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {
    private MainViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        viewModel = new MainViewModel(this);
        super.onCreate(savedInstanceState);
        getViewModel().loadFragment(MainScreenFragment.getInstance(new Bundle()), MainScreenFragment.class.getName());
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public MainViewModel getViewModel() {
        return viewModel;
    }
}
