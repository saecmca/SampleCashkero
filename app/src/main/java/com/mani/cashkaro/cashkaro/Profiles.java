package com.mani.cashkaro.cashkaro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookAuthorizationException;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

public class Profiles extends AppCompatActivity implements View.OnClickListener{
    LoginButton loginButton;
    private CallbackManager callbackManager;
    private AccessTokenTracker accessTokenTracker;
    private ProfileTracker profileTracker;
    private Button btnFace;
    TextView tvTic;
    ImageView profilee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_profile);

        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton) findViewById(R.id.login_button);
        tvTic=(TextView)findViewById(R.id.profName);
         profilee=(ImageView)findViewById(R.id.profimage);
        btnFace=(Button)findViewById(R.id.btnFacebook);
        ImageView back=(ImageView)findViewById(R.id.back);
        back.setOnClickListener(this);
        btnFace.setOnClickListener(this);
        loginButton.setReadPermissions(Arrays.asList("email", "user_friends"));

        loginButton.registerCallback(callbackManager, callback);


        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldToken, AccessToken newToken) {
            }
        };

        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile newProfile) {
                displayMessage(newProfile);
            }
        };
        accessTokenTracker.startTracking();
        profileTracker.startTracking();


    }





    /* Facebook Codes */
    private FacebookCallback<LoginResult> callback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            AccessToken accessToken = loginResult.getAccessToken();
            Profile profile = Profile.getCurrentProfile();
            displayMessage(profile);

            System.out.println("onSuccess");
            GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {

                @Override
                public void onCompleted(JSONObject object, GraphResponse response) {
                    Log.i("LoginActivity", response.toString());
                    // Get facebook data from login
                    Bundle bFacebookData = getFacebookData(object);

                    String FacebookID = String.valueOf(bFacebookData.get("idFacebook"));
                    String EmailID = String.valueOf(bFacebookData.get("email"));
                    String ProfileIMG = String.valueOf(bFacebookData.get("profile_pic"));
                    String FirstName = String.valueOf(bFacebookData.get("first_name"));
                    String lastName = String.valueOf(bFacebookData.get("last_name"));
                    String gender = String.valueOf(bFacebookData.get("gender"));
                    Log.w("facebook", "onCompleted: " + EmailID);
                    try{
                        tvTic.setText(FirstName);
                        Picasso.with(Profiles.this).load(ProfileIMG).into(profilee);
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    //   Localstorage.saveLoginPref(Login.this, true, "", FirstName, "", EmailID, "");
                    //Toast.makeText(getApplicationContext(), model.getStatus().getDescription(), Toast.LENGTH_SHORT).show();
                  /*  Intent main = new Intent(Login.this, HomeActivity.class);
                    startActivity(main);
                    finish();*/

                    // signinPresenter.callSocailmediaservice(FirstName + " " + lastName, EmailID, "", gender, AppConstants.PLATFORM, ProfileIMG, FacebookID, AppConstants.SocailFacebook, getSavedFirebaseToken);

                }
            });
            Bundle parameters = new Bundle();
            parameters.putString("fields", "id, first_name, last_name, email,gender, birthday, location"); // Parámetros que pedimos a facebook
            request.setParameters(parameters);
            request.executeAsync();


        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException e) {
            if (e instanceof FacebookAuthorizationException) {
                if (AccessToken.getCurrentAccessToken() != null) {
                    LoginManager.getInstance().logOut();
                }
            }
        }
    };

    private Bundle getFacebookData(JSONObject object) {
        try {
            Bundle bundle = new Bundle();
            String id = object.getString("id");

            try {
                URL profile_pic = new URL("https://graph.facebook.com/" + id + "/picture?width=200&height=150");
                Log.i("profile_pic", profile_pic + "");
                bundle.putString("profile_pic", profile_pic.toString());

                Log.d("facebook email", "getFacebookData: " + object.getString("email"));

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            }

            bundle.putString("idFacebook", id);
            if (object.has("first_name"))
                bundle.putString("first_name", object.getString("first_name"));
            if (object.has("last_name"))
                bundle.putString("last_name", object.getString("last_name"));
            if (object.has("email"))
                bundle.putString("email", object.getString("email"));
            if (object.has("gender"))
                bundle.putString("gender", object.getString("gender"));
            if (object.has("birthday"))
                bundle.putString("birthday", object.getString("birthday"));
            if (object.has("location"))
                bundle.putString("location", object.getJSONObject("location").getString("name"));

            return bundle;
        } catch (JSONException e) {
            Log.d("facebook", "Error parsing JSON");
        }
        return null;
    }

    private void displayMessage(Profile profile) {
        if (profile != null) {
            Log.d("Facebook", "Name : " + profile.getName());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onStop() {
        super.onStop();
        accessTokenTracker.stopTracking();
        profileTracker.stopTracking();

    }

    @Override
    public void onResume() {
        super.onResume();
       Profile profile = Profile.getCurrentProfile();
        displayMessage(profile);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnFacebook:

                Profile profile = Profile.getCurrentProfile().getCurrentProfile();

                    // user has not logged in
                    loginButton.performClick();


                break;
            case R.id.back:
                finish();
                break;
        }
    }
}
