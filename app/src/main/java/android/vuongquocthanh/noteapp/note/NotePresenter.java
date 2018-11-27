package android.vuongquocthanh.noteapp.note;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.vuongquocthanh.noteapp.adapter.AdapterNote;
import android.vuongquocthanh.noteapp.data.dao.DAONote;
import android.vuongquocthanh.noteapp.data.database.DatabaseHelper;
import android.vuongquocthanh.noteapp.data.entity.Note;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class NotePresenter implements NoteContract.Presenter {

    private NoteContract.View noteContractView;

    public NotePresenter(NoteContract.View noteContractView) {
        this.noteContractView = noteContractView;
    }

    public void loadDataFromDatabase(Context context, DatabaseHelper databaseHelper, DAONote daoNote){
        databaseHelper = new DatabaseHelper(context);
        daoNote = new DAONote(databaseHelper);
        List<Note> listNote = daoNote.getAllNote();
        Log.e("listAllTasks", listNote.size()+"");
        loadDataSuccess(listNote);
    }

    public void loadNoteCompletedFromDatabase(Context context, DatabaseHelper databaseHelper, DAONote daoNote){
        databaseHelper = new DatabaseHelper(context);
        daoNote = new DAONote(databaseHelper);
        List<Note> listNoteCompleted = new ArrayList<>();
        listNoteCompleted.addAll(daoNote.getAllNoteCompleted());
        Log.e("completedsize", listNoteCompleted.size()+"");
        loadDataSuccess(listNoteCompleted);
    }

    public void loadNoteActiveFromDatabase(Context context, DatabaseHelper databaseHelper, DAONote daoNote){
        databaseHelper = new DatabaseHelper(context);
        daoNote = new DAONote(databaseHelper);
        List<Note> listNoteActive = new ArrayList<>();
        listNoteActive.addAll(daoNote.getAllNoteNotCompleted());
        Log.e("completedsize", listNoteActive.size()+"");
        loadDataSuccess(listNoteActive);
    }

    public void clearNoteCompleted(Context context, DatabaseHelper databaseHelper, DAONote daoNote){
        databaseHelper = new DatabaseHelper(context);
        daoNote = new DAONote(databaseHelper);
        daoNote.deleteNoteCompleted(1);

    }

    @Override
    public void loadDataSuccess(List<Note> listNote) {
        noteContractView.displayAllNote(listNote);
    }

    @Override
    public void addNewNote() {
        noteContractView.displayAddNewNote();
    }
}
