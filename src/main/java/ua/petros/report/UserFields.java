package ua.petros.report;

import java.util.*;

public class UserFields extends AbstractFields {

    //"Username", "First Name", "Last Name", "Email",
    //					"Mobile1", "Mobile2", "Address", "Account", "Bank", "Role"

    //"Username", "FirstName", "LastName", "Email",
    //        "Mobile1", "Mobile2", "Address", "Account", "Bank", "Roles"

    @Override
    Map<String, String> initMap() {
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

}
