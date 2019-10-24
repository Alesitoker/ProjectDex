package es.saladillo.alejandrodiaz.projectdex.ui.login.signin;

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

import es.saladillo.alejandrodiaz.projectdex.R;
import es.saladillo.alejandrodiaz.projectdex.ui.login.LoginFirebaseRepository;
import es.saladillo.alejandrodiaz.projectdex.databinding.FragmentSigninBinding;

public class SignInFragment extends Fragment {

    private FragmentSigninBinding b;
    private SignInFragmentViewModel viewModel;
    private NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        b = FragmentSigninBinding.inflate(inflater, container, false);
        return b.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this, new SignInFragmentViewModelFactory(
                new LoginFirebaseRepository())).get(SignInFragmentViewModel.class);
        navController = NavHostFragment.findNavController(this);
        setupViews();
    }

    private void setupViews() {
        b.btnLogin.setOnClickListener(v -> signIn(b.txtEmail.getText().toString().replaceAll("\\s+",""),
                b.txtPassword.getText().toString().replaceAll("\\s+","")));
        b.lblNotRegistered.setOnClickListener(v -> navigateToSignUp());
    }

    private void signIn(String email, String password) {
        if (email.isEmpty() || password.isEmpty()) {
            Log.d("agua", "Estoy vacio");
        } else {
            viewModel.signIn(email, password);
        }
    }

    private void navigateToSignUp() {
        navController.navigate(R.id.actionSignIntoSignUp);
    }
}
