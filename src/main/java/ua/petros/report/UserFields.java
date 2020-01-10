package ua.petros.report;

import java.util.*;

public class UserFields {
    public static final Map<String, String> fieldMap = initMap();
    //"Username", "First Name", "Last Name", "Email",
    //					"Mobile1", "Mobile2", "Address", "Account", "Bank", "Role"

    //"Username", "FirstName", "LastName", "Email",
    //        "Mobile1", "Mobile2", "Address", "Account", "Bank", "Roles"

    private static Map<String, String> initMap() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("Username", "Username");
        map.put("First Name", "FirstName");
        map.put("Last Name", "LastName");
        map.put("Email", "Email");
        map.put("Mobile1", "Mobile1");
        map.put("Mobile2", "Mobile2");
        map.put("Address", "Address");
        map.put("Account", "Account");
        map.put("Bank", "Bank");
        map.put("Role", "Roles");
        return Collections.unmodifiableMap(map);
    }

    public static final Map<String, String> getFields(List<String>fieldNames){
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
}
