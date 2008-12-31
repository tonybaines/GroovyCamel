import org.codehaus.groovy.grails.commons.GrailsClassUtils as GCU

Ant.property(environment:"env")                             
grailsHome = Ant.antProject.properties."env.GRAILS_HOME"    

includeTargets << grailsScript ( "Init" )

target ('default': "Creates a new routing service class") {
    depends(checkVersion)
	parseArguments()

	typeName = "RoutingService"
	artifactName = "${argsMap.params[0]}RoutingService" 	
	artifactPath = "grails-app/services"
	
	Ant.mkdir(dir:"${basedir}/${artifactPath}")
	
	println("Creating: ${artifactPath}/${artifactName}.groovy")

	def outFile = new File("${artifactPath}/${artifactName}.groovy")
	outFile.write(
"""
import org.apache.camel.language.groovy.GroovyRouteBuilder

import static org.apache.camel.builder.script.ScriptBuilder.*;

class ${artifactName} extends GroovyRouteBuilder {

	public void configure() {
		from("<inbound-uri>").to("<outbound-uri>")
	}
}
""")
}