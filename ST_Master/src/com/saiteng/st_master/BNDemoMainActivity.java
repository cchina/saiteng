package com.saiteng.st_master;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import com.baidu.navisdk.adapter.BNOuterLogUtil;
import com.baidu.navisdk.adapter.BNOuterTTSPlayerCallback;
import com.baidu.navisdk.adapter.BNRoutePlanNode;
import com.baidu.navisdk.adapter.BNRoutePlanNode.CoordinateType;
import com.baidu.navisdk.adapter.BNaviSettingManager;
import com.baidu.navisdk.adapter.BaiduNaviManager;
import com.baidu.navisdk.adapter.BaiduNaviManager.NaviInitListener;
import com.baidu.navisdk.adapter.BaiduNaviManager.RoutePlanListener;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class BNDemoMainActivity extends Activity {


	public static List<Activity> activityList = new LinkedList<Activity>();

	private static final String APP_FOLDER_NAME = "BNSDKSimpleDemo";

	private Button mWgsNaviBtn = null;
	private Button mGcjNaviBtn = null;
	private Button mBdmcNaviBtn = null;
	private Button mDb06ll = null;
	private String mSDCardPath = null;

	public static final String ROUTE_PLAN_NODE = "routePlanNode";
	public static final String SHOW_CUSTOM_ITEM = "showCustomItem";
	public static final String RESET_END_NODE = "resetEndNode";
	public static final String VOID_MODE = "voidMode";
    private Handler handler=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		activityList.add(this);
		MyApplication.getInstance().addActivity(this);
		//setContentView(R.layout.activity_routeplan);
		mWgsNaviBtn = (Button) findViewById(R.id.wgsNaviBtn);
		mGcjNaviBtn = (Button) findViewById(R.id.gcjNaviBtn);
		mBdmcNaviBtn = (Button) findViewById(R.id.bdmcNaviBtn);
		mDb06ll = (Button) findViewById(R.id.mDb06llNaviBtn);
		BNOuterLogUtil.setLogSwitcher(true);
		handler = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				//TTS语音播报 引擎 加载成功后 开始加载 导航界面
				if("true".equals(msg.obj.toString())){
					initListener();
				}else if("false".equals(msg.obj.toString())){
					BNDemoMainActivity.this.finish();
				}else{
					BNDemoMainActivity.this.finish();
				}
			}
		};
		if (initDirs()) {
			initNavi();		
		}
		 BNOuterLogUtil.setLogSwitcher(true);
	}


	@Override
	protected void onResume() {
		super.onResume();
	}

	private void initListener() {

		//if (BaiduNaviManager.isNaviInited()) {
			routeplanToNavi(CoordinateType.BD09LL);
		//}

	}


	private boolean initDirs() {
		mSDCardPath = getSdcardDir();
		if (mSDCardPath == null) {
			return false;
		}
		File f = new File(mSDCardPath, APP_FOLDER_NAME);
		if (!f.exists()) {
			try {
				f.mkdir();
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	String authinfo = null;

	/**
	  * 内部TTS播报状态回传handler
	  */
	private Handler ttsHandler = new Handler() {
	    public void handleMessage(Message msg) {
	        int type = msg.what;
	        switch (type) {
	            case BaiduNaviManager.TTSPlayMsgType.PLAY_START_MSG: {
	              //   showToastMsg("Handler : TTS play start");
	                break;
	            }
	            case BaiduNaviManager.TTSPlayMsgType.PLAY_END_MSG: {
	           //      showToastMsg("Handler : TTS play end");
	                break;
	            }
	            default :
	                break;
	        }
	    }
	};
	
	/**
	 * 内部TTS播报状态回调接口
	 */
	private BaiduNaviManager.TTSPlayStateListener ttsPlayStateListener = new BaiduNaviManager.TTSPlayStateListener() {
        
        @Override
        public void playEnd() {
            showToastMsg("TTSPlayStateListener : TTS endding");
        }
        
        @Override
        public void playStart() {
           showToastMsg("TTSPlayStateListener : TTS starting");
        }
    };
	
	public void showToastMsg(final String msg) {
	    BNDemoMainActivity.this.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                Toast.makeText(BNDemoMainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
	}
	
	private void initNavi() {	
	
		BNOuterTTSPlayerCallback ttsCallback = null;

		BaiduNaviManager.getInstance().init(this, mSDCardPath, APP_FOLDER_NAME, new NaviInitListener() {
			@Override
			public void onAuthResult(int status, String msg) {
				if (0 == status) {
					authinfo = "key校验成功!";
				} else {
					authinfo = "key校验失败, " + msg;
				}
				BNDemoMainActivity.this.runOnUiThread(new Runnable() {

					@Override
					public void run() {
						Toast.makeText(BNDemoMainActivity.this, authinfo, Toast.LENGTH_LONG).show();
					}
				});
			}
			
			public void initSuccess() {
				Toast.makeText(BNDemoMainActivity.this, "百度导航引擎初始化成功", Toast.LENGTH_SHORT).show();
				initSetting();
				Message msg = handler.obtainMessage();
				msg.obj = "true";
				handler.sendMessage(msg);
			}

			public void initStart() {
				Toast.makeText(BNDemoMainActivity.this, "百度导航引擎初始化开始", Toast.LENGTH_SHORT).show();
			}

			public void initFailed() {
				Toast.makeText(BNDemoMainActivity.this, "百度导航引擎初始化失败", Toast.LENGTH_SHORT).show();
			}
		},  null, ttsHandler, null);

	}

	private String getSdcardDir() {
		if (Environment.getExternalStorageState().equalsIgnoreCase(Environment.MEDIA_MOUNTED)) {
			return Environment.getExternalStorageDirectory().toString();
		}
		return null;
	}

	private void routeplanToNavi(CoordinateType coType) {
		BNRoutePlanNode sNode = null;
		BNRoutePlanNode eNode = null;
		switch (coType) {
			case BD09LL: {
				sNode = new BNRoutePlanNode(Config.mLongitude, Config.mLatitude, "我的位置", null, coType);
				eNode = new BNRoutePlanNode(Config.mGZLongitude, Config.mGZLatitude, "目标位置", null, coType);
				break;
			}
			default:
				;
			}
			if (sNode != null && eNode != null) {
				List<BNRoutePlanNode> list = new ArrayList<BNRoutePlanNode>();
				list.add(sNode);
				list.add(eNode);
				BaiduNaviManager.getInstance().launchNavigator(this, list, 1, true, new DemoRoutePlanListener(sNode));
			}
	}

	public class DemoRoutePlanListener implements RoutePlanListener {

		private BNRoutePlanNode mBNRoutePlanNode = null;

		public DemoRoutePlanListener(BNRoutePlanNode node) {
			mBNRoutePlanNode = node;
		}

		@Override
		public void onJumpToNavigator() {
			/*
			 * 设置途径点以及resetEndNode会回调该接口
			 */
		 
			for (Activity ac : activityList) {
			   
				if (ac.getClass().getName().endsWith("BNDemoGuideActivity")) {
				 
					return;
				}
			}
			Intent intent = new Intent(BNDemoMainActivity.this, BNDemoGuideActivity.class);
			Bundle bundle = new Bundle();
			bundle.putSerializable(ROUTE_PLAN_NODE, (BNRoutePlanNode) mBNRoutePlanNode);
			intent.putExtras(bundle);
			startActivity(intent);
			
		}

		@Override
		public void onRoutePlanFailed() {
			if(Config.mGZLongitude==0||Config.mGZLatitude==0){
				Toast.makeText(BNDemoMainActivity.this, "请先监控到目标点的位置", Toast.LENGTH_SHORT).show();
			}else
				Toast.makeText(BNDemoMainActivity.this, "算路失败", Toast.LENGTH_SHORT).show();
		}
	}
	
	private void initSetting(){
	    BNaviSettingManager.setDayNightMode(BNaviSettingManager.DayNightMode.DAY_NIGHT_MODE_DAY);
	    BNaviSettingManager.setShowTotalRoadConditionBar(BNaviSettingManager.PreViewRoadCondition.ROAD_CONDITION_BAR_SHOW_ON);
	    BNaviSettingManager.setVoiceMode(BNaviSettingManager.VoiceMode.Veteran);	    
        BNaviSettingManager.setPowerSaveMode(BNaviSettingManager.PowerSaveMode.DISABLE_MODE);
        BNaviSettingManager.setRealRoadCondition(BNaviSettingManager.RealRoadCondition.NAVI_ITS_ON);
	}
	
	private BNOuterTTSPlayerCallback mTTSCallback = new BNOuterTTSPlayerCallback() {

		@Override
		public void stopTTS() {
			// TODO Auto-generated method stub
			Log.e("test_TTS", "stopTTS");
		}

		@Override
		public void resumeTTS() {
			// TODO Auto-generated method stub
			Log.e("test_TTS", "resumeTTS");
		}

		@Override
		public void releaseTTSPlayer() {
			// TODO Auto-generated method stub
			Log.e("test_TTS", "releaseTTSPlayer");
		}

		@Override
		public int playTTSText(String speech, int bPreempt) {
			// TODO Auto-generated method stub
			Log.e("test_TTS", "playTTSText" + "_" + speech + "_" + bPreempt);
			
			return 1;
		}

		@Override
		public void phoneHangUp() {
			// TODO Auto-generated method stub
			Log.e("test_TTS", "phoneHangUp");
		}

		@Override
		public void phoneCalling() {
			// TODO Auto-generated method stub
			Log.e("test_TTS", "phoneCalling");
		}

		@Override
		public void pauseTTS() {
			// TODO Auto-generated method stub
			Log.e("test_TTS", "pauseTTS");
		}

		@Override
		public void initTTSPlayer() {
			// TODO Auto-generated method stub
			Log.e("test_TTS", "initTTSPlayer");
		}

		@Override
		public int getTTSState() {
			// TODO Auto-generated method stub
			Log.e("test_TTS", "getTTSState");
			return 1;
		}
	};

}
