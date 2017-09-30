package com.modi.modivideos.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.modi.modivideos.R;
import com.modi.modivideos.callback.RecyclerViewOnClickInterface;
import com.modi.modivideos.data.LatestVideo;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Avilash on 30-09-2017.
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {

    private List<LatestVideo> mDataset;
    private Context mContext;
    private RecyclerViewOnClickInterface mListener;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView tv_video_desc;
        private ImageView iv_video_bg;
        public CardView cv_video;

        public ViewHolder(View itemView, TextView tv_video_desc, ImageView iv_video_bg, CardView cv_video) {
            super(itemView);
            this.tv_video_desc = tv_video_desc;
            this.iv_video_bg = iv_video_bg;
            this.cv_video = cv_video;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)


    public VideoAdapter(List<LatestVideo> mDataset, Context mContext , RecyclerViewOnClickInterface mListener) {
        this.mDataset = mDataset;
        this.mContext = mContext;
        this.mListener = mListener;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public VideoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.video_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        CardView cv_video = (CardView) v.findViewById(R.id.cv_video);
        TextView tv_video_desc = (TextView) v.findViewById(R.id.tv_video_desc);
        ImageView iv_video_bg = (ImageView) v.findViewById(R.id.iv_video_bg);
        ViewHolder vh = new ViewHolder(v, tv_video_desc, iv_video_bg, cv_video);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onRVItemClick(position);
            }
        });
        holder.tv_video_desc.setText(mDataset.get(position).getVid_dsc());
        Picasso.with(mContext).load(mDataset.get(position).getVid_pic()).into(holder.iv_video_bg);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
