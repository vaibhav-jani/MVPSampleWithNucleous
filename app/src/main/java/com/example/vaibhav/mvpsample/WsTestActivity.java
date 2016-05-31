package com.example.vaibhav.mvpsample;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.vaibhav.mvpsample.adapter.SampleAdapter;

import ui.DividerScroll;
import ws.AppWs;
import ws.entity.BaseRequest;
import ws.entity.BaseResponse;
import ws.entity.SampleRequest;
import ws.entity.SampleResponse;

public class WsTestActivity extends BaseActivity {

    private Button btnStart;

    private RecyclerView recyclerView;

    private SampleAdapter sampleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_test);

        initViews();

        loadData();
    }


    private void initViews() {

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        sampleAdapter = new SampleAdapter(this);
        recyclerView.setAdapter(sampleAdapter);

        DividerScroll.decorate(recyclerView, R.drawable.divider1, false, true);

        btnStart = (Button) findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadData();
            }
        });
    }

    private void loadData() {

        showProgressDialog();

        SampleRequest sampleRequest = new SampleRequest();
        sampleRequest.setFirstName("Rajanikant");
        sampleRequest.setLastName("");

        AppWs.WsListener listener = new AppWs.WsListener() {

            @Override
            public void onResponseSuccess(BaseResponse baseResponse) {

                dismissProgressDialog();

                if(baseResponse instanceof SampleResponse) {

                    SampleResponse sampleResponse = (SampleResponse) baseResponse;
                    sampleAdapter.setSampleBeans(sampleResponse.getItems());

                }
            }

            @Override
            public void notifyResponseFailed(String message, BaseRequest request) {

                dismissProgressDialog();
            }
        };

        AppWs.getSampleEndPointResponse(sampleRequest, this, listener);

    }

}
