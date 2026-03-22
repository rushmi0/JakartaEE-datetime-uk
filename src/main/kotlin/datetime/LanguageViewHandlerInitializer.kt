package win.rushmi0.extension.datetime

import jakarta.faces.context.FacesContext
import jakarta.servlet.ServletContextEvent
import jakarta.servlet.ServletContextListener
import jakarta.servlet.annotation.WebListener

//@WebListener
class LanguageViewHandlerInitializer : ServletContextListener {

    override fun contextInitialized(sce: ServletContextEvent?) {

        val ctx = sce?.servletContext

        ctx?.log(
            """
                
            =======================================================
               New Module : datetime-uk-1.0
               Feature    : Datatime override UK format
            =======================================================
            
            """.trimIndent()
        )

        val facesContext = FacesContext.getCurrentInstance()

        if (facesContext != null) {
            val app = facesContext.application
            val originalViewHandler = app.viewHandler
            app.viewHandler = LanguageViewHandler(originalViewHandler)

            ctx?.log("Custom ViewHandler successfully registered (Locale override active)")
        } else {
            ctx?.log("FacesContext not available at startup (ViewHandler will be applied during request phase)")
        }
    }
}