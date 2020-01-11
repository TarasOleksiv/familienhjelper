package ua.petros.report;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class MemberFields extends AbstractFields {

    //"Name", "Email",
	//			"Mobile", "City", "Account", "Bank", "DonorType"

    //"Name", "Email",
	//			"Mobile", "City", "Account", "Bank", "DonorType"

    @Override
    Map<String, String> initMap() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("Name", "Name");
        map.put("Email", "Email");
        map.put("Mobile", "Mobile");
        map.put("City", "City");
        map.put("Account", "Account");
        map.put("Bank", "Bank");
        map.put("DonorType", "DonorType");
        return Collections.unmodifiableMap(map);
    }

}
