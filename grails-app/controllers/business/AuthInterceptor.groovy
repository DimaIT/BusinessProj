package business


class AuthInterceptor {
    AuthInterceptor() {
        matchAll().excludes(controller:"user")
    }

    boolean before() {
        if (!session.user) {
            redirect(controller: "user", action: "login")
            return false
        }
        return true
    }

    boolean after() { true }

    void afterView() {}
}
