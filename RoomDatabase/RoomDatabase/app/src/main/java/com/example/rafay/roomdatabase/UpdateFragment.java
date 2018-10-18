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
public class UpdateFragment extends Fragment {


    private EditText UserId, UserName, UserEmail;
    private Button UpdateButton;


    public UpdateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_update, container, false);

        UserId = view.findViewById(R.id.txtupdateid);
        UserName = view.findViewById(R.id.txtupdatename);
        UserEmail = view.findViewById(R.id.txtupdateemail);
        UpdateButton = view.findViewById(R.id.bnupdateuser);

        UpdateButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                int id = Integer.parseInt(UserId.getText().toString());
                String name = UserName.getText().toString();
                String email = UserEmail.getText().toString();

                User user  = new User();
                user.setId(id);
                user.setName(name);
                user.setEmail(email);


                MainActivity.mydatabase.myDAO().updateuser(user);
                Toast.makeText(getActivity(), "User Updated Successfully !", Toast.LENGTH_SHORT).show();

                UserId.setText("");
                UserName.setText("");
                UserEmail.setText("");

            }
        });

        return view;
    }

}
