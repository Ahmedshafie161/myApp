package com.example.ui.main;
import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;
        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;
        import com.example.R;
        import com.example.RecyclerItemListener;
        import com.example.pojo.MyAggregateData;
        import com.example.pojo.PostModel;
        import java.util.ArrayList;
        import java.util.List;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {
    ArrayList<MyAggregateData> arrayAggrList ;
    List<PostModel> postModelList = new ArrayList<>();
    RecyclerItemListener listener ;
    Context context;
    // intilizing recycler with arraylist data , context and listener to send data back to activity
    public MyRecyclerAdapter(ArrayList<MyAggregateData> arrayAggrList, RecyclerItemListener listener) {
        this.arrayAggrList = arrayAggrList;
        this.context = context;
        this.listener=listener;
    }
    public MyRecyclerAdapter() {
    }

    @NonNull
    @Override
    // inflate xml file ,create viewholder ,associate xml with view holder , send view holder to onBindVIewHolder
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate view xml file
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_layout,parent,false);
        //associate xml with view holder , inflate views
        MyViewHolder mvh = new MyViewHolder(v);
        return mvh;
    }


    @Override
    // get the required data and add it to viewholder
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        //MyAggregateData myAggregateData = arrayAggr.get(position);
        //assign data to view associated with holder, bind data
        //MyAggregateData arrayAggr= arrayAggrList.get(position);
        //holder.bind(arrayAggr);

        PostModel postModel = postModelList.get(position);
        holder.bind(postModel);
    }
    public void setList(List<PostModel> postModel){
        this.postModelList = postModel;
        notifyDataSetChanged();
    }
    public void addList(PostModel postModel){
        this.postModelList.add(postModel);
        notifyDataSetChanged();
    }
    public void addItem(MyAggregateData mA){
        this.arrayAggrList.add(mA);
        notifyDataSetChanged();
    }
    // return to view the number of data
    @Override
    public int getItemCount() {
        return postModelList.size();
        //return arrayAggrList.size();
    }
    // inflate views ,bind data , register listener ,
    public  class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;
        MyAggregateData arrayAggr;   // required to send data to activity,used in listener
        PostModel postModel;
        //inflate views , register view listener, send data to activity
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textView = (TextView) itemView.findViewById(R.id.textView);
            //send data from adapter to activity
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (arrayAggr!=null){
                    listener.onItemClick(arrayAggr);
                }else {
                        listener.onItemClick(postModel);

                    }
                }
            });
        }
        // bind data to views
        void bind(MyAggregateData arrayAggr ){
            this.arrayAggr=arrayAggr;
            textView.setText(arrayAggr.getName());
        }
        void bind(PostModel postModel){
            this.postModel = postModel;
            textView.setText(postModel.getTitle());
        }
        // layout listener
    }
}

