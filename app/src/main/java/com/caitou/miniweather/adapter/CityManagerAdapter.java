package com.caitou.miniweather.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.caitou.miniweather.R;

import java.util.List;

/**
 * @className:
 * @classDescription:
 * @Author: Guangzhao Cai
 * @createTime: 2017-01-19.
 */

public class CityManagerAdapter extends RecyclerView.Adapter<CityManagerAdapter.ViewHolder> {
    private Context mContext;
    private List<CityManagerData> mDataList;
    private ItemOnClickListener mListener;

    public CityManagerAdapter(Context context, List<CityManagerData> dataList, ItemOnClickListener listener) {
        mContext = context;
        this.mDataList = dataList;
        this.mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null)
            mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_city_manager, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.cityNameTv.setText(mDataList.get(position).getCityName());
        holder.tempTv.setText(mDataList.get(position).getTemp());
        holder.firstWordTv.setText(mDataList.get(position).getCityName().substring(0,1));
        holder.weatherTv.setText(mDataList.get(position).getWeather());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onItemClick(holder.itemView, holder.getLayoutPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    /**
     * 添加recycle view条目
     * @param position
     * @param data
     */
    public void addItem(int position, CityManagerData data) {
        if (mDataList.size() == 0 || mDataList.isEmpty()) {
            mDataList.add(0, data);
            notifyItemInserted(0);
            return;
        }
        mDataList.add(position, data);
        notifyItemInserted(position);
    }

    /**
     * 删除recycle view条目
     * @param position
     */
    public void removeItem(int position) {
        if (mDataList.size() == 0)
            return;
        if (mDataList.size() == 1) {
            mDataList.remove(0);
            notifyItemRemoved(0);
            return;
        }
        mDataList.remove(position);
        notifyItemRemoved(0);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView firstWordTv;
        TextView cityNameTv;
        TextView weatherTv;
        TextView tempTv;

        public ViewHolder(View itemView) {
            super(itemView);
            firstWordTv = (TextView) itemView.findViewById(R.id.item_city_manager_tv_name_first_word);
            cityNameTv = (TextView) itemView.findViewById(R.id.item_city_manager_tv_city_name);
            weatherTv = (TextView) itemView.findViewById(R.id.item_city_manager_tv_weather);
            tempTv = (TextView) itemView.findViewById(R.id.item_city_manager_tv_temp);
        }
    }

    // item点击事件
    public interface ItemOnClickListener {
        void onItemClick(View view, int position);
    }
}
