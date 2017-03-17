package antidisturb.bd.com.antidisturb.helper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import antidisturb.bd.com.antidisturb.R;
import antidisturb.bd.com.antidisturb.bean.InterceptInfo;
import antidisturb.bd.com.antidisturb.listener.ListViewStateChangeListener;
import antidisturb.bd.com.antidisturb.util.Constant;

/**
 * Created by Administrator on 2017/3/13.
 */
public class ListViewHelper {

    private static final List<InterceptInfo> emptyList = new ArrayList<InterceptInfo>();

    private Context context;
    private ListView listView;

    private int layoutId;
    private int theFragmentType;
    private MyAdapter adapter;
    private List<InterceptInfo> interceptList;
    private List<Integer> checkedList = new ArrayList<Integer>();
    private ListViewStateChangeListener onStateChangeListener;
    public static final int LISTVIEW_FRAGMENT = R.layout.listview_fragment;
    private boolean isCheckNow = false;
    private MyListClickListener listener;
    public ListViewHelper(ListView listView, int fragementType, int layoutId,
                          List<InterceptInfo> interceptList) {

        this.layoutId = layoutId;
        this.listView = listView;
        this.theFragmentType = fragementType;
        context = listView.getContext();
        if (interceptList == null) {
            this.interceptList = emptyList;
        } else {
            this.interceptList = interceptList;
        }
        adapter = new MyAdapter(context);
        listener = new MyListClickListener();
        this.listView.setAdapter(adapter);
        this.listView.setOnItemClickListener(listener);

        this.listView.setOnScrollListener(new MyOnScrollListener());
    }

    public void setCheckStateChangeListener(ListViewStateChangeListener listener){
        onStateChangeListener = listener;
    }

    public void setDataToShow(List<InterceptInfo> interceptList) {
        this.interceptList = interceptList;
        adapter.notifyDataSetChanged();
    }

     class MyAdapter extends BaseAdapter {
         private LayoutInflater inflater;
         private ViewHolder viewHolder;
         private MyDeleteClickListener deleteClickListener;
         private MyDeleteCheckListener deleteCheckListener;
         public MyAdapter(Context context) {
             inflater = LayoutInflater.from(context);
             deleteClickListener = new MyDeleteClickListener();
         }


         @Override
         public int getCount() {
             return interceptList.size();
         }

         @Override
         public Object getItem(int position) {
             return interceptList.get(position);
         }

         @Override
         public long getItemId(int position) {
             return 0;
         }

         @Override
         public View getView(int position, View convertView, ViewGroup parent) {
             if (convertView == null)
             {
                 convertView = inflater.inflate(layoutId, parent, false);
                 viewHolder = new ViewHolder();
                 viewHolder.phoneNumText = (TextView) convertView.findViewById(R.id.phoneNumText);
                 viewHolder.contentText = (TextView) convertView.findViewById(R.id.contentText);
                 viewHolder.timeText = (TextView) convertView.findViewById(R.id.timeText);
                 viewHolder.deleteImage = (ImageView) convertView.findViewById(R.id.deleteImage);
                 viewHolder.deleteCheck = (CheckBox) convertView.findViewById(R.id.deleteCheck);
                 convertView.setTag(viewHolder);
             } else {
                 viewHolder = (ViewHolder) convertView.getTag();
             }
             switch (theFragmentType) {
                 case FragmentHelper.FRAGMENT_SMS:// sms
                     viewHolder.contentText.setVisibility(View.VISIBLE);
                     break;
             }
             viewHolder.deleteCheck.setTag(position);
             if(isCheckNow){
                 if(checkedList.contains(position)){//checked
                     viewHolder.deleteImage.setVisibility(View.GONE);
                     viewHolder.deleteCheck.setVisibility(View.VISIBLE);
                     viewHolder.deleteCheck.setChecked(true);
                 }else{
                     viewHolder.deleteImage.setVisibility(View.GONE);
                     viewHolder.deleteCheck.setVisibility(View.VISIBLE);
                     viewHolder.deleteCheck.setChecked(false);
                 }
             }else{
                 viewHolder.deleteImage.setVisibility(View.VISIBLE);
                 viewHolder.deleteCheck.setVisibility(View.GONE);
             }
             if(interceptList.get(position).getItemName() == null || "".equals(interceptList.get(position).getItemName()))
                 viewHolder.phoneNumText.setText(interceptList.get(position).getItemNum());
             else
                 viewHolder.phoneNumText.setText(interceptList.get(position).getItemName());
             viewHolder.contentText.setText(interceptList.get(position).getItemInfo());
            // viewHolder.timeText.setText(Constant.getDate(context,interceptList.get(position).getTime()));
             viewHolder.deleteImage.setOnClickListener(deleteClickListener);
             viewHolder.deleteCheck.setOnCheckedChangeListener(deleteCheckListener);
             return convertView;
         }

         class ViewHolder {
             TextView phoneNumText, contentText, timeText;
             ImageView deleteImage;
             CheckBox deleteCheck;
         }

         class MyDeleteClickListener  implements View.OnClickListener {
             @Override
             public void onClick(View v) {
                 switch (v.getId()) {
                     case R.id.deleteImage://before delete
                         isCheckNow = true;
                         adapter.notifyDataSetChanged();
//					viewHolder.deleteCheck.setChecked(true);
                         //the interface call
                         onStateChangeListener.OnListViewCheckStateChange(isCheckNow);
                         break;
                 }
             }
         }

         class MyDeleteCheckListener implements CompoundButton.OnCheckedChangeListener {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if(isChecked){
                     if(!checkedList.contains(buttonView.getTag()))
                         checkedList.add((Integer) buttonView.getTag());
                 }
                 else{
                     checkedList.remove(((Integer)buttonView.getTag()));
                 }
                 onStateChangeListener.OnListViewCheckNumChange(checkedList.size());
             }
         }
     }

     class MyListClickListener implements AdapterView.OnItemClickListener {
         private Bundle bundle;
         private Intent intent;

         @Override
         public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

             bundle.clear();

             bundle.putSerializable("interceptInfo",interceptList.get((int) id));

             bundle.putInt("fragmentType", theFragmentType);
             intent.putExtras(bundle);

             switch (theFragmentType){
                 case FragmentHelper.FRAGMENT_SMS:
                   //  intent.setClass(context, SmsDetailActivity.class);
                     break;
             }

             context.startActivity(intent);

         }
     }

    private class MyOnScrollListener implements AbsListView.OnScrollListener {
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {

        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem,
                             int visibleItemCount, int totalItemCount) {
            if (onStateChangeListener != null && firstVisibleItem + visibleItemCount == totalItemCount){
                switch (theFragmentType) {
                    case FragmentHelper.FRAGMENT_SMS:
                        onStateChangeListener.OnListViewQuerySmsToAppend();
                        break;
//                    case FragmentHelper.FRAGMENT_CALL:
//                        onStateChangeListener.OnListViewQueryCallToAppend();
//                        break;
                }
            }
        }
    }
}
