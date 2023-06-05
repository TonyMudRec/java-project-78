package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NumberSchemaTest {
    @Test
    void isValidTest() {
        Validator v = new Validator();
        NumberSchema schema = v.number();

        assertThat(schema.isValid(null)).as("with not required null").isTrue();
        assertThat(schema.positive().isValid(null)).as("with not required null only positive").isTrue();

        schema.required();

        assertThat(schema.isValid(null)).as("with required null").isFalse();
        assertThat(schema.isValid("5")).as("with string 5").isFalse();
        assertThat(schema.isValid(10)).as("with valid 10").isTrue();
        assertThat(schema.isValid(-10)).as("with negative").isFalse();
        assertThat(schema.isValid(0)).as("with zero").isFalse();

        schema.range(5, 10);

        assertThat(schema.isValid(5)).as("5 in range").isTrue();
        assertThat(schema.isValid(10)).as("10 in range").isTrue();
        assertThat(schema.isValid(4)).as("4 not in range").isFalse();
        assertThat(schema.isValid(11)).as("11 not in range").isFalse();
    }
}
