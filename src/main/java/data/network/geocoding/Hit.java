package data.network.geocoding;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Hit {

    @SerializedName("point")
    public Point point;

    @SerializedName("extent")
    public List<Double> extent = null;

    @SerializedName("name")
    public String name;

    @SerializedName("country")
    public String country;

    @SerializedName("countrycode")
    public String countrycode;

    @SerializedName("osm_id")
    public long osmId;

    @SerializedName("osm_type")
    public String osmType;

    @SerializedName("osm_key")
    public String osmKey;

    @SerializedName("osm_value")
    public String osmValue;

    @SerializedName("state")
    public String state;

    @SerializedName("postcode")
    public String postcode;

    public static class Point {

        @SerializedName("lat")
        public Double lat;

        @SerializedName("lng")
        public Double lng;
    }
}
