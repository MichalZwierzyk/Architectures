package com.justeat.architectures.mvp;

class UserUiModel {
    private final String name;
    private final String surname;
    private final UserRole role;

    UserUiModel(String name, String surname, UserRole role) {
        this.name = name;
        this.surname = surname;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    String getSurname() {
        return surname;
    }

    UserRole getRole() {
        return role;
    }
}
