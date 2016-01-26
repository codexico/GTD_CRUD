package com.example.fk.gtd_crud;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
//import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.fk.gtd_crud.adapter.StuffAdapter;
import com.example.fk.gtd_crud.model.Stuff;
//import com.example.fk.gtd_crud.model.StuffBuilder;
//import com.orm.query.Select;

import java.util.List;

public class MainListActivity extends AppCompatActivity {

    private ListView lvStuff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToAddStuffActivity();
            }
        });

        long stuffCount = Stuff.count(Stuff.class, null, null);
        if (stuffCount == 0) {
            Toast.makeText(MainListActivity.this, "You don't have stuff!", Toast.LENGTH_SHORT).show();
        }

        lvStuff = (ListView) findViewById(R.id.lvStuff);

        List<Stuff> allStuffs = Stuff.listAll(Stuff.class);
        lvStuff.setAdapter(new StuffAdapter(this, allStuffs));

        // SugarORM starts the index with 1, I think this is causing errors when trying to
        // make pagination
//        List<Stuff> firstpage = Select.from(Stuff.class)
//                .where("")
//                .limit("8")
//                .list();
//        lvStuff.setAdapter(new StuffAdapter(this, firstpage));

        lvStuff.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Stuff stuff = (Stuff) lvStuff.getItemAtPosition(position);
                goToStuffItemActivity(stuff.getId());
            }
        });
    }

    private void goToAddStuffActivity() {
        Intent i = new Intent(MainListActivity.this, StuffAddActivity.class);
        startActivity(i);
    }

    private void goToStuffItemActivity(long stuff_id) {
        Intent i = new Intent(MainListActivity.this, StuffItemActivity.class);
        i.putExtra("stuffid", Long.toString(stuff_id));
        startActivity(i);
    }

}
