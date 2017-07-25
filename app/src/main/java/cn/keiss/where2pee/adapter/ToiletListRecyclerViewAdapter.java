package cn.keiss.where2pee.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.keiss.where2pee.R;
import cn.keiss.where2pee.bean.ToiletListItem;
import cn.keiss.where2pee.listener.RecyclerViewItemClickListener;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

/**
 * Created by hekai on 2017/7/18.
 *
 */

public class ToiletListRecyclerViewAdapter extends RecyclerView.Adapter<ToiletListRecyclerViewAdapter.MyViewHolder> {

    private List<ToiletListItem> toiletListItems =new ArrayList<>();
    private RecyclerViewItemClickListener listener;




    public ToiletListRecyclerViewAdapter(RecyclerViewItemClickListener listener, Context context){
        this.listener = listener;
    }


    //自定义viewHolder
    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView ivItemToiletListPhoto;
        private MaterialRatingBar ratingBarItemToiletList;
        private TextView tvItemToiletListUpNum;
        private TextView tvItemToiletListDownNum;
        private TextView tvItemToiletListCharge;
        private TextView tvItemToiletListHavePaper;
        private TextView tvItemToiletListWashHand;
        private TextView tvItemToiletListDistance;



        private RecyclerViewItemClickListener recyclerViewItemClickListener;

        MyViewHolder(View itemView, RecyclerViewItemClickListener listener) {
            super(itemView);
            ivItemToiletListPhoto = (ImageView) itemView.findViewById(R.id.iv_item_toilet_list_photo);
            ratingBarItemToiletList = (MaterialRatingBar) itemView.findViewById(R.id.rating_bar_item_toilet_list);
            tvItemToiletListUpNum = (TextView) itemView.findViewById(R.id.tv_item_toilet_list_up_num);
            tvItemToiletListDownNum = (TextView) itemView.findViewById(R.id.tv_item_toilet_list_down_num);
            tvItemToiletListCharge = (TextView) itemView.findViewById(R.id.tv_item_toilet_list_charge);
            tvItemToiletListHavePaper = (TextView) itemView.findViewById(R.id.tv_item_toilet_list_have_paper);
            tvItemToiletListWashHand = (TextView) itemView.findViewById(R.id.tv_item_toilet_list_wash_hand);
            tvItemToiletListDistance = (TextView) itemView.findViewById(R.id.tv_item_toilet_list_distance);
            this.recyclerViewItemClickListener =listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            recyclerViewItemClickListener.onClick(view,getLayoutPosition());
        }

    }




    //根据viewType返回viewHolder
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.view_toilet_list_item,parent,false),listener);



    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        ToiletListItem item = toiletListItems.get(position);
        holder.tvItemToiletListDistance.setText(item.getDistance());

    }

    @Override
    public int getItemCount() {
        return 0;
    }




    public void setItems(List<ToiletListItem> items){
        toiletListItems.clear();
        for (ToiletListItem item  :items) {
            toiletListItems.add(item);
            notifyItemInserted(0);
        }
    }

    public void removeItems(){
        toiletListItems.clear();
        notifyDataSetChanged();
    }

}
