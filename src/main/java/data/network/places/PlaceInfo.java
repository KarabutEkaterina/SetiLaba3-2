package data.network.places;

import com.google.gson.annotations.SerializedName;

public class PlaceInfo {
    @SerializedName("wikipedia_extracts")
    public Wiki info;

    public String getInfo() {
        return info != null ? info.text : "no description";
    }

    private class Wiki {
        @SerializedName("text")
        public String text;
    }
}