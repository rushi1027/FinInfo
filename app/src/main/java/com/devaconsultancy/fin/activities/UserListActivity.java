package com.devaconsultancy.fin.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.devaconsultancy.fin.R;
import com.devaconsultancy.fin.adapter.RecyclerViewAdapter;
import com.devaconsultancy.fin.pojo.DataModal;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class UserListActivity extends AppCompatActivity {

    private Realm realm;
    private String name;
    private String age;
    private String city;

    private Toolbar toolbar;
    private DataModal modal;
    private RecyclerView recyclerView;
    private RecyclerView rvAgeRecyclerView;
    private RecyclerView rvCityRecyclerView;
    private RecyclerView rvNameRecyclerView;
    private List<DataModal> userDetailList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        //To configure realm database
        configureDatabase();
        //To set all the UI components
        setupUI();
        //To initialise all the objects
        initialiseObjects();
        // calling method to add data to Realm database..
        getAllUsers();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.all:
                //set visibility as gone
                rvAgeRecyclerView.setVisibility(View.GONE);
                rvCityRecyclerView.setVisibility(View.GONE);
                rvNameRecyclerView.setVisibility(View.GONE);
                //set visibility as visible
                recyclerView.setVisibility(View.VISIBLE);
                //To sort list
                getAllUsers();

                return true;
            case R.id.name:
                //set visibility as gone
                recyclerView.setVisibility(View.GONE);
                rvAgeRecyclerView.setVisibility(View.GONE);
                rvCityRecyclerView.setVisibility(View.GONE);
                //set visibility as visible
                rvNameRecyclerView.setVisibility(View.VISIBLE);
                //To sort list
                sortUserName();

                return true;
            case R.id.age:
                //set visibility as gone
                recyclerView.setVisibility(View.GONE);
                rvCityRecyclerView.setVisibility(View.GONE);
                rvNameRecyclerView.setVisibility(View.GONE);
                //set visibility as visible
                rvAgeRecyclerView.setVisibility(View.VISIBLE);
                //To sort list
                sortUserAge();

                return true;
            case R.id.city:
                //set visibility as gone
                recyclerView.setVisibility(View.GONE);
                rvNameRecyclerView.setVisibility(View.GONE);
                rvAgeRecyclerView.setVisibility(View.GONE);
                //set visibility as visible
                rvCityRecyclerView.setVisibility(View.VISIBLE);
                //To sort list
                sortUserCity();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * This method configures the database
     */
    public void configureDatabase() {
        Realm.init(this);
        Realm.init(getApplicationContext());

        RealmConfiguration config =
                new RealmConfiguration.Builder()
                        .allowWritesOnUiThread(true)
                        .deleteRealmIfMigrationNeeded()
                        .build();

        Realm.setDefaultConfiguration(config);
    }

    /**
     * This method initialise the UI components of the fragment
     */
    public void setupUI() {
        recyclerView = (RecyclerView) findViewById(R.id.rv_user_details);
        rvAgeRecyclerView = (RecyclerView) findViewById(R.id.rv_user_age);
        rvCityRecyclerView = (RecyclerView) findViewById(R.id.rv_user_city);
        rvNameRecyclerView = (RecyclerView) findViewById(R.id.rv_user_name);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    /**
     * This method initialises all the objects
     */
    public void initialiseObjects() {
        realm = Realm.getDefaultInstance();

    }

    /**
     * This method adds data to database
     */
    public void getAllUsers() {
        userDetailList = new ArrayList<>();

        DataModal modal = new DataModal();
        modal.setId(Long.parseLong("10"));
        modal.setName("Rushikesh");
        modal.setAge("27");
        modal.setCity("Latur");
        userDetailList.add(modal);

        DataModal modal2 = new DataModal();
        modal2.setId(Long.parseLong("11"));
        modal2.setName("Rushikesh");
        modal2.setAge("28");
        modal2.setCity("Pune");
        userDetailList.add(modal2);

        DataModal modal3 = new DataModal();
        modal3.setId(Long.parseLong("12"));
        modal3.setName("Rushikesh");
        modal3.setAge("28");
        modal3.setCity("Pune");
        userDetailList.add(modal3);

        DataModal modal4 = new DataModal();
        modal4.setId(Long.parseLong("13"));
        modal4.setName("Kailash");
        modal4.setAge("24");
        modal4.setCity("Mumbai");
        userDetailList.add(modal4);

        // on below line we are calling a method to execute a transaction.
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                realm.insert(userDetailList);
            }
        });
        //To set adapter to recyclerview
        setAdapter();
    }

    /**
     * This method sets adapter to recyclerview
     */
    public void setAdapter() {
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(UserListActivity.this, userDetailList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(UserListActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    /**
     * This method sort list by user name
     */
    public void sortUserName() {
        userDetailList = new ArrayList<>();

        DataModal modal = new DataModal();
        modal.setId(Long.parseLong("10"));
        modal.setName("Rushikesh");
        userDetailList.add(modal);

        DataModal modal2 = new DataModal();
        modal2.setId(Long.parseLong("11"));
        modal2.setName("Akash");
        userDetailList.add(modal2);

        DataModal modal3 = new DataModal();
        modal3.setId(Long.parseLong("12"));
        modal3.setName("Pooja");
        userDetailList.add(modal3);

        // on below line we are calling a method to execute a transaction.
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                realm.insert(userDetailList);
            }
        });
        //To set adapter to recyclerview
        setNameAdapter();
    }

    /**
     * This method sets name adapter to recyclerview
     */
    public void setNameAdapter() {
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(UserListActivity.this, userDetailList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(UserListActivity.this, LinearLayoutManager.VERTICAL, false);
        rvNameRecyclerView.setLayoutManager(mLayoutManager);
        rvNameRecyclerView.setItemAnimator(new DefaultItemAnimator());
        rvNameRecyclerView.setAdapter(recyclerViewAdapter);
    }

    /**
     * This method sort list by user age
     */
    public void sortUserAge() {
        userDetailList = new ArrayList<>();

        DataModal modal = new DataModal();
        modal.setId(Long.parseLong("10"));
        modal.setName("Akash");
        modal.setAge("25");
        userDetailList.add(modal);

        DataModal modal2 = new DataModal();
        modal2.setId(Long.parseLong("11"));
        modal2.setName("Pooja");
        modal2.setAge("26");
        userDetailList.add(modal2);

        DataModal modal3 = new DataModal();
        modal3.setId(Long.parseLong("12"));
        modal3.setName("Rushikesh");
        modal3.setAge("27");
        userDetailList.add(modal3);

        // on below line we are calling a method to execute a transaction.
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                realm.insert(userDetailList);
            }
        });
        //To set adapter to recyclerview
        setAgeAdapter();
    }

    /**
     * This method sets age adapter to recyclerview
     */
    public void setAgeAdapter() {
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(UserListActivity.this, userDetailList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(UserListActivity.this, LinearLayoutManager.VERTICAL, false);
        rvAgeRecyclerView.setLayoutManager(mLayoutManager);
        rvAgeRecyclerView.setItemAnimator(new DefaultItemAnimator());
        rvAgeRecyclerView.setAdapter(recyclerViewAdapter);
    }

    /**
     * This method sort list by user city
     */
    public void sortUserCity() {
        userDetailList = new ArrayList<>();

        DataModal modal = new DataModal();
        modal.setId(Long.parseLong("10"));
        modal.setName("Akash");
        modal.setAge("25");
        modal.setCity("Pimpri");
        userDetailList.add(modal);

        DataModal modal2 = new DataModal();
        modal2.setId(Long.parseLong("11"));
        modal2.setName("Pooja");
        modal2.setAge("26");
        modal2.setCity("Pune");
        userDetailList.add(modal2);

        DataModal modal3 = new DataModal();
        modal3.setId(Long.parseLong("12"));
        modal3.setName("Rushikesh");
        modal3.setAge("27");
        modal3.setCity("Latur");
        userDetailList.add(modal3);

        // on below line we are calling a method to execute a transaction.
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                realm.insert(userDetailList);
            }
        });
        //To set adapter to recyclerview
        setCityAdapter();
    }

    /**
     * This method sets age adapter to recyclerview
     */
    public void setCityAdapter() {
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(UserListActivity.this, userDetailList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(UserListActivity.this, LinearLayoutManager.VERTICAL, false);
        rvCityRecyclerView.setLayoutManager(mLayoutManager);
        rvCityRecyclerView.setItemAnimator(new DefaultItemAnimator());
        rvCityRecyclerView.setAdapter(recyclerViewAdapter);
    }
}
