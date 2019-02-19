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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * A simple {@link Fragment} subclass.
 */
public class AulaFragment extends Fragment {


View view;
ListView listinha, lista2;
String[] listaSemana = {"Segunda", "Terça", "Quarta", "Quinta", "Sexta"};
String[] horarioAulas = {"07:00 - 07:50", "07:50 - 08:40", "08:40 - 09:30", "09:30 - 09:50", "09:50 - 10:40", "10:40 - 11:30", "11:30 - 12:20"};
String[] tipoAula = new String[7];
TextView tvAulinha, tvAjuda;
String diazinho;
Button btnAnimaListaSemana;


ViewGroup.LayoutParams listaDiasSemana;
ViewGroup.LayoutParams listadiasAulas;

    SharedPreferences sharedPreferencess = MenuManeiro.contexto.getSharedPreferences("guardarDados", Context.MODE_PRIVATE);

    String sala = sharedPreferencess.getString("salaUser", "");
    String diahj = "a";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_aula, container, false);
        listinha = (ListView) view.findViewById(R.id.listinha);
        lista2 = (ListView) view.findViewById(R.id.lista2);
        btnAnimaListaSemana = (Button) view.findViewById(R.id.btnAnimaListaDias);
        tvAulinha = (TextView) view.findViewById(R.id.tvAulinha);
        tvAjuda = (TextView) view.findViewById(R.id.tvAjuda);


        listaCustomizada listacustomizada = new listaCustomizada(view.getContext(), listaSemana);
        listinha.setAdapter(listacustomizada);


        //erro aqui que pego independente da sala de aula

        try {
            //IP computador / nome_da_pasta / nome_web_service / nome_método
            String url = "listaAulasporDia";
            String parametros[] = {"sala", "diaVer"};
            String valores[] = {sala, diahj};
            String retorno = new ConectaHTTP(url, parametros, valores).execute("").get();

            Log.i("Retorno XML ", retorno);

            //Método para ler um XML com categorias (quando o webservice retorna uma lista)
            BuscaConteudoXML xml = new BuscaConteudoXML(retorno, "Materia");
            NodeList elementos = xml.elementosXML();

            //Para cada item encontrado à partir do ponto principal
            for (int a = 0; a < elementos.getLength(); a++) {
                Node node = elementos.item(a);

                //Testando se existe um item dentro da estrutura "Produto"
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;


                    tipoAula[a] = ((xml.getValue("materia", element).toString()));


                }
            }
        }catch (Exception e){
            e.getMessage();
            Toast.makeText(view.getContext(), ""+e.toString(), Toast.LENGTH_SHORT).show();
        }

        final listaCustomizada2Aulas lista = new listaCustomizada2Aulas(view.getContext(), horarioAulas, tipoAula);
        lista2.setAdapter(lista);




        listinha.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                switch(i)

                {
                    case 0:

                        diazinho = "segunda";
                        animalistaAulas();
                        tvAulinha.setText("As aulas de Segunda são: ");
                        break;

                    case 1:
                        diazinho = "terca";
                        animalistaAulas();
                        tvAulinha.setText("As aulas de Terça são: ");
                        break;

                    case 2:

                        diazinho = "quarta";
                        animalistaAulas();
                        tvAulinha.setText("As aulas de Quarta são: ");
                        break;

                    case 3:
                        diazinho = "quinta";
                        animalistaAulas();
                        tvAulinha.setText("As aulas de Quinta são: ");
                        break;
                    case 4:
                        diazinho = "sexta";
                        animalistaAulas();
                        tvAulinha.setText("As aulas de Sexta são: ");
                        break;
                }


                lista2.setAdapter(null);

                try {
                    //IP computador / nome_da_pasta / nome_web_service / nome_método
                    String url = "listaAulasporDia";
                    String parametros[] = {"sala", "diaVer"};
                    String valores[] = {sala, diazinho};
                    String retorno = new ConectaHTTP(url, parametros, valores).execute("").get();

                    Log.i("Retorno XML ", retorno);

                    //Método para ler um XML com categorias (quando o webservice retorna uma lista)
                    BuscaConteudoXML xml = new BuscaConteudoXML(retorno, "Materia");
                    NodeList elementos = xml.elementosXML();

                    //Para cada item encontrado à partir do ponto principal
                    for (int a = 0; a < elementos.getLength(); a++) {
                        Node node = elementos.item(a);

                        //Testando se existe um item dentro da estrutura "Produto"
                        if (node.getNodeType() == Node.ELEMENT_NODE) {
                            Element element = (Element) node;


                            tipoAula[a] = ((xml.getValue("materia", element).toString()));


                        }
                    }
                }catch (Exception e){
                    e.getMessage();
                    Toast.makeText(view.getContext(), ""+e.toString(), Toast.LENGTH_SHORT).show();
                }

                final listaCustomizada2Aulas lista = new listaCustomizada2Aulas(view.getContext(), horarioAulas, tipoAula);
                lista2.setAdapter(lista);





            }
        });

        btnAnimaListaSemana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listaDiasSemana = listinha.getLayoutParams();
                listadiasAulas  = lista2.getLayoutParams();

