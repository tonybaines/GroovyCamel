import grails.util.GrailsUtil
import org.codehaus.groovy.grails.commons.GrailsApplication

class DebugUrlMappings {
    static mappings = {
      if (GrailsUtil.environment == GrailsApplication.ENV_DEVELOPMENT) {
          "/debug/$action?/$id?"{
              controller = "debug"
    	  }
      }
	}
}
