class NotificationController {

    def index = {
    	def message = "Hi, this is a Hello World with JMS & ActiveMQ, " + new Date()
    	sendJMSMessage("queue.in", message)
    	render message
    }
    def save = {
    	def message= params.message
    	if (message != null) {
    		sendJMSMessage("queue.in", message)
    		println('Sent ' + message)
    	}
    	render (view: 'create')
    }
}
