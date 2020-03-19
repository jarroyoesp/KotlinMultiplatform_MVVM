package com.jarroyo.kmp_mvvm

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jarroyo.kmp_mvvm.controllers.generateMemes
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CORS
import io.ktor.features.ContentNegotiation
import io.ktor.gson.GsonConverter
import io.ktor.http.ContentType
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty


fun Application.module() {
    install(ContentNegotiation) {
        register(ContentType.Application.Json, GsonConverter(buildGson()))
    }

    install(CORS)

    install(Routing) {
        get("/") {
            call.respondText("My Example Blog2", ContentType.Text.Html)
        }

        get("/meme") {
            call.respond( generateMemes().toList() )
        }
    }
}

private fun buildGson(): Gson {
    return GsonBuilder()
        .setPrettyPrinting()
        .serializeNulls()
        .create()
}


fun main(args: Array<String>) {
    embeddedServer(Netty, 8080, watchPaths = listOf("MainKt"), module = Application::module).start()
}