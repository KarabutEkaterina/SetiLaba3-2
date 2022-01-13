package data.network.places;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlacesResponse {

    @SerializedName("features")
    public List<Place> place;
}


