package signup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.hp.socialapp.AppPreferences;
import com.example.hp.socialapp.MainActivity;
import com.example.hp.socialapp.R;

import login_package.LoginActivity;

public class SignUpActivity extends AppCompatActivity implements SignUpCallbBack {
        Button signupButton;
        EditText nameEdittext;
        EditText usernameEdittext;
        EditText passwordEdittext;
        SignUp signUp;
        LinearLayout linearLayout;
        Button goToSignIn;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
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

            setContentView(R.layout.activity_signup);


            signupButton = (Button) findViewById(R.id.signup_button);
            goToSignIn = (Button) findViewById(R.id.switch_sign_in);

            signupButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(fieldChecker()) {
                        new SigUpAsyncTask(SignUpActivity.this).execute(getUrl());
                        try {
                            if (signUp.getStatus().equals("success")) {
                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(SignUpActivity.this,"This user exists", Toast.LENGTH_SHORT).show();
                            }
                        }
                        catch (NullPointerException e){
                            e.printStackTrace();
                        }
                    }
                }
            });
            goToSignIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(intent);
                }
            });
        }
        public String getPassword(){
            passwordEdittext = (EditText) findViewById(R.id.password_edittext);
            return    passwordEdittext.getText().toString();

        }
        public String getUsername(){
            usernameEdittext = (EditText) findViewById(R.id.username_edittext);
            return usernameEdittext.getText().toString();
        }
        public String getUrl(){

            nameEdittext = (EditText) findViewById(R.id.name_edittext);

            String fullName = nameEdittext.getText().toString();

            String[] name = fullName.split("\\s+");

            return "http://appsix.net/paintbook/index.php?RegisterUser=&User="
                    +getUsername() +"&password="+getPassword()+"&Emri="+name[0]+"&Mbiemri="+name[1];


        }

        public boolean fieldChecker(){
            linearLayout=  (LinearLayout) findViewById(R.id.linear_layout);
            for (int i = 1; i < linearLayout.getChildCount(); i++) {
                EditText editText = (EditText) linearLayout.getChildAt(i);
                if(editText.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),
                            "Fill the field:"+editText.getHint().toString(),
                            Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
            return true;
        }


    @Override
    public void onSignUpResponse(SignUp signUp) {
        this.signUp=signUp;
        }

}



