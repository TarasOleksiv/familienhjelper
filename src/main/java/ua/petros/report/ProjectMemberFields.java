package ua.petros.report;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Taras on 10.01.2020.
 */
public class ProjectMemberFields extends AbstractFields {

    // "Project", "Donor", "Pledge", "Start Pledge",	"Stop Pledge"

    // "Project", "Member", "Pledge", "StartPledge",	"StopPledge"

    @Override
    Map<String, String> initMap() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("Project", "Project");
        map.put("Donor", "Member");
        map.put("Pledge", "Pledge");
        map.put("Start Pledge", "StartPledge");
        map.put("Stop Pledge", "StopPledge");
        return Collections.unmodifiableMap(map);
    }
}
