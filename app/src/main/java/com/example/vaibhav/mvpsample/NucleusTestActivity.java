package com.example.vaibhav.mvpsample;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.vaibhav.mvpsample.adapter.SampleAdapter;
import com.example.vaibhav.mvpsample.bean.SampleBean;
import com.example.vaibhav.mvpsample.presenter.NucleusTestPresenter;

import java.util.ArrayList;

import nucleus.factory.RequiresPresenter;
import nucleus.view.NucleusAppCompatActivity;
import ui.DividerScroll;
import utils.Logger;
import utils.Notify;
import ws.entity.SampleRequest;

@RequiresPresenter(NucleusTestPresenter.class)
public class NucleusTestActivity extends NucleusAppCompatActivity<NucleusTestPresenter> {

    private SampleAdapter sampleAdapter;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_test);

        initViews();

        loadData();
    }

    private void initViews() {

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        assert recyclerView != null;
        recyclerView.setLayoutManager(layoutManager);

        sampleAdapter = new SampleAdapter(this);
        recyclerView.setAdapter(sampleAdapter);

        DividerScroll.decorate(recyclerView, R.drawable.divider1, false, true);

        Button btnStart = (Button) findViewById(R.id.btnStart);
        assert btnStart != null;
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadData();
            }
        });
    }

    private void loadData() {

        SampleRequest sampleRequest = new SampleRequest();
        sampleRequest.setFirstName("Rajanikant");
        sampleRequest.setLastName("");

        showProgressDialog();
        getPresenter().requestData(sampleRequest);
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

        dismissProgressDialog();
        sampleAdapter.setSampleBeans(items);
    }

    public void onError(Throwable throwable, SampleRequest sampleRequest) {

        dismissProgressDialog();
        Notify.toast("On Error", this);
    }
}
