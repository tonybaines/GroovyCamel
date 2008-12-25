class ThreeService extends AbstractJmsEndpoint {

	boolean transactional = false
	static expose = ['jms']
	static destination = "queue.three"
}
