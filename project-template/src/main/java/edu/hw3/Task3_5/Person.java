package edu.hw3.Task3_5;

public class Person {
    private Person() {

    }

    private String first_name;
    private String last_name;

    public Person(String fullName) {
        String[] parts = fullName.split(" ");
        if (parts.length > 1) {
            this.first_name = parts[0];
            this.last_name = parts[parts.length - 1];
        } else {
            this.first_name = "";
            this.last_name = fullName;
        }
    }

    public String getFullName() {
        if (first_name.isEmpty()) {
            return last_name;
        } else {
            return first_name + " " + last_name;
        }
    }

    public String getFirstName() {
        return first_name;
    }

    public String getLastName() {
        return last_name;
    }
}

