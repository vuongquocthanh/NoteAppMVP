package android.vuongquocthanh.noteapp.statistics;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.vuongquocthanh.noteapp.R;
import android.vuongquocthanh.noteapp.data.dao.DAONote;
import android.vuongquocthanh.noteapp.data.database.DatabaseHelper;
import android.widget.TextView;

public class Statistics_Fragment extends Fragment implements StatisticContract.View {
    View viewFragmentStatistic;
    private TextView statistics;
    private StatisticsPresenter statisticsPresenter = new StatisticsPresenter(this);
    private DatabaseHelper databaseHelper;
    private DAONote daoNote;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewFragmentStatistic = inflater.inflate(R.layout.fragment_statistics, container, false);
        databaseHelper = new DatabaseHelper(viewFragmentStatistic.getContext());
        daoNote = new DAONote(databaseHelper);
        initViews();
        statisticsPresenter.loadStatistic(viewFragmentStatistic.getContext(), databaseHelper, daoNote);
        return viewFragmentStatistic;
    }

    @Override
    public void showStatistics(int activeNote, int completedNote) {
        String displayStatistic = "Active Note: "+activeNote+"\nCompleted Note: "+completedNote;
        statistics.setText(displayStatistic);
    }

    @Override
    public void showStatisticsNoneNote(String message) {
        statistics.setText(message);
    }

    private void initViews(){
        statistics = (TextView) viewFragmentStatistic.findViewById(R.id.statistics);
    }
}
