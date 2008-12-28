import org.junit.*
import org.apache.camel.*
import org.apache.camel.builder.*
import org.apache.camel.impl.*
import org.apache.camel.context.*

public class TestSimpleCamelRouting extends GroovyTestCase {
	CamelContext myCamelContext


	void setUp() {
		RouteBuilder builder = new MyRouteBuilderService()
		myCamelContext = new DefaultCamelContext();
		myCamelContext.addRoutes(builder);
		myCamelContext.start()
	}
	
	
	void testShouldRouteFromAtoB() {
		setUp()
		println('')
	}
}
