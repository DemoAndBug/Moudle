package com.rhw.learning.video.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.rhw.learning.R;
import com.rhw.learning.constant.Constant;
import com.rhw.learning.utils.LogUtils;
import com.rhw.learning.video.BrowserActivity;
import com.rhw.learning.video.VideoContral;
import com.rhw.learning.video.monitor.VideoValue;

/**
 * function: 全屏显示视频
 * @author Simon
 */
public class VideoFullDialog extends Dialog implements CustomVideoView.VideoPlayerListener {

    private static final String TAG = VideoFullDialog.class.getSimpleName();
    private CustomVideoView mVideoView;

    private Context mContext;
    private RelativeLayout mRootView;
    private ViewGroup mParentView;
    private ImageView mBackButton;

    private VideoValue mXAdInstance;
    private int mPosition;
    private FullToSmallListener mListener;
    //private XADFrameImageLoadListener mFrameLoadListener;
    private boolean isFirst = true;

    private VideoContral.VideoContralListener mSlotListener;

    public VideoFullDialog(Context context, CustomVideoView mraidView, VideoValue instance,
                           int position) {
        super(context, R.style.dialog_full_screen);
        this.mContext = context;
        this.mXAdInstance = instance;
        this.mPosition = position;
        this.mVideoView = mraidView;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.xadsdk_dialog_video_layout);
        initVideoView();
    }

    public void setListener(FullToSmallListener listener) {
        this.mListener = listener;
    }

//    public void setFrameLoadListener(XADFrameImageLoadListener listener) {
//        this.mFrameLoadListener = listener;
//    }

    public void setSlotListener(VideoContral.VideoContralListener slotListener) {
        this.mSlotListener = slotListener;
    }

    private void initVideoView() {
        mParentView = (RelativeLayout) findViewById(R.id.content_layout);
        mBackButton = (ImageView) findViewById(R.id.xadsdk_player_close_btn);
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickBackBtn();
            }
        });
        mRootView = (RelativeLayout) findViewById(R.id.root_view);
        mRootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickVideo();
            }
        });
        mVideoView.setListener(this);
        mVideoView.mute(false);
        mParentView.addView(mVideoView);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        LogUtils.i(TAG, "onWindowFocusChanged");
        mVideoView.isShowFullBtn(false); //防止第一次，有些手机仍显示全屏按钮
        if (!hasFocus) {
            mPosition = mVideoView.getCurrentPosition();
            mVideoView.pauseForFullScreen();
        } else {
            if (isFirst) { //为了适配某些手机不执行seekandresume中的播放方法
                mVideoView.seekAndResume(mPosition);
            } else {
                mVideoView.resume();
            }
        }
        isFirst = false;
    }

    @Override
    public void dismiss() {
        LogUtils.e(TAG, "dismiss");
        this.mParentView.removeView(mVideoView);
        super.dismiss();
    }

    @Override
    public void onBackPressed() {
        onClickBackBtn();
        super.onBackPressed();
    }

    @Override
    public void onClickFullScreenBtn() {
        onClickVideo();
    }

    @Override
    public void onClickVideo() {
        String desationUrl = mXAdInstance.clickUrl;
        if (mSlotListener != null) {
            if (mVideoView.isFrameHidden() && !TextUtils.isEmpty(desationUrl)) {
                mSlotListener.onClickVideo(desationUrl);
                try {

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
        this.dismiss();
        try {
           /* ReportManager.exitfullScreenReport(mXAdInstance.event.exitFull.content, mVideoView.getCurrentPosition()
                / SDKConstant.MILLION_UNIT);*/
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (mListener != null) {
            mListener.getCurrentPlayPosition(this.mVideoView.getCurrentPosition());
        }
    }

    @Override
    public void onVideoLoadSuccess() {
        if (mVideoView != null) {
            mVideoView.resume();
        }
    }

    @Override
    public void onVideoLoadFailed() {
    }

    @Override
    public void onVideoLoadComplete() {
        try {
            int position = mVideoView.getDuration() / Constant.MILLION_UNIT;
            //ReportManager.sueReport(mXAdInstance.endMonitor, true, position);
        } catch (Exception e) {
            e.printStackTrace();
        }

        dismiss();
        if (mListener != null) {
            mListener.playComplete();
        }
    }

    @Override
    public void onBufferUpdate(int time) {
        try {
            if (mXAdInstance != null) {
                //ReportManager.suReport(mXAdInstance.middleMonitor, time / SDKConstant.MILLION_UNIT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClickPlay() {

    }

    public interface FullToSmallListener {
        void getCurrentPlayPosition(int position);

        void playComplete();//全屏播放结束时回调
    }
}
