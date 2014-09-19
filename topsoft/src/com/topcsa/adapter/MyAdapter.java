package com.topcsa.adapter;

import java.util.List;

import com.topcsa.entity.item_list_news;
import com.topcsa.topsoft.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyAdapter<T> extends BaseAdapter {
	
	protected LayoutInflater mInflater;
	protected Context mContext;
	public List<item_list_news> mDatas;
	protected final int mItemLayoutId;

	public MyAdapter(Context context, List<item_list_news> mDatas, int itemLayoutId) {
		this.mContext = context;
		this.mInflater = LayoutInflater.from(mContext);
		this.mDatas = mDatas;
		this.mItemLayoutId = itemLayoutId;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mDatas.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mDatas.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder viewholder;
		if(convertView==null){
			convertView=mInflater.inflate(mItemLayoutId, parent,false);
			viewholder =new ViewHolder();
			viewholder.tv_title=(TextView) convertView.findViewById(R.id.tv_title_listview_blog);
			viewholder.tv_author=(TextView) convertView.findViewById(R.id.tv_author_listview_blog);
			viewholder.tv_date=(TextView) convertView.findViewById(R.id.tv_data_lisyview_blog);
			convertView.setTag(viewholder);
		}else{
			viewholder=(ViewHolder) convertView.getTag();
		}
		
		viewholder.tv_title.setText(mDatas.get(position).getTitle());
		viewholder.tv_author.setText(mDatas.get(position).getAuthor());
		viewholder.tv_date.setText(mDatas.get(position).getDate());
		return convertView;
	}
	
	static class ViewHolder{
		public TextView tv_title;
		public TextView tv_author;
		public TextView tv_date;
	}

}
