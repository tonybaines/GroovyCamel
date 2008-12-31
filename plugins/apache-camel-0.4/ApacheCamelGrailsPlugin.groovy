class ApacheCamelGrailsPlugin {
    def version = 0.4
    def dependsOn = [:]

    def author = "Tony Baines	"
    def authorEmail = "tony.baines.1@gmail.com"
    def title = "Integrate Apache Camel into Grails"
    def description = '''\
Use the power of Apache Camel to implement the Enterprise Integration Patterns
(EIP) in Grails applications.
'''

    // URL to the plugin's documentation
    def documentation = "http://grails.org/ApacheCamel+Plugin"

    def doWithSpring = {
        // Find all RouteBuilders in the default package
        // and initialise the Camel context
    	camelContext(org.apache.camel.spring.CamelContextFactoryBean) {
		  packages = "default"
		}
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
        // TODO Implement code that is executed when any artefact that this plugin is
        // watching is modified and reloaded. The event contains: event.source,
        // event.application, event.manager, event.ctx, and event.plugin.
    }

    def onConfigChange = { event ->
        // TODO Implement code that is executed when the project configuration changes.
        // The event is the same as for 'onChange'.
    }
}
