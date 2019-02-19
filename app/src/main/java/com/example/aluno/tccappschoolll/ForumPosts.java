package com.example.aluno.tccappschoolll;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class ForumPosts extends Fragment {

    ListView listaPosts;
    String[] nomeProfessor = new String[4];
    String[] postProfessor = new String[4];
    EditText postagem;

    View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

       view = inflater.inflate(R.layout.fragment_forum_posts, container, false);


        listaPosts = (ListView) view.findViewById(R.id.listaposts);
        postagem = (EditText) view.findViewById(R.id.txtPost);

        nomeProfessor[0] = "Gustavinho";
        postProfessor[0] = "Aprendemos sobre como fazer soma e divisão";




        SharedPreferences sharedPreferencess = MenuManeiro.contexto.getSharedPreferences("guardarDados", Context.MODE_PRIVATE);

        String cpf = (sharedPreferencess.getString("cpfUser", ""));


//        try {
//            //IP computador / nome_da_pasta / nome_web_service / nome_método
//            String url = "PostaForum";
//            String parametros[] = {"postagem", "cpf"};
//            String valores[] = {postagem.getText().toString(), cpf};
//            String retorno = new ConectaHTTP(url, parametros, valores).execute("").get();
//
//            Log.i("Retorno XML ", retorno);
//
//
//           }catch (Exception e)
//         {
//            e.getMessage();
//            Toast.makeText(view.getContext(), ""+e.toString(), Toast.LENGTH_SHORT).show();
//         }
//
//
//
//
//        listaCustomizada3ForumPosts lista = new listaCustomizada3ForumPosts(view.getContext(), nomeProfessor, postProfessor);
//        listaPosts.setAdapter(lista);




        return view;
    }
}
