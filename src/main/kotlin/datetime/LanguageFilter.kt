package com.brightbetter.extension.datetime

import jakarta.servlet.*
import jakarta.servlet.annotation.WebFilter
import jakarta.servlet.http.HttpServletRequest
import java.io.IOException


@WebFilter("/*")
class LanguageFilter : Filter {
    @Throws(IOException::class, ServletException::class)
    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain) {
        //println("run on : LanguageFilter | $request")
        if (request is HttpServletRequest) {
            val wrappedRequest = LocaleOverrideRequestWrapper(request)
            chain.doFilter(wrappedRequest, response)
        } else {
            chain.doFilter(request, response)
        }
    }
}