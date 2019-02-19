package com.example.aluno.tccappschoolll;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class listaCustomizada2Aulas extends ArrayAdapter<String>{

    private String [] horarios, aulas;

    private Context context;


    public listaCustomizada2Aulas(Context context, String [] horarios, String [] aulas) {
        super(context, R.layout.listacustomizada2aulas, horarios);

        this.context = context;
        this.horarios = horarios;
        this.aulas = aulas;


    }

    public View getView (int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View r = convertView;
        ViewHolder viewHolder = null;
        if(r==null)
        {

            LayoutInflater inflater = LayoutInflater.from(getContext());

            r = inflater.inflate(R.layout.listacustomizada2aulas, null, true);
            viewHolder = new ViewHolder(r);
            r.setTag(viewHolder);

        }else {

            viewHolder = (ViewHolder) r.getTag();
        }


        viewHolder.tvw1.setText("" + horarios[position]);
        viewHolder.tvw2.setText("" +aulas[position]);


        return r;





    }
    class ViewHolder
    {
        TextView tvw1, tvw2;

        ViewHolder(View v)
        {
            tvw1 = v.findViewById(R.id.tvHoras);
            tvw2 = v.findViewById(R.id.tvAulas);


        }
    }

}
