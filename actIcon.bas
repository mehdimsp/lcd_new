B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Activity
Version=6.8
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: False
	#IncludeTitle: false
#End Region

Sub Process_Globals
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.

End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.

	Private sv1 As ScrollView
	Private lbltitle As Label
	Private Label1 As Label
End Sub

Sub Activity_Create(FirstTime As Boolean)
Activity.LoadLayout("frmlisticon")
lbltitle.Typeface = Typeface.LoadFromAssets("SROYA.TTF")
Label1.Typeface = Typeface.LoadFromAssets("SROYA.TTF")
Dim stop As Int = 8dip

For i = 1 To 8
 Dim im As ImageView
 im.Initialize("imgb")
 sv1.Panel.AddView(im,3%x,stop,95%x,120%x)
 im.SetBackgroundImage(LoadBitmap(File.DirAssets,"c" & i & ".jpg"))
 im.Gravity = Gravity.FILL
 mycode.playAnimation(im,"alpha",800)
 DoEvents
 im.Tag = "c" & i & ".jpg"
 stop = stop + 121%x
Next
sv1.Panel.Height = stop
 Dim verticalPosition As String = "bottom" '"top" or "bottom"
    Dim horizontalPosition As String = "center" '"left", "right" or "center"
    Dim r As Reflector 
    Dim args(3) As Object = Array As Object(r.GetActivity, verticalPosition, horizontalPosition)
    Dim types(3) As String = Array As String("android.app.Activity", "java.lang.String", "java.lang.String")    
   
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)
Activity.Finish
End Sub

Sub imgb_Click
Dim a As ImageView
a = Sender
mycode.pre.SetString("pic",a.Tag)
ToastMessageShow("تصویر مورد نظر انتخاب شد",False)
Activity.Finish
End Sub

Sub btnback_Click
Activity.Finish
End Sub