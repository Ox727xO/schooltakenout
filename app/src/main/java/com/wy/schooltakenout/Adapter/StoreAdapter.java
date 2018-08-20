package com.wy.schooltakenout.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wy.schooltakenout.Data.Store;
import com.wy.schooltakenout.R;

import java.util.List;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.ViewHolder> {
	private Context context;
	private List<Store> storeList;

	//用于连接Store列表的项中的各个View
	static class ViewHolder extends RecyclerView.ViewHolder{
		ImageView storeImage;
		TextView storeName;
		LinearLayout storeTags;

		public ViewHolder(View view){
			super(view);

			storeImage=view.findViewById(R.id.store_img);
			storeName=view.findViewById(R.id.store_name);
			storeTags=view.findViewById(R.id.store_tags);
		}
	}

	public StoreAdapter(List<Store> storeList){
	    this.storeList=storeList;
	}

	//初始时调用，连接layout
	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		if(context==null){
			context=parent.getContext();
		}
		View view= LayoutInflater.from(context).inflate(R.layout.store_item,parent,false);
		return new ViewHolder(view);
	}

	//用于生成Store列表中各个项
	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
		Store store=storeList.get(position);

		holder.storeName.setText(store.getStoreName());
		Glide.with(context).load(store.getStoreImg()).into(holder.storeImage);
		//防止标签重复生成
        holder.storeTags.removeAllViews();
        //动态添加标签
		for(String storeTag: store.getStoreTags()) {
			TextView tagView = new TextView(context);
			tagView.setWidth(140);
			tagView.setHeight(70);
			tagView.setText(storeTag);
			tagView.setTextSize(10);
			tagView.setGravity(Gravity.CENTER);
			tagView.setBackground(context.getResources().getDrawable(R.drawable.ic_tag));
			holder.storeTags.addView(tagView);
		}
	}

	@Override
	public int getItemCount() {
		return storeList.size();
	}
}