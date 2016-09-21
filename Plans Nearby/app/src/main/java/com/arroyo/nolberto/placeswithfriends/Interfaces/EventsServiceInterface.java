package com.arroyo.nolberto.placeswithfriends.Interfaces;

import com.arroyo.nolberto.placeswithfriends.BuildConfig;
import com.arroyo.nolberto.placeswithfriends.Models.EventBriteModels.Event;
import com.arroyo.nolberto.placeswithfriends.Models.EventBriteModels.Events;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by nolbertoarroyo on 8/18/16.
 */
public interface EventsServiceInterface {
    //get all events
    @GET("events/search/?token="+ BuildConfig.EVENTBRITE_TOKEN+"")
    Call<Events> getEvents();

    //get events by id
    @GET("/v3/events/{id}/?expand=venue,category,ticket_classes&token="+ BuildConfig.EVENTBRITE_TOKEN+"")
    Call<Event> getEventById(@Path("id") String id);

    //search events by query and sort by date
    @GET("events/search/?expand=venue,category,ticket_classes&sort_by=date&token="+ BuildConfig.EVENTBRITE_TOKEN+"")
    Call<Events> getEventsResults(@Query("q") String query);

    //get events by current location, sorted by date
    @GET("events/search/?expand=venue,category,ticket_classes,display_settings&sort_by=date&token="+ BuildConfig.EVENTBRITE_TOKEN+"")
    Call<Events> getEventsResults(@Query("q") String query, @Query("location.latitude") String lat, @Query("location.longitude") String lon);


    //get search results by city
    @GET("events/search/?expand=venue,category,ticket_classes&sort_by=date&token="+ BuildConfig.EVENTBRITE_TOKEN+"")
    Call<Events> getEventsResults(@Query("q") String query,@Query("venue.city") String city);

    //get events by current location, sorted by date
    @GET("events/search/?expand=venue,category,ticket_classes,display_settings&sort_by=date&token="+ BuildConfig.EVENTBRITE_TOKEN+"")
    Call<Events> getEventsCatResults(@Query("q") String query, @Query("location.latitude") String lat, @Query("location.longitude") String lon, @Query("categories") String category);


    //get search results by city
    @GET("events/search/?expand=venue,category,ticket_classes&sort_by=date&token="+ BuildConfig.EVENTBRITE_TOKEN+"")
    Call<Events> getEventsCatResults(@Query("q") String query,@Query("venue.city") String city, @Query("categories") String category);

    @GET("events/search/?expand=venue,category,ticket_classes,display_settings&sort_by=date&token="+ BuildConfig.EVENTBRITE_TOKEN+"")
    Call<Events> getWeekendResultsNear(@Query("start_date.keyword") String weekendOnly, @Query("location.latitude") String lat, @Query("location.longitude") String lon);


    //get search results by city
    @GET("events/search/?expand=venue,category,ticket_classes&sort_by=date&token="+ BuildConfig.EVENTBRITE_TOKEN+"")
    Call<Events> getWeekendResultsCity(@Query("start_date.keyword") String weekendOnly,@Query("venue.city") String city);

}
