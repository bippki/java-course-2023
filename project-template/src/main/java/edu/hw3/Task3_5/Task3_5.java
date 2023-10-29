package edu.hw3.Task3_5;

public class Task3_5 {
    private Task3_5() {

    }
    String fullName;

    public Task3_5(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getLastName() {
        String[] parts = fullName.split(" ");
        if (parts.length > 0) {
            return parts[parts.length - 1];
        }
        return fullName;
    }
}


