package com.justeat.architectures.segue;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.justeat.architectures.R;

public class SegueActivity extends AppCompatActivity {
    private SegueFragment view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // View
        if (savedInstanceState != null) {
            view = (SegueFragment) getFragmentManager().getFragment(savedInstanceState, SegueFragment.class.getSimpleName());
        } else {
            view = new SegueFragment();
            getFragmentManager().beginTransaction().add(R.id.container, view).commit();
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Model
        UserRepo userRepo = new UserRepo();

        // ComponentsViewModel
        ViewModel viewModel = new ViewModel(userRepo, view.getUserUiModel());

        view.setViewModel(viewModel);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getFragmentManager().putFragment(outState, SegueFragment.class.getSimpleName(), view);
    }
}
