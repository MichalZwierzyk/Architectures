package com.justeat.architectures.mvi;

class GetUserUiEvent implements UiEvent {
    private final String name;

    GetUserUiEvent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
