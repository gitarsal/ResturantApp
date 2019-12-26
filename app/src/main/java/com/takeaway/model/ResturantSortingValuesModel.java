package com.takeaway.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ResturantSortingValuesModel extends BaseModel implements Parcelable {
    private float bestMatch;
    private float newest;
    private float ratingAverage;
    private int distance;
    private float popularity;
    private int averageProductPrice;
    private int deliveryCosts;
    private int minCost;

    public float getBestMatch() {
        return bestMatch;
    }

    public void setBestMatch(float bestMatch) {
        this.bestMatch = bestMatch;
    }

    public float getNewest() {
        return newest;
    }

    public void setNewest(float newest) {
        this.newest = newest;
    }

    public float getRatingAverage() {
        return ratingAverage;
    }

    public void setRatingAverage(float ratingAverage) {
        this.ratingAverage = ratingAverage;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public float getPopularity() {
        return popularity;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public int getAverageProductPrice() {
        return averageProductPrice;
    }

    public void setAverageProductPrice(int averageProductPrice) {
        this.averageProductPrice = averageProductPrice;
    }

    public int getDeliveryCosts() {
        return deliveryCosts;
    }

    public void setDeliveryCosts(int deliveryCosts) {
        this.deliveryCosts = deliveryCosts;
    }

    public int getMinCost() {
        return minCost;
    }

    public void setMinCost(int minCost) {
        this.minCost = minCost;
    }


    protected ResturantSortingValuesModel(Parcel in) {
        super(in);
        bestMatch = in.readFloat();
        newest = in.readFloat();
        ratingAverage = in.readFloat();
        distance = in.readInt();
        popularity = in.readFloat();
        averageProductPrice = in.readInt();
        deliveryCosts = in.readInt();
        minCost = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeFloat(bestMatch);
        dest.writeFloat(newest);
        dest.writeFloat(ratingAverage);
        dest.writeInt(distance);
        dest.writeFloat(popularity);
        dest.writeInt(averageProductPrice);
        dest.writeInt(deliveryCosts);
        dest.writeInt(minCost);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ResturantSortingValuesModel> CREATOR = new Creator<ResturantSortingValuesModel>() {
        @Override
        public ResturantSortingValuesModel createFromParcel(Parcel in) {
            return new ResturantSortingValuesModel(in);
        }

        @Override
        public ResturantSortingValuesModel[] newArray(int size) {
            return new ResturantSortingValuesModel[size];
        }
    };
}
