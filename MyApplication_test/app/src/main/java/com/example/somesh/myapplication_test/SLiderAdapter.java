package com.example.somesh.myapplication_test;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Somesh on 12/24/2017.
 */

public class SLiderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SLiderAdapter(Context context){
        this.context=context;
    }

    //ARRAYS for Storing Slid images
    public int [] slide_images={

        R.drawable.img1,
        R.drawable.img2,
        R.drawable.img3
    };

    public String [] slide_heading={

            "EAT",
            "SLEEP",
            "CODE"
    };

    public String [] slide_discription={

            "This is a text discription of\n" +
                    "anything related to the Fire Cloud app and now has no meaning is just for test purpose.",
            "This is a text discription of\n" +
                    "anything related to the Fire Cloud app and now has no meaning is just for test purpose.",
            "This is a text discription of\n" +
                    "anything related to the Fire Cloud app and now has no meaning is just for test purpose."

    };

    @Override
    public int getCount() {
        return slide_heading.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (RelativeLayout) object;
    }

    public Object instantiateItem (ViewGroup container, int position){

        layoutInflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.slide_layout, container , false);

        ImageView slideImageView =(ImageView) view.findViewById(R.id.slide_image);
        TextView slideTextheading =(TextView) view.findViewById(R.id.slide_heading);
        TextView slideTextdesc =(TextView) view.findViewById(R.id.slide_desc);

        slideImageView.setImageResource(slide_images[position]);
        slideTextheading.setText(slide_heading[position]);
        slideTextdesc.setText(slide_discription[position]);

        container.addView(view);

        return view;
    }

    public void destroyItem(ViewGroup container, int position,Object object){

        container.removeView((RelativeLayout)object);
    }
}
