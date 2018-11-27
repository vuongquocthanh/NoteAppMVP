package android.vuongquocthanh.noteapp.notedetail;

import android.content.Context;
import android.vuongquocthanh.noteapp.data.dao.DAONote;
import android.vuongquocthanh.noteapp.data.database.DatabaseHelper;
import android.vuongquocthanh.noteapp.data.entity.Note;

public interface NoteDetailContract {
    interface View {
        void deleteSuccessNote();

        void editSuccessNote();

        void editFailure();
    }

    interface Presenter {
        void deleteNote(Context context, DatabaseHelper databaseHelper, DAONote daoNote, Note note);

        void editNote(Context context, DatabaseHelper databaseHelper, DAONote daoNote, int id, String title, String content);

    }
}
