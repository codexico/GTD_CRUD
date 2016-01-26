package com.example.fk.gtd_crud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

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
        String extraStuffId = intent.getStringExtra("stuffid");
        long l = Long.parseLong(extraStuffId.trim());

        oldStuff = Stuff.findById(Stuff.class, l);
        etTitle.setText(oldStuff.title);
        etDescription.setText(oldStuff.description);
        etContact.setText(oldStuff.contact);
        etContext.setText(oldStuff.context);
        etLocation.setText(oldStuff.location);

        cbDone.setChecked(oldStuff.done);
    }

    @OnClick(R.id.btAddStuff)
    public void updateStuff() {

        oldStuff.title = etTitle.getText().toString();
        oldStuff.description = etDescription.getText().toString();
        oldStuff.contact = etContact.getText().toString();
        oldStuff.context = etContext.getText().toString();
        oldStuff.location = etLocation.getText().toString();

        oldStuff.done = cbDone.isChecked();

        oldStuff.save();

        Toast.makeText(StuffItemActivity.this, "Stuff updated", Toast.LENGTH_SHORT).show();
        goToStuffListActivity();
    }

    @OnClick(R.id.btRemoveStuff)
    public void removeStuff() {
        // TODO: Undo delete with Snackbar
        oldStuff.delete();
//        oldStuff.delete();
        goToStuffListActivity();
    }

    private void goToStuffListActivity() {
        Intent i = new Intent(StuffItemActivity.this, MainListActivity.class);
        startActivity(i);
    }
}
