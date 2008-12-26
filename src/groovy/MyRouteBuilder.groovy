import org.apache.camel.language.groovy.GroovyRouteBuilder

import static org.apache.camel.builder.script.ScriptBuilder.*;

public class MyRouteBuilder extends GroovyRouteBuilder {
	public void configure() {
		from('activemq:queue.in').
		choice()
			.when(groovy("request.body %3 == 0")).to('activemq:queue.three')
			.when(groovy("request.body %2 == 0")).to('activemq:queue.two')
			.otherwise().to('activemq:queue.one')
	}
}