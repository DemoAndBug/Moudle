package com.rhw.learning.video;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.rhw.learning.constant.Constant;
import com.rhw.learning.utils.Utils;
import com.rhw.learning.video.monitor.VideoValue;
import com.rhw.learning.video.widget.CustomVideoView;
import com.rhw.learning.video.widget.VideoFullDialog;

/**
 * Author:renhongwei
 * Date:2017/11/30 on 14:10
 */
public class VideoContral implements CustomVideoView.VideoPlayerListener {

    private Context mContext;
    /**
     * UI
     */
    private CustomVideoView mVideoView;
    private ViewGroup mParentView;
    /**
     * Data
     */
    private VideoValue mXAdInstance;
    private VideoContralListener mSlotListener;
    private boolean canPause = false; //是否可自动暂停标志位
    private boolean isFirst = false;
    private int lastArea = 0; //防止将要滑入滑出时播放器的状态改变

    //传递消息到appcontext层
    public interface VideoContralListener {

        public ViewGroup getParentGroup();

        public void onVideoLoadSuccess();

        public void onVideoLoadFailed();

        public void onVideoLoadComplete();

        public void onClickVideo(String url);
    }

    public VideoContral(VideoValue adInstance,
                        VideoContralListener slotLitener, CustomVideoView.FrameImageLoadListener frameLoadListener) {
        this.mXAdInstance = adInstance;
        this.mSlotListener = slotLitener;
        this.mParentView = slotLitener.getParentGroup();
        this.mContext = mParentView.getContext();
        initVideoView(frameLoadListener);
    }
    private void initVideoView(CustomVideoView.FrameImageLoadListener frameImageLoadListener) {

        mVideoView = new CustomVideoView(mContext, mParentView);
        if (mXAdInstance != null) {
            mVideoView.setDataSource(mXAdInstance.resource);
            mVideoView.setFrameURI(mXAdInstance.thumb);
            mVideoView.setFrameLoadListener(frameImageLoadListener);
            mVideoView.setListener(this);
        }
        RelativeLayout paddingView = new RelativeLayout(mContext);
        paddingView.setBackgroundColor(mContext.getResources().getColor(android.R.color.black));
        paddingView.setLayoutParams(mVideoView.getLayoutParams());
        mParentView.addView(paddingView);
        mParentView.addView(mVideoView);
    }

    private boolean isPlaying() {
        if (mVideoView != null) {
            return mVideoView.isPlaying();
        }
        return false;
    }

    private boolean isPauseBtnClick() {
        if (mVideoView != null) {
            return mVideoView.isPauseBtnClicked();
        }
        return false;
    }

    private boolean isComplete() {
        if (mVideoView != null) {
            return mVideoView.isComplete();
        }
        return false;
    }

