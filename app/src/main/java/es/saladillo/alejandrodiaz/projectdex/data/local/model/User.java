package es.saladillo.alejandrodiaz.projectdex.data.local.model;

public class User {

    private String email;
    private String passsword;
    private String userName;

    public User(String email, String passsword, String userName) {
        this.email = email;
        this.passsword = passsword;
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPasssword() {
        return passsword;
    }

    public String getUserName() {
        return userName;
    }
}
