package hexlet.code.schemas;

public abstract class BaseSchema {
    private boolean required = false;

    public boolean isRequired() {
        return required;
    }

    public boolean isNull(Object data) {
        return data == null || String.valueOf(data).isEmpty();
    }

    public abstract boolean isValid(Object data);

    public BaseSchema required() {
        this.required = true;
        return this;
    }
}
