package main.java.com;

import org.json.JSONArray;

public class Main1 {

    public static void main(String[] args) {
        String s = "[\n" +
                "  {\n" +
                "    \"exec_name\": \"abc123\",\n" +
                "    \"platform\": \"linux\",\n" +
                "    \"host_ip_addrs\": \"127.0.0.1\",\n" +
                "    \"paw\": \"aczrpe\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"exec_name\": \"aaa456\",\n" +
                "    \"platform\": \"linux\",\n" +
                "    \"host_ip_addrs\": \"127.0.0.1\",\n" +
                "    \"paw\": \"pghbjn\"\n" +
                "  }\n" +
                "]";

        JSONArray jsonArray = new JSONArray(s);
        System.out.println(jsonArray.getJSONObject(1).get("exec_name"));
    }
}
