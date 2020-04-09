package com.youloft.cn;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.umeng.commonsdk.UMConfigure;
import com.umeng.commonsdk.utils.UMUtils;
import com.youloft.api.ApiManager;
import com.youloft.api.webview.WebViewUtil;
import com.youloft.cn.core.AdManager;
import com.youloft.cn.core.StatisticsManager;
import com.youloft.cn.core.banner.Banner;
import com.youloft.cn.core.callback.BannerAdListener;
import com.youloft.cn.core.callback.InterstitialAdListener;
import com.youloft.cn.core.callback.RewardedAdListener;
import com.youloft.cn.core.interstitial.Interstitial;
import com.youloft.cn.core.rewardvideo.RewardVideo;
import com.youloft.cn.core.utils.LogUtils;

public class MainActivity extends AppCompatActivity {
    private Banner banner = new Banner();
    private Interstitial interstitial = new Interstitial();
    private RewardVideo rewardVideo = new RewardVideo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //国内接口初始化统计
        ApiManager.init(this, "9011e420-50fd-4b62-96b4-6dcaa26514ea", "6ef864206a884e81819985c78767edad", "https://testchinaapi.youloft.com");

        //广告SDK初始化
        AdManager.initAdSdk(this,
                "{\"gameid\":\"9011e420-50fd-4b62-0000-6dcaa26514ea\",\"gamesecret\":\"6ef864206a884e81819985c78767edad\",\"appver\":\"0\",\"lastver\":0,\"defaultjson\":{\"normal_reward_video\":[{\"type\":0,\"TTAD\":{\"appid\":\"5026701\",\"advertId\":\"926701821\",\"rate\":100},\"GDTAD\":{\"appid\":\"1109819649\",\"advertId\":\"4010999188209952\",\"rate\":0}},{\"type\":1,\"TTAD\":{\"appid\":\"5026701\",\"advertId\":\"926701380\",\"rate\":100},\"GDTAD\":{\"appid\":\"1109819649\",\"advertId\":\"3070598118206984\",\"rate\":0}},{\"type\":2,\"TTAD\":{\"appid\":\"5026701\",\"advertId\":\"926701984\",\"rate\":100},\"GDTAD\":{\"appid\":\"1109819649\",\"advertId\":\"7090595128505837\",\"rate\":0}}]}}");


        //第三方统计初始化
        StatisticsManager.initStatistics(this, "", "8684e4c844fde3c4f7db6ca3242807c4", UMUtils.getChannelByXML(this));



        findViewById(R.id.tv_show_byte_dance_banner).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Banner banner = new Banner();
                banner.showBanner("normal_reward_video", true);
                banner.setBannerAdListener(new BannerAdListener() {
                    @Override
                    public void onBannerLoaded() {
                        LogUtils.e("加载完毕----------");
                    }

                    @Override
                    public void onBannerFailed(String errorMsg) {
                        LogUtils.e("加载失败");
                    }

                    @Override
                    public void onBannerClicked() {
                        LogUtils.e("点击");
                    }
                });

            }
        });
        findViewById(R.id.tv_hide_banner).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                banner.hideBanner();

            }
        });

        findViewById(R.id.tv_load_interstitial).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Interstitial interstitial = new Interstitial();
                interstitial.loadInterstitialAd("normal_reward_video");
                interstitial.setInterstitialAdListener(new InterstitialAdListener() {
                    @Override
                    public void onInterstitialLoaded() {
                        LogUtils.e("加载成功+++++");
                    }

                    @Override
                    public void onInterstitialFailed(String errorMsg) {

                    }

                    @Override
                    public void onInterstitialShown() {
                        LogUtils.e("显示成功+++++");
                    }

                    @Override
                    public void onInterstitialClicked() {
                        LogUtils.e("点击+++++");
                    }

                    @Override
                    public void onInterstitialDismissed() {
                        LogUtils.e("关闭+++++");
                    }
                });
            }
        });

        findViewById(R.id.tv_show_interstitial).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                AdManager.showInterstitialAd();
                interstitial.showInterstitialAd();

            }
        });

        findViewById(R.id.tv_load_reward_video).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                AdManager.loadRewardVideoAd("normal_reward_video");
                RewardVideo rewardVideo = new RewardVideo();
                rewardVideo.loadRewardedVideoAd("normal_reward_video");
                rewardVideo.showRewardedVideoAd();
                rewardVideo.setOnRewardVideoAdListener(new RewardedAdListener() {
                    @Override
                    public void onRewardedVideoLoadSuccess() {

                    }

                    @Override
                    public void onRewardedVideoLoadFailure(String s) {

                    }

                    @Override
                    public void onRewardedVideoClicked() {

                    }

                    @Override
                    public void onRewardedVideoClosed() {

                    }

                    @Override
                    public void onRewardedVideoCompleted() {

                    }

                    @Override
                    public void onRewardedVideoStarted() {

                    }
                });
            }
        });

        findViewById(R.id.tv_show_reward_video).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                AdManager.showRewardedVideoAd("normal_reward_video");
                rewardVideo.showRewardedVideoAd();
            }
        });

        findViewById(R.id.tv_test_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                StatisticsManager.startLevel("10");
                WebViewUtil.pay(MainActivity.this, "https://chinaapi.youloft.com/payview/index?orderid=633f64bb-e734-4d70-b74b-ab4400aa1917", "https://chinaapi.youloft.com/returnapp");
            }
        });

        findViewById(R.id.tv_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiManager.userLogin("123", 99, "Android测试", "https://www.jsiahsiah.hiji.com");

            }
        });

        findViewById(R.id.tv_sync).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiManager.userSync("", 11);
            }
        });

        findViewById(R.id.tv_sync_stream).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiManager.userSyncStream("", 11);
            }
        });

        findViewById(R.id.tv_heartbeat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiManager.userHeartbeat();
            }
        });

        findViewById(R.id.tv_user_order).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiManager.userOrder("900a0733-2e50-4479-99b9-df331e4fbab8");
            }
        });

        findViewById(R.id.tv_resume_purchase).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiManager.userResumepurchase();
            }
        });

        findViewById(R.id.tv_consume).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiManager.userConsume("900a0733-2e50-4479-99b9-df331e4fbab8", "c8ac1773-ab8b-4728-952d-ab7901127006");

            }
        });

        findViewById(R.id.tv_query_order).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiManager.userQueryOrder("900a0733-2e50-4479-99b9-df331e4fbab8", "915b9ad0-bb55-47c9-81b2-ab7900f1b81e");
            }
        });


        findViewById(R.id.tv_leader_board).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiManager.userLeaderBoard();
            }
        });


        findViewById(R.id.tv_upload_socre).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiManager.userUploadScore(10, "测试排行榜描述第10名", "900a4b40-783e-4c1e-b77d-d75570ae23ce");
            }
        });

        findViewById(R.id.tv_get_user_rank).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiManager.userGetUserRank("900a4b40-783e-4c1e-b77d-d75570ae23ce");
            }
        });

        findViewById(R.id.tv_channelpay_order).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiManager.channelPayOrder(7, "900a0733-2e50-4479-99b9-df331e4fbab8");
            }
        });

        findViewById(R.id.tv_channelpay_version).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiManager.channelPayGetAppVersion(3);
            }
        });

        findViewById(R.id.tv_config_sync).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ApiManager.configSync("0",0);
