package com.jhj.myapplication3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jhj.myapplication3.ui.main.ListVO;

import java.util.ArrayList;

public class ChatAdapter extends BaseAdapter {
    private LayoutInflater inflater; // 추출한 inflater를 저장할 변수
    private Context context; // inflater를 추출하기 위한 화면정보
    private int layout; // 템플릿의 id
    private ArrayList<ListVO> al; //꾸밀 데이터

    public ChatAdapter(Context context, int layout, ArrayList<ListVO> al){
        this.context=context;
        this.layout = layout;
        this.al = al;

        this.inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() { // 리스트뷰 항목 개수 지정
        return al.size();
    }

    @Override
    public Object getItem(int position) { // position 번쨰의 아이템을 리턴~!
        return al.get(position);
    }

    @Override
    public long getItemId(int position) { //
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // ============================================항목당 한번씩 일어나는 일~!
        // - ArrayList<ChatVO>에 저장되어 있는 데이터로 View로 바뀐 템플릿을 꾸민다!
        // - ListView에 추가한다~~
        if (convertView ==null ){ //이전에 inflate된 항목 뷰가 없다면~~
            convertView = inflater.inflate(layout, parent, false);
        }
        // 아직 안꾸밈 ~

        //템플릿을 View로 만들어서 저장해둔 객체인 convertView 안에서 찾겠다~
        // 템플릿에서 찾겠다!!
        TextView tv_id = convertView.findViewById(R.id.tv_id);
        tv_id.setText(al.get(position).getWorker_id());
        ImageView imageView = convertView.findViewById(R.id.img);
        imageView.setImageResource(R.drawable.worker_icon);

        return convertView;
    }


}

