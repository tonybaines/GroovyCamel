import org.apache.camel.language.groovy.GroovyRouteBuilder

import static org.apache.camel.builder.script.ScriptBuilder.*;

public class MyRouteBuilderService extends GroovyRouteBuilder {
	def inQueueUri = 'activemq:queue.in'
	def queueUri1 = 'activemq:queue.one'
	def queueUri2 = 'activemq:queue.two'
	def queueUri3 = 'activemq:queue.three'
	public void configure() {
		from(inQueueUri).
//		to(queueUri1)
		choice()
			.when(groovy("request.body %3 == 0")).to(queueUri3)
			.when(groovy("request.body %2 == 0")).to(queueUri2)
			.otherwise().to(queueUri1)
	}
}