package com.elorrieta.trivial.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;

import com.elorrieta.trivial.R;
import com.elorrieta.trivial.model.bean.Usuario;

import java.util.ArrayList;


public class RankingAdapter extends ArrayAdapter<Usuario> {

    private Context mContext;
    private ArrayList<Usuario> userList = new ArrayList<>();

    public RankingAdapter(@NonNull Context context, ArrayList<Usuario> list) {
        super(context, 0 , list);
        mContext = context;
        userList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.ranking_item, parent, false);

        Usuario currentUser = userList.get(position);
        int drawableId = R.drawable.rounded_background;

        switch (position) {
            case 0:
                drawableId = R.drawable.rounded_background_first;
                break;
            case 1:
                drawableId = R.drawable.rounded_background_second;
                break;
            case 2:
                drawableId = R.drawable.rounded_background_third;
                break;
        }

        ((View) listItem.findViewById(R.id.rankingItemBackground)).setBackground( ResourcesCompat.getDrawable( listItem.getResources(), drawableId, null) );
        ((TextView) listItem.findViewById(R.id.rankingItemUsername)).setText( currentUser.getUsername() );

        return listItem;
    }
}