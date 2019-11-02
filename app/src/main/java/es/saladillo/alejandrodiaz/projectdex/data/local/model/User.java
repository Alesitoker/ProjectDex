package es.saladillo.alejandrodiaz.projectdex.data.local.model;

public class User {

    private String email;
    private String passsword;

    public User(String email, String passsword) {
        this.email = email;
        this.passsword = passsword;
    }

    public String getEmail() {
        return email;
    }

    public String getPasssword() {
        return passsword;
    }
}
