package com.justeat.architectures.components;

class GetUserUiEvent implements UiEvent {
    private final String name;

    GetUserUiEvent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
