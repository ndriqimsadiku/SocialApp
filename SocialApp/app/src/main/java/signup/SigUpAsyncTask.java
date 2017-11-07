package signup;

import android.os.AsyncTask;

import java.io.IOException;

import apiservice.ApiServices;

/**
 * Created by hp on 11/4/2017.
 */

public class SigUpAsyncTask  extends AsyncTask<String,String,String> {
    SignUpCallbBack signUpCallbBack;

    public SigUpAsyncTask(SignUpCallbBack signUpCallbBack) {
        this.signUpCallbBack=signUpCallbBack;
    }

    @Override
    protected String doInBackground(String... strings) {
        try{
            return ApiServices.get(strings[0]);
        } catch (IOException e ){
            return null;
        }
    }
    protected void onPostExecute(String s){
        super.onPostExecute(s);
        SignUpResponse signUpResponse = new SignUpResponse(s);
        signUpCallbBack.onSignUpResponse(signUpResponse.getUserInfo());
    }

}