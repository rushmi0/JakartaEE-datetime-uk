package com.brightbetter.extension.datetime

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletRequestWrapper
import java.util.*

class LocaleOverrideRequestWrapper(request: HttpServletRequest) : HttpServletRequestWrapper(request) {
    private val forcedLocale: Locale? = Locale.UK

    override fun getLocale(): Locale? {
        //println("run on : LocaleOverrideRequestWrapper : forced Locale $forcedLocale")
        return forcedLocale
    }
}