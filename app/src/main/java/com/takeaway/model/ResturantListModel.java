package com.takeaway.model;

import android.os.Parcel;
import android.os.Parcelable;



public class ResturantListModel extends BaseModel implements Parcelable {
    private String name;
    private String status;
    private boolean isFavourite;
    private ResturantSortingValuesModel sortingValues;

    protected ResturantListModel(Parcel in) {
        super(in);
        name = in.readString();
        status = in.readString();
        isFavourite = in.readByte() != 0;
        sortingValues = in.readParcelable(ResturantSortingValuesModel.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(name);
        dest.writeString(status);
        dest.writeByte((byte) (isFavourite ? 1 : 0));
        dest.writeParcelable(sortingValues, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ResturantListModel> CREATOR = new Creator<ResturantListModel>() {
        @Override
        public ResturantListModel createFromParcel(Parcel in) {
            return new ResturantListModel(in);
        }

        @Override
        public ResturantListModel[] newArray(int size) {
            return new ResturantListModel[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ResturantSortingValuesModel getSortingValues() {
        return sortingValues;
    }

    public void setSortingValues(ResturantSortingValuesModel sortingValues) {
        this.sortingValues = sortingValues;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }
}
