package airlines;

import utils.JsonUtils;

import java.util.Map;

public class Base {
    static Map<String, Object> data;

    static {
        String env = System.getProperty("env", "QA");
        String fileCompletePath = System.getProperty("user.dir") + "/src/test/resources/Env/" + env + "/" + env + "_Env.json";

        data = JsonUtils.getJsonDataAsMap(fileCompletePath);
        System.out.println("✅ Loaded environment config from: " + fileCompletePath);


    }
}
