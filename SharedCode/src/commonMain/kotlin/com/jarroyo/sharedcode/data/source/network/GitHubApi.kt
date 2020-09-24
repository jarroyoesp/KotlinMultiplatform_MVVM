package com.jarroyo.kotlinmultiplatform.source.network


import com.jarroyo.sharedcode.base.Response
import com.jarroyo.sharedcode.base.exception.NetworkConnectionException
import io.ktor.client.features.json.serializer.KotlinxSerializer
import com.jarroyo.sharedcode.domain.model.github.GitHubRepo
import com.jarroyo.sharedcode.httpClientEngine
import com.jarroyo.sharedcode.utils.networkSystem.isNetworkAvailable
import io.ktor.client.HttpClient
import io.ktor.client.features.DefaultRequest.Feature.install
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.logging.DEFAULT
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.get
import kotlinx.serialization.builtins.list
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.nonstrict
import kotlinx.serialization.parse

class GitHubApi(private val clientA: HttpClient) {

    private val nonStrictJson = Json { isLenient = true; ignoreUnknownKeys = true }

    private val client by lazy {
        HttpClient() {
            install(JsonFeature) {
                serializer = KotlinxSerializer(nonStrictJson)
            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.INFO
            }
        }
    }

    suspend fun getGitHubRepoList(username: String): Response<List<GitHubRepo>> {
        try {
            if (isNetworkAvailable()) {
                val url = "https://api.github.com/users/${username}/repos"
                return Response.Success(clientA.get(url))
            } else {
                return Response.Error(NetworkConnectionException())
            }
        } catch (ex: Exception) {
            Logger.DEFAULT.log("getGitHubRepoList - "+ ex.message!!)
            return Response.Error(ex)
        }
    }
}