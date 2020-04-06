package com.jarroyo.kotlinmultiplatform.source.network


import com.jarroyo.sharedcode.base.Response
import com.jarroyo.sharedcode.base.exception.NetworkConnectionException
import com.jarroyo.sharedcode.domain.model.github.GitHubRepo
import com.jarroyo.sharedcode.utils.networkSystem.isNetworkAvailable
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.serialization.builtins.list
import kotlinx.serialization.json.Json

class GitHubApi {

    private val httpClient = HttpClient()

    suspend fun getGitHubRepoList(username: String): Response<List<GitHubRepo>> {
        try {
            if (isNetworkAvailable()) {
                val url =
                    "https://api.github.com/users/${username}/repos"
                val json = httpClient.get<String>(url)

                val response = Json.nonstrict.parse(GitHubRepo.serializer().list, json)
                return Response.Success(response)
            } else {
                return Response.Error(NetworkConnectionException())
            }
        } catch (ex: Exception) {
            return Response.Error(ex)
        }
    }
}