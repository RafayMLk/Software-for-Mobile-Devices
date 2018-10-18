package com.example.rafay.roomdatabase;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddUserFragment extends Fragment {

    private EditText UserId, UserName, UserEmail;
    private Button BnSave;


    public AddUserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_add_user, container, false);

        UserId = view.findViewById(R.id.txtuserid);
        UserName = view.findViewById(R.id.txtname);
        UserEmail = view.findViewById(R.id.txtemail);
        BnSave = view.findViewById(R.id.bnsaveuser);

        BnSave.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                int userid = Integer.parseInt(UserId.getText().toString());
                String username = UserName.getText().toString();
                String useremail = UserEmail.getText().toString();

                User user  = new User();
                user.setId(userid);
                user.setName(username);
                user.setEmail(useremail);

                MainActivity.mydatabase.myDAO().addUser(user);
                Toast.makeText(getActivity(), "User Added Successfully !", Toast.LENGTH_SHORT).show();

                UserId.setText("");
                UserName.setText("");
                UserEmail.setText("");

            }
        });

        return view;
    }

}
