package com.brightbetter.extension.datetime

import jakarta.faces.application.ViewHandler
import jakarta.faces.application.ViewHandlerWrapper
import jakarta.faces.context.FacesContext
import java.util.*


class LanguageViewHandler(wrapped: ViewHandler?) : ViewHandlerWrapper(wrapped) {
    override fun calculateLocale(context: FacesContext?): Locale? {
        val now = Locale.UK
        //println("current : $now")
        return now
    }
}