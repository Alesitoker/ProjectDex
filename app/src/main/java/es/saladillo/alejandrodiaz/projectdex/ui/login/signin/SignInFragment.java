package es.saladillo.alejandrodiaz.projectdex.ui.login.signin;

import android.content.Context;
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
import es.saladillo.alejandrodiaz.projectdex.databinding.FragmentSigninBinding;
import es.saladillo.alejandrodiaz.projectdex.ui.main.DrawerLocker;
import es.saladillo.alejandrodiaz.projectdex.ui.main.setupStartDestination;
import es.saladillo.alejandrodiaz.projectdex.utils.KeyboardUtils;
import es.saladillo.alejandrodiaz.projectdex.utils.SnackbarUtils;
import es.saladillo.alejandrodiaz.projectdex.utils.TextUtils;

public class SignInFragment extends Fragment {

    private FragmentSigninBinding b;
    private SignInFragmentViewModel viewModel;
    private NavController navController;
    private DrawerLocker drawerLocker;
    private setupStartDestination setupStartDestination;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            drawerLocker = (DrawerLocker) context;
            setupStartDestination = (setupStartDestination) context;
        } catch (ClassCastException e) {
            throw new ClassCastException("Listener must implement ToolbarConfigurationInterface");
        }
    }

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
        drawerLocker.setDrawerEnabled(false);
        setupViews();
        observe();
        observeErrorMessage();
        observeSuccessMessage();
    }

    private void observe() {
        viewModel.getLoading().observe(this, loading -> b.pbListPokemon.setVisibility(loading ? View.VISIBLE : View.INVISIBLE));
    }

    private void setupViews() {
        b.btnLogin.setOnClickListener(v -> signIn(b.txtEmail.getText().toString(),
                b.txtPassword.getText().toString()));
        b.lblNotRegistered.setOnClickListener(v -> navigateToSignUp());

        b.txtEmail.addTextChangedListener((TextUtils.AfterTextChanged) editable -> enableButton());
        b.txtPassword.addTextChangedListener((TextUtils.AfterTextChanged) editable -> enableButton());
    }

    private void enableButton() {
        if (b.txtEmail.getText().toString().isEmpty() || b.txtPassword.getText().toString().isEmpty()) {
            b.btnLogin.setEnabled(false);
        } else {
            b.btnLogin.setEnabled(true);
        }
    }

    private void signIn(String email, String password) {
        viewModel.signIn(email, password);
        KeyboardUtils.hideSoftKeyboard(requireActivity());
    }

    private void observeErrorMessage() {
        viewModel.errorMessage().observe(getViewLifecycleOwner(),
                new EventObserver<>(this::showMessage));
    }

    private void observeSuccessMessage() {
        viewModel.successMessage().observe(getViewLifecycleOwner(),
                new EventObserver<>(message -> {
                    setupStartDestination.setStartDestination();
//                    navigateToListPokemon();
                }));
    }

    private void navigateToListPokemon() {
        navController.navigate(R.id.actionSignInToListPokemon);
    }

    private void showMessage(String message) {
        SnackbarUtils.snackbar(requireView(), message);
    }

    private void navigateToSignUp() {
        navController.navigate(R.id.actionSignInToSignUp);
    }
}
