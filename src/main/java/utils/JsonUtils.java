package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class JsonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT) // ✅ Pretty-print JSON
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); // ✅ Prevent failure for unknown properties

    // ✅ Reads JSON File and Returns as a Map
    public static Map<String, Object> getJsonDataAsMap(String jsonFileName) {
        File jsonFile = new File(jsonFileName);

        if (!jsonFile.exists()) {
            throw new RuntimeException("❌ JSON file not found: " + jsonFileName);
        }

        try {
            return objectMapper.readValue(jsonFile, new TypeReference<>() {});
        } catch (IOException e) {
            throw new RuntimeException("❌ Error reading JSON file: " + jsonFileName, e);
        }
    }
}
