package data.network.weather;

import data.network.ApiFactoryUtils;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherApiFactory {

    public static WeatherApi create() {
        return ApiFactoryUtils.create("https://api.openweathermap.org", WeatherApi.class);
    }
}