//                Toast.makeText(view.getContext(), "Lista de aulas = " +listadiasAulas.height + "Lista dias: " +listaDiasSemana.height, Toast.LENGTH_SHORT).show();


                if(listadiasAulas.height == 1)
                {
                    Log.i("TESTE", "ALTURA 13");
                    animalistaAulas();


                }else{
                    Log.i("TESTE", "ALTURA 1000");
                    animalistaDias();
                }

//                if(listadiasAulas.height == 1000)
//                {
//
//
//                }





                //CHAMAR OUTRO MÉTODO QUE FAÇA A LISTA DESCER E MUDAR AS PALAVRAS DO TEXT VIEW
                //BOTAR UM IF ELSE QUE VEJA A ALTURA DA LISTA E DEPENDENDO DELA CHAME UM MÉTODO

            }
        });





        return view;


    }



    void animalistaDias()
    {

        tvAjuda.setVisibility(View.INVISIBLE);
        listinha.setVisibility(View.VISIBLE);
        btnAnimaListaSemana.setBackground(getResources().getDrawable(R.drawable.setabaixo));

        listaDiasSemana = listinha.getLayoutParams();
        listadiasAulas  = lista2.getLayoutParams();



        listaDiasSemana.height = 800;
        listinha.setLayoutParams(listaDiasSemana);


        listadiasAulas.height = 1;
        lista2.setLayoutParams(listadiasAulas);





        btnAnimaListaSemana.animate()


                .setInterpolator(new FastOutSlowInInterpolator())
                .setDuration(1000)
                .translationX(0)
                .translationY(-1200);

                listinha.animate()
                .setInterpolator(new FastOutSlowInInterpolator())
                .setDuration(1000)
                .translationX(0)
                .translationY(-400);

        listinha.setVisibility(View.VISIBLE);
        lista2.setVisibility(View.INVISIBLE);

        if(listadiasAulas.height == 13){
            tvAulinha.setText("Escolha o dia da semana que você deseja ver sua aula");
        }



    }


    void animalistaAulas()
    {
        lista2.setVisibility(View.VISIBLE);
        tvAjuda.setVisibility(View.VISIBLE);
        listinha.setVisibility(View.INVISIBLE);

        btnAnimaListaSemana.setBackground(getResources().getDrawable(R.drawable.setacima));

        Log.i("TESTE", "ANIMA LISTA AULAS");

        listaDiasSemana = listinha.getLayoutParams();
        listadiasAulas  = lista2.getLayoutParams();

        listadiasAulas.height = 1200;
        lista2.setLayoutParams(listadiasAulas);


        listaDiasSemana.height = 3;
        listinha.setLayoutParams(listaDiasSemana);


        btnAnimaListaSemana.animate()


                .setInterpolator(new FastOutSlowInInterpolator())
                .setDuration(1000)
                .translationX(0)
                .translationY(0);

        listinha.animate()

                .setInterpolator(new FastOutSlowInInterpolator())
                .setDuration(1000)
                .translationX(0)
                .translationY(0);


        if(listadiasAulas.height == 1000)
        {



        }





    }


}
