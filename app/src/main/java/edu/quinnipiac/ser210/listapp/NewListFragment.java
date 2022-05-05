package edu.quinnipiac.ser210.listapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;


public class NewListFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Boolean listCreated = false, itemAdded = false;
    private ListsDataSource dataSource;
    private View toolbarView, editTextView;
    private NavController navController = null;
    private Reminders list = null;


    public NewListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        listCreated = false;
        itemAdded = false;
        dataSource = new ListsDataSource(this.getContext());
        dataSource.open();
        List<Reminders> allLists = dataSource.getAllLists();
        ArrayAdapter<Reminders> adapter = new ArrayAdapter<Reminders>(this.getContext(), android.R.layout.simple_list_item_1, allLists);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_list, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        Log.v("viewtest", "VIEWTEST");
        view.findViewById(R.id.editFragNewView).setOnClickListener(this);
        view.findViewById(R.id.addButtonNewView).setOnClickListener(this);
        view.findViewById(R.id.createButtonNewView).setOnClickListener(this);

        editTextView = getView().findViewById(R.id.selectListEditView);
        if (editTextView != null)
            Log.v("editText", "EditText");
        toolbarView = getView().findViewById(R.id.toolbar);
        if (toolbarView != null)
            Log.v("toolBar", "toolBar");

    }


    @Override
    public void onClick(View view) {
        Log.v("calling", "button");
        switch (view.getId()) {
            case R.id.editFragNewView:
                if (listCreated) {
                    navController.navigate(R.id.action_newListFragment_to_editFragment);
                }
                break;
            case R.id.createButtonNewView:
                Log.v("button clicked", "button");
                if (listCreated) {
                    Snackbar createWarning = Snackbar.make(view, "You've already made a new list!", 2000);
                    createWarning.show();
                } else {
                    Log.v("create list", "list");
                    EditText listNameView = (EditText) editTextView;
                    String listName = listNameView.getText().toString();

                    //Database implementation
                    list = dataSource.createList(listName);

                    listCreated = true;
                    Snackbar createComplete = Snackbar.make(view, "List " + listName + " Created!", 2000);
                    createComplete.show();
                }
                break;
            case R.id.addButtonNewView:
                if (listCreated) {
                    Snackbar addWarning = Snackbar.make(view, "You haven't made a list yet!", 2000);
                    addWarning.show();
                } else if (itemAdded) {
                    Snackbar addWarning = Snackbar.make(view, "You already added an item. Go to edit to add more!", 2000);
                    addWarning.show();
                } else {
                    EditText AddlistItemView = (EditText) getView().findViewById(R.id.editTextTextMultiLine);
                    String listItem = AddlistItemView.getText().toString();

                    list = dataSource.addItem(listItem);

                    itemAdded = true;
                    Snackbar addComplete = Snackbar.make(view, listItem + " Added!", 2000);
                    addComplete.show();
                }
                break;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        dataSource.close();
    }

    @Override
    public void onResume() {
        super.onResume();
        dataSource.open();
    }
}