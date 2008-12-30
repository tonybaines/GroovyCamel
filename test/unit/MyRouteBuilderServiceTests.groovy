import org.apache.camel.*
import org.apache.camel.builder.*
import org.apache.camel.impl.*

public class MyRouteBuilderServiceTests extends GroovyTestCase {
	CamelContext myCamelContext

	protected void setUp() throws java.lang.Exception {
		RouteBuilder builder = new MyRouteBuilderService()
		builder.inQueueUri = 'direct:queue.in'
		builder.queueUri1 = 'mock:queue.one'
		builder. queueUri2 = 'mock:queue.two'
		builder. queueUri3 = 'mock:queue.three'
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
