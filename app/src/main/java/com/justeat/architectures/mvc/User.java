package com.justeat.architectures.mvc;

class User {
    private final String name;
    private final String surname;

    User(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    String getName() {
        return name;
    }

    String getSurname() {
        return surname;
    }
}
