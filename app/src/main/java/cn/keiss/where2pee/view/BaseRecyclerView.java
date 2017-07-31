package cn.keiss.where2pee.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cn.keiss.where2pee.R;
import cn.keiss.where2pee.util.Fields;
import cn.keiss.where2pee.util.ToastUtil;

/**
 * Created by hekai on 2017/7/17.
 *
 */

public class BaseRecyclerView extends RecyclerView {
    private View emptyView;
    private RecyclerView.Adapter recyclerViewAdapter;
    private Context context;



    public BaseRecyclerView(Context context) {
        super(context);
        this.context = context;
    }

    public BaseRecyclerView(Context context, AttributeSet attributeSet){
        super(context,attributeSet);
    }



    @Override
    public void setAdapter(Adapter adapter) {
        this.recyclerViewAdapter = adapter;
        final Adapter oldAdapter = getAdapter();
        if (oldAdapter != null) {
            oldAdapter.unregisterAdapterDataObserver(observer);
        }
        super.setAdapter(adapter);
        if (adapter != null) {
            adapter.registerAdapterDataObserver(observer);
        }

        checkIfEmpty();
    }





    final private AdapterDataObserver observer = new AdapterDataObserver() {
        @Override
        public void onChanged() {
            checkIfEmpty();
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {

            checkIfEmpty();
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            checkIfEmpty();
        }
    };

    private void checkIfEmpty() {
        if (emptyView != null && getAdapter() != null) {
            final boolean emptyViewVisible =
                    getAdapter().getItemCount() == 0;
            emptyView.setVisibility(emptyViewVisible ? VISIBLE : GONE);
            setVisibility(emptyViewVisible ? GONE : VISIBLE);
        }
    }



    public void setEmptyView(View emptyView) {
        this.emptyView = emptyView;
        checkIfEmpty();
    }






    //根据不同情况设置没有心得的界面（0没有心得、1没有网络、2有网络但请求服务器失败）
    public void setEmptyViewHint(int status){
        ImageView imageView = (ImageView) emptyView.findViewById(R.id.iv_empty_view);
        TextView textView = (TextView) emptyView.findViewById(R.id.tv_empty_view);

        switch (status){
            case Fields.GETTING_POSITION:
                    textView.setText("正在定位");
                    imageView.setImageResource(R.drawable.ic_near_me_black_24dp);
                break;
            case Fields.GET_POSITION_FAILED:
                    textView.setText("定位失败,点击空白处重试");
                    imageView.setImageResource(R.drawable.ic_autorenew_black_24dp);
                break;
            case Fields.NET_UNAVAILABLE:
                if (recyclerViewAdapter.getItemCount() ==0 || recyclerViewAdapter.getItemCount() ==1){
                    textView.setText("网络不可用,点击空白处重试");
                    imageView.setImageResource(R.drawable.ic_warning_black_24dp);
                }else {
                    ToastUtil.showToast(context,"网络不可用,请检查网络设置");
                }
                break;
            case Fields.SEARCHING :
                textView.setText("正在搜索");
                imageView.setImageResource(R.drawable.ic_youtube_searched_for_black_24dp);
                break;
            case Fields.SEARCH_RESULT_EMPTY:
                textView.setText("附近未搜到卫生间");
                imageView.setImageResource(R.drawable.ic_youtube_searched_for_black_24dp);
        }
    }
}
