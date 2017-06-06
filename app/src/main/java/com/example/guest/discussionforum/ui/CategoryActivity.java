package com.example.guest.discussionforum.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.guest.discussionforum.R;
import com.example.guest.discussionforum.models.Category;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CategoryActivity extends AppCompatActivity implements View.OnClickListener {
    private DatabaseReference mCategoriesReference;
    @Bind(R.id.addCategory) Button mAddCategory;
    @Bind(R.id.categoryName) EditText mCategoryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mCategoriesReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child("categories");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);

        mAddCategory.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mAddCategory) {
            String name = mCategoryName.getText().toString();
            Category category = new Category(name);
            saveCategoryToFirebase(category);

        }
    }

    public void saveCategoryToFirebase(Category category) {
        mCategoriesReference.push().setValue(category);
    }
}
