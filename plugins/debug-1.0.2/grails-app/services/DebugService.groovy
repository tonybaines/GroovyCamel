import org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes
import org.codehaus.groovy.grails.commons.ConfigurationHolder

class DebugService {
    public static final String ATTRIB_DEBUGINFO = "org.grails.plugins.grails-debug.DebugInfo"
    public static final String ATTRIB_COLLECTED = "org.grails.plugins.grails-debug.Collected"
    public static final String ATTRIB_REQUEST_START = "org.grails.plugins.grails-debug.RequestStart"
    static SIMPLE_TYPES = [Boolean, Number, String, GString]

    static FRIENDLY_NAMES = [
        system: "System Information",
        params: "Request parameters",
        stats: "Request statistics",
        headers: "Request headers",
        controller: "Controller info",
        session: "Session attributes",
        requestAttributes: "Request attributes",
        model: "Model"
    ]

    def collectors = [:]
    
    DebugService() {
        addCollector('system') { request ->
        	traceVar(request, 'system', 'Java Version', System.getProperty('java.version'))
        	traceVar(request, 'system', 'Java Vendor', System.getProperty('java.vendor'))
        	traceVar(request, 'system', 'Total processors', Runtime.runtime.availableProcessors())
        	traceVar(request, 'system', 'Total memory', Runtime.runtime.totalMemory())
        	traceVar(request, 'system', 'Free memory', Runtime.runtime.freeMemory())
        	traceVar(request, 'system', 'Max. memory', Runtime.runtime.maxMemory())
        }
        addCollector('controller') { request ->
            if (request[GrailsApplicationAttributes.CONTROLLER_NAME_ATTRIBUTE]) {
                traceVar(request, 'controller', 'controller', request[GrailsApplicationAttributes.CONTROLLER_NAME_ATTRIBUTE])
                traceVar(request, 'controller', 'action', 
                    request[GrailsApplicationAttributes.ACTION_NAME_ATTRIBUTE] ? 
                    request[GrailsApplicationAttributes.ACTION_NAME_ATTRIBUTE] : "*default*")
                traceVar(request, 'controller', 'mapped from URI', 
                    request.forwardURI)
            }
        }
        
        addCollector('params') { request ->
            // Controller may not always be resolved if there is an error, such as URL mapping error
            def params = request[GrailsApplicationAttributes.CONTROLLER]?.params
            if (params) {
                params.keySet().sort().each() {
                    traceVar(request, 'params', it, params[it])
                }
            }
        }
        addCollector('headers') { request ->
            request.headerNames.toList().sort().each() { h ->
                request.getHeaders(h).each() {
                    traceVar(request, 'headers', h, it)
                }
            }
        }
        addCollector('session') { request ->
            request.session.attributeNames.toList().sort().each() {
                def attribute = request.session.getAttribute(it)
                traceVar(request, 'session', it, isSafe(attribute) ? attribute : "Instance of ${attribute.getClass()}")
            }
        }
        addCollector('requestAttributes') { request ->
            request.attributeNames.toList().sort().each() {
                def attr = request.getAttribute(it)
                traceVar(request, 'requestAttributes', it, isSafe(attr) ? attr : "Instance of ${attr.getClass()}")
            }
        }
        addCollector('model') { request ->
            request[GrailsApplicationAttributes.PAGE_SCOPE]?.variables.each() { k, v ->
                traceVar(request, 'model', k, isSafe(v) ? v : "Instance of ${v.getClass()}")
            }
        }
    }
    
    private boolean isSafe(value) {
        return SIMPLE_TYPES.find {
            return (it.isAssignableFrom(value.getClass()))
        }
    }

    void addCollector(category, Closure collector) {
        collectors[category] = collector
    }
    
    void collect(request) {
        if (!request[ATTRIB_COLLECTED]) {
            collectors.values().each() {
                it.call(request)
            }
            request[ATTRIB_COLLECTED] = true
        }
    }
    
    void traceVar(def request, def category, def name, def value) {
        def tracing = "false" != (ConfigurationHolder.config.grails.debug.enabled ?: '')
        
        if (tracing && Boolean.valueOf(ConfigurationHolder.config.grails.debug[category] ?: 'false')) {
            log.info((FRIENDLY_NAMES[category] ? FRIENDLY_NAMES[category] : category) + " ${name} = ${value}")
        }
        def debugInfo = getDebugInfo(request, category)
        debugInfo << [name, value]
    }
    
    def getDebugInfo(request) {
        if (request[ATTRIB_DEBUGINFO] == null) {
            request[ATTRIB_DEBUGINFO] = [:]
        }
        
        return request[ATTRIB_DEBUGINFO]
    }
    
    def getDebugInfo(request, category) {
        def info = getDebugInfo(request)
        
        if (info[category] == null) {
            info[category] = []
        }
        
        return info[category]
    }
}