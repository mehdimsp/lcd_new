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

public class actvis extends Activity implements B4AActivity{
	public static actvis mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = true;
	public static final boolean includeTitle = false;
    public static WeakReference<Activity> previousOne;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (processBA == null) {
			processBA = new BA(this.getApplicationContext(), null, null, "ir.safhebeshkan.apkkala", "ir.safhebeshkan.apkkala.actvis");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (actvis).");
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
		activityBA = new BA(this, layout, processBA, "ir.safhebeshkan.apkkala", "ir.safhebeshkan.apkkala.actvis");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "ir.safhebeshkan.apkkala.actvis", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (actvis) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (actvis) Resume **");
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
		return actvis.class;
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
        BA.LogInfo("** Activity (actvis) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            BA.LogInfo("** Activity (actvis) Resume **");
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
public anywheresoftware.b4a.objects.PanelWrapper _im = null;
public static boolean _f5 = false;
public anywheresoftware.b4a.phone.Phone _shake1 = null;
public ir.safhebeshkan.apkkala.main _main = null;
public ir.safhebeshkan.apkkala.actabout _actabout = null;
public ir.safhebeshkan.apkkala.acticon _acticon = null;
public ir.safhebeshkan.apkkala.mycode _mycode = null;
public ir.safhebeshkan.apkkala.service1 _service1 = null;
public ir.safhebeshkan.apkkala.starter _starter = null;

public static void initializeProcessGlobals() {
             try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
}
public static String  _activity_click() throws Exception{
 //BA.debugLineNum = 88;BA.debugLine="Sub Activity_Click";
 //BA.debugLineNum = 90;BA.debugLine="End Sub";
return "";
}
public static String  _activity_create(boolean _firsttime) throws Exception{
String _f1 = "";
String _f2 = "";
String _f3 = "";
int _opa = 0;
boolean _f4 = false;
boolean _f6 = false;
anywheresoftware.b4a.agraham.reflection.Reflection _obj1 = null;
anywheresoftware.b4a.phone.Phone.PhoneVibrate _p1 = null;
anywheresoftware.b4a.objects.MediaPlayerWrapper _m1 = null;
 //BA.debugLineNum = 18;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 19;BA.debugLine="Activity.Color = Colors.Transparent";
mostCurrent._activity.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 20;BA.debugLine="Activity.LoadLayout(\"frm1\")";
mostCurrent._activity.LoadLayout("frm1",mostCurrent.activityBA);
 //BA.debugLineNum = 22;BA.debugLine="Dim f1,f2,f3 As String";
_f1 = "";
_f2 = "";
_f3 = "";
 //BA.debugLineNum = 23;BA.debugLine="Dim opa As Int";
_opa = 0;
 //BA.debugLineNum = 24;BA.debugLine="Dim f4,f6 As Boolean";
_f4 = false;
_f6 = false;
 //BA.debugLineNum = 27;BA.debugLine="f1 = mycode.pre.getString(\"pic\")";
_f1 = mostCurrent._mycode._pre.GetString("pic");
 //BA.debugLineNum = 28;BA.debugLine="f3 = mycode.pre.getString(\"op\")";
_f3 = mostCurrent._mycode._pre.GetString("op");
 //BA.debugLineNum = 29;BA.debugLine="f4 = mycode.pre.GetBoolean(\"vibrate\")";
_f4 = mostCurrent._mycode._pre.GetBoolean("vibrate");
 //BA.debugLineNum = 30;BA.debugLine="f5 = mycode.pre.GetBoolean(\"lock\")";
_f5 = mostCurrent._mycode._pre.GetBoolean("lock");
 //BA.debugLineNum = 31;BA.debugLine="f6 = mycode.pre.GetBoolean(\"play\")";
_f6 = mostCurrent._mycode._pre.GetBoolean("play");
 //BA.debugLineNum = 33;BA.debugLine="If f1.Length = 0 Then";
if (_f1.length()==0) { 
 //BA.debugLineNum = 34;BA.debugLine="f1 = \"c1.jpg\"";
_f1 = "c1.jpg";
 };
 //BA.debugLineNum = 37;BA.debugLine="Try";
try { //BA.debugLineNum = 39;BA.debugLine="If IsNumber(f3) Then";
if (anywheresoftware.b4a.keywords.Common.IsNumber(_f3)) { 
 //BA.debugLineNum = 40;BA.debugLine="opa = f3";
_opa = (int)(Double.parseDouble(_f3));
 //BA.debugLineNum = 41;BA.debugLine="If opa < 0 And opa > 255 Then";
if (_opa<0 && _opa>255) { 
 //BA.debugLineNum = 42;BA.debugLine="opa = 128";
_opa = (int) (128);
 };
 };
 //BA.debugLineNum = 46;BA.debugLine="im.SetBackgroundImage(LoadBitmap(File.DirAsse";
mostCurrent._im.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),_f1).getObject()));
 //BA.debugLineNum = 48;BA.debugLine="Dim obj1 As Reflector";
_obj1 = new anywheresoftware.b4a.agraham.reflection.Reflection();
 //BA.debugLineNum = 49;BA.debugLine="obj1.Target = im.Background";
_obj1.Target = (Object)(mostCurrent._im.getBackground());
 //BA.debugLineNum = 50;BA.debugLine="obj1.RunMethod2(\"setAlpha\", f3, \"java.lang.in";
_obj1.RunMethod2("setAlpha",_f3,"java.lang.int");
 //BA.debugLineNum = 52;BA.debugLine="f2 = mycode.pre.getString(\"audio\")";
_f2 = mostCurrent._mycode._pre.GetString("audio");
 //BA.debugLineNum = 53;BA.debugLine="If f2.Length = 0 Then";
if (_f2.length()==0) { 
 //BA.debugLineNum = 54;BA.debugLine="f2 = \"1.wav\"";
_f2 = "1.wav";
 };
 //BA.debugLineNum = 57;BA.debugLine="If f4 = True Then";
if (_f4==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 58;BA.debugLine="Dim p1 As PhoneVibrate";
_p1 = new anywheresoftware.b4a.phone.Phone.PhoneVibrate();
 //BA.debugLineNum = 59;BA.debugLine="p1.Vibrate(800)";
_p1.Vibrate(processBA,(long) (800));
 };
 //BA.debugLineNum = 62;BA.debugLine="If f6 = True Then";
if (_f6==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 63;BA.debugLine="Dim m1 As MediaPlayer";
_m1 = new anywheresoftware.b4a.objects.MediaPlayerWrapper();
 //BA.debugLineNum = 64;BA.debugLine="m1.Initialize2(\"\")";
_m1.Initialize2(processBA,"");
 //BA.debugLineNum = 65;BA.debugLine="m1.Load(File.DirAssets,f2)";
_m1.Load(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),_f2);
 //BA.debugLineNum = 66;BA.debugLine="m1.Play";
_m1.Play();
 };
 } 
       catch (Exception e40) {
			processBA.setLastException(e40); };
 //BA.debugLineNum = 72;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 96;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 97;BA.debugLine="If KeyCode = KeyCodes.KEYCODE_BACK Then";
