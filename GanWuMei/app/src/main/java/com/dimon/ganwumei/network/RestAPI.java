package com.dimon.ganwumei.network;


import com.dimon.ganwumei.database.GanhuoResult;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;


/**
 * Created by Dimon on 2016/3/11.
 */
public interface RestAPI {


    @GET("/data/Android/{limit}/{page}")
    Observable<GanhuoResult> getAndroidGoods(
            @Path("limit") int limit,
            @Path("page") int page
    );

    @GET("/data/iOS/{limit}/{page}")
    Observable<GanhuoResult> getIosGoods(
            @Path("limit") int limit,
            @Path("page") int page
    );

    @GET("/data/all/{limit}/{page}")
    Observable<GanhuoResult> getAllGoods(
            @Path("limit") int limit,
            @Path("page") int page
    );

    @GET("/data/福利/{limit}/{page}")
    Observable<GanhuoResult> getBenefitsGoods(
            @Path("limit") int limit,
            @Path("page") int page
    );
//
//    @GET("/day/{year}/{month}/{day}")
//    Observable<DayGanhuoResult> getGoodsByDay(
//            @Path("year") int year,
//            @Path("month") int month,
//            @Path("day") int day
//    );


}
