package ir.safhebeshkan.apkkala;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class mycode {
private static mycode mostCurrent = new mycode();
public static Object getObject() {
    throw new RuntimeException("Code module does not support this method.");
}
 public anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4a.objects.preferenceactivity.PreferenceManager _pre = null;
public ir.safhebeshkan.apkkala.main _main = null;
public ir.safhebeshkan.apkkala.actabout _actabout = null;
public ir.safhebeshkan.apkkala.acticon _acticon = null;
public ir.safhebeshkan.apkkala.actvis _actvis = null;
public ir.safhebeshkan.apkkala.service1 _service1 = null;
public ir.safhebeshkan.apkkala.starter _starter = null;
public static String  _createemptyshortcut(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.ActivityWrapper _act1) throws Exception{
anywheresoftware.b4a.objects.preferenceactivity.PreferenceManager _prefmgr = null;
 //BA.debugLineNum = 43;BA.debugLine="Sub CreateEmptyShortCut(act1 As Activity)";
 //BA.debugLineNum = 44;BA.debugLine="Dim PrefMgr As AHPreferenceManager";
_prefmgr = new anywheresoftware.b4a.objects.preferenceactivity.PreferenceManager();
 //BA.debugLineNum = 46;BA.debugLine="If PrefMgr.GetBoolean(\"shortcutinstalled\") Then";
if (_prefmgr.GetBoolean("shortcutinstalled")) { 
 //BA.debugLineNum = 47;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 60;BA.debugLine="End Sub";
return "";
}
public static String  _labelspace(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.ConcreteViewWrapper _view1) throws Exception{
anywheresoftware.b4a.agraham.reflection.Reflection _obj1 = null;
 //BA.debugLineNum = 37;BA.debugLine="Sub LabelSpace(view1 As View)";
 //BA.debugLineNum = 38;BA.debugLine="Dim Obj1 As Reflector";
_obj1 = new anywheresoftware.b4a.agraham.reflection.Reflection();
 //BA.debugLineNum = 39;BA.debugLine="Obj1.Target = view1";
_obj1.Target = (Object)(_view1.getObject());
 //BA.debugLineNum = 40;BA.debugLine="Obj1.RunMethod3(\"setLineSpacing\", 1, \"java.lang.fl";
_obj1.RunMethod3("setLineSpacing",BA.NumberToString(1),"java.lang.float",BA.NumberToString(1.5),"java.lang.float");
 //BA.debugLineNum = 41;BA.debugLine="End Sub";
return "";
}
public static String  _playanimation(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.ConcreteViewWrapper _obj1,String _saction,int _sspeed) throws Exception{
anywheresoftware.b4a.objects.AnimationWrapper _animation1 = null;
 //BA.debugLineNum = 20;BA.debugLine="Sub playAnimation(obj1 As View,sAction As String,s";
 //BA.debugLineNum = 21;BA.debugLine="Dim animation1 As Animation";
_animation1 = new anywheresoftware.b4a.objects.AnimationWrapper();
 //BA.debugLineNum = 22;BA.debugLine="Select Case sAction";
switch (BA.switchObjectToInt(_saction,"rotate","scale","alpha","translate")) {
case 0: {
 //BA.debugLineNum = 24;BA.debugLine="animation1.InitializeRotateCenter(\"\",0,360,obj1";
_animation1.InitializeRotateCenter(_ba,"",(float) (0),(float) (360),(android.view.View)(_obj1.getObject()));
 break; }
case 1: {
 //BA.debugLineNum = 26;BA.debugLine="animation1.InitializeScaleCenter(\"\",1,1,2,2,obj";
_animation1.InitializeScaleCenter(_ba,"",(float) (1),(float) (1),(float) (2),(float) (2),(android.view.View)(_obj1.getObject()));
 break; }
case 2: {
 //BA.debugLineNum = 28;BA.debugLine="animation1.InitializeAlpha(\"\",0,100)";
_animation1.InitializeAlpha(_ba,"",(float) (0),(float) (100));
 break; }
case 3: {
 //BA.debugLineNum = 30;BA.debugLine="animation1.InitializeTranslate(\"\",0,0,obj1.Left";
_animation1.InitializeTranslate(_ba,"",(float) (0),(float) (0),(float) (_obj1.getLeft()),(float) (_obj1.getTop()));
 break; }
}
;
 //BA.debugLineNum = 32;BA.debugLine="animation1.RepeatCount = 0";
_animation1.setRepeatCount((int) (0));
 //BA.debugLineNum = 33;BA.debugLine="animation1.Duration = sSpeed";
_animation1.setDuration((long) (_sspeed));
 //BA.debugLineNum = 34;BA.debugLine="animation1.Start(obj1)";
_animation1.Start((android.view.View)(_obj1.getObject()));
 //BA.debugLineNum = 35;BA.debugLine="End Sub";
return "";
}
public static String  _playaudio(anywheresoftware.b4a.BA _ba,String _sfilename) throws Exception{
anywheresoftware.b4a.objects.MediaPlayerWrapper _m1 = null;
 //BA.debugLineNum = 7;BA.debugLine="Sub playAudio(sfilename As String)";
 //BA.debugLineNum = 8;BA.debugLine="Dim m1 As MediaPlayer";
_m1 = new anywheresoftware.b4a.objects.MediaPlayerWrapper();
 //BA.debugLineNum = 9;BA.debugLine="m1.Initialize2(\"\")";
_m1.Initialize2((_ba.processBA == null ? _ba : _ba.processBA),"");
 //BA.debugLineNum = 10;BA.debugLine="m1.Load(File.DirAssets,sfilename)";
_m1.Load(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),_sfilename);
 //BA.debugLineNum = 11;BA.debugLine="m1.Play";
_m1.Play();
 //BA.debugLineNum = 12;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 3;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 4;BA.debugLine="Dim pre As AHPreferenceManager";
_pre = new anywheresoftware.b4a.objects.preferenceactivity.PreferenceManager();
 //BA.debugLineNum = 5;BA.debugLine="End Sub";
return "";
}
public static String  _setalpha(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.ConcreteViewWrapper _control,int _alpha) throws Exception{
anywheresoftware.b4a.agraham.reflection.Reflection _r = null;
 //BA.debugLineNum = 14;BA.debugLine="Sub SetAlpha(Control As View, Alpha As Int)";
 //BA.debugLineNum = 15;BA.debugLine="Dim r As Reflector";
_r = new anywheresoftware.b4a.agraham.reflection.Reflection();
 //BA.debugLineNum = 16;BA.debugLine="r.Target = Control";
_r.Target = (Object)(_control.getObject());
 //BA.debugLineNum = 17;BA.debugLine="r.RunMethod2(\"setAlpha\", Alpha, \"java.lang.int\")";
_r.RunMethod2("setAlpha",BA.NumberToString(_alpha),"java.lang.int");
 //BA.debugLineNum = 18;BA.debugLine="End Sub";
return "";
}
}
