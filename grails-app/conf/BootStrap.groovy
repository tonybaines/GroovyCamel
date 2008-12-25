import org.apache.camel.*
import org.apache.camel.builder.*
import org.apache.camel.impl.*
import org.apache.camel.context.*

class BootStrap {
	CamelContext myCamelContext

     def init = { servletContext ->
     	RouteBuilder builder = new MyRouteBuilder()
		myCamelContext = new DefaultCamelContext();
		myCamelContext.addRoutes(builder);
		myCamelContext.start()
     }
     def destroy = {
		myCamelContext.stop()
     }
} 