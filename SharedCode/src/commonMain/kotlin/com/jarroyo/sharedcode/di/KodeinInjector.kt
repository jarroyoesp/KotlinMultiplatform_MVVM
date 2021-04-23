package com.jarroyo.sharedcode.di

import com.jarroyo.kotlinmultiplatform.source.network.GitHubApi
import com.jarroyo.sharedcode.ApplicationDispatcher
import com.jarroyo.sharedcode.DatabaseCreator
import com.jarroyo.sharedcode.data.repository.CounterRepository
import com.jarroyo.sharedcode.data.repository.GitHubRepository
import com.jarroyo.sharedcode.data.source.disk.DiskDataSourceImpl
import com.jarroyo.sharedcode.data.source.disk.IDiskDataSource
import com.jarroyo.sharedcode.data.source.network.INetworkDataSource
import com.jarroyo.sharedcode.data.source.network.NetworkDataSource
import com.jarroyo.sharedcode.domain.usecase.counter.GetCounterUseCase
import com.jarroyo.sharedcode.domain.usecase.github.getRepos.GetGitHubRepoListUseCase
import com.jarroyo.sharedcode.domain.usecase.user.CreateUserUseCase
import com.jarroyo.sharedcode.domain.usecase.user.GetUserListUseCase
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import org.kodein.di.*
import kotlin.coroutines.CoroutineContext
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
val KodeinInjector = DI {

    //bind<CoroutineContext>() with provider { ApplicationDispatcher }

    bind<CoroutineContext>() with provider { Dispatchers.Main }

    val client = HttpClient() {
        install(JsonFeature) {
            serializer = KotlinxSerializer(json = Json {
                isLenient = false
                ignoreUnknownKeys = true
                allowSpecialFloatingPointValues = true
                useArrayPolymorphism = false
            })
        }
    }

    /**
     * NETWORK API
     */
    bind<GitHubApi>() with provider { GitHubApi() }

    /**
     * NETWORK DATA SOURCE
     */
    bind<INetworkDataSource>() with provider { NetworkDataSource(instance(), client) }
    /**
     * Disk Data Source
     */
    bind<IDiskDataSource>() with provider { DiskDataSourceImpl(DatabaseCreator.getDataBase(InjectorCommon.mContextArgs)) }


    /**
     * REPOSITORIES
     */
    bind<CounterRepository>() with provider { CounterRepository(InjectorCommon.mContextArgs) }
    bind<GitHubRepository>() with provider { GitHubRepository(instance(), instance()) }


    /**
     * USECASES
     */
    bind<GetCounterUseCase>() with singleton { GetCounterUseCase(instance()) }
    bind<GetGitHubRepoListUseCase>() with singleton { GetGitHubRepoListUseCase(instance()) }
    bind<GetUserListUseCase>() with singleton { GetUserListUseCase(instance()) }
    bind<CreateUserUseCase>() with singleton { CreateUserUseCase(instance()) }
}