package login_package;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

import apiservice.ApiServices;


//done

public class LogInAsyncTask  extends AsyncTask<String,String,String> {



        LoginCallBack callBack;


        public LogInAsyncTask(LoginCallBack callBack){
            this.callBack=callBack;
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
            LoginResponse loginReponse = new LoginResponse(s);
           callBack.onLoginResponseCallback(loginReponse.getLogInUserinfo());
        }
    }