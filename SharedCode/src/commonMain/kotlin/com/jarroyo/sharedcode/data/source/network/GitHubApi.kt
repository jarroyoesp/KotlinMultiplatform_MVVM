package com.jarroyo.kotlinmultiplatform.source.network


import com.jarroyo.sharedcode.base.Response
import com.jarroyo.sharedcode.domain.model.github.GitHubRepo
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.serialization.json.Json
import kotlinx.serialization.list

class GitHubApi {

    private val httpClient = HttpClient()

    /*suspend fun getGitHubRepoList(username: String, success: (CurrentWeather) -> Unit, failure: (Throwable?) -> Unit) {
        try {
            val url =
                "https://api.openweathermap.org/data/2.5/weather?q=${location.cityName}&APPID=f11780da3330643cd659bb6dbb4e44a3&units=metric"
            val json = httpClient.get<String>(url)

            Json.nonstrict.parse(GitHubRepo.serializer(), json)
                .also(success)
        } catch (ex: Exception) {
            failure(ex)
        }
    }*/

    suspend fun getGitHubRepoList(username: String): Response<List<GitHubRepo>> {
        try {
            val url =
                "https://api.github.com/users/${username}/repos"
            val json = httpClient.get<String>(url)

            val response = Json.nonstrict.parse(GitHubRepo.serializer().list, json)
            return Response.Success(response)

        } catch (ex: Exception) {
            return Response.Error(ex)
        }
    }
}