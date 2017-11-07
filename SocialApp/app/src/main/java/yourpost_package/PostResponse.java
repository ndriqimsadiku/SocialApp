package yourpost_package;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by hp on 11/4/2017.
 */

public class PostResponse {


    ArrayList<Posts> post = new ArrayList<>();

    public  PostResponse(String s){
        try {
            JSONObject jsonObject = new JSONObject(s);
            Log.i("message",s);
            JSONArray jsonArray =jsonObject.optJSONArray("postet");
            for (int i=0;i<jsonArray.length();i++){
                post.add(new Posts(jsonArray.optJSONObject(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public  ArrayList<Posts> getPostsInfos() {
        return post;
    }
}
