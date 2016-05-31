package com.example.vaibhav.mvpsample.presenter;

import android.os.Bundle;

import com.example.vaibhav.mvpsample.MVPTestActivity;

import nucleus.presenter.RxPresenter;
import ws.AppWs;
import ws.entity.BaseRequest;
import ws.entity.BaseResponse;
import ws.entity.SampleRequest;
import ws.entity.SampleResponse;

/**
 * Created by vaibhav on 30/5/16.
 */
public class MVPTestPresenter extends RxPresenter<MVPTestActivity> {

    private MVPTestActivity mvpTestActivity;

    @Override
    protected void onCreate(Bundle savedState) {

        super.onCreate(savedState);
    }

    @Override
    protected void onTakeView(MVPTestActivity mvpTestActivity) {

        super.onTakeView(mvpTestActivity);

        this.mvpTestActivity = mvpTestActivity;

        loadData();
    }

    public void loadData() {

        SampleRequest sampleRequest = new SampleRequest();
        sampleRequest.setFirstName("Rajanikant");
        sampleRequest.setLastName("");

        mvpTestActivity.showProgressDialog();

        AppWs.WsListener listener = new AppWs.WsListener() {

            @Override
            public void onResponseSuccess(BaseResponse baseResponse) {

                if(baseResponse instanceof SampleResponse) {

                    mvpTestActivity.dismissProgressDialog();

                    SampleResponse sampleResponse = (SampleResponse) baseResponse;
                    mvpTestActivity.showSampleData(sampleResponse.getItems());
                }
            }

            @Override
            public void notifyResponseFailed(String message, BaseRequest request) {

                mvpTestActivity.dismissProgressDialog();
            }
        };

        AppWs.getSampleEndPointResponse(sampleRequest, mvpTestActivity, listener);
    }

}
