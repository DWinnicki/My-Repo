package com.davidwinnicki.myrepo.network

import com.davidwinnicki.myrepo.model.Repo
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {

    @GET("users/{user}/repos")
    fun getReposForUser(@Path("user") user: String): Observable<List<Repo>>

    companion object {
        private const val BASE_URL = "https://api.github.com/"

        fun getInstance(): GitHubService = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(GitHubService::class.java)
    }
}