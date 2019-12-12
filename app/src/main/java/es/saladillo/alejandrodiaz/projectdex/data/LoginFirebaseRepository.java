package es.saladillo.alejandrodiaz.projectdex.data;


import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

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
    public LiveData<Resource<String>> signUp(String email, String password, String userName) {
        MutableLiveData<Resource<String>> result = new MutableLiveData<>();
        // TODO: añadir loading al signup.
        result.postValue(Resource.loading());
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        addUserNameToUser(task.getResult().getUser(), userName);
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

    private void addUserNameToUser(FirebaseUser user, String username) {
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(username)
                .build();
        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("user", "User profile updated.");
                        }
                    }
                });
    }
}
