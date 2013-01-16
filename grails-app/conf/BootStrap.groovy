import com.sonymobile.swdtools.statist.*
import grails.util.Environment

class BootStrap {

    def springSecurityService

    def init = { servletContext ->
		switch (Environment.current) {
			case Environment.DEVELOPMENT:
				def Gerrit = new System(name: "Gerrit", prio: 1, archived: 0)
				def Jenkins = new System(name: "Jenkins", prio: 1, archived: 0)
				def CrashTool = new System(name: "CrashTool", prio: 3, archived: 0)
				def ClearCase = new System(name: "ClearCase", prio: 1, archived: 1)
				 Gerrit.save()
				 Jenkins.save()
				 CrashTool.save()
				 ClearCase.save()
				def TagGerrit = new Tag(name: "GERRIT")
				def TagJenkins = new Tag(name: "JENKINS")
				def TagSeld = new Tag(name: "SELD")
				def TagJpto = new Tag(name: "JPTO")
				def TagCnbj = new Tag(name: "CNBJ")
				 TagGerrit.save()
				 TagJenkins.save()
				 TagSeld.save()
				 TagJpto.save()
				 TagCnbj.save()
				def StatusOk = new Status(name: "Ok", severity: 10)
				def Status1 = new Status (name: "1", severity: 1)
				def Status2 = new Status (name: "2", severity: 2)
				def Status3 = new Status (name: "3", severity: 3)
				def Status4 = new Status (name: "4", severity: 4)
				def Status5 = new Status (name: "5", severity: 5)
				 StatusOk.save()
				 Status1.save()
				 Status2.save()
				 Status3.save()
				 Status4.save()
				 Status5.save()
//				def Status01 = new StatusItem(system: Gerrit, text: "Replication issue 01", tag: TagSeld, date: new Date(), status: Status4)
//				def Status02 = new StatusItem(system: Gerrit, text: "Replication issue 02", tag: TagSeld, date: new Date(), status: Status4)
//				def Status03 = new StatusItem(system: Gerrit, text: "Replication issue 03", tag: TagJpto, date: new Date(), status: Status4)
//				def Status04 = new StatusItem(system: Gerrit, text: "Replication issue 04", tag: TagJpto, date: new Date(), status: Status4)
//				def Status05 = new StatusItem(system: Jenkins, text: "Build issue 05", tag: TagSeld, date: new Date(), status: Status5)
//				def Status06 = new StatusItem(system: Jenkins, text: "Uptime issue 06", tag: TagJpto, date: new Date(), status: Status2)
//				def Status07 = new StatusItem(system: Jenkins, text: "Uptime issue 07", tag: TagCnbj, date: new Date(), status: Status1)
//				def Status08 = new StatusItem(system: Gerrit, text: "Replication issue 08", tag: TagSeld, date: new Date(), status: Status3)
//				 Status01.save()
//				 Status02.save()
//				 Status03.save()
//				 Status04.save()
//				 Status05.save()
//				 Status06.save()
//				 Status07.save()
//				 Status08.save()

                break
			case Environment.PRODUCTION:
				break
        }
        //Security
        def userRole = Role.findByAuthority('ROLE_USER') ?: new Role(authority: 'ROLE_USER').save(failOnError: true)
        def adminRole = Role.findByAuthority('ROLE_ADMIN') ?: new Role(authority: 'ROLE_ADMIN').save(failOnError: true)
        def adminUser = User.findByUsername('admin') ?: new User(
                username: 'admin',
                //password: springSecurityService.encodePassword('admin'),
                password: 'admin',
                enabled: true).save(failOnError: true)

        if (!adminUser.authorities.contains(adminRole)) {
            UserRole.create adminUser, adminRole, true
        }

        new RequestMap(url: "/**", configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()
        new RequestMap(url: "/system/**", configAttribute: 'ROLE_ADMIN').save()
        new RequestMap(url: "/statusitem/**", configAttribute: 'ROLE_ADMIN').save()


    }
    def destroy = {
    }
}
