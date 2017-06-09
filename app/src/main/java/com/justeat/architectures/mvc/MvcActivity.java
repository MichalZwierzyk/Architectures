package com.justeat.architectures.mvc;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.justeat.architectures.R;

public class MvcActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // View
        MvcFragment view = new MvcFragment();
        getFragmentManager().beginTransaction().add(R.id.container, view).commit();

        // Model
        UserRepo userRepo = new UserRepo();
        // Presenter
        Controller controller = new Controller(userRepo);

        view.setController(controller);
        userRepo.addObserver(view);
    }
}
