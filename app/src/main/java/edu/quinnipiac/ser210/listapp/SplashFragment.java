package edu.quinnipiac.ser210.listapp;

import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;


/**
 * A simple {@link Fragment} subclass.
 */
public class SplashFragment extends Fragment implements View.OnClickListener {

    NavController navController = null;
    MainActivity mainActivity;
    private Button buttonNew,buttonAdd,buttonView;




    public SplashFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_splash, container, false);

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        view.findViewById(R.id.newbutton).setOnClickListener(this);
        view.findViewById(R.id.editbutton).setOnClickListener(this);
        view.findViewById(R.id.allviewbutton).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.newbutton:
                navController.navigate(R.id.action_splashFragment_to_newListFragment);

                break;
            case R.id.editbutton:
                navController.navigate(R.id.action_splashFragment_to_selectListFragment);
                break;
            case R.id.allviewbutton:
                navController.navigate(R.id.action_splashFragment_to_selectListFragment);

                break;
        }

    }

    /*
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
    savedInstanceState.putInt();
    }
     */
}
