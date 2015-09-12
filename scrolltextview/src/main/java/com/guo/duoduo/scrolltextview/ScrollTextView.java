package com.guo.duoduo.scrolltextview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.view.animation.DecelerateInterpolator;
import android.widget.Scroller;
import android.widget.TextView;

/**
 * Created by 10129302 郭攀峰 on 15-5-26.
 * 可以跟随手指移动的TextView，这样就不需要在ScrollView中嵌套TextView了
 * 不影响其点击消息处理。
 */
public class ScrollTextView extends TextView {

    private Scroller mScroller;
    private int mTouchSlop;
    private int mMinimumVelocity;
    private int mMaximumVelocity;

    private float mLastMotionY;
    private boolean mIsBeingDragged;
    private VelocityTracker mVelocityTracker;

    private int mActivePointerId = INVALID_POINTER;
    private static final int INVALID_POINTER = -1;

    public ScrollTextView(Context context) {
        super(context);
        init(context, null);
    }

    public ScrollTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ScrollTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();

        final Scroller scroller = mScroller;
        if(scroller.computeScrollOffset()) { //正在滚动，让view滚动到当前位置
            int scrollY = scroller.getCurrY();
            final int maxY = (getLineCount() * getLineHeight() + getPaddingTop() + getPaddingBottom()) - getHeight();
            boolean toEdge = scrollY < 0 || scrollY > maxY;
            if(scrollY < 0)
                scrollY = 0;
            else if(scrollY > maxY)
                scrollY = maxY;

            scrollTo(0, scrollY);
            if(toEdge) //移到两端，由于位置没有发生变化，导致滚动条不显示
                awakenScrollBars();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean isConsumed = false;
        int contentHeight = getLineCount() * getLineHeight();
        if(contentHeight > getHeight()) {
            isConsumed = processScroll(event);
        }

        return isConsumed || super.onTouchEvent(event);
    }

    private void init(Context context, AttributeSet attrs) {
        mScroller = new Scroller(context, new DecelerateInterpolator(0.5f));
        ViewConfiguration configuration = ViewConfiguration.get(context);
        mTouchSlop = configuration.getScaledTouchSlop();
        mMinimumVelocity = configuration.getScaledMinimumFlingVelocity();
        mMaximumVelocity = configuration.getScaledMaximumFlingVelocity();
    }

    private boolean processScroll(MotionEvent event) {
        boolean isConsumed = false;
        initVelocityTracker(event);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if(!mScroller.isFinished()) {
                    mScroller.forceFinished(true);
                }
                mLastMotionY = event.getY();
                mActivePointerId = event.getPointerId(0);
                break;
            case MotionEvent.ACTION_MOVE:
                int pointerId = mActivePointerId;
                if(INVALID_POINTER != pointerId) {
                    int pointerIndex = event.findPointerIndex(pointerId);
                    float y = event.getY(pointerIndex);
                    int deltaY = (int) (mLastMotionY - y);
                    if(Math.abs(deltaY) > mTouchSlop) {
                        mLastMotionY = y;
                        mScroller.startScroll(getScrollX(), getScrollY(), 0, deltaY, 0);
                        invalidate();
                        mIsBeingDragged = true;
                        isConsumed = true;
                    }
                }
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                int pointerUpId = mActivePointerId;
                if(mIsBeingDragged && INVALID_POINTER != pointerUpId) {
                    VelocityTracker velocityTracker = mVelocityTracker;
                    velocityTracker.computeCurrentVelocity(1000, mMaximumVelocity);
                    int initialVelocity = (int) velocityTracker.getYVelocity(pointerUpId);
                    if(Math.abs(initialVelocity) > mMinimumVelocity) {
                        fling(-initialVelocity);
                    }
                    mActivePointerId = INVALID_POINTER;
                    mIsBeingDragged = false;
                    releaseVelocityTracker();
                    isConsumed = true;
                }
                break;
        }

        return isConsumed;
    }

    private void initVelocityTracker(MotionEvent event) {
        if(mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);
    }


    private void releaseVelocityTracker() {
        if (mVelocityTracker != null) {
            mVelocityTracker.clear();
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
    }

    private void fling(int velocity) {
        int maxY = (getLineCount() * getLineHeight() + getPaddingTop() + getPaddingBottom()) - getHeight();
        mScroller.fling(getScrollX(), getScrollY(), 0, velocity, 0, 0, 0, Math.max(0, maxY));
        invalidate();
    }
}
