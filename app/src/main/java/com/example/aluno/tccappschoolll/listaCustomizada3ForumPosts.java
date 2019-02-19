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

public class listaCustomizada3ForumPosts extends ArrayAdapter<String> {

    private String[] nomeProfessor, postProfessor;

    private Context context;


    public listaCustomizada3ForumPosts(Context context, String nomeProfessor[], String postProfessor[]) {
        super(context, R.layout.listacustomizadaforum, nomeProfessor);

        this.context = context;
        this.nomeProfessor = nomeProfessor;
        this.postProfessor = postProfessor;


    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View r = convertView;
        ViewHolder viewHolder = null;
        if (r == null) {

            LayoutInflater inflater = LayoutInflater.from(getContext());

            r = inflater.inflate(R.layout.listacustomizadaforum, null, true);
            viewHolder = new ViewHolder(r);
            r.setTag(viewHolder);

        } else {

            viewHolder = (ViewHolder) r.getTag();
        }


        viewHolder.tvw1.setText(nomeProfessor[position]);
        viewHolder.tvw2.setText(postProfessor[position]);


        return r;


    }

    class ViewHolder {
        TextView tvw1;
        TextView tvw2;

        ViewHolder(View v) {
            tvw1 = v.findViewById(R.id.tvNomeAluno);
            tvw2 = v.findViewById(R.id.tvPostAluno);
        }
    }

}
