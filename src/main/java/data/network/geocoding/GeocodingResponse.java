package data.network.geocoding;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GeocodingResponse {

    @SerializedName("hits")
    public List<Hit> hits = null;

    @SerializedName("locale")
    public String locale;
}


