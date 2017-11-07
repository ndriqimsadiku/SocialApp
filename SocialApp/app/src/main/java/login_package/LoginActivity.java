package login_package;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.hp.socialapp.AppPreferences;
import com.example.hp.socialapp.MainActivity;
import com.example.hp.socialapp.R;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import signup.SignUpActivity;


//done

public class LoginActivity extends Activity implements LoginCallBack{

        EditText loginUsernameEdittext,loginPasswordEdittext;
        Button SignInButton,goToSignUp;
        RelativeLayout relativeLayout;
        LogIn logIn;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            AppPreferences.init(getApplicationContext());
            try {
                if (!AppPreferences.getUserId().equals("")) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
            catch (NullPointerException e){
                e.printStackTrace();
            }
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);

            SignInButton =(Button)  findViewById(R.id.LogInbutton);
            goToSignUp = (Button)findViewById(R.id.ResignUpButton);
            SignInButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    final String status = "success";

                    if(fieldChecker()) {
                        new LogInAsyncTask(LoginActivity.this).execute(getUrl());

                        try {
                            Log.i("Status", logIn.getStatus());

                            if (logIn.getStatus().equals(status)) {
                                AppPreferences.saveUserId(logIn.getUserID());

                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                                }
                                else {
                                Toast.makeText(LoginActivity.this, "Ju keni shenuar te dhenat gabim!",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                        catch (NullPointerException e){
                            e.printStackTrace();
                        }
                    }
                }
            });
            goToSignUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                    startActivity(intent);
                }
            });
        }

        public String getUrl(){

            loginUsernameEdittext = (EditText) findViewById(R.id.email_login);
            loginPasswordEdittext = (EditText) findViewById(R.id.password_login);
            String email = loginUsernameEdittext.getText().toString();
            String md5Password = md5(loginPasswordEdittext.getText().toString());
            return "http://appsix.net/paintbook/index.php?User="+email+"&Password="+md5Password;
        }

        public static String md5(final String s) {
            final String MD5 = "MD5";

            try {
                //md5 hash
                MessageDigest digest = java.security.MessageDigest
                        .getInstance(MD5);
                digest.update(s.getBytes());

                byte messageDigest[] = digest.digest();

                // string hex
                StringBuilder hexString = new StringBuilder();

                for (byte aMessageDigest : messageDigest) {
                    String h = Integer.toHexString(0xFF & aMessageDigest);

                    while (h.length() < 2)
                        h = "0" + h;
                    hexString.append(h);
                }
                return hexString.toString();
            }
            catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            return "";
        }
        public boolean fieldChecker() {
            try {
                relativeLayout = (RelativeLayout) findViewById(R.id.login_relative);
                for (int i = 1; i < relativeLayout.getChildCount(); i++) {
                    EditText editText = (EditText) relativeLayout.getChildAt(i);
                    if (editText.getText().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(),
                                "Fill the field:" + editText.getHint().toString(),
                                Toast.LENGTH_SHORT).show();
                        return false;
                    }
                }
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Keni Bere nje gabim!", Toast.LENGTH_LONG).show();
            }
            return true;
        }
        @Override
        public void onLoginResponseCallback(LogIn logIn) {

            this.logIn=logIn;
        }
    }
