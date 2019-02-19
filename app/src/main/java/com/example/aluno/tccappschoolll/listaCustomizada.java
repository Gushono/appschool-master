package com.example.aluno.tccappschoolll;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class listaCustomizada extends ArrayAdapter<String>{

    private String [] dia;

    private Context context;


    public listaCustomizada(Context context, String [] dia) {
        super(context, R.layout.listacustomizada, dia);

        this.context = context;
        this.dia = dia;


    }

        public View getView (int position, @Nullable View convertView, @NonNull ViewGroup parent){
            View r = convertView;
                ViewHolder viewHolder = null;
                if(r==null)
                {

                    LayoutInflater inflater = LayoutInflater.from(getContext());

                    r = inflater.inflate(R.layout.listacustomizada, null, true);
                    viewHolder = new ViewHolder(r);
                    r.setTag(viewHolder);

                }else {

                    viewHolder = (ViewHolder) r.getTag();
                }


                viewHolder.tvw1.setText("" + dia[position]);


        return r;





    }
    class ViewHolder
    {
        TextView tvw1;

        ViewHolder(View v)
        {
            tvw1 = v.findViewById(R.id.tvDia);
        }
    }

    }
