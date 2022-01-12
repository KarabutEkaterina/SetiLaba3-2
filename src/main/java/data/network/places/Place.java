package data.network.places;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Place {

    @SerializedName("properties")
    public String name;

    @SerializedName("features")
    public String id;

}
