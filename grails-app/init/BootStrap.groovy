import business.User

class BootStrap {

    def init = { servletContext ->
        User.findOrSaveByLoginAndPasswordAndRole("admin", "admin", "admin")
    }
    def destroy = {
    }
}
