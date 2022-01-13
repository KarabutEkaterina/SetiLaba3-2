package data.network.weather;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {

    String API_KEY = "59e02963190d7f9583a97d9e3123a3c9";

    @GET("data/2.5/weather")
    Single<WeatherResponse> getWeather(@Query("lat") Double lat, @Query("lon") Double lon, @Query("appid") String key);
}
