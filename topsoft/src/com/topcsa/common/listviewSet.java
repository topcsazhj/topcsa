package com.topcsa.common;

import java.lang.reflect.Field;

import com.topcsa.topsoft.R;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.widget.AbsListView;
import android.widget.ListView;

public class listviewSet {
	/**
	 * �Զ��廬����ͼƬ
	 * @param rsc��Դ
	 * @param srcͼƬID
	 * @param lvѡ��listview
	 */
	public static void setScrollBar(Resources rsc,int src,ListView lv){
		 try {  
	            Field f = AbsListView.class.getDeclaredField("mFastScroller");  
	            f.setAccessible(true);  
	            Object o=f.get(lv);  
	            f=f.getType().getDeclaredField("mThumbDrawable");  
	            f.setAccessible(true);  
	            Drawable drawable=(Drawable) f.get(o);  
	            drawable=rsc.getDrawable(R.drawable.scroll);  
	            f.set(o,drawable);
	        } catch (Exception e) {  
	            throw new RuntimeException(e);  
	        }  
	}
}
