package ua.petros.report;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractFields {
    private final Map<String, String> fieldMap = initMap();

    abstract Map<String, String> initMap();

    public final Map<String, String> getFields(List<String>fieldNames){
        Map<String, String> map = new LinkedHashMap<>();
        for (Map.Entry<String, String> entry : fieldMap.entrySet()) {
            for (String fieldName: fieldNames){
                if (entry.getKey().equals(fieldName)){
                    map.put(entry.getKey(), entry.getValue());
                }
            }
        }
        return Collections.unmodifiableMap(map);
    }

    public final Map<String, String> getFieldMap(){
        return fieldMap;
    }
}
