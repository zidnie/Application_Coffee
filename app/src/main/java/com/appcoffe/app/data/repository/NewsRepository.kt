package com.appcoffe.app.data.repository

import com.appcoffe.app.data.model.ActionState
import com.appcoffe.app.data.model.News
import com.appcoffe.app.remote.NewsService
import com.appcoffe.app.remote.RetrofitApi
import retrofit2.await
import retrofit2.http.Query
import java.lang.Exception
import java.net.URL

class NewsRepository {
    private val newsService: NewsService by lazy { RetrofitApi.newsService() }

    suspend fun listNews() : ActionState<List<News>> {
        return try {
            val list = newsService.listNews().await()
            ActionState(list.komunitas)
        } catch (e: Exception) {
            ActionState(massage = e.message, isSuccess = false)
        }
    }

    suspend fun detailNews(url: String) : ActionState<News> {
        return try {
            val list = newsService.detailNews(url).await()
            ActionState(list.komunitas.first())
        } catch (e: Exception) {
            ActionState(massage = e.message, isSuccess = false)
        }
    }

    suspend fun searchNews(query: String) : ActionState<List<News>> {
        return try {
            val list = newsService.searchNews(query).await()
            ActionState(list.komunitas)
        } catch (e: Exception) {
            ActionState(massage = e.message, isSuccess = false)
        }
    }
}