package es.saladillo.alejandrodiaz.projectdex.data;

import androidx.lifecycle.LiveData;

import es.saladillo.alejandrodiaz.projectdex.base.Resource;

public interface LoginRepository {
    LiveData<Resource<String>> signIn(String email, String password);
    LiveData<Resource<String>> signUp(String email, String password);

}
