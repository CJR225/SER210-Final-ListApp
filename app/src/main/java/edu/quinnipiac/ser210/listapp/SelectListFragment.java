package edu.quinnipiac.ser210.listapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class SelectListFragment extends Fragment implements View.OnClickListener {

private NavController navController = null;
private ListsDataSource dataSource;
private ListView selectListView, holder;
private View selectEditView;
private Boolean listSelected = false;
public FragHelper helper;


    public SelectListFragment() {
        // Required empty public constructor
    }

    //Display item names on the list
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_select_list, container, false);
        dataSource = new ListsDataSource(this.getContext());
        dataSource.open();
        // use the SimpleCursorAdapter to show the
        // elements in a ListView
        holder = (ListView) view.findViewById(R.id.select_list_view);
        List<Reminders> values = dataSource.getAllLists();
        ArrayAdapter<Reminders> adapter = new ArrayAdapter<Reminders>(this.getContext(), android.R.layout.simple_list_item_1, values);
        holder.setAdapter(adapter);

        selectListView = (ListView) view.findViewById(R.id.select_list_view);
        List<String> listItems = new ArrayList<String>();
        int i = 0;
        while(adapter.getCount() > i) {
            listItems.add(adapter.getItem(i).getListName());
            i++;
        }
        ArrayAdapter<String> listNames = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_list_item_1, listItems);

        selectListView.setAdapter(listNames);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        view.findViewById(R.id.editFrag_SelectView).setOnClickListener(this);
        selectEditView = getView().findViewById(R.id.selectListEditView);
    }

    //Compares the name in the edit-text to the list names, and selects the named list to send to edit list
    //UNIMPLEMENTED
    @Override
    public void onClick(View view) {
        ArrayAdapter<Reminders> adapter = (ArrayAdapter<Reminders>) holder.getAdapter();
        ArrayAdapter<String> names = (ArrayAdapter<String>) selectListView.getAdapter();
        EditText listNameView = (EditText) selectEditView;
        String listName = listNameView.getText().toString();
        int itemIndex = 0;
        int i = 0;
        while (i < adapter.getCount()) {
            if (names.getItem(i).toLowerCase() == listName.toLowerCase()) {
                itemIndex = i;
                listSelected = true;
            }
            i++;
        }
        //if(listSelected = false)
        if(listSelected) {
            Snackbar createWarning = Snackbar.make(view, "This list does not exist!", 2000);
            createWarning.show();
        } else {
            //helper.setActiveList(item);
            navController.navigate(R.id.action_selectListFragment_to_editFragment);
        }
    }

}