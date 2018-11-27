package android.vuongquocthanh.noteapp.note;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.vuongquocthanh.noteapp.R;
import android.vuongquocthanh.noteapp.statistics.Statistics_Fragment;


public class NoteActivity extends AppCompatActivity{
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    Fragment fragment = null;
    private NavigationView navView;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        initViews();

        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.START);
            }
        });
        toolbar.setTitle("Note App");
        toolbar.setTitleTextColor(getColor(R.color.colorTitleToolBar));
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameContent, new Note_Fragment());
        ft.commit();
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                displayFragment(menuItem.getItemId());
                drawerLayout.closeDrawer(Gravity.START);
                return false;
            }
        });
    }

    private void displayFragment(int item){

        switch (item){
            case R.id.menu_tasks_act:
                fragment = new Note_Fragment();
                break;
            case R.id.menu_statistics:
                fragment = new Statistics_Fragment();
                break;
            default:
                fragment = new Note_Fragment();
                break;
        }
        if (fragment!=null){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frameContent, fragment);
            ft.commit();
        }
    }

    private void initViews(){
        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        navView = findViewById(R.id.nav_view);
    }
}
