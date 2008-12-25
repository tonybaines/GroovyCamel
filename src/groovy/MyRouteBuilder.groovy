import org.apache.camel.builder.*

public class MyRouteBuilder extends RouteBuilder {
	public void configure() {
		from('activemq:queue.in').multicast().
			to('activemq:queue.one',
			   'activemq:queue.two')
	}
}