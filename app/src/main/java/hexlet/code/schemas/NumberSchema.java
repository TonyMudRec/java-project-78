package hexlet.code.schemas;

public class NumberSchema extends BaseSchema {

    private int startRange = Integer.MIN_VALUE;
    private int endRange = Integer.MAX_VALUE;
    private boolean onlyPositive = false;

    public NumberSchema() {
    }

    @Override
    public boolean schemaValidator(Object data) {
        if (data instanceof String) {
            return false;
        }
        int intData = (int) data;
        return startRange <= intData
                && intData <= endRange
                && ((intData > 0) | !onlyPositive);
    }

    public NumberSchema positive() {
        onlyPositive = true;
        return this;
    }

    public NumberSchema range(int start, int end) {
        startRange = start;
        endRange = end;
        return this;
    }

    @Override
    public NumberSchema required() {
        return (NumberSchema) super.required();
    }
}
