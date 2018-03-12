package ir.safhebeshkan.apkkala.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_1{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
//BA.debugLineNum = 1;BA.debugLine="pnl1.Width = 100%x"[1/General script]
views.get("pnl1").vw.setWidth((int)((100d / 100 * width)));
//BA.debugLineNum = 2;BA.debugLine="pnl1.Height = 100%y"[1/General script]
views.get("pnl1").vw.setHeight((int)((100d / 100 * height)));
//BA.debugLineNum = 3;BA.debugLine="ImageView1.SetLeftAndRight(0%x,100%x)"[1/General script]
views.get("imageview1").vw.setLeft((int)((0d / 100 * width)));
views.get("imageview1").vw.setWidth((int)((100d / 100 * width) - ((0d / 100 * width))));
//BA.debugLineNum = 5;BA.debugLine="imgsetting.Left = 27%x"[1/General script]
views.get("imgsetting").vw.setLeft((int)((27d / 100 * width)));
//BA.debugLineNum = 6;BA.debugLine="imgabout.Left = 52%x"[1/General script]
views.get("imgabout").vw.setLeft((int)((52d / 100 * width)));
//BA.debugLineNum = 7;BA.debugLine="imgexit.Left = 77%x"[1/General script]
views.get("imgexit").vw.setLeft((int)((77d / 100 * width)));
//BA.debugLineNum = 8;BA.debugLine="imgstart.Left = 2%x"[1/General script]
views.get("imgstart").vw.setLeft((int)((2d / 100 * width)));
//BA.debugLineNum = 9;BA.debugLine="Label1.SetLeftAndRight(0%x,100%x)"[1/General script]
views.get("label1").vw.setLeft((int)((0d / 100 * width)));
views.get("label1").vw.setWidth((int)((100d / 100 * width) - ((0d / 100 * width))));
//BA.debugLineNum = 10;BA.debugLine="Label1.SetTopAndBottom(1%y,45%y)"[1/General script]
views.get("label1").vw.setTop((int)((1d / 100 * height)));
views.get("label1").vw.setHeight((int)((45d / 100 * height) - ((1d / 100 * height))));
//BA.debugLineNum = 11;BA.debugLine="imgstart.SetLeftAndRight(2%x,25%x)"[1/General script]
views.get("imgstart").vw.setLeft((int)((2d / 100 * width)));
views.get("imgstart").vw.setWidth((int)((25d / 100 * width) - ((2d / 100 * width))));
//BA.debugLineNum = 12;BA.debugLine="imgstart.SetTopAndBottom(58%y,74%y)"[1/General script]
views.get("imgstart").vw.setTop((int)((58d / 100 * height)));
views.get("imgstart").vw.setHeight((int)((74d / 100 * height) - ((58d / 100 * height))));
//BA.debugLineNum = 13;BA.debugLine="imgsetting.SetLeftAndRight(27%x,50%x)"[1/General script]
views.get("imgsetting").vw.setLeft((int)((27d / 100 * width)));
views.get("imgsetting").vw.setWidth((int)((50d / 100 * width) - ((27d / 100 * width))));
//BA.debugLineNum = 14;BA.debugLine="imgsetting.SetTopAndBottom(58%y,74%y)"[1/General script]
views.get("imgsetting").vw.setTop((int)((58d / 100 * height)));
views.get("imgsetting").vw.setHeight((int)((74d / 100 * height) - ((58d / 100 * height))));
//BA.debugLineNum = 15;BA.debugLine="imgabout.SetLeftAndRight(52%x,75%x)"[1/General script]
views.get("imgabout").vw.setLeft((int)((52d / 100 * width)));
views.get("imgabout").vw.setWidth((int)((75d / 100 * width) - ((52d / 100 * width))));
//BA.debugLineNum = 16;BA.debugLine="imgabout.SetTopAndBottom(58%y,74%y)"[1/General script]
views.get("imgabout").vw.setTop((int)((58d / 100 * height)));
views.get("imgabout").vw.setHeight((int)((74d / 100 * height) - ((58d / 100 * height))));
//BA.debugLineNum = 17;BA.debugLine="imgexit.SetLeftAndRight(77%x,98%x)"[1/General script]
views.get("imgexit").vw.setLeft((int)((77d / 100 * width)));
views.get("imgexit").vw.setWidth((int)((98d / 100 * width) - ((77d / 100 * width))));
//BA.debugLineNum = 18;BA.debugLine="imgexit.SetTopAndBottom(58%y,74%y)"[1/General script]
views.get("imgexit").vw.setTop((int)((58d / 100 * height)));
views.get("imgexit").vw.setHeight((int)((74d / 100 * height) - ((58d / 100 * height))));
//BA.debugLineNum = 19;BA.debugLine="Label2.SetLeftAndRight(29%x,50%x)"[1/General script]
views.get("label2").vw.setLeft((int)((29d / 100 * width)));
views.get("label2").vw.setWidth((int)((50d / 100 * width) - ((29d / 100 * width))));
//BA.debugLineNum = 20;BA.debugLine="Label2.SetTopAndBottom(73%y,80%y)"[1/General script]
views.get("label2").vw.setTop((int)((73d / 100 * height)));
views.get("label2").vw.setHeight((int)((80d / 100 * height) - ((73d / 100 * height))));
//BA.debugLineNum = 21;BA.debugLine="Label3.SetLeftAndRight(54%x,75%x)"[1/General script]
views.get("label3").vw.setLeft((int)((54d / 100 * width)));
views.get("label3").vw.setWidth((int)((75d / 100 * width) - ((54d / 100 * width))));
//BA.debugLineNum = 22;BA.debugLine="Label3.SetTopAndBottom(73%y,80%y)"[1/General script]
views.get("label3").vw.setTop((int)((73d / 100 * height)));
views.get("label3").vw.setHeight((int)((80d / 100 * height) - ((73d / 100 * height))));
//BA.debugLineNum = 23;BA.debugLine="Label4.SetLeftAndRight(76%x,98%x)"[1/General script]
views.get("label4").vw.setLeft((int)((76d / 100 * width)));
views.get("label4").vw.setWidth((int)((98d / 100 * width) - ((76d / 100 * width))));
//BA.debugLineNum = 24;BA.debugLine="Label4.SetTopAndBottom(73%y,80%y)"[1/General script]
views.get("label4").vw.setTop((int)((73d / 100 * height)));
views.get("label4").vw.setHeight((int)((80d / 100 * height) - ((73d / 100 * height))));
//BA.debugLineNum = 26;BA.debugLine="Label5.SetLeftAndRight(6%x,27%x)"[1/General script]
views.get("label5").vw.setLeft((int)((6d / 100 * width)));
views.get("label5").vw.setWidth((int)((27d / 100 * width) - ((6d / 100 * width))));
//BA.debugLineNum = 27;BA.debugLine="Label5.SetTopAndBottom(73%y,80%y)"[1/General script]
views.get("label5").vw.setTop((int)((73d / 100 * height)));
views.get("label5").vw.setHeight((int)((80d / 100 * height) - ((73d / 100 * height))));
//BA.debugLineNum = 28;BA.debugLine="Button1.SetLeftAndRight(35%x,70%x)"[1/General script]
views.get("button1").vw.setLeft((int)((35d / 100 * width)));
views.get("button1").vw.setWidth((int)((70d / 100 * width) - ((35d / 100 * width))));
//BA.debugLineNum = 29;BA.debugLine="Button1.SetTopAndBottom(81%y,91%y)"[1/General script]
views.get("button1").vw.setTop((int)((81d / 100 * height)));
views.get("button1").vw.setHeight((int)((91d / 100 * height) - ((81d / 100 * height))));
//BA.debugLineNum = 31;BA.debugLine="ImageView12.SetLeftAndRight(0%x,100%x)"[1/General script]
views.get("imageview12").vw.setLeft((int)((0d / 100 * width)));
views.get("imageview12").vw.setWidth((int)((100d / 100 * width) - ((0d / 100 * width))));
//BA.debugLineNum = 32;BA.debugLine="ImageView12.SetTopAndBottom(92%y,100%y)"[1/General script]
views.get("imageview12").vw.setTop((int)((92d / 100 * height)));
views.get("imageview12").vw.setHeight((int)((100d / 100 * height) - ((92d / 100 * height))));
//BA.debugLineNum = 35;BA.debugLine="AutoScaleRate(0)"[1/General script]
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0d);
//BA.debugLineNum = 36;BA.debugLine="AutoScaleAll"[1/General script]
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);

}
}