package com.superapp.activity.intro;


import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.superapp.R;
import com.superapp.activity.intro.ActivityIntroScreen;


public class ScreenSlidePagerAdapter extends PagerAdapter {

	private ActivityIntroScreen activity;
	private int [] images={R.drawable.loginbg, R.drawable.loginbg, R.drawable.loginbg, R.drawable.loginbg, R.drawable.loginbg};
	//private String [] sliderText={"ONE TICKET.\nLIMITLESS ACTIVITIES.","FITNESS WILL NEVER\nBE BORING AGAIN!","EASY AS A\nTAP","GOOD THINGS COME\nUNLIMITED!","EASY AS A\nTAP"};

	private String[] sliderText={"Welcome","Western Classic","Sufi","Fusion","Spiritual"};
	private String[] LowersliderText={"Indian classical music is diverse because of India's vast cultural diversity",
										"Feel the essence of European Music with a single touch of your finger",
										"Connect yourselves with your inner mystical dimension",
										"Indian classical music is diverse because of India's vast cultural diversity",
			                            "Silence is often misinterpreted but never misquoted"};

	private LayoutInflater inflater;
	private ImageView pagerImage;
	private TextView tvPagerText,tvLowerPagerText;

	
	public ScreenSlidePagerAdapter(ActivityIntroScreen activity){
		this.activity=activity;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return images.length;
	}

	@SuppressWarnings("static-access")
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// TODO Auto-generated method stub
		inflater = (LayoutInflater) activity
				.getSystemService(activity.LAYOUT_INFLATER_SERVICE);
		View itemView = inflater.inflate(R.layout.viewpageritem, container,
				false);
		pagerImage=(ImageView) itemView.findViewById(R.id.ivImage);
		tvPagerText=(TextView) itemView.findViewById(R.id.tvPagerText);
		tvLowerPagerText=(TextView) itemView.findViewById(R.id.tvLowerPagerText);
		 
		
		pagerImage.setImageDrawable(activity.getResources().getDrawable(images[position]));
		tvPagerText.setText(sliderText[position]);
		tvLowerPagerText.setText(LowersliderText[position]);
		
		//
		//tvFiticket.setText(Html.fromHtml("<strong>FIT</strong>"+"ICKET"));
		 
		
		itemView.setTag(position);
		
//		Skip.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//					Intent i=new Intent(activity,Bookactivity.class);
//					i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//					activity.startActivity(i);
//			}
//		});
		// Add viewpager_item.xml to ViewPager
		((ViewPager) container).addView(itemView);
		return itemView;
	}
	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == ((RelativeLayout) arg1);
	} 
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// Remove viewpager_item.xml from ViewPager
		((ViewPager) container).removeView((RelativeLayout) object);

	}

}
