package business

class User {
    String login
    String password
    String role = "user"

    static constraints = {
        login(blank:false, nullable:false, unique:true)
        password(blank:false, password:true)
        role(inList:["admin", "user"])
    }

    static transients = ['admin']
    boolean isAdmin(){
        return role == "admin"
    }

    static mapping = {
        table 'user_table'
    }

    def beforeInsert = {
        password = password.encodeAsSHA()
    }

    @Override
    String toString(){
        login
    }
}
