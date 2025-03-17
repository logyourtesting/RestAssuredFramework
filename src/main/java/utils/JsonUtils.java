package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class JsonUtils {

    static ObjectMapper objectMapper=new ObjectMapper();
    public  static Map<String,Object> getJsonDataAsMap(String jsonFileName) throws IOException {
       Map<String, Object> data=objectMapper.readValue(new File(jsonFileName), new TypeReference<>() {});

       return data;
    }
}
