package es.saladillo.alejandrodiaz.projectdex.ui.login;

public interface LoginRepository {
    void signIn(String email, String password);
    void signUp(String email, String password);

}
