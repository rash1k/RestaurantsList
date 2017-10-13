package ua.example.rash1k.listofrestaurants.data.network;


import android.net.Uri;
import android.support.v4.util.SimpleArrayMap;
import android.util.Log;

import java.net.MalformedURLException;
import java.net.URL;

public class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();

    static final String FOURSQUARE_BASE_URL = "https://api.foursquare.com/v2/";

    static final String FOURSQUARE_VENUES_SEARCH_URL =
            FOURSQUARE_BASE_URL + "venues/search";

    /*
     * One of the values below, indicating your intent in performing the search.
     * If no value is specified, defaults to checkin.
     */
    private static final String INTENT = "checkin";
    /* The limit we want our API to return */
    private static final String LIMIT = "100";

    /* The radius(meters) we want our API to return */
    private static final String RADIUS = "5000";

    /* The category for restaurants we want our API to return */
    private static final String CATEGORY_ID = "4d4b7105d754a06374d81259";

    /* The value of the v parameter is essentially a date in YYYYMMDD format that lets you tell
     *us “I’m prepared for API changes up to this date.”
     */
    private static final String VERSIONING = "20171211";

    /*
     *required unless ll is provided. A string naming a place in the world. If the near string
     * is not geocodable, returns a failed_geocode error. Otherwise, searches within the bounds
     * of the geocode and adds a geocode object to the response.
     */
    private static final String NEAR_PARAM = "near";

    /*
     * Finds venues that the current user (or, for userless requests, a typical user)
     * is likely to checkin to at the provided ll, at the current moment in time.
     * This is the intent we recommend most apps use.
     */
    private static final String INTENT_PARAM = "intent";

    /*Number of results to return, up to 50.*/
    private static final String LIMIT_PARAM = "limit";

    /*
     * Limit results to venues within this many meters of the specified location.
     * Defaults to a city-wide area. Only valid for requests with intent=browse,
     * or requests with intent=checkin and categoryId or query. Does not apply to
     * intent=match requests. The maximum supported radius is currently 100,000 meters.
     */
    private static final String RADIUS_PARAM = "radius";

    /*
     * A comma separated list of categories to limit results to. If you specify categoryId.
     * specifying a radius may improve results. If specifying a top-level category, all
     * sub-categories will also match the query. Does not apply to intent=match requests.
     */
    private static final String CATEGORY_ID_PARAM = "categoryId";

    /*A v parameter, which is a date that essentially represents the
     *“version” of the API you expect from Foursquare.
     */


    private static final String VERSIONING_PARAM = "v";

    private static final String LOCATION_QUERY = "Mountain View, CA";

    static URL getUrl() {
        String locationQuery = "Mountain View, CA";
        return buildUrlWithLocationQuery(locationQuery);
    }

    static final SimpleArrayMap<String, String> sQueryMap =
            new SimpleArrayMap<String, String>(4) {{
        put(NEAR_PARAM, LOCATION_QUERY);
        put(INTENT_PARAM, INTENT);
        put(RADIUS_PARAM, RADIUS);
        put(CATEGORY_ID_PARAM, CATEGORY_ID);
    }};


    private static URL buildUrlWithLocationQuery(String locationQuery) {
        Uri restaurantsQueryUri = Uri.parse(FOURSQUARE_BASE_URL).buildUpon()
                .appendQueryParameter(VERSIONING_PARAM, VERSIONING)
                .appendQueryParameter(NEAR_PARAM, locationQuery)
                .appendQueryParameter(INTENT_PARAM, INTENT)
                .appendQueryParameter(LIMIT_PARAM, LIMIT)
                .appendQueryParameter(RADIUS_PARAM, RADIUS)
                .appendQueryParameter(CATEGORY_ID_PARAM, CATEGORY_ID).build();

        try {
            URL restaurantsQueryUrl = new URL(restaurantsQueryUri.toString());
            Log.d(TAG, "URL: " + restaurantsQueryUrl);
            return restaurantsQueryUrl;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
