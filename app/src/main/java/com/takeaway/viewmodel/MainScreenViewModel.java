package com.takeaway.viewmodel;


import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.takeaway.R;
import com.takeaway.database.databasetable.FavouriteRestaurantsTable;
import com.takeaway.model.MainScreenModel;
import com.takeaway.model.ResturantListModel;
import com.takeaway.views.activities.MainActivity;
import com.takeaway.views.adapters.ResturantListAdapter;
import com.takeaway.views.fragment.MainScreenFragment;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainScreenViewModel extends BaseViewModel<MainScreenModel> {

    private ResturantListAdapter recycleAdapter;
    private Realm realm;

    public MainScreenViewModel(MainScreenFragment mainScreenFragment) {
        super(mainScreenFragment);
        setModel(new MainScreenModel());
        ResturantListItemViewModel menuItemViewModel = new ResturantListItemViewModel(mainScreenFragment);
        recycleAdapter = new ResturantListAdapter(R.layout.fragment_resturant_list_item, menuItemViewModel, mainScreenFragment);
        initRealm();
    }

    public Realm getRealm() {
        return realm;
    }

    @Override
    public MainActivity getActivity() {
        return (MainActivity) super.getActivity();
    }

    private void initRealm() {
        try {

            realm = Realm.getDefaultInstance();

        } catch (Exception e) {

            RealmConfiguration config = new RealmConfiguration.Builder(getActivity())
                    .deleteRealmIfMigrationNeeded()
                    .build();

            realm = Realm.getInstance(config);

        }

    }

    @Override
    public MainScreenFragment getFragment() {
        return (MainScreenFragment) super.getFragment();
    }

    @BindingAdapter("sortListItems")
    public static void setSortListItems(MaterialSpinner spinner, List<String> data) {
        spinner.setItems(data);
    }

    @Bindable
    public List<String> getSortListData() {
        return Arrays.asList(getFragment().getResources().getStringArray(R.array.sortList));
    }

    @Override
    MainScreenModel getModel() {
        return model;
    }


    public ResturantListAdapter getRecycleAdapter() {
        return recycleAdapter;
    }

    @BindingAdapter("setAdapter")
    public static void bindRecyclerViewAdapter(RecyclerView recyclerView, ResturantListAdapter adapter) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(adapter);
    }

    public void setInAdapter(List<ResturantListModel> resturantArrayList) {
        getModel().setResturantArrayList(resturantArrayList);
        sortResturantListData();
    }

    public void sortResturantListData() {

        Collections.sort(getModel().getResturantArrayList(), new Comparator<ResturantListModel>() {
            @Override
            public int compare(ResturantListModel lhs, ResturantListModel rhs) {
                switch (getModel().getSortIndex()) {
                    case 0:
                        return Float.compare(lhs.getSortingValues().getBestMatch(), rhs.getSortingValues().getBestMatch());
                    case 1:
                        return Float.compare(lhs.getSortingValues().getDistance(), rhs.getSortingValues().getDistance());
                    case 2:
                        return Float.compare(lhs.getSortingValues().getNewest(), rhs.getSortingValues().getNewest());
                    case 3:
                        return Float.compare(lhs.getSortingValues().getPopularity(), rhs.getSortingValues().getPopularity());
                    case 4:
                        return Float.compare(lhs.getSortingValues().getRatingAverage(), rhs.getSortingValues().getRatingAverage());
                    case 5:
                        return Float.compare(lhs.getSortingValues().getDeliveryCosts(), rhs.getSortingValues().getDeliveryCosts());
                    case 6:
                        return Float.compare(lhs.getSortingValues().getMinCost(), rhs.getSortingValues().getMinCost());
                    case 7:
                        return Float.compare(lhs.getSortingValues().getAverageProductPrice(), rhs.getSortingValues().getAverageProductPrice());


                }
                return 0;
            }
        });

        Collections.sort(getModel().getResturantArrayList(), new Comparator<ResturantListModel>() {
            @Override
            public int compare(ResturantListModel lhs, ResturantListModel rhs) {

                return Integer.compare(getFilteredValue(lhs.getName(), lhs.getStatus()), getFilteredValue(rhs.getName(), rhs.getStatus()));
            }
        });
        this.recycleAdapter.setMenuList(getModel().getResturantArrayList());
        this.recycleAdapter.notifyDataSetChanged();

    }


    private boolean isFaourite(String name) {
        return realm.where(FavouriteRestaurantsTable.class).equalTo("name", name).findFirst() != null;
    }

    private int getFilteredValue(String name, String status) {

        if (isFaourite(name) && status.equalsIgnoreCase("open"))
            return 1;
        if (isFaourite(name) && status.equalsIgnoreCase("order ahead"))
            return 2;
        if (isFaourite(name) && status.equalsIgnoreCase("closed"))
            return 3;
        else if (status.equalsIgnoreCase("open"))
            return 4;
        else if (status.equalsIgnoreCase("order ahead"))
            return 5;
        else if (status.equalsIgnoreCase("closed"))
            return 6;

        return 0;
    }

    public MaterialSpinner.OnItemSelectedListener onItemSelectedListenerForSorting = new MaterialSpinner.OnItemSelectedListener() {
        @Override
        public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
            getModel().setSortIndex(position);
            sortResturantListData();
        }
    };

}
