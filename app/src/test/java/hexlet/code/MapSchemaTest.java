package hexlet.code;

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

        assertThat(schema.isValid(null)).as("with not required null").isTrue(); // true

        schema.required();

        assertThat(schema.isValid(null)).as("with required null").isFalse(); // false
        assertThat(schema.isValid(new HashMap<>())).as("with initialized map").isTrue(); // true

        Map<String, String> data = new HashMap<>();

        data.put("key1", "value1");

        assertThat(schema.isValid(data)).as("with valid value").isTrue(); // true

        schema.sizeof(2);

        assertThat(schema.isValid(data)).as("with wrong size").isFalse();  // false
        data.put("key2", "value2");
        assertThat(schema.isValid(data)).as("with correct size").isTrue(); // true
    }
}
