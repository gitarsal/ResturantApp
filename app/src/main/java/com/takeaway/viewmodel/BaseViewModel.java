
package com.takeaway.viewmodel;

import androidx.databinding.BaseObservable;

import com.google.gson.Gson;
import com.takeaway.model.BaseModel;
import com.takeaway.views.activities.BaseActivity;
import com.takeaway.views.fragment.BaseFragment;


public abstract class BaseViewModel<T extends BaseModel> extends BaseObservable {
    private BaseActivity activity;
    private BaseFragment fragment;
    protected T model;
    private Gson gson;

    public BaseViewModel() {

    }

    public BaseViewModel(BaseActivity activity) {

        model = getModel();
        this.activity = activity;
    }

    public BaseViewModel(BaseFragment fragment) {
        model = getModel();
        this.fragment = fragment;
        this.activity = fragment.getBaseActivity();
    }


    public void setModel(T model) {
        this.model = model;
    }

    abstract T getModel();


    public BaseActivity getActivity() {
        return activity;
    }


    public BaseFragment getFragment() {
        return fragment;
    }

    public Gson getGson() {
        if (gson == null)
            gson = new Gson();
        return gson;
    }
}

