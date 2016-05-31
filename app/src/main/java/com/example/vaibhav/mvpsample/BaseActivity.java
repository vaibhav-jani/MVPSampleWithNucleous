package com.example.vaibhav.mvpsample;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;

import utils.Logger;


/**
 * Created by vaibhav on 30/5/16.
 */
public class BaseActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;

    protected void showProgressDialog(){

        if(progressDialog == null) {

            progressDialog = new ProgressDialog(this);
        }

        progressDialog.setMessage(getString(R.string.loading));

        progressDialog.show();
    }

    protected void dismissProgressDialog(){

        try {

            if(progressDialog != null && progressDialog.isShowing()) {

                progressDialog.dismiss();
            }

        } catch (Exception e) {

            Logger.e(e);
        }
    }

}
