package android.vuongquocthanh.noteapp.note;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.vuongquocthanh.noteapp.addnote.AddNoteActivity;
import android.vuongquocthanh.noteapp.R;
import android.vuongquocthanh.noteapp.adapter.AdapterNote;
import android.vuongquocthanh.noteapp.data.dao.DAONote;
import android.vuongquocthanh.noteapp.data.database.DatabaseHelper;
import android.vuongquocthanh.noteapp.data.entity.Note;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Note_Fragment extends Fragment implements NoteContract.View {

    private RecyclerView recyclerViewNoteList;
    private LinearLayout noTasks;
    private FloatingActionButton fabButton;
    private ImageView imgSort, imgMore;
    View viewFragmentNote;
    private DatabaseHelper databaseHelper;
    private DAONote daoNote;
    private TextView tvAllNote;
    private NotePresenter notePresenter = new NotePresenter(this);
    List<Note> mList = new ArrayList<>();
    private AdapterNote adapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewFragmentNote = inflater.inflate(R.layout.fragment_notelist, container, false);
        databaseHelper = new DatabaseHelper(viewFragmentNote.getContext());
        initViews();
        daoNote = new DAONote(databaseHelper);
        adapter = new AdapterNote(mList);
        recyclerViewNoteList.setAdapter(adapter);
        notePresenter.loadDataFromDatabase(viewFragmentNote.getContext(), databaseHelper, daoNote);
        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notePresenter.addNewNote();
            }
        });


        imgSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(viewFragmentNote.getContext(), imgSort);
                popupMenu.getMenuInflater().inflate(R.menu.menu_sort, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.menu_all:
                                mList.clear();
                                notePresenter.loadDataFromDatabase(viewFragmentNote.getContext(), databaseHelper, daoNote);
                                tvAllNote.setText("ALL NOTES");
                                return true;

                            case R.id.menu_active:
                                mList.clear();
                                notePresenter.loadNoteActiveFromDatabase(viewFragmentNote.getContext(), databaseHelper, daoNote);
                                tvAllNote.setText("ACTIVE NOTES");
                                return true;

                            case R.id.menu_completed:
                                mList.clear();
                                notePresenter.loadNoteCompletedFromDatabase(viewFragmentNote.getContext(), databaseHelper, daoNote);
                                tvAllNote.setText("COMPLETED NOTES");
                                return true;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });

        imgMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(viewFragmentNote.getContext(), imgMore);
                popupMenu.getMenuInflater().inflate(R.menu.menu_more, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.menu_clear_comple:
                                notePresenter.clearNoteCompleted(viewFragmentNote.getContext(), databaseHelper, daoNote);
                                for(int i=0; i<mList.size(); i++){
                                    if (mList.get(i).getCompleted()==1){
                                        mList.remove(i);
                                        adapter.notifyDataSetChanged();
                                    }
                                }
                                return true;
                            case R.id.menu_refresh:

                                return true;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
        return viewFragmentNote;
    }

    @Override
    public void displayAllNote(List<Note> listNote) {
        mList.addAll(listNote);
        adapter.notifyDataSetChanged();
        NoTasks(listNote);
    }

    @Override
    public void displayAddNewNote() {
        startActivity(new Intent(viewFragmentNote.getContext(), AddNoteActivity.class));
    }

    @Override
    public void NoTasks(List<Note> listNote) {
        if (listNote.size()>0){
            noTasks.setVisibility(View.INVISIBLE);
        }else{
            noTasks.setVisibility(View.VISIBLE);
        }
    }


    private void showMessage(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).show();
    }

    private void initViews(){
        recyclerViewNoteList = viewFragmentNote.findViewById(R.id.recyclerViewNoteList);
        noTasks = viewFragmentNote.findViewById(R.id.noTasks);
        fabButton = viewFragmentNote.findViewById(R.id.fabButton);
        imgSort = viewFragmentNote.findViewById(R.id.imgSort);
        imgMore = viewFragmentNote.findViewById(R.id.imgMore);
        tvAllNote = viewFragmentNote.findViewById(R.id.tvAllNote);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(viewFragmentNote.getContext());
        recyclerViewNoteList.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(viewFragmentNote.getContext(), ((LinearLayoutManager) layoutManager).getOrientation());
        recyclerViewNoteList.addItemDecoration(dividerItemDecoration);
    }
}
