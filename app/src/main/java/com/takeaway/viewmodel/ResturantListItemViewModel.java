package com.takeaway.viewmodel;

import androidx.databinding.Bindable;

import com.takeaway.R;
import com.takeaway.database.databasetable.FavouriteRestaurantsTable;
import com.takeaway.model.ResturantListModel;
import com.takeaway.views.fragment.MainScreenFragment;

public class ResturantListItemViewModel extends BaseViewModel<ResturantListModel> {

    private MainScreenFragment mainScreenFragment;


    @Override
    public ResturantListModel getModel() {
        return model;
    }

    public ResturantListItemViewModel(MainScreenFragment fragment) {

        super(fragment);
        mainScreenFragment = fragment;

    }

    @Override
    public MainScreenFragment getFragment() {
        return (MainScreenFragment) super.getFragment();
    }

    @Bindable
    public String getResturantName() {
        return getModel().getName();
    }

    @Bindable
    public String getDistance() {
        return getFragment().getString(R.string.distance, String.valueOf(getModel().getSortingValues().getDistance()));
    }

    @Bindable
    public String getRating() {
        return getFragment().getString(R.string.rating, String.valueOf(getModel().getSortingValues().getRatingAverage()));
    }

    @Bindable
    public String getMinimumCost() {
        return getFragment().getString(R.string.minCost, String.valueOf(getModel().getSortingValues().getMinCost()));
    }


    @Bindable
    public String getAverageProductPrice() {
        return getFragment().getString(R.string.averageProductPrice, String.valueOf(getModel().getSortingValues().getAverageProductPrice()));
    }

    @Bindable
    public String getBestMatch() {
        return getFragment().getString(R.string.bestmatch, String.valueOf(getModel().getSortingValues().getBestMatch()));
    }

    @Bindable
    public String getDeliveryCosts() {
        return getFragment().getString(R.string.deliveryCosts, String.valueOf(getModel().getSortingValues().getDeliveryCosts()));
    }

    @Bindable
    public String getNewest() {
        return getFragment().getString(R.string.newest, String.valueOf(getModel().getSortingValues().getNewest()));
    }

    @Bindable
    public String getStatus() {
        return getFragment().getString(R.string.status, String.valueOf(getModel().getStatus()));
    }


    public void onFavouriteItemClicked() {
        mainScreenFragment.getViewModel().getRealm().beginTransaction();

        if (getModel().isFavourite()) {
            FavouriteRestaurantsTable favouriteRestaurantsTable = mainScreenFragment.getViewModel().getRealm().where(FavouriteRestaurantsTable.class).equalTo("name", getModel().getName()).findFirst();
            favouriteRestaurantsTable.removeFromRealm();
        } else {
            FavouriteRestaurantsTable favouriteRestaurantsTable = mainScreenFragment.getViewModel().getRealm().createObject(FavouriteRestaurantsTable.class);
            favouriteRestaurantsTable.setName(getModel().getName());
        }
        mainScreenFragment.getViewModel().getRealm().commitTransaction();

        notifyPropertyChanged(com.takeaway.BR.buttonText);
        mainScreenFragment.getViewModel().sortResturantListData();
    }


    @Bindable
    public String getButtonText() {

        getModel().setFavourite(mainScreenFragment.getViewModel().getRealm().where(FavouriteRestaurantsTable.class).equalTo("name", getModel().getName()).findFirst() != null);
        return getModel().isFavourite() ? getFragment().getString(R.string.RemoveFromfav) : getFragment().getString(R.string.addToFav);


    }

}

