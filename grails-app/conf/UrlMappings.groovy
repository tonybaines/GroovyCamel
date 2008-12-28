class UrlMappings {
    static mappings = {
//      "/$controller/$id"{
//          action = [GET:"show", PUT:"update", DELETE:"delete", POST:"save"]
//      }
//      "/$controller"{
//          action = [GET:"list"]
//      }
      "/$controller/$action?/$id?"{
	      constraints {
			  // apply constraints here
		  }
	  }
	  "500"(view:'/error')
	}
}
