package specdemo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Jacksonized
@Builder
public class Field {

    @JsonProperty("name")
    String name;

    @JsonProperty("value")
    String value;

    public static Field field(String name, String value) {
        return new Field(name, value);
    }
}
