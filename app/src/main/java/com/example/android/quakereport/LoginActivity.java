package com.example.android.quakereport;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class LoginActivity extends AppCompatActivity {
    private GoogleSignInClient mGoogleSignInClient ;
    private  int RC_SIGN_IN = 200 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Check for existing Google Sign In account, if the user is already signed in
// the GoogleSignInAccount will be non-null.


        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);



        // Set the dimensions of the sign-in button.
        SignInButton signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_WIDE);

        findViewById(R.id.vip_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this ,"Logged in as Developer", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(LoginActivity.this, CurrentLocation.class);
                startActivity(intent);
                finish();
            }
        });


     // Build a GoogleSignInClient with the options specified by gso.


          // Configure sign-in to request the user's ID, email address, and basic
          // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestProfile()
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


        findViewById(R.id.sign_in_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.sign_in_button:
                        signIn();
                        break;
                    // ...
                }
            }
        });
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            Toast.makeText(LoginActivity.this ,"Welcome " + account.getDisplayName(), Toast.LENGTH_LONG).show();

            // Signed in successfully, show authenticated UI.
            Intent intent = new Intent(this , CurrentLocation.class);
            startActivity(intent);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Toast.makeText(LoginActivity.this , e.getMessage() , Toast.LENGTH_LONG).show();
        }
    }


    @Override
    protected void onStart() {
        super.onStart();

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null) {
            Toast.makeText(LoginActivity.this ,"Logged in as "+account.getEmail(), Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, CurrentLocation.class);
            startActivity(intent);
            finish();
        }
    }

}


