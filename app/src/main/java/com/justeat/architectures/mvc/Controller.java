package com.justeat.architectures.mvc;

class Controller {
    private UserRepo userRepo;

    Controller(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    void onGetUserButtonClicked() {
        // Transform Ui Event into Action
        userRepo.getUser();
    }
}
