package com.justeat.architectures.mvp;

class Presenter {
    private UserView userView;
    private UserRepo userRepo;

    Presenter(UserView userView, UserRepo userRepo) {
        this.userView = userView;
        this.userRepo = userRepo;
    }

    void onGetUserButtonClicked() {
        // Transform Ui Event into Action
        User user = userRepo.getUser();
        // Transform Result into UiModel
        UserUiModel userUiModel = new UserUiModel(user.getName(), user.getSurname(), user.getRole());
        userView.renderUser(userUiModel);
    }
}
