import grails.util.GrailsUtil
import org.codehaus.groovy.grails.commons.GrailsApplication
import org.springframework.web.util.UrlPathHelper
import javax.servlet.http.HttpServletRequest
import org.codehaus.groovy.grails.commons.ConfigurationHolder

class DebugFilters {
    def filters =  {
        debugGlobalFilter(controller:'*', action:'*') {
                before = {
                    if (getWeAreDebugging()) {
                        request[DebugService.ATTRIB_REQUEST_START] = System.currentTimeMillis()
                        if (isDebugEnabled()) {
                            log.info("Request received for action ${actionName} of controller ${controllerName} from host ${request.remoteAddr}")
                        }
                        
                        def debugService = applicationContext.debugService
                        debugService.traceVar(request, 'stats', 'requestStart', new Date(request[DebugService.ATTRIB_REQUEST_START]))
                    }
                }
                
                after = { 
                    if (getWeAreDebugging()) {
                        if (isDebugEnabled()) {
                            log.info("Finished executing request for action ${actionName} of controller ${controllerName} from host ${request.remoteAddr}")
                        }
                        
                        def debugService = applicationContext.debugService
                        def now = System.currentTimeMillis()
                        debugService.traceVar(request, 'stats', 'requestHandled', new Date(now))
                    }
                }
                
                afterView = {
                    if (getWeAreDebugging()) {
                        if (isDebugEnabled()) {
                            log.info("Finished rendering request for action ${actionName} of controller ${controllerName} from host ${request.remoteAddr}")
                        }
                        def debugService = applicationContext.debugService
                        def now = System.currentTimeMillis()
                        debugService.traceVar(request, 'stats', 'requestRendered', new Date(now))

                        if (isDebugEnabled()) {
                            debugService.collect(request)
                            log.info("Total request time: ${now - request[DebugService.ATTRIB_REQUEST_START]}ms")
                        }
                    }
                }
        }
    }
    
    // Are we set to automatically output all collected data to log?
    // If not, we still collate data but it must be rendered explicitly by the application, for example using the GSP <debug:xxx> tags
    private isDebugEnabled() {
        // This logic is hassle - to handle defaulting to on if no value specified, or value is blank,
        // but only turn OFF if it is set to false
        def logging = 'false' != (ConfigurationHolder.config.grails.debug.enabled ?: '')
    }
    
    // Work out if we should even attempt to log/debug anything at all
    // If we are in production we don't want this filter to do diddly-squat
    private getWeAreDebugging() {
        return (GrailsUtil.environment != GrailsApplication.ENV_PRODUCTION) || 
            ConfigurationHolder.config.grails.debug.productionOverride
    }
}