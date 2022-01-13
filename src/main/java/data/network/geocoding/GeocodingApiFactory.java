package data.network.geocoding;

import data.network.ApiFactoryUtils;
import data.network.weather.WeatherApi;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class GeocodingApiFactory {

    public static GeocodingApi create() {
        return ApiFactoryUtils.create("https://graphhopper.com", GeocodingApi.class);
    }
}
