package com.justeat.architectures.segue;

class ViewModel {
    private UserRepo userRepo;
    private UserUiModel userUiModel;

    ViewModel(UserRepo userRepo, UserUiModel userUiModel) {
        this.userRepo = userRepo;
        this.userUiModel = userUiModel;
    }

    void onGetUserButtonClicked(GetUserUiEvent getUserUiEvent) {
        // Transform Ui Event into Action
        GetUserAction getUserAction = new GetUserAction(getUserUiEvent.getName());
        UserResult userResult = userRepo.getUser(getUserAction);
        if (userResult != null) {
            // Transform Result into UiModel (Presentation Logic in View Model)
            String userFullName = userResult.getName() + userResult.getSurname();

            int userColor;
            if (userResult.getRole() == UserRole.ADMIN) {
                userColor = 0xFFFF0000;
            } else {
                userColor = 0xFF0000FF;
            }

            // Consume UiModel
            userUiModel.fullname.set(userFullName);
            userUiModel.color.set(userColor);
        } else {
            // Consume UiModel
            userUiModel.fullname.set("Unknown");
            userUiModel.color.set(0xFF000000);
        }
    }
}
