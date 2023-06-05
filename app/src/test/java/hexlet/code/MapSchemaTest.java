package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import org.junit.jupiter.api.Test;
import hexlet.code.schemas.MapSchema;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

public class MapSchemaTest {
    @Test
    void isValidTest() {
        Validator v = new Validator();
        MapSchema schema = v.map();

        assertThat(schema.isValid(null)).as("with not required null").isTrue();

        schema.required();

        assertThat(schema.isValid(null)).as("with required null").isFalse();
        assertThat(schema.isValid(new HashMap<>())).as("with initialized map").isTrue();

        Map<String, String> data = new HashMap<>();

        data.put("key1", "value1");

        assertThat(schema.isValid(data)).as("with valid value").isTrue(); // true

        schema.sizeof(2);

        assertThat(schema.isValid(data)).as("with wrong size").isFalse();
        data.put("key2", "value2");
        assertThat(schema.isValid(data)).as("with correct size").isTrue();
    }

    @Test
    void insertedMapTest() {
        Validator v = new Validator();
        MapSchema schema = v.map();

        Map<String, BaseSchema> schemas = new HashMap<>();

        schemas.put("name", v.string().required());

        schemas.put("age", v.number().positive());
        schema.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", 100);
        assertThat(schema.isValid(human1)).as("with valid values").isTrue();

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);
        assertThat(schema.isValid(human2)).as("with required null").isTrue();

        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);
        assertThat(schema.isValid(human3)).as("with not required null").isFalse();

        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        human4.put("age", -5);
        assertThat(schema.isValid(human4)).as("with invalid value").isFalse();
    }
}
