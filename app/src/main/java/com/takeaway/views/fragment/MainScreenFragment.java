package com.takeaway.views.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.takeaway.R;
import com.takeaway.databinding.FragmentMainScreenBinding;
import com.takeaway.model.ResturantMainModel;
import com.takeaway.utils.Utils;
import com.takeaway.viewmodel.MainScreenViewModel;
import com.takeaway.views.activities.MainActivity;

public class MainScreenFragment extends BaseFragment<FragmentMainScreenBinding, MainScreenViewModel> {
    private MainScreenViewModel mainScreenViewModel;

    public static MainScreenFragment getInstance(Bundle bundle) {
        MainScreenFragment fragment = new MainScreenFragment();
        if (bundle != null)
            fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public MainActivity getBaseActivity() {
        return (MainActivity) super.getBaseActivity();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        mainScreenViewModel = new MainScreenViewModel(this);
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ResturantMainModel resturantMainModel = getViewModel().getGson().fromJson(Utils.loadJSONFromAsset(getBaseActivity()), ResturantMainModel.class);
        getViewModel().setInAdapter(resturantMainModel.getRestaurants());
        getViewDataBinding().searchResturantEdittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                getViewModel().getRecycleAdapter().getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                getViewModel().getRecycleAdapter().getFilter().filter(editable);
            }
        });

        getViewDataBinding().sortSpinner.setOnItemSelectedListener(getViewModel().onItemSelectedListenerForSorting);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_main_screen;
    }

    @Override
    public MainScreenViewModel getViewModel() {
        return mainScreenViewModel;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getViewModel().getRealm().close();
    }
}
