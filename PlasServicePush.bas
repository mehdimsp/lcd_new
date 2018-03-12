B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Service
Version=6.8
@EndOfDesignText@
#Region  Service Attributes 
	#StartAtBoot: true
	#StartCommandReturnValue: android.app.Service.START_STICKY
#End Region

Sub Process_Globals
Dim push As PlasPush

End Sub

Sub Service_Create
	StartServiceAt("", DateTime.Now + 60000*5,True)
	push.Initialize("2vqp1f6261c1881_37")
	'شناسه ی برنامه را جایگزین کنید
End Sub

Sub Service_Start (StartingIntent As Intent)
	StartServiceAt("", DateTime.Now + 60000*5,True)
End Sub

Sub Service_Destroy
StartService("")
End Sub
'کدهای دیگر را اصلاح نکنید
