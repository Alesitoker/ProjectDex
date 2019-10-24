package es.saladillo.alejandrodiaz.projectdex.ui.login.signup;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import es.saladillo.alejandrodiaz.projectdex.ui.login.LoginFirebaseRepository;
import es.saladillo.alejandrodiaz.projectdex.databinding.FragmentSignupBinding;

public class SignUpFragment extends Fragment {

    private FragmentSignupBinding b;
    private SignUpFragmentViewModel viewModel;
    private NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        b = FragmentSignupBinding.inflate(inflater, container, false);
        return b.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this, new SignUpFragmentViewModelFactory(
                new LoginFirebaseRepository())).get(SignUpFragmentViewModel.class);
        navController = NavHostFragment.findNavController(this);
        setupViews();
    }

    private void setupViews() {
        b.btnSignUp.setOnClickListener(v -> signUp(b.txtEmail.getText().toString().replaceAll("\\s+",""),
                b.txtPassword.getText().toString().replaceAll("\\s+","")));
        b.lblReturnSignIn.setOnClickListener(v -> ReturnToSignIn());
    }

    private void signUp(String email, String password) {
        if (email.isEmpty() || password.isEmpty()) {
            Log.d("agua", "Estoy vacio");
        } else {
            viewModel.singUp(email, password);
        }

    }

    private void ReturnToSignIn() {
        navController.navigateUp();
    }
}
