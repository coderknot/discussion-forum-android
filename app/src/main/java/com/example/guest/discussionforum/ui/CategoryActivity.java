package com.example.guest.discussionforum.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.guest.discussionforum.R;
import com.example.guest.discussionforum.adapters.FirebaseCategoryViewHolder;
import com.example.guest.discussionforum.adapters.FirebaseMessageViewHolder;
import com.example.guest.discussionforum.models.Category;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CategoryActivity extends AppCompatActivity implements View.OnClickListener {
    private DatabaseReference mCategoriesReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

    @Bind(R.id.categoryRecyclerView) RecyclerView mCategoryRecyclerView;
    @Bind(R.id.addCategory) Button mAddCategory;
    @Bind(R.id.categoryName) EditText mCategoryName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);

        mCategoriesReference = FirebaseDatabase
                .getInstance()
                .getReference().child("categories");
        setUpFirebaseAdapter();

        mAddCategory.setOnClickListener(this);
    }

    private void setUpFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Category, FirebaseCategoryViewHolder>(Category.class, R.layout.category_list_item, FirebaseCategoryViewHolder.class, mCategoriesReference) {
            @Override
            protected void populateViewHolder(FirebaseCategoryViewHolder viewHolder, Category model, int position) {
                viewHolder.bindCategory(model);
            }
        };

        mCategoryRecyclerView.setHasFixedSize(true);
        mCategoryRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mCategoryRecyclerView.setAdapter(mFirebaseAdapter);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }
}
