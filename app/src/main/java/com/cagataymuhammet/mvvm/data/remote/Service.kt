package com.cagataymuhammet.mvvm.data.remote

import com.cagataymuhammet.mvvm.util.Constants
import com.cagataymuhammet.mvvm.data.model.UserModel
import com.cagataymuhammet.mvvm.util.Resource
import io.reactivex.Observable
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface Service {

    @GET("posts")
    suspend  fun getUsers(): Response<List<UserModel>>

    /*
    @GET("posts/{id}")
    fun getUser(@Path("id") id: Int): Observable<UserModel>

    @Headers("Content-Type: application/json;charset=utf-8")
    @POST("posts")
    fun addUser(@Body article: UserModel): Observable<UserModel>

     */

    companion object {

        fun build(): Service {

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .build()

            return retrofit.create(Service::class.java)

        }
    }
}