package signup;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hp on 11/4/2017.
 */

public class SignUpResponse {

    SignUp signUp;

    public SignUpResponse(String s){
        try {
            JSONObject jsonObject = new JSONObject(s);
            signUp  = new SignUp(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public SignUp getUserInfo(){
        return signUp;
    }
}
