package com.takeaway.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;


public class MainScreenModel extends BaseModel implements Parcelable {

    private List<ResturantListModel> resturantArrayList;
    private int sortIndex;

    public MainScreenModel() {


    }

    protected MainScreenModel(Parcel in) {
        super(in);
        resturantArrayList = in.createTypedArrayList(ResturantListModel.CREATOR);
        sortIndex = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeTypedList(resturantArrayList);
        dest.writeInt(sortIndex);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MainScreenModel> CREATOR = new Creator<MainScreenModel>() {
        @Override
        public MainScreenModel createFromParcel(Parcel in) {
            return new MainScreenModel(in);
        }

        @Override
        public MainScreenModel[] newArray(int size) {
            return new MainScreenModel[size];
        }
    };

    public List<ResturantListModel> getResturantArrayList() {
        return resturantArrayList;
    }

    public void setResturantArrayList(List<ResturantListModel> resturantArrayList) {
        this.resturantArrayList = resturantArrayList;
    }

    public int getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(int sortIndex) {
        this.sortIndex = sortIndex;
    }
}
