package android.vuongquocthanh.noteapp.note;

import android.vuongquocthanh.noteapp.data.entity.Note;

import java.util.List;

public interface NoteContract {
    interface View{
        void displayAllNote(List<Note> listNote);
        void displayAddNewNote();
        void NoTasks(List<Note> listNote);
    }

    interface Presenter{
        void loadDataSuccess(List<Note> listNote);
        void addNewNote();
    }
}
