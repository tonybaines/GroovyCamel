class OneService extends AbstractJmsEndpoint {

	boolean transactional = false
	static expose = ['jms']
	static destination = "queue.one"
	
}
