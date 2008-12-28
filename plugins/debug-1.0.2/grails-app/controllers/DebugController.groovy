import grails.util.GrailsUtil
import org.codehaus.groovy.grails.commons.GrailsApplication
import org.codehaus.groovy.grails.plugins.PluginManagerHolder

class DebugController {

	def beforeInterceptor = { 
		if (GrailsUtil.environment == GrailsApplication.ENV_PRODUCTION) {
			response.sendError( 404)
			return false; 
		}
	}
	
	def afterInterceptor = { model -> 
	    model.env = GrailsUtil.environment 
	    model.plugins = PluginManagerHolder.pluginManager.allPlugins
	}
	
	static defaultAction = 'controlPanel'
	
	def controlPanel = {
	
	}
	
	def info = {
	    
	}
	
	def flushSession = {
		session.invalidate()
		flash.message = "Your session has been flushed"
		render(view:'controlPanel')
	}
}