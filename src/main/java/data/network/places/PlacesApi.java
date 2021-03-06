package data.network.places;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PlacesApi {

    String API_KEY = "5ae2e3f221c38a28845f05b6e365dd812b281893d1506c37aebe39a6";

    @GET("0.1/en/places/radius")
    Single<PlacesResponse> whatToSee(@Query("lang") String lang,
                                       @Query("radius") Double radius,
                                       @Query("lon") Double lon,
                                       @Query("lat") Double lat,
                                       @Query("apikey") String apikey);

    @GET("0.1/en/places/xid/{id}")
    Single<PlaceInfo> placeInfo(
            @Path("id") Integer id,
            @Query("apikey") String apikey
    );
}
