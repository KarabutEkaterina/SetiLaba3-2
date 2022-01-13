package data.network.places;

import data.network.ApiFactoryUtils;

public class PlacesApiFactory {
    public static PlacesApi create() {
        return ApiFactoryUtils.create("http://api.opentripmap.com/", PlacesApi.class);
    }

}