//                AdManager.initAdSdk(MainActivity.this,
//                        "{\n" +
//                                "\t\"appver\": \"0\",\n" +
//                                "\t\"gamesecret\": \"6ef864206a884e81819985c78767edad\",\n" +
//                                "\t\"lastver\": \"0\",\n" +
//                                "\t\"defaultjson\": \"{ \\\"normal_reward_video\\\" : \\\"[{\\\\\\\"type\\\\\\\": 0, \\\\\\\"TTAD\\\\\\\": { \\\\\\\"appid\\\\\\\": \\\\\\\"5026701\\\\\\\", \\\\\\\"advertId\\\\\\\": \\\\\\\"926701821\\\\\\\", \\\\\\\"rate\\\\\\\":50 }, \\\\\\\"GDTAD\\\\\\\": { \\\\\\\"appid\\\\\\\": \\\\\\\"1109819649\\\\\\\", \\\\\\\"advertId\\\\\\\": \\\\\\\"4010999188209952\\\\\\\", \\\\\\\"rate\\\\\\\": 50 } },{\\\\\\\"type\\\\\\\": 1, \\\\\\\"TTAD\\\\\\\": { \\\\\\\"appid\\\\\\\": \\\\\\\"5026701\\\\\\\", \\\\\\\"advertId\\\\\\\": \\\\\\\"926701380\\\\\\\", \\\\\\\"rate\\\\\\\": 50 }, \\\\\\\"GDTAD\\\\\\\": { \\\\\\\"appid\\\\\\\": \\\\\\\"1109819649\\\\\\\", \\\\\\\"advertId\\\\\\\": \\\\\\\"3070598118206984\\\\\\\", \\\\\\\"rate\\\\\\\": 50 } },{\\\\\\\"type\\\\\\\": 2, \\\\\\\"TTAD\\\\\\\": { \\\\\\\"appid\\\\\\\": \\\\\\\"5026701\\\\\\\", \\\\\\\"advertId\\\\\\\": \\\\\\\"926701984\\\\\\\", \\\\\\\"rate\\\\\\\": 50 }, \\\\\\\"GDTAD\\\\\\\": { \\\\\\\"appid\\\\\\\": \\\\\\\"1109819649\\\\\\\", \\\\\\\"advertId\\\\\\\": \\\\\\\"7090595128505837\\\\\\\", \\\\\\\"rate\\\\\\\": 50 } }]\\\"}\",\n" +
//                                "\t\"gameid\": \"\"\n" +
//                                "}");

                AdManager.initAdSdk(MainActivity.this,
                        "{\"gameid\":\"9011e420-50fd-4b62-96b4-6dcaa26514ea\",\"gamesecret\":\"6ef864206a884e81819985c78767edad\",\"appver\":\"0\",\"lastver\":0,\"defaultjson\":{\"normal_reward_video\":[{\"type\":0,\"TTAD\":{\"appid\":\"5026701\",\"advertId\":\"926701821\",\"rate\":50},\"GDTAD\":{\"appid\":\"1109819649\",\"advertId\":\"4010999188209952\",\"rate\":50}},{\"type\":1,\"TTAD\":{\"appid\":\"5026701\",\"advertId\":\"926701380\",\"rate\":50},\"GDTAD\":{\"appid\":\"1109819649\",\"advertId\":\"3070598118206984\",\"rate\":50}},{\"type\":2,\"TTAD\":{\"appid\":\"5026701\",\"advertId\":\"926701984\",\"rate\":100},\"GDTAD\":{\"appid\":\"1109819649\",\"advertId\":\"7090595128505837\",\"rate\":0}}]}}");
            }
        });

        findViewById(R.id.tv_dedeem_consume).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiManager.dedeemConsume("TEST000309");
            }
        });

    }
}
