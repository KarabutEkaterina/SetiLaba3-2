package data.network.geocoding;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GeocodingApi {

    String API_KEY = "808b4b19-ccff-4368-be76-86f23fd2c3e3";

    @GET("api/1/geocode")
    Single<GeocodingResponse> queryPlaces(@Query("q") String query, @Query("key") String key);
}
