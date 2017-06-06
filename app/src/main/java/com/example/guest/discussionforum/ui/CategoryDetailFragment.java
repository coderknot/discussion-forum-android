package com.example.guest.discussionforum.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.guest.discussionforum.R;
import com.example.guest.discussionforum.models.Message;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;


public class CategoryDetailFragment extends Fragment {
    @Bind(R.id.messageTitle) TextView mMessageTitle;
    @Bind(R.id.messageContent) TextView mMessageContent;

    private Message mMessage;

    public  static CategoryDetailFragment newInstance(Message message) {
        CategoryDetailFragment categoryDetailFragment = new CategoryDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("message", Parcels.wrap(message));
        categoryDetailFragment.setArguments(args);
        return categoryDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMessage = Parcels.unwrap(getArguments().getParcelable("message"));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_category_detail, container, false);
        ButterKnife.bind(this, view);

        mMessageTitle.setText(mMessage.getTitle());
        mMessageContent.setText(mMessage.getContent());

        return view;
    }
}
