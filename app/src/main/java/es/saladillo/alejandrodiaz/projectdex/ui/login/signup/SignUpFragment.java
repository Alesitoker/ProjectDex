package es.saladillo.alejandrodiaz.projectdex.ui.login.signup;

import android.os.Bundle;
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
import es.saladillo.alejandrodiaz.projectdex.base.EventObserver;
import es.saladillo.alejandrodiaz.projectdex.data.LoginFirebaseRepository;
import es.saladillo.alejandrodiaz.projectdex.databinding.FragmentSignupBinding;
import es.saladillo.alejandrodiaz.projectdex.utils.KeyboardUtils;
import es.saladillo.alejandrodiaz.projectdex.utils.SnackbarUtils;
import es.saladillo.alejandrodiaz.projectdex.utils.TextUtils;

import static es.saladillo.alejandrodiaz.projectdex.utils.ValidationUtils.isValidEmail;

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
        observe();
        observeErrorMessage();
        observeSuccessMessage();
    }

    private void observe() {
        viewModel.getLoading().observe(this, loading -> b.pbListPokemon.setVisibility(loading ? View.VISIBLE : View.INVISIBLE));
    }

    private void setupViews() {
        b.btnSignUp.setOnClickListener(v -> signUp(b.txtEmail.getText().toString(),
                b.txtPassword.getText().toString(), b.txtUserName.getText().toString()));
        b.lblReturnSignIn.setOnClickListener(v -> ReturnToSignIn());

        b.txtEmail.addTextChangedListener((TextUtils.AfterTextChanged) editable -> enableButton());
        b.txtUserName.addTextChangedListener((TextUtils.AfterTextChanged) editable -> enableButton());
        b.txtPassword.addTextChangedListener((TextUtils.AfterTextChanged) editable -> enableButton());
    }

    private void enableButton() {
        if (b.txtEmail.getText().toString().isEmpty() || b.txtPassword.getText().toString().isEmpty()) {
            b.btnSignUp.setEnabled(false);
        } else {
            b.btnSignUp.setEnabled(true);
        }
    }

    private void signUp(String email, String password, String userName) {
        if (validateForm()) {
            viewModel.singUp(email, password, userName);
        }
        KeyboardUtils.hideSoftKeyboard(requireActivity());
    }

    private void observeErrorMessage() {
        viewModel.errorMessage().observe(getViewLifecycleOwner(),
                new EventObserver<>(this::showMessage));
    }

    private void observeSuccessMessage() {
        viewModel.successMessage().observe(getViewLifecycleOwner(),
                new EventObserver<>(this::signUpSuccessful));
    }

    private boolean validateForm() {
        boolean validE = false, validP = false, validName = false;

        if (checkEmail()) {
            validE = true;
        }

        if (checkPassword()) {
            validP = true;
        }

        if (!b.txtUserName.getText().toString().isEmpty()) {
            validName = true;
            b.txtLUserName.setErrorEnabled(false);
        } else {
            b.txtLUserName.setError(getString(R.string.error_invalid_username));
        }

        return validE && validP && validName;
    }

    private boolean checkEmail() {
        boolean valid = false;
        if (!isValidEmail(b.txtEmail.getText().toString())) {
            b.txtLEmail.setError(getString(R.string.error_invalid_email));
        } else {
            b.txtLEmail.setErrorEnabled(false);
            valid = true;
        }
        return valid;
    }

    private boolean checkPassword() {
        boolean valid = false;
        String password = b.txtPassword.getText().toString();
        if (password.length() < 6) {
            b.txtLPassword.setError(getString(R.string.error_weak_password));
        } else if (!password.matches("\\S+.*\\S+")) {
            b.txtLPassword.setError(getString(R.string.error_blankspace_password));
        } else if (!password.matches("(\\w|\\p{Punct}|\\p{Blank})+")) {
            b.txtLPassword.setError(getString(R.string.error_character_password));
        } else {
            b.txtLPassword.setErrorEnabled(false);
            valid = true;
        }
        return valid;
    }

    private void showMessage(String message) {
        SnackbarUtils.snackbar(requireView(), message);
    }

    private void signUpSuccessful(String message) {
        showMessage(message);
        ReturnToSignIn();
    }

    private void ReturnToSignIn() {
        navController.navigateUp();
    }
}
