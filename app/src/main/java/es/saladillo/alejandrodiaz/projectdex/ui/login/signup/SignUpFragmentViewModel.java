package es.saladillo.alejandrodiaz.projectdex.ui.login.signup;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import es.saladillo.alejandrodiaz.projectdex.base.Event;
import es.saladillo.alejandrodiaz.projectdex.base.Resource;
import es.saladillo.alejandrodiaz.projectdex.data.local.model.User;
import es.saladillo.alejandrodiaz.projectdex.data.LoginRepository;

public class SignUpFragmentViewModel extends ViewModel {

    private LoginRepository loginRepository;

    private final MutableLiveData<User> singUpTrigger = new MutableLiveData<>();
    private final LiveData<Resource<String>> singUpLiveData;
    private final MediatorLiveData<Event<String>> errorMessage = new MediatorLiveData<>();
    private final MediatorLiveData<Event<String>> successMessage = new MediatorLiveData<>();

    public SignUpFragmentViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
        singUpLiveData = Transformations.switchMap(singUpTrigger, user ->
                loginRepository.signUp(user.getEmail(), user.getPasssword()));

        setupErrorMessage();
        setupSuccessMessage();
    }

    private void setupErrorMessage() {
        errorMessage.addSource(singUpLiveData, resource -> {
            if (resource.hasError()) {
                errorMessage.setValue(new Event<>(resource.getException().getMessage()));
            }
        });
    }

    private void setupSuccessMessage() {
        successMessage.addSource(singUpLiveData, resource -> {
            if (resource.hasSuccess()) {
                successMessage.setValue(new Event<>(resource.getData()));
            }
        });
    }

    LiveData<Event<String>> errorMessage() {
        return errorMessage;
    }

    LiveData<Event<String>> successMessage() {
        return successMessage;
    }

    void singUp(String email, String password) {
        singUpTrigger.setValue(new User(email, password));
    }
}
