import java.util.HashMap;
import java.util.Map;

public class StudentInputParser {
    public Map<String, String> parse(String raw) {
        Map<String, String> map = new HashMap<>();
        String[] parts = raw.split(";");
        for (String p : parts) {
            String[] kv = p.split("=", 2);
            if (kv.length == 2) {
                map.put(kv[0].trim(), kv[1].trim());
            }
        }
        return map;
    }
}
