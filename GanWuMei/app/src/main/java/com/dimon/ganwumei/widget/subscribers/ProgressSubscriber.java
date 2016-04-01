package com.dimon.ganwumei.widget.subscribers;

import android.content.Context;
import android.widget.Toast;

import com.dimon.ganwumei.widget.progress.ProgressCancelListener;
import com.dimon.ganwumei.widget.progress.ProgressDialogHandler;
import com.socks.library.KLog;

import rx.Subscriber;

/**
 * 用于在Http请求开始时，自动显示一个ProgressDialog
 * 在Http请求结束时，关闭ProgressDialog
 * 调用者自己对请求数据进行处理
 *
 * Created by Dimon on 2016/3/28.
 */
public class ProgressSubscriber<T> extends Subscriber<T> implements ProgressCancelListener {


    private SubscriberOnNextListener mSubscriberOnNextListener;
    private ProgressDialogHandler mProgressDialogHandler;

    private Context context;

    public ProgressSubscriber(SubscriberOnNextListener mSubscriberOnNextListener, Context context) {
        KLog.a("到这里pro");
        this.mSubscriberOnNextListener = mSubscriberOnNextListener;
        this.context = context;
        mProgressDialogHandler = new ProgressDialogHandler(context, this, true);
    }

    private void showProgressDialog(){
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
        }
    }

    private void dismissProgressDialog(){
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
            mProgressDialogHandler = null;
        }
    }

    @Override
    public void onStart() {
        showProgressDialog();
    }

    @Override
    public void onCompleted() {
        dismissProgressDialog();
        Toast.makeText(context, "Get GanWu Completed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(Throwable e) {
        dismissProgressDialog();
        KLog.e(e.getMessage());
        Toast.makeText(context, "error:" + e.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNext(T t) {
        KLog.a("onNext");
        mSubscriberOnNextListener.onNext(t);
    }

    @Override
    public void onCancelProgress() {
        if (!this.isUnsubscribed()) {
            this.unsubscribe();
        }
    }
}
