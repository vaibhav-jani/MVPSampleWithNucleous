package com.example.vaibhav.mvpsample.presenter;

import android.os.Bundle;

import com.example.vaibhav.mvpsample.NucleusTestActivity;

import nucleus.presenter.RxPresenter;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action2;
import rx.functions.Func0;
import rx.schedulers.Schedulers;
import ws.AppWs;
import ws.entity.SampleRequest;
import ws.entity.SampleResponse;

/**
 * Created by vaibhav on 30/5/16.
 */
public class NucleusTestPresenter extends RxPresenter<NucleusTestActivity> {

    private static final String KEY_SAMPLE_REQUEST = "key_sample_request";

    private SampleRequest sampleRequest;

    private final int sampleRequestId = 1;

    @Override
    protected void onCreate(Bundle savedState) {

        super.onCreate(savedState);

        if(savedState != null) {

            this.sampleRequest = (SampleRequest) savedState.getSerializable(KEY_SAMPLE_REQUEST);

        }

        restartableLatestCache(sampleRequestId,

                new Func0<Observable<SampleResponse>>() {

                    @Override
                    public Observable<SampleResponse> call() {

                        return AppWs.getSampleEndPointResponse(sampleRequest)
                                .subscribeOn(Schedulers.newThread())
                                .observeOn(AndroidSchedulers.mainThread());
                    }
                },

                new Action2<NucleusTestActivity, SampleResponse>() {

                    @Override
                    public void call(NucleusTestActivity nucleusTestActivity, SampleResponse sampleResponse) {

                        nucleusTestActivity.showSampleData(sampleResponse.getItems());
                    }
                },

                new Action2<NucleusTestActivity, Throwable>() {

                    @Override
                    public void call(NucleusTestActivity nucleusTestActivity, Throwable throwable) {

                        nucleusTestActivity.onError(throwable, sampleRequest);
                    }
                }
        );
    }

    @Override
    protected void onSave(Bundle state) {

        super.onSave(state);

        state.putSerializable(KEY_SAMPLE_REQUEST, sampleRequest);
    }

    @Override
    protected void onTakeView(NucleusTestActivity mvpTestActivity) {

        super.onTakeView(mvpTestActivity);
    }

    public void requestData(SampleRequest request) {

        this.sampleRequest = request;

        start(sampleRequestId);
    }

}
