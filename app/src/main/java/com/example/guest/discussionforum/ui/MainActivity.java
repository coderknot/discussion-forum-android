package com.example.guest.discussionforum.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.guest.discussionforum.Constants;
import com.example.guest.discussionforum.MessageListActivity;
import com.example.guest.discussionforum.R;
import com.example.guest.discussionforum.models.Message;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private DatabaseReference mMessagesReference;
    private ValueEventListener mMessagesReferenceListener;

    @Bind(R.id.createMessage) Button mCreateMessage;
    @Bind(R.id.viewMessages) Button mViewMessages;
    @Bind(R.id.viewCategories) Button mViewCategories;
    @Bind(R.id.content) EditText mContent;
    @Bind(R.id.title) EditText mTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mMessagesReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_MESSAGE);

        mMessagesReferenceListener = mMessagesReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {
                    String message = messageSnapshot.getValue().toString();

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mCreateMessage.setOnClickListener(this);
        mViewMessages.setOnClickListener(this);
        mViewCategories.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mCreateMessage) {
            String title = mTitle.getText().toString();
            String content = mContent.getText().toString();

            Message message = new Message(title, content);
            saveMessageToFirebase(message);
            Toast.makeText(MainActivity.this, "Message Saved", Toast.LENGTH_SHORT).show();
        }
        if(v == mViewMessages) {
            Intent intent = new Intent(MainActivity.this, MessageListActivity.class);
            startActivity(intent);
        }
        if(v == mViewCategories) {
            Toast.makeText(MainActivity.this, "View Catergories Button clicked!", Toast.LENGTH_SHORT).show();
        }
    }

    public void saveMessageToFirebase(Message message) {
        mMessagesReference.push().setValue(message);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMessagesReference.removeEventListener(mMessagesReferenceListener);
    }
}
