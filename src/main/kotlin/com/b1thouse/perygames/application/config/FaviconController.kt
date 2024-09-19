package com.b1thouse.perygames.application.config

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody


@Controller
internal class FaviconController {
    @GetMapping("favicon.ico")
    @ResponseBody
    fun returnNoFavicon() {
    }
}