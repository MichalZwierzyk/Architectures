package com.justeat.architectures.mvvm;

public class ViewModel {
    private UserRepo userRepo;
    private UserUiModel userUiModel;

    ViewModel(UserRepo userRepo, UserUiModel userUiModel) {
        this.userRepo = userRepo;
        this.userUiModel = userUiModel;
    }

    public void onGetUserButtonClicked() {
        // Transform Ui Event into Action
        User user = userRepo.getUser();
        // Transform Result into UiModel (Presentation Logic in View Model)
        String userFullName = user.getName() + user.getSurname();

        int userColor;
        if (user.getRole() == UserRole.ADMIN) {
            userColor = 0xFFFF0000;
        } else {
            userColor = 0xFF0000FF;
        }

        // Consume UiModel
        userUiModel.fullname.set(userFullName);
        userUiModel.color.set(userColor);
    }
}
