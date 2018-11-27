package android.vuongquocthanh.noteapp.statistics;

import android.content.Context;
import android.vuongquocthanh.noteapp.data.dao.DAONote;
import android.vuongquocthanh.noteapp.data.database.DatabaseHelper;

public interface StatisticContract {
    interface View{
        void showStatistics(int activeNote, int completedNote);
        void showStatisticsNoneNote(String message);
    }

    interface Presenter{
    }
}
