package com.jarroyo.kotlinmultiplatform.source.network


import com.jarroyo.sharedcode.base.Response
import com.jarroyo.sharedcode.base.exception.NetworkConnectionException
import io.ktor.client.features.json.serializer.KotlinxSerializer
import com.jarroyo.sharedcode.domain.model.github.GitHubRepo
import com.jarroyo.sharedcode.utils.networkSystem.isNetworkAvailable
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.logging.DEFAULT
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.get
import kotlinx.serialization.json.Json

class GitHubApi {

    suspend fun getGitHubRepoList(client: HttpClient, username: String): Response<List<GitHubRepo>> {
        return try {
            if (isNetworkAvailable()) {

                Logger.DEFAULT.log("getGitHubRepoList - ")
                val url = "https://api.github.com/users/${username}/repos"
                val response = client.get<List<GitHubRepo>>(url)
                Logger.DEFAULT.log("getGitHubRepoList - $response")
                Response.Success(response)
            } else {
                Response.Error(NetworkConnectionException())
            }
        } catch (ex: Exception) {
            Logger.DEFAULT.log("getGitHubRepoList - "+ ex.message!!)
            Response.Error(ex)
        }
    }
}