package com.example.vaibhav.mvpsample.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vaibhav.mvpsample.R;
import com.example.vaibhav.mvpsample.bean.SampleBean;

import java.util.ArrayList;

/**
 * Created by vaibhav on 30/5/16.
 */
public class SampleAdapter extends RecyclerView.Adapter<SampleAdapter.ViewHolder>{

    private ArrayList<SampleBean> sampleBeans;

    private Activity activity;

    private LayoutInflater inflater;

    public SampleAdapter(Activity baseActivity) {

        this.activity = baseActivity;

        inflater = LayoutInflater.from(activity);

        sampleBeans = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.item_simple_row, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        SampleBean sampleBean = sampleBeans.get(position);

        holder.tvText.setText(sampleBean.toString());

    }

    @Override
    public int getItemCount() {

        return sampleBeans.size();
    }

    public ArrayList<SampleBean> getSampleBeans() {

        return sampleBeans;
    }

    public void setSampleBeans(ArrayList<SampleBean> sampleBeans) {

        if(sampleBeans == null) {

            sampleBeans = new ArrayList<>();
        }

        this.sampleBeans = sampleBeans;

        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public View itemView;

        public TextView tvText;

        public ViewHolder(View itemView) {

            super(itemView);

            this.itemView = itemView;

            tvText = (TextView) itemView.findViewById(R.id.tvText);
        }
    }
}
