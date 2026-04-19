package com.example.rssapp.network

import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Url

interface RssApiService {
    @GET
    suspend fun getFeedFromUrl(@Url url: String): Response<ResponseBody>
}



object NetworkObject{
    private val okHttpClient = OkHttpClient.Builder().build()
    val retrofit = Retrofit.Builder().baseUrl("https://www.theguardian.com/world/rss").client(okHttpClient).build()
    val rssApiService: RssApiService = retrofit.create(RssApiService::class.java)
}