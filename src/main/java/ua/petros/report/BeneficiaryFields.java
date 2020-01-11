package ua.petros.report;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Taras on 10.01.2020.
 */
public class BeneficiaryFields extends AbstractFields {

    // "Name", "Family", "Description",
	//			"Income", "Income Type", "Currency", "Born", "Field Contact"

    // "Name", "Family", "Description",
	//			"Income", "IncomeType", "Currency", "Datefield", "User"

    @Override
    Map<String, String> initMap() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("Name", "Name");
        map.put("Family", "Family");
        map.put("Income", "Income");
        map.put("Income Type", "IncomeType");
        map.put("Currency", "Currency");
        map.put("Born", "Datefield");
        map.put("Field Contact", "User");
        map.put("Description", "Description");
        return Collections.unmodifiableMap(map);
    }
}
