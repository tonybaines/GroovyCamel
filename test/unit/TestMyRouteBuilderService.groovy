import org.apache.camel.*
import org.apache.camel.builder.*
import org.apache.camel.impl.*

public class TestMyRouteBuilderService extends GroovyTestCase {
	CamelContext myCamelContext

	protected void setUp() throws java.lang.Exception {
		RouteBuilder builder = new MyRouteBuilderService()
		builder.inQueueUri = 'activemq:queue.in'
		builder.queueUri1 = 'activemq:queue.one'
		builder. queueUri2 = 'activemq:queue.two'
		builder. queueUri3 = 'activemq:queue.three'
		myCamelContext = new DefaultCamelContext();
		myCamelContext.addRoutes(builder);
		myCamelContext.start()
	}
	
	
	void testShouldRouteFromAtoB() {
		println('Testing')
	}
	
	protected void tearDown() throws java.lang.Exception {
		myCamelContext.stop()
	}
}
