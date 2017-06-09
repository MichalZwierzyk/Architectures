package com.justeat.architectures.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.justeat.architectures.R;

public class MvpActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // View
        MvpFragment view = new MvpFragment();
        getFragmentManager().beginTransaction().add(R.id.container, view).commit();

        // Model
        UserRepo userRepo = new UserRepo();
        // Presenter
        Presenter presenter = new Presenter(view, userRepo);

        view.setPresenter(presenter);
    }
}
