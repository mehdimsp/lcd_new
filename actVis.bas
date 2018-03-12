B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Activity
Version=6.8
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: True
	#IncludeTitle: false
#End Region

Sub Process_Globals
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.

End Sub

Sub Globals
Dim im As Panel
Dim f5 As Boolean
Dim shake1 As Phone
End Sub

Sub Activity_Create(FirstTime As Boolean)
Activity.Color = Colors.Transparent
Activity.LoadLayout("frm1")

Dim f1,f2,f3 As String
Dim opa As Int
Dim f4,f6 As Boolean


f1 = mycode.pre.getString("pic")
f3 = mycode.pre.getString("op")
f4 = mycode.pre.GetBoolean("vibrate")
f5 = mycode.pre.GetBoolean("lock")
f6 = mycode.pre.GetBoolean("play")

If f1.Length = 0 Then
 f1 = "c1.jpg"
End If

Try

	If IsNumber(f3) Then
	 opa = f3
	 If opa < 0 And opa > 255 Then
	  opa = 128
	 End If
	End If

     im.SetBackgroundImage(LoadBitmap(File.DirAssets,f1))
	
	Dim obj1 As Reflector
     obj1.Target = im.Background
     obj1.RunMethod2("setAlpha", f3, "java.lang.int")
	
	f2 = mycode.pre.getString("audio")
	If f2.Length = 0 Then
	 f2 = "1.wav"
	End If
	
	If f4 = True Then
	 Dim p1 As PhoneVibrate
	 p1.Vibrate(800)
	End If
	
	If f6 = True Then
	 Dim m1 As MediaPlayer
	 m1.Initialize2("")
	 m1.Load(File.DirAssets,f2)
	 m1.Play
	End If
	
Catch
End Try

End Sub

Sub Activity_Resume

End Sub

Sub shake_OnShake(Direction As Boolean)
 StopService(service1)
 CancelScheduledService(service1)
 Activity.Finish
End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

Sub Activity_Click

End Sub

Sub Activity_Touch (Action As Int, X As Float, Y As Float)

End Sub

Sub Activity_KeyPress (KeyCode As Int) As Boolean 'Return True to consume the event
 If KeyCode = KeyCodes.KEYCODE_BACK Then
  If f5 = True Then
   Return True
  Else
   StopService(service1)
   CancelScheduledService(service1)
   Activity.Finish
   Return True
  End If
 End If
 Return True
End Sub
