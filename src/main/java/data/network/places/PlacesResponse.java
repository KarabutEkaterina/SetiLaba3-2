package data.network.places;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlacesResponse {

    @SerializedName("place")
    public List<Place> place = null;
}


