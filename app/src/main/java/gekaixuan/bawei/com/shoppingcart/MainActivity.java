package gekaixuan.bawei.com.shoppingcart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private String url = "http://result.eolinker.com/iYXEPGn4e9c6dafce6e5cdd23287d2bb136ee7e9194d3e9?uri=one";
    private List<Bean.DataBean> list = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private Myadapter adapter;
    private CheckBox  all;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        all=(CheckBox)findViewById(R.id.allcb);
        initData();
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter = new Myadapter(list, this));
       all.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
          if (all.isChecked()){
              all.setChecked(true);
              adapter.selectedAll();
          }else{
              all.setChecked(false);
              adapter.cancleAll();
          }
      }
  });
    }







    private void initData() {
        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Bean data = new Gson().fromJson(result, Bean.class);
                list.addAll(data.getData());
                adapter.notifyDataSetChanged();
                mRecyclerView.setAdapter(adapter);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
    public void setCb(boolean bool) {
        all.setChecked(bool);
    }
}
