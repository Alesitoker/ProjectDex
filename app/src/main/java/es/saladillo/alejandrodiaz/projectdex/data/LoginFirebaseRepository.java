package es.saladillo.alejandrodiaz.projectdex.data;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;

public class LoginFirebaseRepository implements LoginRepository {

    private FirebaseAuth mAuth;

    public LoginFirebaseRepository() {
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void signIn(String email, String password) {
        // TODO: Quitar log, hacer mensajes para la interfaz de usuario.
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Log.d("tagL", "signInWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        // navegar completo.
                    } else {
                        Log.w("tagL", "signInWithEmail:failure", task.getException());
//                        Toast.makeText(requireContext(), "Authentication failed.",
//                                Toast.LENGTH_SHORT).show();
//                            updateUI(null);
                        try {
                            throw task.getException();
                        } catch (FirebaseAuthInvalidCredentialsException e) {
//                            SnackbarUtils.snackbar(requireView(), "Incorrect email or password.");
                        } catch (Exception e) {
                            Log.e("tagL", e.getMessage());
                        }
                    }

                    if (!task.isSuccessful()) {
//                        Toast.makeText(requireContext(), "Muy bieneeesss he fallado",
//                                Toast.LENGTH_SHORT).show();
//                            mStatusTextView.setText(R.string.auth_failed);
                    }
//                        hideProgressDialog();
                });
    }

    @Override
    public void signUp(String email, String password) {

    }
}
