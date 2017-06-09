package com.justeat.architectures.segue;

class GetUserUiEvent {
    private final String name;

    GetUserUiEvent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
