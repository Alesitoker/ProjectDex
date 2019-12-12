package es.saladillo.alejandrodiaz.projectdex.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.NavInflater;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import es.saladillo.alejandrodiaz.projectdex.R;

public class MainActivity extends AppCompatActivity implements ToolbarConfigurationInterface, DrawerLocker, setupStartDestination {

    private NavController navController;
    AppBarConfiguration appBarConfiguration;
    private DrawerLayout drawerLayout;
    private static final String PREFERENCES_FILE = "prefs";
    private static final String PREF_NAV_DRAWER_OPENED = "navdrawerOpened";
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navController = Navigation.findNavController(this, R.id.navHostFragment);
        firebaseAuth = FirebaseAuth.getInstance();
        setupNavigationGraph();
        setupsViews();


    }

    @Override
    public void setStartDestination() {
        setupNavigationGraph();
    }

    private void setupNavigationGraph() {
        int startDestinationResId = 0;
        FirebaseUser user = firebaseAuth.getCurrentUser();

        NavHostFragment navHost =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(
                        R.id.navHostFragment);

        NavController navController = navHost.getNavController();

        NavInflater navInflater = navController.getNavInflater();

        NavGraph navGraph = navInflater.inflate(R.navigation.main_navigation);

        if(user != null) {
            startDestinationResId = R.id.listPokemonFragment;
        } else {
            startDestinationResId = R.id.signInFragment;
        }

        navGraph.setStartDestination(startDestinationResId);
        navController.setGraph(navGraph);
    }

    private void setupsViews() {
        NavHostFragment navHost =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(
                        R.id.navHostFragment);

        NavController navController = navHost.getNavController();
        NavInflater navInflater = navController.getNavInflater();
        NavGraph navGraph = navInflater.inflate(R.navigation.main_navigation);
        navController.setGraph(navGraph);

        drawerLayout = ActivityCompat.requireViewById(this, R.id.drawerLayout);

        appBarConfiguration =
                new AppBarConfiguration.Builder(
                        R.id.listPokemonFragment, R.id.listTeamsFragment)
                        .setDrawerLayout(drawerLayout)
                        .build();
    }

    private void setupNavigationDrawer() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userName = getString(R.string.default_user_name);
        NavigationView navigationView =
                ActivityCompat.requireViewById(this, R.id.navigationView);

        View headerLayout = navigationView.getHeaderView(0);
        TextView lblUserName = headerLayout.findViewById(R.id.lblUserName);
        if (user != null && user.getDisplayName() != null) {
            userName = user.getDisplayName();
        }
        lblUserName.setText(getString(R.string.activity_main_navHeader_lblUserName, userName));

        NavigationUI.setupWithNavController(navigationView, navController);

        if (!readPreference()) {
            drawerLayout.openDrawer(GravityCompat.START);
            savePreference();
        }
    }

    private boolean readPreference() {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
        return sharedPref.getBoolean(PREF_NAV_DRAWER_OPENED, false);
    }

    private void savePreference() {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(PREF_NAV_DRAWER_OPENED, true);
        editor.apply();
    }

    @Override
    public void configureToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(navController.getCurrentDestination().getLabel());
        setupNavigationDrawer();
    }

    @Override
    public void setDrawerEnabled(boolean enabled) {
        if (!enabled)
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        else
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return NavigationUI.onNavDestinationSelected(item, navController) || super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
