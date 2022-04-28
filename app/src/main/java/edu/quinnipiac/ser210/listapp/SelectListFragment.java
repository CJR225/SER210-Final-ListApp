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

public class SelectListFragment extends ListFragment implements View.OnClickListener {

private NavController navController = null;
private ListsDataSource dataSource;
public ListView selectListView;

    public SelectListFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select_list, container, false);
        dataSource = new ListsDataSource(this.getContext());
        dataSource.open();
        List<Lists> allLists = dataSource.getAllLists();
        ArrayAdapter<Lists> adapter = new ArrayAdapter<Lists>(this.getContext(), android.R.layout.simple_list_item_1, allLists);
        setListAdapter(adapter);

        int i =0;
        while (i < allLists.size()) {
            adapter.add(allLists.get(i));
            i++;
        }

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        view.findViewById(R.id.editFrag_SelectView).setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        navController.navigate(R.id.action_selectListFragment_to_editFragment);
    }
}