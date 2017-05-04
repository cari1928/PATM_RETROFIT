package me.apexjcl.retrofitsample.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by radog on 04/05/2017.
 */

public class ServiceGenerator {

    private static final String baseUrl = "https//jsonplaceholder.typicode.com";
    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create());

    public static <S> S generate(Class<S> serviceClass) {
        return builder.build().create(serviceClass);
    }

}
