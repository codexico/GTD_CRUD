package com.example.fk.gtd_crud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fk.gtd_crud.model.Stuff;
import com.example.fk.gtd_crud.model.StuffBuilder;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StuffAddActivity extends AppCompatActivity {

    @Bind(R.id.etTitle)
    EditText etTitle;

    @Bind(R.id.etDescription)
    EditText etDescription;

    @Bind(R.id.etContext)
    EditText etContext;

    @Bind(R.id.etContact)
    EditText etContact;

    @Bind(R.id.etLocation)
    EditText etLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stuff_add);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.btAddStuff)
    public void addStuff() {
        if (isStuffEmpty()) {
            Toast.makeText(StuffAddActivity.this, R.string.StuffEmpty, Toast.LENGTH_SHORT).show();
            return;
        }

        Stuff newStuff = new StuffBuilder()
                .setTitle(etTitle.getText().toString())
                .setDescription(etDescription.getText().toString())
                .setContact(etContact.getText().toString())
                .setContext(etContext.getText().toString())
                .setLocation(etLocation.getText().toString())
                .build();

        newStuff.save();
        Toast.makeText(StuffAddActivity.this, R.string.Stuff_saved, Toast.LENGTH_SHORT).show();
        goToStuffListActivity();
    }

    private void goToStuffListActivity() {
        Intent i = new Intent(StuffAddActivity.this, MainListActivity.class);
        startActivity(i);
    }

    private boolean isStuffEmpty() {
        return etTitle.getText().toString().isEmpty() &&
                etDescription.getText().toString().isEmpty();
    }
}
