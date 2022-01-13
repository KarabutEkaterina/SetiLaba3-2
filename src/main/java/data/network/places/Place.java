package data.network.places;

import com.google.gson.annotations.SerializedName;

public class Place {

    @SerializedName("properties")
    public Props name;

    @SerializedName("id")
    public String id;

    public String getName() {
        return name.name;
    }

    private class Props {
        @SerializedName("name")
        public String name;
    }
}
