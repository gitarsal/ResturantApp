
package com.takeaway.views.activities;

import android.os.Bundle;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.takeaway.BR;
import com.takeaway.viewmodel.BaseViewModel;




public abstract class BaseActivity<T extends ViewDataBinding, V extends BaseViewModel>extends AppCompatActivity {
    private T mViewDataBinding;
    private V mViewModel;


    private int getBindingVariable() {
        return BR.viewModel;
    }

    public abstract
    @LayoutRes
    int getLayoutId();

    public abstract V getViewModel();



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        performDataBinding();

    }

    public T getViewDataBinding() {
        return mViewDataBinding;
    }






    private void performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        this.mViewModel = mViewModel == null ? getViewModel() : mViewModel;
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel);
        mViewDataBinding.executePendingBindings();
    }







    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();

    }



}

