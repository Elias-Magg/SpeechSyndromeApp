package com.example.speechsyndromeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.RecursiveAction;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private RecyclerView mRecyclerView;
    private MainRecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayouteManager;
    private ArrayList<MainRecyclerItem> mExampleList;
    private DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.dot_menu);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        ArrayList<String> tmp = new ArrayList<>();

        mExampleList = new ArrayList<>();
        mExampleList.add(new MainRecyclerButton("Line 1",tmp));
        mExampleList.add(new MainRecyclerLettering("------------a-------------"));
        mExampleList.add(new MainRecyclerButton("Line 1",tmp));
        mExampleList.add(new MainRecyclerLettering("------------a-------------"));
        mExampleList.add(new MainRecyclerButton("Line 1",tmp));
        mExampleList.add(new MainRecyclerLettering("------------a-------------"));
        mExampleList.add(new MainRecyclerButton("Line 1",tmp));
        mExampleList.add(new MainRecyclerButton("Line 1",tmp));
        mExampleList.add(new MainRecyclerButton("Line 1",tmp));
        mExampleList.add(new MainRecyclerButton("Line 1",tmp));
        mExampleList.add(new MainRecyclerButton("Line 1",tmp));
        mExampleList.add(new MainRecyclerButton("Line 1",tmp));
        mExampleList.add(new MainRecyclerButton("Line 1",tmp));
        mExampleList.add(new MainRecyclerButton("Line 1",tmp));
        mExampleList.add(new MainRecyclerButton("Line 1",tmp));
        mExampleList.add(new MainRecyclerButton("Line 1",tmp));
        mExampleList.add(new MainRecyclerButton("Line 1",tmp));
        mExampleList.add(new MainRecyclerButton("Line 1",tmp));
        mExampleList.add(new MainRecyclerButton("Line 1",tmp));
        mExampleList.add(new MainRecyclerButton("Line 1",tmp));

        buildRecyclerView();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.search_bar,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()== R.id.search_bar){
            if(mExampleList.get(0) instanceof MainRecyclerSearch) removeItem(0);
            else insertItem(0, new MainRecyclerSearch("Write Something Here !"));
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.Sources:
                Toast.makeText(this,"Sources",Toast.LENGTH_LONG).show();
                break;
            case R.id.AboutUs:
                Toast.makeText(this,"AboutUs",Toast.LENGTH_LONG).show();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void buildRecyclerView(){
        mRecyclerView = findViewById(R.id.main_recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mLayouteManager = new LinearLayoutManager(this);
        mAdapter = new MainRecyclerViewAdapter(mExampleList);

        mRecyclerView.setLayoutManager(mLayouteManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new MainRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                mExampleList.get(position).click();
                mAdapter.notifyItemChanged(position);
            }
        });

    }

    public void insertItem(int position, MainRecyclerItem item) {
        mExampleList.add(position,item);
        //notifyDataChanged for no animation
        mAdapter.notifyItemInserted(position);
        mLayouteManager.scrollToPosition(0);
    }

    public void removeItem(int position) {
        mExampleList.remove(position);
        mAdapter.notifyItemRemoved(position);
    }

}