package requests;

import java.util.HashMap;

public class UpdateExperimentRequest extends Request {
    public String experimentID;
    public String name;
    public String createdBy;
    public HashMap<String, String> annotations = new HashMap<>();

    public UpdateExperimentRequest(String experimentID, String name,
            String createdBy, HashMap<String, String> annotations) {
        super("updateexperiment", "/experiment/" + experimentID, "PUT");
        this.experimentID = experimentID;
        this.name = name;
        this.createdBy = createdBy;
        this.annotations = annotations;
    }
}
