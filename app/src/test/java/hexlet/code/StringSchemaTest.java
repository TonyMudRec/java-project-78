package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class StringSchemaTest {
    @Test
    void isValidTest() {
        Validator v = new Validator();
        StringSchema schema = v.string();

        assertThat(schema.isValid("")).as("with not required empty").isTrue();
        assertThat(schema.isValid(null)).as("with not required null").isTrue();
        schema.required();
        assertThat(schema.isValid(null)).as("with required null").isFalse();
        assertThat(schema.isValid("")).as("with required empty").isFalse();
        assertThat(schema.isValid(5)).as("with number").isFalse();
        assertThat(schema.isValid("what does the fox say")).as("with valid sentence").isTrue();
        assertThat(schema.isValid("hexlet")).as("with one word").isTrue();
        assertThat(schema.minLength(10).isValid("some text")).as("with minLength").isFalse();
        assertThat(schema.contains("wh").isValid("what does the fox say"))
                .as("with contain 'wh'").isTrue();
        assertThat(schema.contains("what").isValid("what does the fox say"))
                .as("with contain 'what'").isTrue();
        assertThat(schema.contains("whatthe").isValid("what does the fox say"))
                .as("with not contain 'whatthe'").isFalse();
        assertThat(schema.isValid("what does the fox say")).isFalse();
    }
}
