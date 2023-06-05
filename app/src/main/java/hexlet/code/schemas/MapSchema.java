package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema {
    private int mapSize;

    public MapSchema() {
    }

    @Override
    public boolean schemaValidator(Object data) {
        if (data instanceof Map<?, ?>) {
            return (mapSize != 0 && ((Map<?, ?>) data).size() == mapSize)
                    | mapSize == 0;
        }
        return false;
    }

    public MapSchema sizeof(int size) {
        this.mapSize = size;
        return this;
    }
}
