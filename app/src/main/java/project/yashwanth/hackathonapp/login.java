package project.yashwanth.hackathonapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;

public class login extends AppCompatActivity {

    GoogleSignInOptions gso;
    GoogleSignInClient mGoogleSignInClient;
    Context mContext;
    int RC_SIGN_IN=201;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mContext = this;
        SignInButton signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);


        FirebaseMessaging fm=FirebaseMessaging.getInstance();
        fm.send(new RemoteMessage.Builder(NetworkConstants.SENDER_ID+"@gcm.googleapis.com")
        .setMessageId(Integer.toString(1))
                .addData("my_message", "Hello World")
                .addData("my_action","SAY_HELLO")
                .build());



        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this,gso);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account!=null){
            Toast.makeText(this,"Already logged in",Toast.LENGTH_LONG).show();
            startActivity(new Intent(getApplicationContext(),Menu.class));
        }

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
    }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            //Toast.makeText(mContext,"data "+task.toString(),Toast.LENGTH_LONG).show();
            validate(task);
        }
    }
    public void validate(Task<GoogleSignInAccount> completedTask){
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            Toast.makeText(mContext,"Signed in successfully",Toast.LENGTH_LONG).show();
            startActivity(new Intent(getApplicationContext(),Menu.class));

        } catch (ApiException e) {

            Log.w("Google login", "signInResult:failed code=" + e.getStatusCode());
            Toast.makeText(mContext,"Signed in failed, Please signin to continue",Toast.LENGTH_LONG).show();
        }
    }
}
