package com.takeaway.viewmodel;

import android.os.Bundle;

import com.takeaway.R;
import com.takeaway.model.BaseModel;
import com.takeaway.views.activities.MainActivity;
import com.takeaway.views.fragment.BaseFragment;
import com.takeaway.views.fragment.MainScreenFragment;

public class MainViewModel extends BaseViewModel<BaseModel> {

    public MainViewModel(MainActivity mainActivity) {
        super(mainActivity);

    }

    @Override
    BaseModel getModel() {
        return model;
    }


    public void loadFragment(BaseFragment fragment, String tag) {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout, fragment, tag)
                .commit();
    }
}

