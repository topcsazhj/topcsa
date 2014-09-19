package com.topcsa.ui;

import java.util.List;

import com.topcsa.topsoft.R;
import com.topcsa.view.CommonUIFragment;
import com.topcsa.view.NewsFragment;
import com.topcsa.widget.SyncHorizontalScrollView;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends FragmentActivity {

	public static final String ARGUMENTS_NAME = "arg";
	private SyncHorizontalScrollView hsv;
	private RadioGroup rg;
	private ImageView iv_tab_line, iv_home_left, iv_home_right;
	public static String[] tabTitle = {  "协会新闻", "技术中心", "会员风采", "成长轨迹","协会概况",
			"基金公告" }; // 标题
	private ViewPager mViewPager;
	private RelativeLayout rl_tab;
	private LayoutInflater mInflater;
	private int indicatorWidth;
	private int currentIndicatorLeft = 0;
	private TabFragmentPagerAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		FindviewById();
		initView();
		setListener();

	}

	private void setListener() {

		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {

				if (rg != null && rg.getChildCount() > position) {
					((RadioButton) rg.getChildAt(position)).performClick();
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});

		rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {

				if (rg.getChildAt(checkedId) != null) {

					TranslateAnimation animation = new TranslateAnimation(
							currentIndicatorLeft, ((RadioButton) rg
									.getChildAt(checkedId)).getLeft(), 0f, 0f);
					animation.setInterpolator(new LinearInterpolator());
					animation.setDuration(100);
					animation.setFillAfter(true);

					// 执行位移动画
					iv_tab_line.startAnimation(animation);

					mViewPager.setCurrentItem(checkedId); // ViewPager 跟随一起 切换

					// 记录当前 下标的距最左侧的 距离
					currentIndicatorLeft = ((RadioButton) rg
							.getChildAt(checkedId)).getLeft();
					//当当前选中项与边界的距离超过两个选项卡的长度时，自动滑动滚动条显示下一个选项卡
					hsv.smoothScrollTo(
							(checkedId > 1 ? ((RadioButton) rg
									.getChildAt(checkedId)).getLeft() : 0)
									- ((RadioButton) rg.getChildAt(2))
											.getLeft(), 0);
				}
			}
		});
	}

	private void initView() {
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		//获取屏幕宽度，并设置选项卡默认显示的个数
		indicatorWidth = dm.widthPixels / 4;
		LayoutParams cursor_Params = iv_tab_line.getLayoutParams();
		cursor_Params.width = indicatorWidth;
		// 初始化滑动下标的宽
		iv_tab_line.setLayoutParams(cursor_Params);
		//横向滑动条设置参数
		hsv.setSomeParam(rl_tab, iv_home_left, iv_home_right, MainActivity.this);
		// 获取布局填充器
		mInflater = (LayoutInflater) this
				.getSystemService(LAYOUT_INFLATER_SERVICE);
		initNavigationHSV();
		mAdapter = new TabFragmentPagerAdapter(getSupportFragmentManager());
		mViewPager.setAdapter(mAdapter);
	}
	/**
	 * 在radiogroup中添加radiobutton(即选项卡)
	 */
	private void initNavigationHSV() {

		rg.removeAllViews();

		for (int i = 0; i < tabTitle.length; i++) {

			RadioButton rb = (RadioButton) mInflater.inflate(
					R.layout.nav_radiogroup_item, null);
			rb.setId(i);
			rb.setText(tabTitle[i]);
			rb.setLayoutParams(new LayoutParams(indicatorWidth,
					LayoutParams.MATCH_PARENT));
			
			rg.addView(rb);
		}
		rg.check(0);//设置默认radiobutton
		Log.i("initNavigationHSV", "添加hsv控件完成");
	}

	private void FindviewById() {
		hsv = (SyncHorizontalScrollView) findViewById(R.id.hsv);
		rg = (RadioGroup) findViewById(R.id.rg);
		iv_tab_line = (ImageView) findViewById(R.id.iv_tab_line);
		iv_home_left = (ImageView) findViewById(R.id.iv_home_left);
		iv_home_right = (ImageView) findViewById(R.id.iv_home_right);
		mViewPager = (ViewPager) findViewById(R.id.mViewPager);
		rl_tab = (RelativeLayout) findViewById(R.id.rl_home_tab);
		Log.i("findviewbyid", "获取xml控件完成");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public static class TabFragmentPagerAdapter extends FragmentPagerAdapter {

		public TabFragmentPagerAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int arg0) {
			Fragment ft = null;
			switch (arg0) {
			case 0:
				ft = new NewsFragment();
				break;

			default:
				ft = new CommonUIFragment();

				Bundle args = new Bundle();
				args.putString(ARGUMENTS_NAME, tabTitle[arg0]);
				ft.setArguments(args);

				break;
			}
			return ft;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return tabTitle.length;
		}

	}

}
