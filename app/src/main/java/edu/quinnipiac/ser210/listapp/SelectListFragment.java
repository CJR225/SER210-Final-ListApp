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
import android.widget.ListView;

import java.util.List;

public class SelectListFragment extends Fragment implements View.OnClickListener {

private NavController navController = null;
private ListsDataSource dataSource;
public ListView selectListView;

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

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        view.findViewById(R.id.editFrag_SelectView).setOnClickListener(this);
        selectListView = (ListView) view.findViewById(R.id.select_list_view);

        List<Reminders> values = dataSource.getAllLists();

        // use the SimpleCursorAdapter to show the
        // elements in a ListView
        ArrayAdapter<Reminders> adapter = new ArrayAdapter<Reminders>(this.getContext(), android.R.layout.simple_list_item_1, values);
        selectListView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        navController.navigate(R.id.action_selectListFragment_to_editFragment);
    }

}