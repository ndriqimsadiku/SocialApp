package yourpost_package;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

import apiservice.ApiServices;

/**
 * Created by hp on 11/4/2017.
 */

public class PostAsyncTask extends AsyncTask<String,String,String> {
    PostCallBack callback;
    public PostAsyncTask(PostCallBack callback){
        this.callback=callback;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    protected void onPostExecute(String s) {
        try {

            super.onPostExecute(s);
            Log.i("message", s);
            PostResponse postsResponse = new PostResponse(s);
            callback.onPostsResponseCallback(postsResponse.getPostsInfos());
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }
    }

    protected String doInBackground(String... strings) {
        try{
            return ApiServices.get(strings[0]);
        } catch (IOException e ){
            return null;
        }
    }
}