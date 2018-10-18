package com.example.rafay.roomdatabase;


import android.arch.persistence.room.Delete;
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
public class DeleteUserFragment extends Fragment {

    public EditText TxtUserId;
    private Button DeleteButton;


    public DeleteUserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_delete_user, container, false);
        TxtUserId=view.findViewById(R.id.deleteid);
        DeleteButton = view.findViewById(R.id.bndelete);

        DeleteButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                int id  = Integer.parseInt(TxtUserId.getText().toString());
                User user = new User();
                user.setId(id);
                MainActivity.mydatabase.myDAO().deleteuser(user);

                Toast.makeText(getActivity(), "User Successfully Removed", Toast.LENGTH_LONG).show();
                TxtUserId.setText("");
            }
        });
        return view;

    }

}