    //pause the  video
    private void pauseVideo(boolean isAuto) {
        if (mVideoView != null) {
            if (isAuto) {
                //发自动暂停监测
                if (!isPauseBtnClick() && isPlaying()) {
                    try {
                        //ReportManager.pauseVideoReport(mXAdInstance.event.pause.content, getPosition());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            mVideoView.seekAndPause(0);
        }
    }

    //resume the video
    private void resumeVideo() {
        if (mVideoView != null) {
            mVideoView.resume();
            if (isPlaying() && isFirst) {
                isFirst = false;
                sendSUSReport(true); //发自动播放监测
            }
        }
    }


    public void updateAdInScrollView() {
        int currentArea = Utils.getVisiblePercent(mParentView);
        //小于0表示未出现在屏幕上，不做任何处理
        if (currentArea <= 0) {
            return;
        }
        //刚要滑入和滑出时，异常状态的处理
        if (Math.abs(currentArea - lastArea) >= 100) {
            return;
        }
        if (currentArea < Constant.VIDEO_SCREEN_PERCENT) {
            //进入自动暂停状态
            if (canPause) {
                pauseVideo(true);
                canPause = false;
                //isFirst = true;
            }
            lastArea = 0;
            isFirst = true;
            mVideoView.setIsComplete(false); // 滑动出50%后标记为从头开始播
            mVideoView.setIsRealPause(false);
            return;
        }

        if (isPauseBtnClick() || isComplete()) {
            //进入手动暂停或者播放结束，播放结束和不满足自动播放条件都作为手动暂停
            pauseVideo(false);
            canPause = false;
            return;
        }

        //满足自动播放条件或者用户主动点击播放，开始播放
        if (Utils.canAutoPlay(mContext, VideoSettings.getCurrentSetting())
                || isPlaying()) {
            lastArea = currentArea;
            resumeVideo();
            canPause = true;
            mVideoView.setIsRealPause(false);
        } else {
            pauseVideo(false);
            mVideoView.setIsRealPause(true); //不能自动播放则设置为手动暂停效果
        }
    }

    public void destroy() {
        mVideoView.destroy();
        mVideoView = null;
        mContext = null;
        mXAdInstance = null;
    }

    /**
     * 实现play层接口
     */
    @Override
    public void onClickFullScreenBtn() {
        try {
            //ReportManager.fullScreenReport(mXAdInstance.event.full.content, getPosition());
        } catch (Exception e) {
            e.printStackTrace();
        }
        mParentView.removeView(mVideoView);
        VideoFullDialog dialog = new VideoFullDialog(mContext, mVideoView, mXAdInstance,
                mVideoView.getCurrentPosition());
        dialog.setListener(new VideoFullDialog.FullToSmallListener() {
            @Override
            public void getCurrentPlayPosition(int position) {
                backToSmallMode(position);
            }

            @Override
            public void playComplete() {
                bigPlayComplete();
            }
        });
        dialog.setSlotListener(mSlotListener);
        dialog.show();
    }

    private void backToSmallMode(int position) {
        if (mVideoView.getParent() == null) {
            mParentView.addView(mVideoView);
        }
        isFirst = false; //防止多发送一次sus
        mVideoView.isShowFullBtn(true);
        mVideoView.mute(true);
        mVideoView.setListener(this);
        mVideoView.seekAndResume(position);
        canPause = true; // 标为可自动暂停
    }

    private void bigPlayComplete() {
        if (mVideoView.getParent() == null) {
            mParentView.addView(mVideoView);
        }
        mVideoView.isShowFullBtn(true);
        mVideoView.mute(true);
        mVideoView.setListener(this);
        mVideoView.seekAndPause(0);
        canPause = false;
    }

    @Override
    public void onClickVideo() {
        String desationUrl = mXAdInstance.clickUrl;
        if (mSlotListener != null) {
            if (mVideoView.isFrameHidden() && !TextUtils.isEmpty(desationUrl)) {
                mSlotListener.onClickVideo(desationUrl);
                try {
                   /* ReportManager.pauseVideoReport(mXAdInstance.clickMonitor, mVideoView.getCurrentPosition()
                            / SDKConstant.MILLION_UNIT);*/
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            //走默认样式
            if (mVideoView.isFrameHidden() && !TextUtils.isEmpty(desationUrl)) {
                Intent intent = new Intent(mContext, BrowserActivity.class);
                intent.putExtra(BrowserActivity.KEY_URL, mXAdInstance.clickUrl);
                mContext.startActivity(intent);
                try {
                    /*ReportManager.pauseVideoReport(mXAdInstance.clickMonitor, mVideoView.getCurrentPosition()
                            / SDKConstant.MILLION_UNIT);*/
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public void onClickBackBtn() {

    }

    @Override
    public void onClickPlay() {
        sendSUSReport(false);
    }

    @Override
    public void onVideoLoadSuccess() {
        if (mSlotListener != null) {
            mSlotListener.onVideoLoadSuccess();
        }

    }

    @Override
    public void onVideoLoadFailed() {
        if (mSlotListener != null) {
            mSlotListener.onVideoLoadFailed();
        }
        //加载失败全部回到初始状态
        canPause = false;

    }



    @Override
    public void onVideoLoadComplete() {
        try {
            //ReportManager.sueReport(mXAdInstance.endMonitor, false, getDuration());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (mSlotListener != null) {
            mSlotListener.onVideoLoadComplete();
        }

        //标记为不能从头播放，只有滑出50%以后，才会标记为可以从头开始
        //canPlayBack = false;
        //isClickPlay = false;
        mVideoView.setIsRealPause(true);

    }

    @Override
    public void onBufferUpdate(int time) {
        try {
            //ReportManager.suReport(mXAdInstance.middleMonitor, time / Constant.MILLION_UNIT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int getPosition() {
        return mVideoView.getCurrentPosition() / Constant.MILLION_UNIT;
    }

    private int getDuration() {
        return mVideoView.getDuration() / Constant.MILLION_UNIT;
    }

    /**
     * 发送SUS监测
     *
     * @param isAuto
     */
    private void sendSUSReport(boolean isAuto) {
        try {
            //ReportManager.susReport(mXAdInstance.startMonitor, isAuto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
