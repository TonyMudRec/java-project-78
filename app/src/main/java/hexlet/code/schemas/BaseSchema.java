package hexlet.code.schemas;

import java.util.Map;

public abstract class BaseSchema {
    private boolean required = false;

    public boolean isNotRequired() {
        return !required;
    }

    public boolean isNull(Object data) {
        if (data instanceof Map<?, ?>) {
            return false;
        }
        return data == null | String.valueOf(data).isEmpty();
    }

    public boolean isValid(Object data) {
        if (isNull(data)) {
            return isNotRequired();
        }
        return schemaValidator(data);
    }
    public abstract boolean schemaValidator(Object data);

    public BaseSchema required() {
        required = true;
        return this;
    }
}
