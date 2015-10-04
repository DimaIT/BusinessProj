import business.User

class BootStrap {

    def init = { servletContext ->
        if (User.findByLoginAndPasswordAndRole("admin", "admin".encodeAsSHA(), "admin") == null)
            new User(login: "admin", password: "admin", role: "admin").save()
    }

    def destroy = {
    }
}
