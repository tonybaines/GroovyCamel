class ThingController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        if(!params.max) params.max = 10
        [ thingInstanceList: Thing.list( params ) ]
    }

    def show = {
        def thingInstance = Thing.get( params.id )

        if(!thingInstance) {
            flash.message = "Thing not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ thingInstance : thingInstance ] }
    }

    def delete = {
        def thingInstance = Thing.get( params.id )
        if(thingInstance) {
            thingInstance.delete()
            flash.message = "Thing ${params.id} deleted"
            redirect(action:list)
        }
        else {
            flash.message = "Thing not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def thingInstance = Thing.get( params.id )

        if(!thingInstance) {
            flash.message = "Thing not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ thingInstance : thingInstance ]
        }
    }

    def update = {
        def thingInstance = Thing.get( params.id )
        if(thingInstance) {
            thingInstance.properties = params
            if(!thingInstance.hasErrors() && thingInstance.save()) {
                flash.message = "Thing ${params.id} updated"
                redirect(action:show,id:thingInstance.id)
            }
            else {
                render(view:'edit',model:[thingInstance:thingInstance])
            }
        }
        else {
            flash.message = "Thing not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
        def thingInstance = new Thing()
        thingInstance.properties = params
        return ['thingInstance':thingInstance]
    }

    def save = {
        def thingInstance = new Thing(params)
        if(!thingInstance.hasErrors() && thingInstance.save()) {
            flash.message = "Thing ${thingInstance.id} created"
            redirect(action:show,id:thingInstance.id)
        }
        else {
            render(view:'create',model:[thingInstance:thingInstance])
        }
    }
}
