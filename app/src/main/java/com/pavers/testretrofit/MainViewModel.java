package com.pavers.testretrofit;

import android.app.Application;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.io.PrintWriter;
import java.io.StringWriter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MainViewModel extends AndroidViewModel {
    private Api api;
    private final CompositeDisposable disposable = new CompositeDisposable();
    private final Handler handler = new Handler();
    private MutableLiveData<String> response = new MutableLiveData<>();
    private StringBuilder sb = new StringBuilder();

    public MainViewModel(@NonNull Application application) {
        super(application);
        Retrofit retrofit = Config.warehouseApi();
        api = retrofit.create(Api.class);
    }

    LiveData<String> getResponse() {
        return response;
    }

    void start() {
        sb.insert(0, "start\n");
        response.setValue(sb.toString());
        startTimer();
    }

    private void startTimer() {
        handler.postDelayed(() -> {
            networkRequest();
            startTimer();
        }, 250);
    }

    void stop() {
        handler.removeCallbacksAndMessages(null);
        sb.insert(0, "stop\n");
        response.setValue(sb.toString());
    }

    private void networkRequest() {
        disposable.add(api.getUpdatedProduct("00001234", false)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(apiResponse -> {
                    if (apiResponse.isSuccessful()) {
                        sb.insert(0, "Network request success\n");
                    } else {
                        sb.insert(0, "Network request failed\n");
                    }
                    response.setValue(sb.toString());
                }, throwable -> {
                    StringWriter sw = new StringWriter();
                    PrintWriter pw = new PrintWriter(sw);
                    throwable.printStackTrace(pw);
                    sb.insert(0, "BEGIN CRASH: \n" + sw.toString() + "\n");
                    response.setValue(sb.toString());
                })
        );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.dispose();
    }
}
