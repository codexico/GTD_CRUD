package com.example.fk.gtd_crud;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.fk.gtd_crud.adapter.StuffAdapter;
import com.example.fk.gtd_crud.dao.StuffDAO;
import com.example.fk.gtd_crud.model.Stuff;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainListActivity extends AppCompatActivity {

    @Bind(R.id.lvStuff) ListView lvStuff;
    StuffDAO stuffDAO;
    List<Stuff> stuffsList;
    StuffAdapter stuffAdapter;

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);
        ButterKnife.bind(this);

        handleIntent(getIntent());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToAddStuffActivity();
            }
        });

        stuffDAO = new StuffDAO(this);
//        List<Stuff> stuffs = stuffDAO.getStuffList();

        stuffsList = loadFirstPage();
        stuffAdapter = new StuffAdapter(this, stuffsList);
        lvStuff.setAdapter(stuffAdapter);

//        loadPage(0);

        lvStuff.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Stuff stuff = (Stuff) lvStuff.getItemAtPosition(position);
                goToStuffItemActivity(stuff);
            }
        });

        lvStuff.setOnScrollListener(new EndlessScrollListener());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Toast.makeText(MainListActivity.this, "onNewIntent", Toast.LENGTH_SHORT).show();
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        Toast.makeText(MainListActivity.this, "handleIntent", Toast.LENGTH_SHORT).show();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //use the query to search your data somehow
            Toast.makeText(MainListActivity.this, query, Toast.LENGTH_SHORT).show();
        }
    }

    private List<Stuff> loadFirstPage() {
        List<Stuff> stuffs = stuffDAO.getStuffListByPage(0);

        if (stuffs.isEmpty()) {
            Toast.makeText(MainListActivity.this, "You don't have stuff!", Toast.LENGTH_SHORT).show();
        }
        return stuffs;
    }


    private void loadPage(int page) {
        Toast.makeText(MainListActivity.this, "Loading more stuff!", Toast.LENGTH_SHORT).show();
        List<Stuff> stuffs = stuffDAO.getStuffListByPage(page);

        if (stuffs.isEmpty()) {
            Toast.makeText(MainListActivity.this, "No stuffs to load!", Toast.LENGTH_SHORT).show();
        }

        stuffsList.addAll(stuffs);
        stuffAdapter.notifyDataSetChanged();
    }

    private void goToAddStuffActivity() {
        Intent i = new Intent(MainListActivity.this, StuffAddActivity.class);
        startActivity(i);
    }

    private void goToStuffItemActivity(Stuff stuff) {
        Intent i = new Intent(MainListActivity.this, StuffItemActivity.class);
        i.putExtra("stuff", stuff);
        startActivity(i);
    }


    public class EndlessScrollListener implements AbsListView.OnScrollListener {

        private int visibleThreshold = 3;
        private int currentPage = 0;
        private int previousTotal = 0;
        private boolean loading = false;

        public EndlessScrollListener() {
        }
        public EndlessScrollListener(int visibleThreshold) {
            this.visibleThreshold = visibleThreshold;
        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem,
                             int visibleItemCount, int totalItemCount) {
            Log.d("onScroll", "onScroll");
            if (loading) {
                Log.d("onScroll", "loading");
                if (totalItemCount > previousTotal) {
                    loading = false;
                    previousTotal = totalItemCount;
                    currentPage++;
                }
            }
            if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
                Log.d("onScroll", "load page");
                loadPage(currentPage + 1);
                loading = true;
            }
        }

        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
        }
    }


}
