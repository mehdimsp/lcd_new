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

End Sub

Sub Globals
	Private lbl1 As Label
	Private Label1 As Label
	Private Panel1 As Panel
	Private pnl1 As Panel
	Private Label2 As Label
	Private Label4 As Label
	Private Label3 As Label
End Sub

Sub Activity_Create(FirstTime As Boolean)
Activity.LoadLayout("frmabout")
pnl1.Color = Colors.RGB(229,226,226)
Panel1.Color = Colors.RGB(229,226,226)
lbl1.Typeface = Typeface.LoadFromAssets("SROYA.TTF")
Label1.Typeface = Typeface.LoadFromAssets("SROYA.TTF")
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

Sub btnback_Click
Activity.Finish
End Sub