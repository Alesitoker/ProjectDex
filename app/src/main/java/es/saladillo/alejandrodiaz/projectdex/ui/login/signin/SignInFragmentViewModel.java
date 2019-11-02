package es.saladillo.alejandrodiaz.projectdex.ui.login.signin;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import es.saladillo.alejandrodiaz.projectdex.base.Event;
import es.saladillo.alejandrodiaz.projectdex.base.Resource;
import es.saladillo.alejandrodiaz.projectdex.data.local.model.User;
import es.saladillo.alejandrodiaz.projectdex.data.LoginRepository;

public class SignInFragmentViewModel extends ViewModel {

    private LoginRepository loginRepository;
    private final MutableLiveData<User> singInTrigger = new MutableLiveData<>();
    private final LiveData<Resource<String>> singInLiveData;
    private final MediatorLiveData<Event<String>> errorMessage = new MediatorLiveData<>();
    private final MediatorLiveData<Event<String>> successMessage = new MediatorLiveData<>();

    public SignInFragmentViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
        singInLiveData = Transformations.switchMap(singInTrigger, user ->
                loginRepository.signIn(user.getEmail(), user.getPasssword()));

        setupErrorMessage();
        setupSuccessMessage();
    }

    private void setupErrorMessage() {
        errorMessage.addSource(singInLiveData, resource -> {
            if(resource.hasError()) {
                errorMessage.setValue(new Event<>(resource.getException().getMessage()));
            }
        });
    }

    private void setupSuccessMessage() {
        successMessage.addSource(singInLiveData, resource -> {
            if(resource.hasSuccess()) {
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

    void signIn(String email, String password) {
        singInTrigger.setValue(new User(email, password));
    }
}
