package digi.codes.project15;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
DrawerLayout drawer;
ImageView menu;

BottomNavigationView bottom;

NavigationView navigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawer=findViewById(R.id.drawer);
        menu=findViewById(R.id.menu);
        bottom=findViewById(R.id.bottom);
        navigation=findViewById(R.id.navigation);
        menu.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               drawer.openDrawer(GravityCompat.START);
           }
       });

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment, new HomeFragment())
                    .commit();
            navigation.setCheckedItem(R.id.home);
        }
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.fragment, new HomeFragment()).commit();
        bottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.home) {
                    fm.beginTransaction().replace(R.id.fragment, new HomeFragment()).commit();
                }
                if (id==R.id.contact){
                    fm.beginTransaction().replace(R.id.fragment,new ContactFragment()).commit();
                }
                if(id==R.id.account){
                    fm.beginTransaction().replace(R.id.fragment,new AccountFragment()).commit();
                }
                if(id==R.id.profile){
                    fm.beginTransaction().replace(R.id.fragment,new ProfileFragment()).commit();
                }


                return true;
            }
        });
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                if (item.getItemId() == R.id.home1) {
                    selectedFragment = new HomeFragment();
                } else if (item.getItemId() == R.id.contact1) {
                    selectedFragment = new ContactFragment();
                } else if (item.getItemId() == R.id.account1) {
                    selectedFragment = new AccountFragment();
                } else if (item.getItemId() == R.id.profile1) {
                    selectedFragment = new ProfileFragment();
                }
                else if(item.getItemId()==R.id.social){
                    startActivity(new Intent(MainActivity.this, SocialActivity.class));
                }

                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment, selectedFragment)
                            .commit();
                }

                // Close the drawer after clicking
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }


        });
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}


