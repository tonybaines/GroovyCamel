import org.apache.camel.builder.*

public class MyRouteBuilder extends RouteBuilder {
	public void configure() {
		from('file://a').to('file://b')
	}
}