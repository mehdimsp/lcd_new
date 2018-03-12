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
Dim push As PlasPush
End Sub

Sub Activity_Create(FirstTime As Boolean)

End Sub

Sub Activity_Resume
	push.Initialize("2vqp1f6261c1881_37")
push.ShowClick 'برای نمایش تعداد کلیک

Dim i As Intent
i.Initialize(i.ACTION_VIEW,push.GetLink)
StartActivity(i)
'دراین قسمت تایین شده که لینکی که دریافت شد با اینتنت باز شود
'میتوانید هرچیز دیگری بنویسید

Activity.Finish
End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub


