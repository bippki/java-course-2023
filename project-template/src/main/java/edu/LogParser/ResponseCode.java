package edu.LogParser;

public enum ResponseCode {
    OK(200, "OK"),
    NOT_FOUND(404, "Not Found"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    UNKNOWN(-1, "Unknown");

    private final int code;
    private final String name;

    ResponseCode(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static ResponseCode getByCode(int code) {
        for (ResponseCode responseCode : values()) {
            if (responseCode.code == code) {
                return responseCode;
            }
        }
        return UNKNOWN;
    }

    static String getResponseCodeName(int responseCode) {
        return ResponseCode.getByCode(responseCode).getName();
    }

}

