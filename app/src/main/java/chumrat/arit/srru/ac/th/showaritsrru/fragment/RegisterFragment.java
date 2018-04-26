package chumrat.arit.srru.ac.th.showaritsrru.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import chumrat.arit.srru.ac.th.showaritsrru.MainActivity;
import chumrat.arit.srru.ac.th.showaritsrru.R;
import chumrat.arit.srru.ac.th.showaritsrru.utility.AddNewUserToServer;
import chumrat.arit.srru.ac.th.showaritsrru.utility.MyAlert;
import chumrat.arit.srru.ac.th.showaritsrru.utility.MyConstant;

public class RegisterFragment extends Fragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Create Toolbar
        createToolbar();


    }   // Main Method

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.itemUpload) {

            uploadValueToSever();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    private void uploadValueToSever() {

//        get value from EditText
        EditText nameEditText = getView().findViewById(R.id.editName);
        EditText userEditText = getView().findViewById(R.id.editUser);
        EditText passwordEditText = getView().findViewById(R.id.editPassword);
        EditText addressEditText = getView().findViewById(R.id.editAddress);

//        Chang Data Type From EditText to String
        String nameString = nameEditText.getText().toString().trim();
        String userString = userEditText.getText().toString().trim();
        String passwordString = passwordEditText.getText().toString().trim();
        String addressString = addressEditText.getText().toString().trim();

//        Check Space
        if (nameString.isEmpty() || userString.isEmpty() || passwordString.isEmpty() || addressString.isEmpty()) {
//             Have Space

            MyAlert myAlert = new MyAlert(getActivity());
            myAlert.normalDialog("Have Space", "Please Fill all blank");

        } else {
//            No Space
            try {

                MyConstant myConstant = new MyConstant();
                AddNewUserToServer addNewUserToServer = new AddNewUserToServer(getActivity());
                addNewUserToServer.execute(nameString, userString, passwordString, addressString,
                        myConstant.getUrlAddUser());

                String result = addNewUserToServer.get();
                Log.d("26AprilV1", "result ==> " + result);

                if (Boolean.parseBoolean(result)) {
                    getActivity().getSupportFragmentManager().popBackStack();
                } else {
                    Toast.makeText(getActivity(), "Error Cannot Upload",
                            Toast.LENGTH_SHORT).show();
                }


            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_register, menu);


    }

    private void createToolbar() {
        Toolbar toolbar = getActivity().findViewById(R.id.toolbarRegister);
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);

//        setup title
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Register");
        ((MainActivity) getActivity()).getSupportActionBar().setSubtitle("Please Fill All Blank");

//        setup Navigation
        ((MainActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();

            }
        });
        setHasOptionsMenu(true);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        return view;
    }
}
