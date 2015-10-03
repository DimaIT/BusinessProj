package business

class Business {
    String name
    String email
    String street
    String zip

    static constraints = {
        name(blank: false)
        email(unique: true, blank: false, nullable: false, email: true)
        zip(maxSize: 100)
    }

    @Override
    String toString(){
        name + ", email: " + email
    }
}
