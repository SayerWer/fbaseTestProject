package com.example.werprojects.myapplication;;

import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.StringDef;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Server/Names");
    String value="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button b = (Button) findViewById(R.id.button);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                value = dataSnapshot.getValue(String.class);

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });



        b.setOnClickListener((new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //myRef.setValue("hh");

                ///////////////////Plays noise////////////////////////////

                try {
                    Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                    Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
                    r.play();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                /////////////////////////////////////////////////////////



                EditText edit = (EditText)findViewById(R.id.editText2);
                String result = edit.getText().toString();
                myRef.setValue(value+":"+result);

                startActivity(new Intent(MainActivity.this, Question.class));


            }
        }));

    }
    @Override
    protected void onStart(){
        super.onStart();

    }








    @Override
    public void onStop() {
        super.onStop();

    }

    }

