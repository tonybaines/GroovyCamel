
//
// This script is executed by Grails after plugin was installed to project.
// This script is a Gant script so you can use all special variables provided
// by Gant (such as 'baseDir' which points on project base dir). You can
// use 'Ant' to access a global instance of AntBuilder
//
// For example you can create directory under project tree:
// Ant.mkdir(dir:"/Users/marc/Projects/Debug/grails-app/jobs")
//

Ant.property(environment:"env")
grailsHome = Ant.antProject.properties."env.GRAILS_HOME"

includeTargets << new File ( "${grailsHome}/scripts/Init.groovy" )  


println "**NOTE** If you are running Grails 1.0.2 please copy the plugins/debug-1.0/grails-app/views/debug folder into your project's grails-app/views/debug foler"

//Ant.copy(toDir:baseDir + "/grails-app/views", dir: baseDir + "/plugins/debug-*/grails-app/views/debug")