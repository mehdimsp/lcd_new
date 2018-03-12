package ir.safhebeshkan.apkkala.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_frmicon{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
views.get("imageview1").vw.setLeft((int)((50d / 100 * width) - (views.get("imageview1").vw.getWidth() / 2)));
views.get("imageview2").vw.setLeft((int)((50d / 100 * width) - (views.get("imageview2").vw.getWidth() / 2)));
views.get("imageview3").vw.setLeft((int)((50d / 100 * width) - (views.get("imageview3").vw.getWidth() / 2)));
views.get("imageview4").vw.setLeft((int)((50d / 100 * width) - (views.get("imageview4").vw.getWidth() / 2)));
views.get("imageview5").vw.setLeft((int)((50d / 100 * width) - (views.get("imageview5").vw.getWidth() / 2)));
views.get("imageview6").vw.setLeft((int)((50d / 100 * width) - (views.get("imageview6").vw.getWidth() / 2)));
views.get("imageview7").vw.setLeft((int)((50d / 100 * width) - (views.get("imageview7").vw.getWidth() / 2)));
views.get("imageview8").vw.setLeft((int)((50d / 100 * width) - (views.get("imageview8").vw.getWidth() / 2)));
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);

}
}