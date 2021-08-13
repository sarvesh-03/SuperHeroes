package com.example.superheroes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.navigation.NavigationView;

import java.util.Objects;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements BlankFragment.Listener {

    private Details details=new Details();
    private NavController navController;
    private DrawerLayout drawerLayout;
    private AppBarConfiguration appBarConfiguration;
    private NavigationView navigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationView=findViewById(R.id.navigationView);
        navController = ((NavHostFragment) Objects.requireNonNull(getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView))).getNavController();
        drawerLayout=findViewById(R.id.drawerLayout);
        appBarConfiguration=new AppBarConfiguration.Builder(R.id.blankFragment).build();
        NavigationUI.setupActionBarWithNavController(this,navController,drawerLayout);
        NavigationUI.setupWithNavController(navigationView,navController);
        //navigationView.se





        //getSupportFragmentManager().beginTransaction().replace(R.id.container,new BlankFragment()).commit();
    }
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController=((NavHostFragment) Objects.requireNonNull(getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView))).getNavController();
        return NavigationUI.navigateUp(navController,drawerLayout);
    }


    @Override
    public void inputSent(superHero hero) {
        Bundle bundle=new Bundle();
        bundle.putSerializable("hero",hero);
        if(navController.getCurrentDestination().getId()==R.id.blankFragment)
        navController.navigate(R.id.action_blankFragment_to_details,bundle);
        if(navController.getCurrentDestination().getId()==R.id.male)
            navController.navigate(R.id.action_male_to_details,bundle);
        if(navController.getCurrentDestination().getId()==R.id.female)
            navController.navigate(R.id.action_female_to_details,bundle);
        if(navController.getCurrentDestination().getId()==R.id.favourite)
            navController.navigate(R.id.action_favourite_to_details,bundle);
        Log.v("Main",hero.getName());
        //details.SetHero(hero);

    }
}