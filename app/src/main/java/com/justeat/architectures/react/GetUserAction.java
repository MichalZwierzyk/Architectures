package com.justeat.architectures.react;

class GetUserAction {
    private final String name;

    GetUserAction(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
