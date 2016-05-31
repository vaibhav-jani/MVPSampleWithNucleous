package com.example.vaibhav.mvpsample;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.vaibhav.mvpsample.adapter.SampleAdapter;
import com.example.vaibhav.mvpsample.bean.SampleBean;
import com.example.vaibhav.mvpsample.presenter.MVPTestPresenter;

import java.util.ArrayList;

import nucleus.factory.RequiresPresenter;
import nucleus.view.NucleusActivity;
import ui.DividerScroll;
import utils.Logger;

@RequiresPresenter(MVPTestPresenter.class)
public class MVPTestActivity extends NucleusActivity<MVPTestPresenter> {

    private SampleAdapter sampleAdapter;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_test);

        initViews();
    }

    private void initViews() {

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        sampleAdapter = new SampleAdapter(this);
        recyclerView.setAdapter(sampleAdapter);

        DividerScroll.decorate(recyclerView, R.drawable.divider1, false, true);

        Button btnStart = (Button) findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getPresenter().loadData();
            }
        });
    }

    public void showProgressDialog(){

        if(progressDialog == null) {

            progressDialog = new ProgressDialog(this);
        }

        progressDialog.setMessage(getString(R.string.loading));

        progressDialog.show();
    }

    public void dismissProgressDialog(){

        try {

            if(progressDialog != null && progressDialog.isShowing()) {

                progressDialog.dismiss();
            }

        } catch (Exception e) {

            Logger.e(e);
        }
    }

    public void showSampleData(ArrayList<SampleBean> items) {

        sampleAdapter.setSampleBeans(items);
    }
}
