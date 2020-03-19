package com.jarroyo.sharedcode.di

import com.jarroyo.kotlinmultiplatform.source.network.GitHubApi
import com.jarroyo.sharedcode.ApplicationDispatcher
import com.jarroyo.sharedcode.data.repository.CounterRepository
import com.jarroyo.sharedcode.data.repository.FirebaseRepository
import com.jarroyo.sharedcode.data.repository.GitHubRepository
import com.jarroyo.sharedcode.data.source.network.INetworkDataSource
import com.jarroyo.sharedcode.data.source.network.NetworkDataSource
import com.jarroyo.sharedcode.domain.usecase.counter.GetCounterUseCase
import com.jarroyo.sharedcode.domain.usecase.firebase.getUsers.GetFirebaseUserListFlowUseCase
import com.jarroyo.sharedcode.domain.usecase.firebase.getUsers.GetFirebaseUserListUseCase
import com.jarroyo.sharedcode.domain.usecase.github.getRepos.GetGitHubRepoListUseCase
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.provider
import org.kodein.di.erased.singleton
import kotlin.coroutines.CoroutineContext
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
val KodeinInjector = Kodein {

    bind<CoroutineContext>() with provider { ApplicationDispatcher }

    /**
     * USECASES
     */
    bind<GetCounterUseCase>() with singleton { GetCounterUseCase(instance()) }
    bind<GetGitHubRepoListUseCase>() with singleton { GetGitHubRepoListUseCase(instance()) }
    bind<GetFirebaseUserListUseCase>() with singleton { GetFirebaseUserListUseCase(instance()) }
    bind<GetFirebaseUserListFlowUseCase>() with singleton { GetFirebaseUserListFlowUseCase(instance()) }

    /**
     * REPOSITORIES
     */
    bind<CounterRepository>() with provider { CounterRepository() }
    bind<GitHubRepository>() with provider { GitHubRepository(instance()) }
    bind<FirebaseRepository>() with provider { FirebaseRepository() }

    /**
     * NETWORK DATA SOURCE
     */
    bind<INetworkDataSource>() with provider { NetworkDataSource(instance()) }

    /**
     * NETWORK API
     */
    bind<GitHubApi>() with provider { GitHubApi() }
}