package com.justeat.architectures.mvi;

@SuppressWarnings("unused")
class UserResult implements Result {
    private final String name;
    private final String surname;
    private final UserRole role;
    private final String code;
    private final double salary;

    UserResult(String name, String surname, UserRole role, String code, double salary) {
        this.name = name;
        this.surname = surname;
        this.role = role;
        this.code = code;
        this.salary = salary;
    }

    String getName() {
        return name;
    }

    String getSurname() {
        return surname;
    }

    UserRole getRole() {
        return role;
    }

    public String getCode() {
        return code;
    }

    public double getSalary() {
        return salary;
    }
}
