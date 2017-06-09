package com.justeat.architectures.mvvm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.justeat.architectures.R;

public class MvvmActivity extends AppCompatActivity {
    private MvvmFragment view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // View
        view = new MvvmFragment();
        getFragmentManager().beginTransaction().add(R.id.container, view).commit();
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
}
