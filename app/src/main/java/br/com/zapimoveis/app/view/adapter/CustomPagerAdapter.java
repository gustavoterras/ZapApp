package br.com.zapimoveis.app.view.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import br.com.zapimoveis.app.R;
import br.com.zapimoveis.app.view.component.GlideImageView;

/**
 * Created by gustavoterras on 08/04/17.
 */

public class CustomPagerAdapter extends PagerAdapter {

    private LayoutInflater layoutInflater;
    private List<String> data;

    public CustomPagerAdapter(Context context, List<String> data) {
        this.data = data;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setList(List<String> data){
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = layoutInflater.inflate(R.layout.item_image_view_pager, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.image);

        GlideImageView.loadImage(imageView, data.get(position));

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((GlideImageView) object);
    }
}