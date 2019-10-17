package es.saladillo.alejandrodiaz.projectdex.ui.login.signup;

import androidx.lifecycle.ViewModel;

import es.saladillo.alejandrodiaz.projectdex.data.LoginRepository;

public class SignUpFragmentViewModel extends ViewModel {

    private LoginRepository loginRepository;

    public SignUpFragmentViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    void singUp(String email, String password) {
        loginRepository.signUp(email, password);
    }
}
