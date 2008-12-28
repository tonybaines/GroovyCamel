class DebugTagLib {
    static namespace = "debug"

    def debugService
    
    def info = { attrs ->
        debugService.collect(request)
        
        def inf = debugService.getDebugInfo(request)
        
        def cat = attrs['category']
        if (!cat) {
            def keys = new HashSet(inf.keySet())
        
            // Force these to always appear first
            dumpCategory(DebugService.FRIENDLY_NAMES.system, debugService.getDebugInfo(request, 'system'))
            dumpCategory(DebugService.FRIENDLY_NAMES.params, debugService.getDebugInfo(request, 'params'))

            keys.remove('system')
            keys.remove('params')
        
            keys.each() {
                dumpCategory(DebugService.FRIENDLY_NAMES[it] ? DebugService.FRIENDLY_NAMES[it] : it, debugService.getDebugInfo(request, it))
            }
        } else {
            dumpCategory(DebugService.FRIENDLY_NAMES[cat] ? DebugService.FRIENDLY_NAMES[cat] : cat, debugService.getDebugInfo(request, cat))
        }
    }
    
    private dumpCategory(title, info) {
        out << """
<div class="debugInfo" style="padding-bottom: 20px">
<table style="border: 1px solid black">
<tr><td colspan="2" style="padding: 0 4px 0 4px; font-weight: bold; text-align: center">$title</td></tr>
<tr><td style="padding: 0 4px 0 4px; border-right: 1px solid black; font-weight: bold">Name</td><td style="padding: 0 4px 0 4px; font-weight: bold">Value</td></tr>
"""
        info.each() {
            out << "<tr><td style=\"white-space: nowrap; padding: 0 4px 0 4px; border-right: 1px solid black;\">${it[0]}</td><td style=\"padding: 0 4px 0 4px;\">${it[1]}</td></tr>"
        }

        out << "</table></div>"
    }
    
    def timeToHere = { attrs -> 
        def start = request[DebugService.ATTRIB_REQUEST_START]
        out << (System.currentTimeMillis() - start)
        out << "ms"
    }
}
