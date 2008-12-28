
class DebugGrailsPlugin {
    def version = "1.0.2"
	
    def author = "Marc Palmer"
    def authorEmail = "marc@anyware.co.uk"
    def title = "Provides debug tools for development mode"
    def description = '''
    Provides automatic logging of request processing times, request parameters, headers, session info 
    etc both to the log and also on demand within a GSP using custom GSP tags.
    '''
	def documentation = "http://grails.org/Debug+Plugin"
	
    def doWithSpring = {
        // TODO Implement runtime spring config (optional)
    }
   
    def doWithApplicationContext = { applicationContext ->
        // TODO Implement post initialization spring config (optional)		
    }

    def doWithWebDescriptor = { xml ->
        // TODO Implement additions to web.xml (optional)
    }
	                                      
    def doWithDynamicMethods = { ctx ->
        // TODO Implement registering dynamic methods to classes (optional)
    }
	
    def onChange = { event ->
        // TODO Implement code that is executed when this class plugin class is changed  
        // the event contains: event.application and event.applicationContext objects
    }
                                                                                  
    def onApplicationChange = { event ->
        // TODO Implement code that is executed when any class in a GrailsApplication changes
        // the event contain: event.source, event.application and event.applicationContext objects
    }
}
