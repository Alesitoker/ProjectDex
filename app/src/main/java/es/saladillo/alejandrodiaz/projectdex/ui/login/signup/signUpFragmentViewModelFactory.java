package es.saladillo.alejandrodiaz.projectdex.ui.login.signup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import es.saladillo.alejandrodiaz.projectdex.data.LoginRepository;

public class signUpFragmentViewModelFactory implements ViewModelProvider.Factory {

    private LoginRepository loginRepository;

    public signUpFragmentViewModelFactory(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new SignUpFragmentViewModel(loginRepository);
    }
}
