package com.example.alertdialogue;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

//AppCompatActivity extends FragmentActivity, 
//so anyone who needs to use features of FragmentActivity can use AppCompatActivity.
public class MainActivity extends AppCompatActivity implements DialogFrag.DialogListener{

    Button  custom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ALERT DIALOGUE WITH CUSTOM LAYOUT
        custom = findViewById(R.id.CustomDialogue);
        custom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFrag dialogFrag = new DialogFrag();
                dialogFrag.show(getSupportFragmentManager(),"DialogFragment");
            }
        });


    }

    // The dialog fragment receives a reference to this Activity through the
    // Fragment.onAttach() callback, which it uses to call the following methods
    // defined by the NoticeDialogFragment.NoticeDialogListener interface
    @Override
    public void onDialogPositiveClick(DialogFragment dialog,String username, String password) {
        String announce = "Username: "+username + "/PassWord: "+password;
        // User touched the dialog's positive button
        Toast.makeText(this, announce, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        // User touched the dialog's negative button
        Toast.makeText(this, "Cancel button on Dialogue clicked!", Toast.LENGTH_SHORT).show();
    }
}