package ir.safhebeshkan.apkkala;


import anywheresoftware.b4a.B4AMenuItem;
import android.app.Activity;
import android.os.Bundle;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.B4AActivity;
import anywheresoftware.b4a.ObjectWrapper;
import anywheresoftware.b4a.objects.ActivityWrapper;
import java.lang.reflect.InvocationTargetException;
import anywheresoftware.b4a.B4AUncaughtException;
import anywheresoftware.b4a.debug.*;
import java.lang.ref.WeakReference;

public class main extends Activity implements B4AActivity{
	public static main mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = false;
	public static final boolean includeTitle = true;
    public static WeakReference<Activity> previousOne;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (processBA == null) {
			processBA = new BA(this.getApplicationContext(), null, null, "ir.safhebeshkan.apkkala", "ir.safhebeshkan.apkkala.main");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (main).");
				p.finish();
			}
		}
        processBA.runHook("oncreate", this, null);
		if (!includeTitle) {
        	this.getWindow().requestFeature(android.view.Window.FEATURE_NO_TITLE);
        }
        if (fullScreen) {
        	getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,   
        			android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
		mostCurrent = this;
        processBA.sharedProcessBA.activityBA = null;
		layout = new BALayout(this);
		setContentView(layout);
		afterFirstLayout = false;
        WaitForLayout wl = new WaitForLayout();
        if (anywheresoftware.b4a.objects.ServiceHelper.StarterHelper.startFromActivity(processBA, wl, false))
		    BA.handler.postDelayed(wl, 5);

	}
	static class WaitForLayout implements Runnable {
		public void run() {
			if (afterFirstLayout)
				return;
			if (mostCurrent == null)
				return;
            
			if (mostCurrent.layout.getWidth() == 0) {
				BA.handler.postDelayed(this, 5);
				return;
			}
			mostCurrent.layout.getLayoutParams().height = mostCurrent.layout.getHeight();
			mostCurrent.layout.getLayoutParams().width = mostCurrent.layout.getWidth();
			afterFirstLayout = true;
			mostCurrent.afterFirstLayout();
		}
	}
	private void afterFirstLayout() {
        if (this != mostCurrent)
			return;
		activityBA = new BA(this, layout, processBA, "ir.safhebeshkan.apkkala", "ir.safhebeshkan.apkkala.main");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "ir.safhebeshkan.apkkala.main", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (main) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (main) Resume **");
        processBA.raiseEvent(null, "activity_resume");
        if (android.os.Build.VERSION.SDK_INT >= 11) {
			try {
				android.app.Activity.class.getMethod("invalidateOptionsMenu").invoke(this,(Object[]) null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	public void addMenuItem(B4AMenuItem item) {
		if (menuItems == null)
			menuItems = new java.util.ArrayList<B4AMenuItem>();
		menuItems.add(item);
	}
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
        try {
            if (processBA.subExists("activity_actionbarhomeclick")) {
                Class.forName("android.app.ActionBar").getMethod("setHomeButtonEnabled", boolean.class).invoke(
                    getClass().getMethod("getActionBar").invoke(this), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (processBA.runHook("oncreateoptionsmenu", this, new Object[] {menu}))
            return true;
		if (menuItems == null)
			return false;
		for (B4AMenuItem bmi : menuItems) {
			android.view.MenuItem mi = menu.add(bmi.title);
			if (bmi.drawable != null)
				mi.setIcon(bmi.drawable);
            if (android.os.Build.VERSION.SDK_INT >= 11) {
				try {
                    if (bmi.addToBar) {
				        android.view.MenuItem.class.getMethod("setShowAsAction", int.class).invoke(mi, 1);
                    }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			mi.setOnMenuItemClickListener(new B4AMenuItemsClickListener(bmi.eventName.toLowerCase(BA.cul)));
		}
        
		return true;
	}   
 @Override
 public boolean onOptionsItemSelected(android.view.MenuItem item) {
    if (item.getItemId() == 16908332) {
        processBA.raiseEvent(null, "activity_actionbarhomeclick");
        return true;
    }
    else
        return super.onOptionsItemSelected(item); 
}
@Override
 public boolean onPrepareOptionsMenu(android.view.Menu menu) {
    super.onPrepareOptionsMenu(menu);
    processBA.runHook("onprepareoptionsmenu", this, new Object[] {menu});
    return true;
    
 }
 protected void onStart() {
    super.onStart();
    processBA.runHook("onstart", this, null);
}
 protected void onStop() {
    super.onStop();
    processBA.runHook("onstop", this, null);
}
    public void onWindowFocusChanged(boolean hasFocus) {
       super.onWindowFocusChanged(hasFocus);
       if (processBA.subExists("activity_windowfocuschanged"))
           processBA.raiseEvent2(null, true, "activity_windowfocuschanged", false, hasFocus);
    }
	private class B4AMenuItemsClickListener implements android.view.MenuItem.OnMenuItemClickListener {
		private final String eventName;
		public B4AMenuItemsClickListener(String eventName) {
			this.eventName = eventName;
		}
		public boolean onMenuItemClick(android.view.MenuItem item) {
			processBA.raiseEventFromUI(item.getTitle(), eventName + "_click");
			return true;
		}
	}
    public static Class<?> getObject() {
		return main.class;
	}
    private Boolean onKeySubExist = null;
    private Boolean onKeyUpSubExist = null;
	@Override
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeydown", this, new Object[] {keyCode, event}))
            return true;
		if (onKeySubExist == null)
			onKeySubExist = processBA.subExists("activity_keypress");
		if (onKeySubExist) {
			if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK &&
					android.os.Build.VERSION.SDK_INT >= 18) {
				HandleKeyDelayed hk = new HandleKeyDelayed();
				hk.kc = keyCode;
				BA.handler.post(hk);
				return true;
			}
			else {
				boolean res = new HandleKeyDelayed().runDirectly(keyCode);
				if (res)
					return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	private class HandleKeyDelayed implements Runnable {
		int kc;
		public void run() {
			runDirectly(kc);
		}
		public boolean runDirectly(int keyCode) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keypress", false, keyCode);
			if (res == null || res == true) {
                return true;
            }
            else if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK) {
				finish();
				return true;
			}
            return false;
		}
		
	}
    @Override
	public boolean onKeyUp(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeyup", this, new Object[] {keyCode, event}))
            return true;
		if (onKeyUpSubExist == null)
			onKeyUpSubExist = processBA.subExists("activity_keyup");
		if (onKeyUpSubExist) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keyup", false, keyCode);
			if (res == null || res == true)
				return true;
		}
		return super.onKeyUp(keyCode, event);
	}
	@Override
	public void onNewIntent(android.content.Intent intent) {
        super.onNewIntent(intent);
		this.setIntent(intent);
        processBA.runHook("onnewintent", this, new Object[] {intent});
	}
    @Override 
	public void onPause() {
		super.onPause();
        if (_activity == null) //workaround for emulator bug (Issue 2423)
            return;
		anywheresoftware.b4a.Msgbox.dismiss(true);
        BA.LogInfo("** Activity (main) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        if (mostCurrent != null)
            processBA.raiseEvent2(_activity, true, "activity_pause", false, activityBA.activity.isFinishing());		
        processBA.setActivityPaused(true);
        mostCurrent = null;
        if (!activityBA.activity.isFinishing())
			previousOne = new WeakReference<Activity>(this);
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        processBA.runHook("onpause", this, null);
	}

	@Override
	public void onDestroy() {
        super.onDestroy();
		previousOne = null;
        processBA.runHook("ondestroy", this, null);
	}
    @Override 
	public void onResume() {
		super.onResume();
        mostCurrent = this;
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (activityBA != null) { //will be null during activity create (which waits for AfterLayout).
        	ResumeMessage rm = new ResumeMessage(mostCurrent);
        	BA.handler.post(rm);
        }
        processBA.runHook("onresume", this, null);
	}
    private static class ResumeMessage implements Runnable {
    	private final WeakReference<Activity> activity;
    	public ResumeMessage(Activity activity) {
    		this.activity = new WeakReference<Activity>(activity);
    	}
		public void run() {
			if (mostCurrent == null || mostCurrent != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (main) Resume **");
		    processBA.raiseEvent(mostCurrent._activity, "activity_resume", (Object[])null);
		}
    }
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
	      android.content.Intent data) {
		processBA.onActivityResult(requestCode, resultCode, data);
        processBA.runHook("onactivityresult", this, new Object[] {requestCode, resultCode});
	}
	private static void initializeGlobals() {
		processBA.raiseEvent2(null, true, "globals", false, (Object[])null);
	}
    public void onRequestPermissionsResult(int requestCode,
        String permissions[], int[] grantResults) {
        for (int i = 0;i < permissions.length;i++) {
            Object[] o = new Object[] {permissions[i], grantResults[i] == 0};
            processBA.raiseEventFromDifferentThread(null,null, 0, "activity_permissionresult", true, o);
        }
            
    }

public anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4a.objects.Timer _tmr1 = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnl1 = null;
public static boolean _bnlset = false;
public anywheresoftware.b4a.objects.ImageViewWrapper _imgtemp = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _imgsetting = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _imgabout = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _imgexit = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _imgstart = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbltitle = null;
public anywheresoftware.b4a.objects.EditTextWrapper _label1 = null;
public co.ronash.pushe.wrapper.PusheWrapper _pushe = null;
public anywheresoftware.b4a.objects.ButtonWrapper _button1 = null;
public ir.safhebeshkan.apkkala.actabout _actabout = null;
public ir.safhebeshkan.apkkala.acticon _acticon = null;
public ir.safhebeshkan.apkkala.actvis _actvis = null;
public ir.safhebeshkan.apkkala.mycode _mycode = null;
public ir.safhebeshkan.apkkala.service1 _service1 = null;
public ir.safhebeshkan.apkkala.starter _starter = null;

public static boolean isAnyActivityVisible() {
    boolean vis = false;
vis = vis | (main.mostCurrent != null);
vis = vis | (actabout.mostCurrent != null);
vis = vis | (acticon.mostCurrent != null);
vis = vis | (actvis.mostCurrent != null);
return vis;}
public static String  _activity_create(boolean _firsttime) throws Exception{
String _verticalposition = "";
String _horizontalposition = "";
anywheresoftware.b4a.agraham.reflection.Reflection _r = null;
Object[] _args = null;
String[] _types = null;
 //BA.debugLineNum = 36;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 38;BA.debugLine="Activity.LoadLayout(\"1\")";
mostCurrent._activity.LoadLayout("1",mostCurrent.activityBA);
 //BA.debugLineNum = 40;BA.debugLine="Pushe.initialize()";
mostCurrent._pushe.initialize(processBA);
 //BA.debugLineNum = 42;BA.debugLine="pnl1.Color = Colors.RGB(229,226,226)";
mostCurrent._pnl1.setColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (229),(int) (226),(int) (226)));
 //BA.debugLineNum = 43;BA.debugLine="lbltitle.Typeface = Typeface.LoadFromAssets(\"SROYA";
mostCurrent._lbltitle.setTypeface(anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets("SROYA.TTF"));
 //BA.debugLineNum = 44;BA.debugLine="Label1.Typeface = Typeface.LoadFromAssets(\"SROYA.T";
mostCurrent._label1.setTypeface(anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets("SROYA.TTF"));
 //BA.debugLineNum = 45;BA.debugLine="Label1.Color = Colors.Transparent";
mostCurrent._label1.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 46;BA.debugLine="tmr1.Initialize(\"tmr1\",100)";
_tmr1.Initialize(processBA,"tmr1",(long) (100));
 //BA.debugLineNum = 48;BA.debugLine="If mycode.pre.GetBoolean(\"first\") = False Then";
if (mostCurrent._mycode._pre.GetBoolean("first")==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 49;BA.debugLine="mycode.pre.SetBoolean(\"first\",True)";
mostCurrent._mycode._pre.SetBoolean("first",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 50;BA.debugLine="mycode.pre.SetBoolean(\"play\",True)";
mostCurrent._mycode._pre.SetBoolean("play",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 51;BA.debugLine="mycode.pre.SetBoolean(\"lock\",True)";
mostCurrent._mycode._pre.SetBoolean("lock",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 52;BA.debugLine="mycode.pre.SetBoolean(\"vibrate\",True)";
mostCurrent._mycode._pre.SetBoolean("vibrate",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 53;BA.debugLine="mycode.pre.SetString(\"op\",\"128\")";
mostCurrent._mycode._pre.SetString("op","128");
 };
 //BA.debugLineNum = 55;BA.debugLine="Dim verticalPosition As String = \"bottom\" '\"top\"";
_verticalposition = "bottom";
 //BA.debugLineNum = 56;BA.debugLine="Dim horizontalPosition As String = \"center\" '\"";
_horizontalposition = "center";
 //BA.debugLineNum = 57;BA.debugLine="Dim r As Reflector";
_r = new anywheresoftware.b4a.agraham.reflection.Reflection();
 //BA.debugLineNum = 58;BA.debugLine="Dim args(3) As Object = Array As Object(r.GetA";
_args = new Object[]{(Object)(_r.GetActivity(processBA)),(Object)(_verticalposition),(Object)(_horizontalposition)};
 //BA.debugLineNum = 59;BA.debugLine="Dim types(3) As String = Array As String(\"andr";
_types = new String[]{"android.app.Activity","java.lang.String","java.lang.String"};
 //BA.debugLineNum = 61;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 161;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 162;BA.debugLine="If KeyCode = KeyCodes.KEYCODE_BACK Then";
if (_keycode==anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK) { 
 //BA.debugLineNum = 163;BA.debugLine="exitApp";
_exitapp();
 //BA.debugLineNum = 164;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 166;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 68;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 70;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 63;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 64;BA.debugLine="StopService(service1)";
anywheresoftware.b4a.keywords.Common.StopService(processBA,(Object)(mostCurrent._service1.getObject()));
 //BA.debugLineNum = 65;BA.debugLine="CancelScheduledService(service1)";
anywheresoftware.b4a.keywords.Common.CancelScheduledService(processBA,(Object)(mostCurrent._service1.getObject()));
 //BA.debugLineNum = 66;BA.debugLine="End Sub";
return "";
}
public static String  _btnsetting_click() throws Exception{
 //BA.debugLineNum = 168;BA.debugLine="Sub btnsetting_Click";
 //BA.debugLineNum = 169;BA.debugLine="settings";
_settings();
 //BA.debugLineNum = 170;BA.debugLine="End Sub";
return "";
}
public static String  _button1_click() throws Exception{
anywheresoftware.b4a.objects.IntentWrapper _i = null;
 //BA.debugLineNum = 174;BA.debugLine="Sub Button1_Click";
 //BA.debugLineNum = 177;BA.debugLine="Private i As Intent";
_i = new anywheresoftware.b4a.objects.IntentWrapper();
 //BA.debugLineNum = 178;BA.debugLine="i.Initialize(i.ACTION_VIEW,\"https://cafebazaar.ir";
_i.Initialize(_i.ACTION_VIEW,"https://cafebazaar.ir/developer/402714907687/?l=fa");
 //BA.debugLineNum = 179;BA.debugLine="StartActivity(i)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(_i.getObject()));
 //BA.debugLineNum = 181;BA.debugLine="End Sub";
return "";
}
public static String  _exitapp() throws Exception{
int _i = 0;
 //BA.debugLineNum = 139;BA.debugLine="Sub exitApp";
 //BA.debugLineNum = 140;BA.debugLine="Dim i As Int";
_i = 0;
 //BA.debugLineNum = 141;BA.debugLine="i = Msgbox2(\"آیا مایل به خروج از برنامه هستید؟\",\"خ";
_i = anywheresoftware.b4a.keywords.Common.Msgbox2(BA.ObjectToCharSequence("آیا مایل به خروج از برنامه هستید؟"),BA.ObjectToCharSequence("خروج"),"بله","خیر","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null),mostCurrent.activityBA);
 //BA.debugLineNum = 142;BA.debugLine="If i = DialogResponse.POSITIVE Then";
if (_i==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 143;BA.debugLine="StopService(service1)";
anywheresoftware.b4a.keywords.Common.StopService(processBA,(Object)(mostCurrent._service1.getObject()));
 //BA.debugLineNum = 144;BA.debugLine="CancelScheduledService(service1)";
anywheresoftware.b4a.keywords.Common.CancelScheduledService(processBA,(Object)(mostCurrent._service1.getObject()));
 //BA.debugLineNum = 145;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 };
 //BA.debugLineNum = 147;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 21;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 22;BA.debugLine="Private pnl1 As Panel";
mostCurrent._pnl1 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 23;BA.debugLine="Dim bnlset As Boolean";
_bnlset = false;
 //BA.debugLineNum = 24;BA.debugLine="Dim imgTemp As ImageView";
mostCurrent._imgtemp = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 25;BA.debugLine="Private imgsetting As ImageView";
mostCurrent._imgsetting = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 26;BA.debugLine="Private imgabout As ImageView";
mostCurrent._imgabout = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 27;BA.debugLine="Private imgexit As ImageView";
mostCurrent._imgexit = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 28;BA.debugLine="Private imgstart As ImageView";
mostCurrent._imgstart = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 29;BA.debugLine="Private lbltitle As Label";
mostCurrent._lbltitle = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 30;BA.debugLine="Private Label1 As EditText";
mostCurrent._label1 = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 31;BA.debugLine="Dim Pushe As Pushe";
mostCurrent._pushe = new co.ronash.pushe.wrapper.PusheWrapper();
 //BA.debugLineNum = 33;BA.debugLine="Private Button1 As Button";
mostCurrent._button1 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 34;BA.debugLine="End Sub";
return "";
}
public static String  _imgabout_click() throws Exception{
 //BA.debugLineNum = 91;BA.debugLine="Sub imgabout_Click";
 //BA.debugLineNum = 92;BA.debugLine="mycode.playAudio(\"b.mp3\")";
mostCurrent._mycode._playaudio(mostCurrent.activityBA,"b.mp3");
 //BA.debugLineNum = 93;BA.debugLine="imgTemp = imgabout";
mostCurrent._imgtemp = mostCurrent._imgabout;
 //BA.debugLineNum = 94;BA.debugLine="tmr1.Enabled = True";
_tmr1.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 95;BA.debugLine="End Sub";
return "";
}
public static String  _imgexit_click() throws Exception{
 //BA.debugLineNum = 85;BA.debugLine="Sub imgexit_Click";
 //BA.debugLineNum = 86;BA.debugLine="mycode.playAudio(\"b.mp3\")";
mostCurrent._mycode._playaudio(mostCurrent.activityBA,"b.mp3");
 //BA.debugLineNum = 87;BA.debugLine="imgTemp = imgexit";
mostCurrent._imgtemp = mostCurrent._imgexit;
 //BA.debugLineNum = 88;BA.debugLine="tmr1.Enabled = True";
_tmr1.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 89;BA.debugLine="End Sub";
return "";
}
public static String  _imgsetting_click() throws Exception{
 //BA.debugLineNum = 97;BA.debugLine="Sub imgsetting_Click";
 //BA.debugLineNum = 98;BA.debugLine="mycode.playAudio(\"b.mp3\")";
mostCurrent._mycode._playaudio(mostCurrent.activityBA,"b.mp3");
 //BA.debugLineNum = 99;BA.debugLine="imgTemp = imgsetting";
mostCurrent._imgtemp = mostCurrent._imgsetting;
 //BA.debugLineNum = 100;BA.debugLine="tmr1.Enabled = True";
_tmr1.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 101;BA.debugLine="End Sub";
return "";
}
public static String  _imgstart_click() throws Exception{
 //BA.debugLineNum = 103;BA.debugLine="Sub imgstart_Click";
 //BA.debugLineNum = 104;BA.debugLine="mycode.playAudio(\"b.mp3\")";
mostCurrent._mycode._playaudio(mostCurrent.activityBA,"b.mp3");
 //BA.debugLineNum = 105;BA.debugLine="imgTemp = imgstart";
mostCurrent._imgtemp = mostCurrent._imgstart;
 //BA.debugLineNum = 106;BA.debugLine="tmr1.Enabled = True";
_tmr1.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 107;BA.debugLine="End Sub";
return "";
}

public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        main._process_globals();
actabout._process_globals();
acticon._process_globals();
actvis._process_globals();
mycode._process_globals();
service1._process_globals();
starter._process_globals();
		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 16;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 18;BA.debugLine="Dim tmr1 As Timer";
_tmr1 = new anywheresoftware.b4a.objects.Timer();
 //BA.debugLineNum = 19;BA.debugLine="End Sub";
return "";
}
public static String  _selectaudio() throws Exception{
com.maximus.id.id _id = null;
int _i = 0;
 //BA.debugLineNum = 129;BA.debugLine="Sub selectaudio";
 //BA.debugLineNum = 130;BA.debugLine="Dim id As id";
_id = new com.maximus.id.id();
 //BA.debugLineNum = 131;BA.debugLine="Dim i As Int";
_i = 0;
 //BA.debugLineNum = 132;BA.debugLine="i = id.InputList1(Array As String(\"صدای شماره 1\",\"";
_i = _id.InputList1(anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{"صدای شماره 1","صدای شماره 2","صدای شماره 3","صدای شماره 4","صدای شماره 5","صدای شماره 6"}),"انتخاب صدای شکسته شدن صفحه",mostCurrent.activityBA);
 //BA.debugLineNum = 133;BA.debugLine="If i <> -3 Then";
if (_i!=-3) { 
 //BA.debugLineNum = 134;BA.debugLine="mycode.pre.SetString(\"audio\",i & \".wav\")";
mostCurrent._mycode._pre.SetString("audio",BA.NumberToString(_i)+".wav");
 //BA.debugLineNum = 135;BA.debugLine="ToastMessageShow(\"صدای مورد تغییر کرد\",False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("صدای مورد تغییر کرد"),anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 137;BA.debugLine="End Sub";
return "";
}
public static String  _settings() throws Exception{
anywheresoftware.b4a.objects.preferenceactivity.PreferenceCategoryWrapper _cat1 = null;
anywheresoftware.b4a.objects.preferenceactivity.PreferenceScreenWrapper _intentscreen = null;
 //BA.debugLineNum = 72;BA.debugLine="Sub settings";
 //BA.debugLineNum = 73;BA.debugLine="Dim cat1 As AHPreferenceCategory";
_cat1 = new anywheresoftware.b4a.objects.preferenceactivity.PreferenceCategoryWrapper();
 //BA.debugLineNum = 74;BA.debugLine="Dim intentScreen As AHPreferenceScreen";
_intentscreen = new anywheresoftware.b4a.objects.preferenceactivity.PreferenceScreenWrapper();
 //BA.debugLineNum = 75;BA.debugLine="intentScreen.Initialize(\"تنظیمات\",\"تنظیمات برنامه\"";
_intentscreen.Initialize("تنظیمات","تنظیمات برنامه");
 //BA.debugLineNum = 76;BA.debugLine="cat1.Initialize(\"صفحه گوشیتو بشکن خخخ\")";
_cat1.Initialize("صفحه گوشیتو بشکن خخخ");
 //BA.debugLineNum = 77;BA.debugLine="cat1.AddCheckBox(\"play\", \"قطع شدن صدا حین شکستن صف";
_cat1.AddCheckBox("play","قطع شدن صدا حین شکستن صفحه گوشی","قطع است","با صدا است",anywheresoftware.b4a.keywords.Common.False,"");
 //BA.debugLineNum = 78;BA.debugLine="cat1.AddEditText(\"op\",\"شفافیت تصویر\",\"با نمایش تصو";
_cat1.AddEditText("op","شفافیت تصویر","با نمایش تصویر،شفافیت آن را میتوانید کم یا زیاد کنید که بین 1 تا 255 میباشد","128","");
 //BA.debugLineNum = 79;BA.debugLine="cat1.AddCheckBox(\"vibrate\",\"ویبره خوردن گوشی حین ش";
_cat1.AddCheckBox("vibrate","ویبره خوردن گوشی حین شکسته شدن صفحه","فعال","غیرفعال",anywheresoftware.b4a.keywords.Common.False,"");
 //BA.debugLineNum = 80;BA.debugLine="cat1.AddCheckBox(\"lock\",\"قفل شدن دکمه برگشت گوشی ح";
_cat1.AddCheckBox("lock","قفل شدن دکمه برگشت گوشی حین شکستن صفحه","فعال","غیرفعال",anywheresoftware.b4a.keywords.Common.False,"");
 //BA.debugLineNum = 81;BA.debugLine="intentScreen.AddPreferenceCategory(cat1)";
_intentscreen.AddPreferenceCategory(_cat1);
 //BA.debugLineNum = 82;BA.debugLine="StartActivity(intentScreen.CreateIntent)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(_intentscreen.CreateIntent()));
 //BA.debugLineNum = 83;BA.debugLine="End Sub";
return "";
}
public static String  _showbreak() throws Exception{
anywheresoftware.b4a.agraham.dialogs.InputDialog _in1 = null;
 //BA.debugLineNum = 149;BA.debugLine="Sub showBreak";
 //BA.debugLineNum = 150;BA.debugLine="Dim in1 As InputDialog";
_in1 = new anywheresoftware.b4a.agraham.dialogs.InputDialog();
 //BA.debugLineNum = 151;BA.debugLine="in1.InputType = in1.INPUT_TYPE_NUMBERS";
_in1.setInputType(_in1.INPUT_TYPE_NUMBERS);
 //BA.debugLineNum = 152;BA.debugLine="If in1.Show(\"لطفا زمان را به ثانیه برای فعال شدن و";
if (_in1.Show("لطفا زمان را به ثانیه برای فعال شدن وارد کنید","توجه","شروع کن","انصراف","",mostCurrent.activityBA,(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null))==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 153;BA.debugLine="If IsNumber(in1.Input) Then";
if (anywheresoftware.b4a.keywords.Common.IsNumber(_in1.getInput())) { 
 //BA.debugLineNum = 154;BA.debugLine="StartServiceAt(service1,DateTime.Now + in1.Input";
anywheresoftware.b4a.keywords.Common.StartServiceAt(processBA,(Object)(mostCurrent._service1.getObject()),(long) (anywheresoftware.b4a.keywords.Common.DateTime.getNow()+(double)(Double.parseDouble(_in1.getInput()))*1000),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 155;BA.debugLine="ToastMessageShow(\"برنامه فعال شد\",False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("برنامه فعال شد"),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 156;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 };
 };
 //BA.debugLineNum = 159;BA.debugLine="End Sub";
return "";
}
public static String  _tmr1_tick() throws Exception{
 //BA.debugLineNum = 109;BA.debugLine="Sub tmr1_Tick";
 //BA.debugLineNum = 110;BA.debugLine="If bnlset = False Then";
if (_bnlset==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 111;BA.debugLine="imgTemp.Top = imgTemp.Top + 15dip";
mostCurrent._imgtemp.setTop((int) (mostCurrent._imgtemp.getTop()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (15))));
 //BA.debugLineNum = 112;BA.debugLine="bnlset = True";
_bnlset = anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 114;BA.debugLine="imgTemp.Top = imgTemp.Top - 15dip";
mostCurrent._imgtemp.setTop((int) (mostCurrent._imgtemp.getTop()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (15))));
 //BA.debugLineNum = 115;BA.debugLine="bnlset = False";
_bnlset = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 116;BA.debugLine="tmr1.Enabled = False";
_tmr1.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 117;BA.debugLine="If imgTemp.Tag = \"about\" Then";
if ((mostCurrent._imgtemp.getTag()).equals((Object)("about"))) { 
 //BA.debugLineNum = 118;BA.debugLine="StartActivity(actIcon)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._acticon.getObject()));
 }else if((mostCurrent._imgtemp.getTag()).equals((Object)("exit"))) { 
 //BA.debugLineNum = 120;BA.debugLine="exitApp";
_exitapp();
 }else if((mostCurrent._imgtemp.getTag()).equals((Object)("setting"))) { 
 //BA.debugLineNum = 122;BA.debugLine="selectaudio";
_selectaudio();
 }else if((mostCurrent._imgtemp.getTag()).equals((Object)("start"))) { 
 //BA.debugLineNum = 124;BA.debugLine="showBreak";
_showbreak();
 };
 };
 //BA.debugLineNum = 127;BA.debugLine="End Sub";
return "";
}
}
