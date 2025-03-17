package airlines;

import utils.JsonUtils;
import java.io.IOException;
import java.util.Map;
public class Base {
static Map<String, Object> data;
    static {
        String env=System.getProperty("env")==null? "QA": System.getProperty("env");

        try {
            String fileCompletePath=System.getProperty("user.dir")+"/src/test/resources/Env/"+env+"/QA_Env.json";
            data = JsonUtils.getJsonDataAsMap(fileCompletePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
