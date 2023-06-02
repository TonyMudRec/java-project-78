package hexlet.code.schemas;

public class NumberSchema extends BaseSchema {

    private int startRange = Integer.MIN_VALUE;
    private int endRange = Integer.MAX_VALUE;
    private boolean onlyPositive = false;

    public NumberSchema() {
    }

    @Override
    public boolean isValid(Object data) {
        if (isNull(data)) {
            return !isRequired();
        }
        if (data instanceof String) {
            return false;
        }
        int intData = (int) data;
        return startRange <= intData
                && intData <= endRange
                && (onlyPositive == (intData > 0));
    }

    public NumberSchema positive() {
        this.onlyPositive = true;
        return this;
    }

    public NumberSchema range(int start, int end) {
        this.startRange = start;
        this.endRange = end;
        return this;
    }
}
