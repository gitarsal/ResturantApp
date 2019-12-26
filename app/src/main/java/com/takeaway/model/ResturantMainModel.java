package com.takeaway.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class ResturantMainModel extends BaseModel implements Parcelable {
private ArrayList<ResturantListModel> restaurants;

    protected ResturantMainModel(Parcel in) {
        super(in);
        restaurants = in.createTypedArrayList(ResturantListModel.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeTypedList(restaurants);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ResturantMainModel> CREATOR = new Creator<ResturantMainModel>() {
        @Override
        public ResturantMainModel createFromParcel(Parcel in) {
            return new ResturantMainModel(in);
        }

        @Override
        public ResturantMainModel[] newArray(int size) {
            return new ResturantMainModel[size];
        }
    };

    public ArrayList<ResturantListModel> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(ArrayList<ResturantListModel> restaurants) {
        this.restaurants = restaurants;
    }
}
