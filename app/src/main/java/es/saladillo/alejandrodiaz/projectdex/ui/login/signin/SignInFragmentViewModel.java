package es.saladillo.alejandrodiaz.projectdex.ui.login.signin;

import androidx.lifecycle.ViewModel;

import es.saladillo.alejandrodiaz.projectdex.ui.login.LoginRepository;

public class SignInFragmentViewModel extends ViewModel {

    private LoginRepository loginRepository;

    public SignInFragmentViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    void signIn(String email, String password) {
        loginRepository.signIn(email, password);
    }
}
