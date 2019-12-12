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
import es.saladillo.alejandrodiaz.projectdex.data.local.model.UserSignin;

public class SignInFragmentViewModel extends ViewModel {

    private LoginRepository loginRepository;
    private final MutableLiveData<UserSignin> singInTrigger = new MutableLiveData<>();
    private final LiveData<Resource<String>> singInLiveData;
    private final MediatorLiveData<Event<String>> errorMessage = new MediatorLiveData<>();
    private final MediatorLiveData<Event<String>> successMessage = new MediatorLiveData<>();
    private final MediatorLiveData<Boolean> loading = new MediatorLiveData<>();

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

        loading.addSource(singInLiveData, resource -> {
            if(resource.isLoading()) {
                loading.setValue(resource.isLoading());
            } else if (!resource.isLoading()) {
                loading.setValue(false);
            }
        });
    }

    public LiveData<Event<String>> errorMessage() {
        return errorMessage;
    }

    public LiveData<Event<String>> successMessage() {
        return successMessage;
    }

    public LiveData<Boolean> getLoading() {
        return loading;
    }

    void signIn(String email, String password) {
        singInTrigger.setValue(new UserSignin(email, password));
    }
}
