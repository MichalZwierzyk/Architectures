package com.justeat.architectures.components;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.justeat.architectures.R;

import javax.inject.Inject;

public class ComponentsActivity extends AppCompatActivity {
    @Inject ViewModelFactory viewModelFactory;

    private ComponentsFragment view;

    @DebugTrace
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // View
        if (savedInstanceState != null) {
            view = (ComponentsFragment) getSupportFragmentManager().getFragment(savedInstanceState, ComponentsFragment.class.getSimpleName());
        } else {
            view = new ComponentsFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.container, view).commit();
        }

        DaggerDiComponent.builder().build().inject(this);
    }

    @DebugTrace
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // ViewModel
        final ComponentsViewModel viewModel = ViewModelProviders.of(view, viewModelFactory).get(ComponentsViewModel.class);

        view.setupUiEventsStream(viewModel.getUiEvents());
        view.render(viewModel.getUserUiModel());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getSupportFragmentManager().putFragment(outState, ComponentsFragment.class.getSimpleName(), view);
    }
}
