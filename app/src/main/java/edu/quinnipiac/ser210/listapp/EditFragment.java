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
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class EditFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ListsDataSource dataSource;
    private Reminders list;
    NavController navController = null;

    public EditFragment() {
        // Required empty public constructor
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_select_list, container, false);
        dataSource = new ListsDataSource(this.getContext());
        dataSource.open();
        //List<Lists> allLists = dataSource.getAllLists();
        //ArrayAdapter<Lists> adapter = new ArrayAdapter<Lists>(this.getContext(), android.R.layout.simple_list_item_1, allLists);
        //setListAdapter(adapter);

        //Recieve information from select list on which list you edit
        return inflater.inflate(R.layout.fragment_edit, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        view.findViewById(R.id.deleteListEditFrag).setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addItemEditFrag:
                //Recieve information from select list on which list you edit
                EditText addItemView = (EditText) getView().findViewById(R.id.addTextViewEditFrag);
                String addItem  = addItemView.getText().toString();
                dataSource.addItem(addItem);
                Snackbar itemAdded = Snackbar.make(getView().findViewById(R.id.toolbar), "Item Added!", 2000);
                itemAdded.show();
                break;

            case R.id.removeItemEditFrag:
                //Implement statement that checks which number list you are editting
                //Current implementation assumes first list
                //implement remove item
                EditText removeItemView = (EditText) getView().findViewById(R.id.addTextViewEditFrag);
                String removeItem  = removeItemView.getText().toString();
                //dataSource.removeItem1(removeItem);
                Snackbar itemRemoved = Snackbar.make(getView().findViewById(R.id.toolbar), "Item Removed!", 2000);
                itemRemoved.show();
                break;

            case R.id.deleteListEditFrag:
                navController.navigate(R.id.action_editFragment_to_splashFragment);
                //dataSource.deleteList(dataSource.getAllItems1());
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