package ua.example.rash1k.listofrestaurants.data.network;


import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestaurantsNetworkDataSource {

    //create builder for client
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    //create builder for Rest Service
    private static Retrofit.Builder sBuilder = new Retrofit.Builder()
            .baseUrl(NetworkUtils.FOURSQUARE_BASE_URL) //connect root api URL
            .addConverterFactory(GsonConverterFactory.create()); //connect GSON serialization


    //Create Rest service
    public static <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = sBuilder
                .client(httpClient.build())
                .build();
        return retrofit.create(serviceClass);
    }
}
