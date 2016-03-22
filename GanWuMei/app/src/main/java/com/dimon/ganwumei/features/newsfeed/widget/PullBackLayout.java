package com.dimon.ganwumei.features.newsfeed.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by Dimon on 2016/3/11.
 */
public class PullBackLayout extends FrameLayout {

    private final ViewDragHelper dragger;

    @Nullable
    private Callback callback;

    public PullBackLayout(Context context) {
        this(context, null);
    }

    public PullBackLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PullBackLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        dragger = ViewDragHelper.create(this, new ViewDragCallback());
    }

    public void setCallback(@Nullable Callback callback) {
        this.callback = callback;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return dragger.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        dragger.processTouchEvent(event);
        return true;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void computeScroll() {
        if (dragger.continueSettling(true)) {
            postInvalidateOnAnimation();
        }
    }

    public interface Callback {

        void onPull(float progress);

        void onPullDown();

        boolean canPullDown();

    }

    private class ViewDragCallback extends ViewDragHelper.Callback {

        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return callback != null && callback.canPullDown();
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            return 0;
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            return Math.max(0, top);
        }

        @Override
        public int getViewHorizontalDragRange(View child) {
            return 0;
        }

        @Override
        public int getViewVerticalDragRange(View child) {
            return getHeight();
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            if (callback != null) {
                callback.onPull((float) top / (float) getHeight());
            }
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            if (callback != null && (yvel > 0 || (yvel == 0 && releasedChild.getTop() > (float) getHeight() / 3f))) {
                callback.onPullDown();
            } else {
                dragger.settleCapturedViewAt(0, 0);
                invalidate();
            }
        }

    }

}
