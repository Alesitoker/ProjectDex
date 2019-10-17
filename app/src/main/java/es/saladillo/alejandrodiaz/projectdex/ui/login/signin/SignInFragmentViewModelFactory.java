package es.saladillo.alejandrodiaz.projectdex.ui.login.signin;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import es.saladillo.alejandrodiaz.projectdex.data.LoginRepository;

public class SignInFragmentViewModelFactory implements ViewModelProvider.Factory {

    private LoginRepository loginRepository;

    public SignInFragmentViewModelFactory(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new SignInFragmentViewModel(loginRepository);
    }
}
