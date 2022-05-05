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

import java.util.List;

public class SelectListFragment extends Fragment implements View.OnClickListener {

private NavController navController = null;
private ListsDataSource dataSource;
private ListView selectListView;
private View selectEditView;
private Boolean listSelected;
public FragHelper helper;
    int i = 0;

    public SelectListFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_select_list, container, false);
        dataSource = new ListsDataSource(this.getContext());
        dataSource.open();

        selectListView = (ListView) view.findViewById(R.id.select_list_view);
        List<Reminders> values = dataSource.getAllLists();

        // use the SimpleCursorAdapter to show the
        // elements in a ListView
        ArrayAdapter<Reminders> adapter = new ArrayAdapter<Reminders>(this.getContext(), android.R.layout.simple_list_item_1, values);
        selectListView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        view.findViewById(R.id.editFrag_SelectView).setOnClickListener(this);
        selectEditView = getView().findViewById(R.id.selectListEditView);
    }

    @Override
    public void onClick(View view) {
        ArrayAdapter<Reminders> adapter = (ArrayAdapter<Reminders>) selectListView.getAdapter();
        EditText listNameView = (EditText) selectEditView;
        String listName = listNameView.getText().toString();
        int item = 0;
        while (i < adapter.getCount()) {
            if (adapter.getItem(i).getListName().toLowerCase() == listName.toLowerCase()) {
                item = i;
                listSelected = true;
            }

        }
        if(listSelected) {
            Snackbar createWarning = Snackbar.make(view, "This list does not exist!", 2000);
            createWarning.show();
        } else {
            helper.setActiveList(item);
            navController.navigate(R.id.action_selectListFragment_to_editFragment);
        }
    }

}