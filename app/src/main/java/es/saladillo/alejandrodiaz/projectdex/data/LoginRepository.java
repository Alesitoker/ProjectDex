package es.saladillo.alejandrodiaz.projectdex.data;

public interface LoginRepository {
    void signIn(String email, String password);
    void signUp(String email, String password);

}
