package hexlet.code.schemas;

public class StringSchema {

    private int minLength = 0;
    private boolean required = false;
    private String contains = "";

    public StringSchema() {
    }

    public StringSchema required() {
        this.required = true;
        return this;
    }

    public StringSchema minLength(int length) {
        this.minLength = length;
        return this;
    }

    public StringSchema contains(String str) {
        this.contains = str;
        return this;
    }

    public boolean isValid(Object data) {
        if (isNull(data) && required) {
            return false;
        }
        String strData = String.valueOf(data);
        return !isNumber(strData)
                && strData.contains(contains)
                && strData.length() >= minLength;
    }

    public boolean isNull(Object data) {
        return data == null || String.valueOf(data).isEmpty();
    }
    public boolean isNumber(String data) {
        for (int i = 0; i < data.length(); i++) {
            if (Character.isDigit(data.charAt(i))) {
                return true;
            }
        }
        return false;
    }
}