if (_keycode==anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK) { 
 //BA.debugLineNum = 98;BA.debugLine="If f5 = True Then";
if (_f5==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 99;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 101;BA.debugLine="StopService(service1)";
anywheresoftware.b4a.keywords.Common.StopService(processBA,(Object)(mostCurrent._service1.getObject()));
 //BA.debugLineNum = 102;BA.debugLine="CancelScheduledService(service1)";
anywheresoftware.b4a.keywords.Common.CancelScheduledService(processBA,(Object)(mostCurrent._service1.getObject()));
 //BA.debugLineNum = 103;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 104;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 };
 };
 //BA.debugLineNum = 107;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 108;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 84;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 86;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 74;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 76;BA.debugLine="End Sub";
return "";
}
public static String  _activity_touch(int _action,float _x,float _y) throws Exception{
 //BA.debugLineNum = 92;BA.debugLine="Sub Activity_Touch (Action As Int, X As Float, Y A";
 //BA.debugLineNum = 94;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 12;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 13;BA.debugLine="Dim im As Panel";
mostCurrent._im = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 14;BA.debugLine="Dim f5 As Boolean";
_f5 = false;
 //BA.debugLineNum = 15;BA.debugLine="Dim shake1 As Phone";
mostCurrent._shake1 = new anywheresoftware.b4a.phone.Phone();
 //BA.debugLineNum = 16;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 10;BA.debugLine="End Sub";
return "";
}
public static String  _shake_onshake(boolean _direction) throws Exception{
 //BA.debugLineNum = 78;BA.debugLine="Sub shake_OnShake(Direction As Boolean)";
 //BA.debugLineNum = 79;BA.debugLine="StopService(service1)";
anywheresoftware.b4a.keywords.Common.StopService(processBA,(Object)(mostCurrent._service1.getObject()));
 //BA.debugLineNum = 80;BA.debugLine="CancelScheduledService(service1)";
anywheresoftware.b4a.keywords.Common.CancelScheduledService(processBA,(Object)(mostCurrent._service1.getObject()));
 //BA.debugLineNum = 81;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 82;BA.debugLine="End Sub";
return "";
}
}
