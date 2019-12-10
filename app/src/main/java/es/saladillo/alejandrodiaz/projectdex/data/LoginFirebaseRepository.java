package es.saladillo.alejandrodiaz.projectdex.data;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

import es.saladillo.alejandrodiaz.projectdex.base.Resource;

public class LoginFirebaseRepository implements LoginRepository {

    private FirebaseAuth mAuth;

    public LoginFirebaseRepository() {
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public LiveData<Resource<String>> signIn(String email, String password) {
        MutableLiveData<Resource<String>> result = new MutableLiveData<>();
        // TODO: añadir loading al signin.
        result.postValue(Resource.loading());
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        result.postValue(Resource.success("login"));
                    } else {
                        try {
                            throw task.getException();
                        } catch (FirebaseAuthInvalidCredentialsException e) {
                            result.postValue(Resource.error(new Exception("Incorrect email or password.")));
                        } catch (Exception e) {
                            result.postValue(Resource.error(new Exception(e.getMessage())));
                        }
                    }
                });
        return result;
    }

    @Override
    public LiveData<Resource<String>> signUp(String email, String password) {
        MutableLiveData<Resource<String>> result = new MutableLiveData<>();
        // TODO: añadir loading al signup.
        result.postValue(Resource.loading());
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        result.postValue(Resource.success("Your account has been successfully created."));
                    } else {
                        try {
                            throw task.getException();
                        } catch(FirebaseAuthUserCollisionException e) {
                            result.postValue(Resource.error(new Exception("The email address is already in use.")));
                        } catch(Exception e) {
                            result.postValue(Resource.error(new Exception(e.getMessage())));
                        }
                    }
                });
        return result;
    }
}
