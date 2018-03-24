package com.example.somesh.Login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.somesh.myapplication_test.Home;
import com.example.somesh.myapplication_test.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

public class Login extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private FirebaseAuth firebaseAuth;
    sessionMaker session;
    private static final String TAG="Error is Present";
    public FirebaseAuth mAuth;
    private static final int RC_SIGN_IN=1;
    private SignInButton signInButton;
    private GoogleApiClient mGoogleApiClient;
    private FirebaseAuth.AuthStateListener listener;



    @Override
    protected void onStart() {
        super.onStart();

        session=new sessionMaker(getApplicationContext());
        firebaseAuth=FirebaseAuth.getInstance();
        signInButton =(SignInButton)findViewById(R.id.googleBtn);
        mAuth.addAuthStateListener(listener);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email=(EditText)findViewById(R.id.email1);
        session=new sessionMaker(getApplicationContext());
        password=(EditText)findViewById(R.id.pass1);
        firebaseAuth=FirebaseAuth.getInstance();
        signInButton =(SignInButton)findViewById(R.id.googleBtn);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mAuth=FirebaseAuth.getInstance();

        listener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()!=null){
                    session.createUserLoginSession(firebaseAuth.getCurrentUser().getEmail());
                    Intent i = new Intent(Login.this, Home.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra("Email", firebaseAuth.getCurrentUser().getEmail());
                    startActivity(i);
                    finish();
                }
            }
        };

        mGoogleApiClient=new GoogleApiClient.Builder(getApplicationContext()).
                enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

                        Toast.makeText(Login.this,"You Got an Error ... Please Try Again Later ...",Toast.LENGTH_LONG).show();
                    }
                }).addApi(Auth.GOOGLE_SIGN_IN_API,gso).build();

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });


    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result=Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if(result.isSuccess()){
                GoogleSignInAccount account=result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            }
            else{

            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {

        final ProgressDialog progressDialog=ProgressDialog.show(Login.this,"Please Wait....","Authenticating ..",true);
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        Log.d(TAG, "signInWithCredential:success" + task.isSuccessful());
                        if(!task.isSuccessful()){

                            Log.w(TAG, "signInWithCredential:unsuccess" + task.getException());
                            Toast.makeText(Login.this,"Failed to Sigin",Toast.LENGTH_LONG).show();
                        }
                        // ...
                    }
                });

    }

    public void Signup(View view){
        Intent i = new Intent(this, Signup.class);
        startActivity(i);
    }

    public void home_click_login(View view){

        String e = email.getText().toString();
        String p = password.getText().toString();
        if(e.matches("") || p.matches("")){

            Toast.makeText(Login.this, "Fields are Empty...", Toast.LENGTH_LONG).show();
        }

        else {

            final ProgressDialog progressDialog=ProgressDialog.show(Login.this,"Please Wait....","Authenticating ..",true);
            (firebaseAuth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString())).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progressDialog.dismiss();
                    if (task.isSuccessful()){
                        Toast.makeText(Login.this,"Login Successful",Toast.LENGTH_LONG).show();
                        session.createUserLoginSession(firebaseAuth.getCurrentUser().getEmail());
                        Intent i = new Intent(Login.this, Home.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        i.putExtra("Email",firebaseAuth.getCurrentUser().getEmail());
                        startActivity(i);
                        finish();
                    }
                    else{
                        Toast.makeText(Login.this,"Login Failed",Toast.LENGTH_LONG).show();
                        Toast.makeText(Login.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }


}
