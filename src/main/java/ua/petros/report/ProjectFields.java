package ua.petros.report;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Taras on 10.01.2020.
 */
public class ProjectFields extends AbstractFields {

    // "Name", "Balance NOK", "Start Date",
	//			"Stop Date", "Status", "FU", "Field Contact"

    // "Name", "Balance", "StartDate",
	//			"StopDate", "Status", "fuUser", "fieldContactUser"

    @Override
    Map<String, String> initMap() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("Name", "Name");
        map.put("Balance NOK", "Balance");
        map.put("Start Date", "StartDate");
        map.put("Stop Date", "StopDate");
        map.put("Status", "Status");
        map.put("FU", "fuUser");
        map.put("Field Contact", "fieldContactUser");
        map.put("Description", "Description");
        map.put("Feedback", "Feedback");
        return Collections.unmodifiableMap(map);
    }
}
