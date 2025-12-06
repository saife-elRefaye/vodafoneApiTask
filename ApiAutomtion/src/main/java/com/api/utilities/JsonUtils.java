package com.api.utilities;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JsonUtils {

    public static Object[][] getLoginData(String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        List<Map<String, Object>> loginList = objectMapper.readValue(
                new File(filePath),
                new TypeReference<List<Map<String, Object>>>() {}
        );

        Object[][] data = new Object[loginList.size()][3];

        for (int i = 0; i < loginList.size(); i++) {
            Map<String, Object> login = loginList.get(i);

            data[i][0] = login.get("username").toString();
            data[i][1] = login.get("password").toString();
            data[i][2] = (int) login.get("expected_status");
        }

        return data;
    }
    public static Map<String, Map<String, Object>> readJsonToNestedMap(String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(
                new File(filePath),
                new TypeReference<Map<String, Map<String, Object>>>() {}
        );
    }

    public static Map<String, Object> getProductData(String filePath, String key) throws IOException {
        Map<String, Map<String, Object>> allData = readJsonToNestedMap(filePath);
        return allData.get(key);
    }
}