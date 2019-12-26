package com.takeaway.views.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.takeaway.databinding.FragmentResturantListItemBinding;
import com.takeaway.model.ResturantListModel;
import com.takeaway.viewmodel.ResturantListItemViewModel;
import com.takeaway.views.fragment.MainScreenFragment;
import java.util.ArrayList;
import java.util.List;
public class ResturantListAdapter extends RecyclerView.Adapter<ResturantListAdapter.GenericViewHolder> implements Filterable {

    private int layoutId;
    private List<ResturantListModel> resturantList;
    private ResturantListItemViewModel itemViewModel;
    private MainScreenFragment mainScreenFragment;
    private List<ResturantListModel> filterResturantList;

    public ResturantListAdapter(@LayoutRes int layoutId, ResturantListItemViewModel itemViewModel, MainScreenFragment mainScreenFragment) {
        this.layoutId = layoutId;
        this.itemViewModel = itemViewModel;
        this.mainScreenFragment = mainScreenFragment;

    }


    private ResturantListModel getObjForPosition(int position) {
        return filterResturantList.get(position);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    filterResturantList = resturantList;
                } else {
                    List<ResturantListModel> filteredList = new ArrayList<>();
                    for (ResturantListModel row : resturantList) {

                        if (row.getName().toLowerCase().startsWith(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    filterResturantList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filterResturantList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filterResturantList = (ArrayList<ResturantListModel>) filterResults.values;
                notifyDataSetChanged();

            }
        };
    }

    private int getLayoutIdForPosition(int position) {
        return layoutId;
    }

    @Override
    public int getItemCount() {
        return filterResturantList == null ? 0 : filterResturantList.size();
    }

    public ResturantListAdapter.GenericViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        FragmentResturantListItemBinding binding = DataBindingUtil.inflate(layoutInflater, viewType, parent, false);
        return new ResturantListAdapter.GenericViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ResturantListAdapter.GenericViewHolder holder, int position) {
        ResturantListModel obj = getObjForPosition(position);
        itemViewModel = new ResturantListItemViewModel(this.mainScreenFragment);
        itemViewModel.setModel(obj);
        holder.bind(itemViewModel);
    }

    @Override
    public int getItemViewType(int position) {
        return getLayoutIdForPosition(position);
    }

    public void setMenuList(List<ResturantListModel> menuList) {

        this.resturantList = menuList;
        this.filterResturantList = resturantList;
    }


    class GenericViewHolder extends RecyclerView.ViewHolder {
        final FragmentResturantListItemBinding binding;

        GenericViewHolder(FragmentResturantListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(ResturantListItemViewModel itemViewModel) {

            binding.setVariable(com.takeaway.BR.viewModel, itemViewModel);
            binding.executePendingBindings();
        }

    }
}




