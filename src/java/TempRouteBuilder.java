import static org.apache.camel.builder.script.ScriptBuilder.*;

import org.apache.camel.language.groovy.GroovyRouteBuilder;


public class TempRouteBuilder extends GroovyRouteBuilder {

	@Override
	public void configure() throws Exception {
		
		from("").choice().when(groovy("")).to("");
	}

}
