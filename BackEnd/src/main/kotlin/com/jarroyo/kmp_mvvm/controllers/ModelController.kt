package com.jarroyo.kmp_mvvm.controllers

import com.jarroyo.kmp_mvvm.model.MemeBackEnd
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.route

fun Routing.meme() {

    route("meme") {
        get { call.respond(generateMemes()) }
    }
}
 fun generateMemes(): List<MemeBackEnd> {
    return listOf(
        MemeBackEnd("Chocolate!!!!", "https://i.imgflip.com/2f49xl.jpg"),
        MemeBackEnd("Third World Problems.", "https://i.imgflip.com/2f37f4.jpg"),
        MemeBackEnd("Cookies...", "https://i.imgflip.com/2f6n2b.jpg"),
        MemeBackEnd("Can't get fired :D", "https://i.kym-cdn.com/photos/images/facebook/001/217/729/f9a.jpg")
    )
}