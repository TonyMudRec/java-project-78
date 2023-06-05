package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;

public final class MapSchema extends BaseSchema {
    private int mapSize;
    private boolean isMultiValidation = false;

    private Map<String, BaseSchema> schemas = new HashMap<>();

    public MapSchema() {
    }

    @Override
    public boolean schemaValidator(Object data) {
        if (data instanceof Map<?, ?>) {
            if (isMultiValidation) {
                for (Object key : ((Map<?, ?>) data).keySet()) {
                    if (!((BaseSchema) ((Map<?, ?>) schemas).get(key)).isValid(((Map<?, ?>) data).get(key))) {
                        return false;
                    }
                }
            }
            return mapSize == 0 || ((Map<?, ?>) data).size() == mapSize;
        }
        return false;
    }

    public MapSchema shape(Map<String, BaseSchema> mapOfSchemas) {
        this.schemas = mapOfSchemas;
        isMultiValidation = true;
        return this;
    }

    public MapSchema sizeof(int size) {
        mapSize = size;
        return this;
    }
}
