package edu.quinnipiac.ser210.listapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;
import java.util.Random;


public class NewListFragment extends Fragment implements View.OnClickListener{

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Boolean listCreated = false, itemAdded = false;
    private ListsDataSource dataSource;
    private View toolbarView,editTextView;
    private NavController navController = null;


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
        List<Lists> allLists = dataSource.getAllLists();
        ArrayAdapter<Lists> adapter = new ArrayAdapter<Lists>(this.getContext(), android.R.layout.simple_list_item_1, allLists);


        //toolbarView = getView().findViewById(R.id.toolbar);
        //editTextView = getView().findViewById(R.id.editTextListName);


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_list, container, false);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        view.findViewById(R.id.editFragNewView).setOnClickListener(this);


    }


    public void onCreateList () {

        //ArrayAdapter<Lists> adapter = (ArrayAdapter<Lists>) getListAdapter();
        if (listCreated) {
           Snackbar createWarning = Snackbar.make(getView().findViewById(R.id.toolbar), "You've already made a new list!", 2000);
            createWarning.show();
        } else {
            EditText listNameView = (EditText) editTextView;
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.editFragNewView:
                navController.navigate(R.id.action_newListFragment_to_editFragment);
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