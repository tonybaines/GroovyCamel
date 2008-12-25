abstract class AbstractJmsEndpoint {

	def onMessage = { println "${this.class.name} GOT MESSAGE: $it" }
}