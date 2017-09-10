package gekaixuan.bawei.com.shoppingcart;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 葛凯旋 on 2017/9/9.
 */

public class Myadapter extends RecyclerView.Adapter<Myadapter.MyViewHolder> {
    private List<Bean.DataBean> list = new ArrayList<>();
    private Context context;
    private MainActivity ac;
    private CheckBox check;


    public Myadapter(List<Bean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
        ac= (MainActivity) context;

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.item, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.tv.setText(list.get(position).getTitle());
        Glide.with(context).load(list.get(position).getImage_url()).into(holder.image);
        holder.cb.setChecked(list.get(position).isChecked());
        holder.cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //先判断是否选中
                Bean.DataBean currentDataBean = list.get(position);
                boolean currentChecked = currentDataBean.isChecked();
                currentDataBean.setChecked(!currentChecked);
                notifyDataSetChanged();

                if (holder.cb.isChecked()) {
                    //去判断其它按钮是否都选中
                     isAllChecked();

                } else {
                    //如果点击的按钮取消掉，即未选中状态，则去判断全选按钮是否是选中状态，如果是则取消选中
                    ac.setCb(false);
                }

            }
        });

    }

    private boolean isAllChecked() {
        for (int i = 0; i < list.size(); i++) {
            Bean.DataBean dataBeans = list.get(i);
            if (!dataBeans.isChecked()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv;
        ImageView image;
        CheckBox cb;

        public MyViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.text);
            image = (ImageView) view.findViewById(R.id.image);
            cb = (CheckBox) view.findViewById(R.id.cb);

        }
    }

    public void selectedAll() {
        for (int i = 0; i < list.size(); i++) {
            Bean.DataBean dataBean = list.get(i);
            dataBean.setChecked(true);
        }
        notifyDataSetChanged();
    }

    public void cancleAll() {
        for (int i = 0; i < list.size(); i++) {
            Bean.DataBean dataBean = list.get(i);
            dataBean.setChecked(false);
        }
        notifyDataSetChanged();
    }


}

