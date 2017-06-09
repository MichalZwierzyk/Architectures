package com.justeat.architectures.vm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.justeat.architectures.R;

public class ViewModelActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // View
        ViewModelFragment view = new ViewModelFragment();
        getFragmentManager().beginTransaction().add(R.id.container, view).commit();

        // Model
        UserRepo userRepo = new UserRepo();

        view.setUserRepo(userRepo);
    }
}
