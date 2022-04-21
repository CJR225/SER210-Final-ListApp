package edu.quinnipiac.ser210.listapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewListFragment extends Fragment implements View.OnClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Boolean listCreated, itemAdded;
    private ListsDataSource dataSource;

    public NewListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewListFragment newInstance(String param1, String param2) {
        NewListFragment fragment = new NewListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        listCreated = false;
        itemAdded = false;
        dataSource = new ListsDataSource(this.getContext());
        dataSource.open();
        List<Lists> allLists = dataSource.getAllLists();
        ArrayAdapter<Lists> adapter = new ArrayAdapter<Lists>(this.getContext(), android.R.layout.simple_list_item_1, allLists);



        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_list, container, false);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.createbutton) {
            if (listCreated) {
                Snackbar createWarning = Snackbar.make(getView().findViewById(R.id.toolbar), "You've already made a new list!", 2000);
                createWarning.show();
            } else {
                EditText listNameView = (EditText) getView().findViewById(R.id.editTextListName);
                String listName = listNameView.getText().toString();

                //Database implementation
                Lists list = null;
                list = dataSource.addListName(listName);

                listCreated = true;
                Snackbar createComplete = Snackbar.make(getView().findViewById(R.id.toolbar), "List " + listName +  " Created!", 2000);
                createComplete.show();
            }
        } else if (view.getId() == R.id.addButton) {
            if (listCreated) {
                Snackbar addWarning = Snackbar.make(getView().findViewById(R.id.toolbar), "You haven't made a list yet!", 2000);
                addWarning.show();
            } else if (itemAdded) {
                Snackbar addWarning = Snackbar.make(getView().findViewById(R.id.toolbar), "You already added an item. Go to edit to add more!", 2000);
                addWarning.show();
            } else {
                EditText AddlistItemView = (EditText) getView().findViewById(R.id.editTextTextMultiLine);
                String listItem  = AddlistItemView.getText().toString();

                Lists list = null;
                list = dataSource.addItem1(listItem);

                itemAdded = true;
                Snackbar addComplete = Snackbar.make(getView().findViewById(R.id.toolbar), listItem + " Added!", 2000);
                addComplete.show();
            }
        }
    }
}

/*
    public void onCreateList () {
        //ArrayAdapter<Lists> adapter = (ArrayAdapter<Lists>) getListAdapter();
        if (listCreated) {
            Snackbar createWarning = Snackbar.make(getView().findViewById(R.id.toolbar), "You've already made a new list!", 2000);
            createWarning.show();
        } else {
            EditText listNameView = (EditText) getView().findViewById(R.id.editTextListName);
            String listName = listNameView.getText().toString();

            //Database implementation
            Lists list = null;
            list = dataSource.addListName(listName);

            listCreated = true;
            Snackbar createComplete = Snackbar.make(getView().findViewById(R.id.toolbar), "List " + listName +  " Created!", 2000);
            createComplete.show();
        }
    }

    public void onAddToList () {
        if (listCreated) {
            Snackbar addWarning = Snackbar.make(getView().findViewById(R.id.toolbar), "You haven't made a list yet!", 2000);
            addWarning.show();
        } else if (itemAdded) {
            Snackbar addWarning = Snackbar.make(getView().findViewById(R.id.toolbar), "You already added an item. Go to edit to add more!", 2000);
            addWarning.show();
        } else {
            EditText AddlistItemView = (EditText) getView().findViewById(R.id.editTextTextMultiLine);
            String listItem  = AddlistItemView.getText().toString();

            Lists list = null;
            list = dataSource.addItem1(listItem);

            itemAdded = true;
            Snackbar addComplete = Snackbar.make(getView().findViewById(R.id.toolbar), listItem + " Added!", 2000);
            addComplete.show();
        }

    }
    */