package signup;

import org.json.JSONObject;



public class SignUp {

    String status;

    public SignUp(JSONObject jsonObject) {
        setStatus(jsonObject.optString("status"));
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
