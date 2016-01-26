package com.example.fk.gtd_crud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fk.gtd_crud.dao.StuffDAO;
import com.example.fk.gtd_crud.model.Stuff;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StuffItemActivity extends AppCompatActivity {

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

    @Bind(R.id.cbDone)
    CheckBox cbDone;

    Stuff oldStuff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stuff_item);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        oldStuff = (Stuff) intent.getSerializableExtra("stuff");

        etTitle.setText(oldStuff.getTitle());
        etDescription.setText(oldStuff.getDescription());
        etContact.setText(oldStuff.getContact());
        etContext.setText(oldStuff.getContext());
        etLocation.setText(oldStuff.getLocation());

        cbDone.setChecked(oldStuff.isDone());
    }

    @OnClick(R.id.btAddStuff)
    public void updateStuff(View v) {

        oldStuff.setTitle(etTitle.getText().toString());
        oldStuff.setDescription(etDescription.getText().toString());
        oldStuff.setContact(etContact.getText().toString());
        oldStuff.setContext(etContext.getText().toString());
        oldStuff.setLocation(etLocation.getText().toString());

        oldStuff.setIsDone((cbDone.isChecked()) ? 1 : 0);

        StuffDAO stuffDAO = new StuffDAO(v.getContext());
        String message = getString(stuffDAO.update(oldStuff));

        Toast.makeText(StuffItemActivity.this, message, Toast.LENGTH_SHORT).show();
        goToStuffListActivity();
    }

    @OnClick(R.id.btRemoveStuff)
    public void removeStuff(View v) {
        // TODO: Undo delete with Snackbar

        StuffDAO stuffDAO = new StuffDAO(v.getContext());
        String message = getString(stuffDAO.delete(oldStuff));

        Toast.makeText(StuffItemActivity.this, message, Toast.LENGTH_SHORT).show();
        goToStuffListActivity();
    }

    private void goToStuffListActivity() {
        Intent i = new Intent(StuffItemActivity.this, MainListActivity.class);
        startActivity(i);
    }
}
