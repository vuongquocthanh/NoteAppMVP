package android.vuongquocthanh.noteapp.addnote;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.vuongquocthanh.noteapp.R;
import android.vuongquocthanh.noteapp.data.dao.DAONote;
import android.vuongquocthanh.noteapp.data.database.DatabaseHelper;
import android.vuongquocthanh.noteapp.note.NoteActivity;
import android.widget.EditText;

public class AddNoteActivity extends AppCompatActivity implements AddNoteContract.View{

    private Toolbar toolbarAddNote;
    private EditText edTitle;
    private EditText edContent;
    private FloatingActionButton fabSave;

    private DatabaseHelper databaseHelper;
    private DAONote daoNote;
    private AddNotePresenter addNotePresenter = new AddNotePresenter(this);

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        initViews();
        fabSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = edTitle.getText().toString().trim();
                String content = edContent.getText().toString().trim();
                addNotePresenter.checkData(title, content);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initViews(){
        toolbarAddNote = (Toolbar) findViewById(R.id.toolbarAddNote);
        edTitle = (EditText) findViewById(R.id.edTitle);
        edContent = (EditText) findViewById(R.id.edContent);
        fabSave = (FloatingActionButton) findViewById(R.id.fabSave);
        databaseHelper = new DatabaseHelper(this);
        daoNote = new DAONote(databaseHelper);
        toolbarAddNote.setTitle("Add Note");
        setSupportActionBar(toolbarAddNote);
        toolbarAddNote.setTitleTextColor(getColor(R.color.colorTitleToolBar));
        toolbarAddNote.setNavigationIcon(R.drawable.ic_back);
        toolbarAddNote.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void checkSuccessData() {
        String title = edTitle.getText().toString().trim();
        String content = edContent.getText().toString().trim();
        addNotePresenter.addDataToDatabase(AddNoteActivity.this, databaseHelper, daoNote, title, content);
        Log.e("datababseSize", daoNote.getAllNote().size()+"");
        startActivity(new Intent(AddNoteActivity.this, NoteActivity.class));
        finish();
    }

    @Override
    public void checkFailureData() {

    }
}
