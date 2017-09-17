package com.example.hp.runtime_permission;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import static android.Manifest.permission.CAMERA;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //Declaring variables
    Toolbar toolbar;
    Button check,request;
    private View view;
    // Permission code used to check and ask for permission is Activity
    private static final int PERMISSION_REQUEST_CODE = 123;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Creating and initializing objects by ID.
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        check = (Button) findViewById(R.id.check);
        request = (Button) findViewById(R.id.request);
        //giving reference to the objects for onClickListener buttons
        check.setOnClickListener(this);
        request.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        view = v;
        int id = v.getId();
        //Checking for the  permission
        switch (id) { //Switch Case
            case R.id.check:
                if (check_Permission()) { // If else statements to check the permission
                    /*
                      Snackbar provide lightweight feedback about an operation.

                      They show a brief message at the bottom of the screen on mobile and
                      lower left on larger devices.

                      They automatically disappear after a timeout.

                      */

                    Snackbar.make(view, "Permission already granted.", Snackbar.LENGTH_LONG).show();
                } else {
                    Snackbar.make(view, "You can request for permission.", Snackbar.LENGTH_LONG).show();
                }
                break;
            //
            case R.id.request:
                // If else statements if permission is already granted to the user
                if (!check_Permission()) {
                    request_Permission();

                } else {
                    Snackbar.make(view, "Cannot request for permission.", Snackbar.LENGTH_LONG).show();
                }
                break;
        }

    }


    private boolean check_Permission() { // Creating method
        // To check if you have a permission, call the ContextCompat.checkSelfPermission() method.
        int result = ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    private void request_Permission() { //Creating method
        //  requestPermissions() method is used to request the appropriate permissions
        ActivityCompat.requestPermissions(this, new String[]{CAMERA}, PERMISSION_REQUEST_CODE);
    }

    @Override
    /*
      * onRequestPermissionsResult is callback for the result from requesting permissions.
      * This method is invoked for every call on requestPermissions().
      * The requested permissions is never null.
      * grantResults for the corresponding permissions which is either
         PERMISSION_GRANTED or PERMISSION_DENIED.
     */
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        //Passing request code  to request for the permissions
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0) ;{

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Snackbar.make(view,"Permission Granted",Snackbar.LENGTH_SHORT).show();

                } else {

                    Snackbar.make(view, "Permission Denied", Snackbar.LENGTH_SHORT).show();

                }
                break;

            }
        }
    }
}
