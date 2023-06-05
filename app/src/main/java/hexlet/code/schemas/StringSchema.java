package hexlet.code.schemas;

public class StringSchema extends BaseSchema {

    private int minLength = 0;
    private String contains = "";

    public StringSchema() {
    }

    public StringSchema minLength(int length) {
        minLength = length;
        return this;
    }

    public StringSchema contains(String str) {
        contains = str;
        return this;
    }

    @Override
    public boolean schemaValidator(Object data) {
        String strData = String.valueOf(data);
        return !isNumber(strData)
                && strData.contains(contains)
                && strData.length() >= minLength;
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
