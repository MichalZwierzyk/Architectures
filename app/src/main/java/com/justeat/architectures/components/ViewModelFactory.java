package com.justeat.architectures.components;

import android.arch.lifecycle.*;
import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

class ViewModelFactory implements ViewModelProvider.Factory {
    private UserRepo userRepo;

    @Inject
    ViewModelFactory(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new ComponentsViewModel(userRepo);
    }
}
