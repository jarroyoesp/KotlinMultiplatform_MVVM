package com.jarroyo.kotlinmultiplatform.source.network


import com.jarroyo.sharedcode.base.Response
import com.jarroyo.sharedcode.domain.model.github.GitHubRepo
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.serialization.json.Json
import kotlinx.serialization.list

class GitHubApi {

    private val httpClient = HttpClient()

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