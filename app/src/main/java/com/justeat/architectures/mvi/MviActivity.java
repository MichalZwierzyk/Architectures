package com.justeat.architectures.mvi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.justeat.architectures.R;

public class MviActivity extends AppCompatActivity {
    private MviFragment view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // View
        if (savedInstanceState != null) {
            view = (MviFragment) getFragmentManager().getFragment(savedInstanceState, MviFragment.class.getSimpleName());
        } else {
            view = new MviFragment();
            getFragmentManager().beginTransaction().add(R.id.container, view).commit();
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Model
        UserRepo userRepo = new UserRepo();

        // ComponentsViewModel
        new ViewModel(userRepo, view.getUserUiModel(), view.getUiEventsStream());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getFragmentManager().putFragment(outState, MviFragment.class.getSimpleName(), view);
    }
}
