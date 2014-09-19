package com.topcsa.view;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.topcsa.adapter.CommonAdapter;
import com.topcsa.adapter.MyAdapter;
import com.topcsa.adapter.ViewHolder;
import com.topcsa.common.listviewSet;
import com.topcsa.entity.item_list_news;
import com.topcsa.topsoft.R;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class NewsFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_news, container,
				false);

		listviewSet(rootView);
		return rootView;
	}
	/**
	 * listview����
	 * @param view
	 */
	private void listviewSet(View view) {
		ListView lv = (ListView) view.findViewById(R.id.lv_news);
		ArrayList<item_list_news> alist = new ArrayList<item_list_news>();
		for (int i = 0; i < 50; i++) {
			item_list_news item_news = new item_list_news("Android�Ļ�����ܼ�ԭ��",
					"���ߣ�top��Э", "����ʱ�䣺2014��9��1��");

			alist.add(item_news);
		}
		CommonAdapter<item_list_news> adapter;
		lv.setAdapter(adapter = new CommonAdapter<item_list_news>(view
				.getContext(), alist, R.layout.item_news_listview) {

			@Override
			public void convert(ViewHolder helper, item_list_news item) {
				helper.setText(R.id.tv_title_listview_blog, item.getTitle());
				helper.setText(R.id.tv_author_listview_blog, item.getAuthor());
				helper.setText(R.id.tv_data_lisyview_blog, item.getDate());

			}
		});
		lv.setEmptyView(view.findViewById(R.id.tv_nodata));
		listviewSet.setScrollBar(getResources(), R.drawable.scroll, lv);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}

}
