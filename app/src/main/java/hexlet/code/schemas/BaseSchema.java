package hexlet.code.schemas;

import java.util.Map;

public abstract class BaseSchema {
    private boolean nullRequired = false;

    public final boolean isNull(Object data) {
        if (data instanceof Map<?, ?>) {
            return false;
        }
        return data == null | String.valueOf(data).isEmpty();
    }

    public final boolean isValid(Object data) {
        if (isNull(data)) {
            return !nullRequired;
        }
        return schemaValidator(data);
    }
    public abstract boolean schemaValidator(Object data);

    public BaseSchema required() {
        nullRequired = true;
        return this;
    }
}
