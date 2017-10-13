package ua.example.rash1k.listofrestaurants.data.network;


import android.support.v4.util.SimpleArrayMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface FoursquareService {

    @GET(NetworkUtils.FOURSQUARE_VENUES_SEARCH_URL + "?v=20171211&limit=100")
    Call<FoursquareJSON> searchRestaurants(@QueryMap SimpleArrayMap<String, String> options);
}
