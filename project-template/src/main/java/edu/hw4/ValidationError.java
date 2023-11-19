package edu.hw4;

class ValidationError {
    private final String value;
    private final String desc;

    public ValidationError(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

}
