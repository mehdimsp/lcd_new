B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Activity
Version=6.8
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: False
	#IncludeTitle: True
#End Region

Sub Process_Globals
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.
	Dim PreviousHash As String
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.

End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	'Activity.LoadLayout("Layout1")
	Activity.Title = "Notification"

End Sub

Sub Activity_Resume	
	Dim title, msg, Alert, Hash, Channel As String
	Dim StartingIntent As Intent
	Dim JSON As JSONParser	
	StartingIntent = Activity.GetStartingIntent
	
	JSON.Initialize(StartingIntent.GetExtra("com.parse.Data"))
	Dim DataMap As Map
	DataMap = JSON.NextObject
	Alert = DataMap.Get("alert")
	Hash = DataMap.Get("push_hash")	
	Channel = StartingIntent.GetExtra("com.parse.Channel")
	
	' If you dismiss a notification activity then restart it from list of previous apps button it appears
	' as though a notification has just been received because the Activity is given the previous StartingIntent.
	' To check if it is a new notification or one that has alredy been processed we save and check the hash of the push.
	If Hash <> PreviousHash Then
		title = "New Notification"
		PreviousHash = Hash
	Else
		title = "Old Notification"
	End If	
	Dim p1 As PhoneIntents
	If DataMap.Get("link")<>"" Then
	StartActivity(p1.OpenBrowser(DataMap.Get("link")))
End If


End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub


