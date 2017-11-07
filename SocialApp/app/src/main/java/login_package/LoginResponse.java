package login_package;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hp on 11/3/2017.
 */

public class LoginResponse {
    LogIn logInUserinfo;

    public LoginResponse(String s){
        try {
            JSONObject jsonObject = new JSONObject(s);
            String logininfo= jsonObject.getString("User");
            JSONArray jsonArray = jsonObject.getJSONArray("User");
            Log.i("login info",logininfo);
            logInUserinfo = new LogIn(jsonArray.optJSONObject(0));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public LogIn getLogInUserinfo() {
        return logInUserinfo;
    }
}
