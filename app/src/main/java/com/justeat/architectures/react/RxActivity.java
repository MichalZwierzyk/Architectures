package com.justeat.architectures.react;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.justeat.architectures.R;

public class RxActivity extends AppCompatActivity {
    private RxFragment view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // View
        if (savedInstanceState != null) {
            view = (RxFragment) getFragmentManager().getFragment(savedInstanceState, RxFragment.class.getSimpleName());
        } else {
            view = new RxFragment();
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
        getFragmentManager().putFragment(outState, RxFragment.class.getSimpleName(), view);
    }
}
