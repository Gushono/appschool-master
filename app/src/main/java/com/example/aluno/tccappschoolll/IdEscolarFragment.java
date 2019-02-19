package com.example.aluno.tccappschoolll;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;


public class IdEscolarFragment extends Fragment {

    String alunoNome, alunoSerie, alunoCidade, alunoInstituicao, imagem, TESTECODIFICADO, alunoComer;
    Date alunoData = new Date();
    Bitmap alunoImagem;
    int alunoRm;
    View view;
    TextView tvRm, tvInstituicao, tvData, tvNome, tvCidade, tvSerie, tvFacultativa, tvFacultativa2;
    CircleImageView imgAluno;
    Switch switchnegocio;
    byte[] stringCodificada;

    SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_idescolar, null);


        tvRm = (TextView) view.findViewById(R.id.tvRm);
        tvInstituicao = (TextView) view.findViewById(R.id.tvInstituicao);
        tvData = (TextView) view.findViewById(R.id.tvDataNascimento);
        tvNome = (TextView) view.findViewById(R.id.tvNome);
        tvCidade = (TextView) view.findViewById(R.id.tvCidade);
        tvSerie = (TextView) view.findViewById(R.id.tvSerie);
        imgAluno = (CircleImageView) view.findViewById(R.id.imgAluno);
        switchnegocio = (Switch) view.findViewById(R.id.switchAlmoco);
        tvFacultativa = (TextView) view.findViewById(R.id.tvFacul);
        tvFacultativa2 = (TextView) view.findViewById(R.id.tvFacul2);


        SharedPreferences sharedPreferencess = view.getContext().getSharedPreferences("guardarDados", Context.MODE_PRIVATE);
        String cpf = sharedPreferencess.getString("cpfUser", "");
//        Toast.makeText(view.getContext(), ""+cpf, Toast.LENGTH_LONG).show();


        try {
            //IP computador / nome_da_pasta / nome_web_service / nome_método
            String url = "ListaAluno";
            String parametros[] = {"cpf"};
            String valores[] = {cpf};
            String retorno = new ConectaHTTP(url, parametros, valores).execute("").get();

            Log.i("Retorno XML ", retorno);

            //Método para ler um XML com categorias (quando o webservice retorna uma lista)
            BuscaConteudoXML xml = new BuscaConteudoXML(retorno, "Aluno");
            NodeList elementos = xml.elementosXML();

            //Para cada item encontrado à partir do ponto principal
            for (int i = 0; i < elementos.getLength(); i++) {
                Node node = elementos.item(i);

                //Testando se existe um item dentro da estrutura "Produto"
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;


                    alunoRm = Integer.parseInt((xml.getValue("Rm", element).toString()));
                    alunoNome = (xml.getValue("Nome", element).toString());
                    alunoSerie = (xml.getValue("Serie", element).toString());
                    alunoCidade = (xml.getValue("Cidade", element).toString());
                    alunoInstituicao = (xml.getValue("NomeInstituicao", element).toString());
                    alunoData = formato.parse(xml.getValue("DataNascimento", element).toString().replace("T00:00:00", "").replace("-", "/"));
                    alunoComer = (xml.getValue("Comer", element).toString());

                    imagem = (xml.getValue("Foto", element).toString());

                    TESTECODIFICADO = imagem;

                    stringCodificada = Base64.decode(imagem, Base64.DEFAULT);

                    Bitmap decodedByte = BitmapFactory.decodeByteArray(stringCodificada, 0, stringCodificada.length);
                    alunoImagem = decodedByte;


                }
            }

        } catch (Exception e) {
//            tvRm.setText("Oiii");
//            Toast.makeText(view.getContext(), ""+e, Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

        if (alunoComer.equals("Sim")) {
            switchnegocio.setChecked(true);
        }

        formato.applyPattern("dd/MM/yyyy");
        formato.format(alunoData);
//        Toast.makeText(view.getContext(), ""+ formato.format(alunoData), Toast.LENGTH_LONG).show();


        if (alunoSerie.equals("3 ano E")) {
            tvFacultativa.setText("A aula facultativa do aluno " + alunoNome);
            tvFacultativa2.setText("É das 11:30 até as 12:20 de Terça - Feira");

        } else if (alunoSerie.equals("3 ano B")) {
            tvFacultativa.setText("A aula facultativa do aluno " + alunoNome);
            tvFacultativa2.setText("É das 07:00 até as 07:50 de Quinta - Feira");
        } else if (alunoSerie.equals("3 ano A"))
        {
            tvFacultativa.setText("A aula facultativa do aluno "+alunoNome);
            tvFacultativa.setText("É das 11:30 as 12:20 de Quarta - Feira");
        }

            tvRm.setText("Rm: " + alunoRm);
        tvInstituicao.setText("Escola: " + alunoInstituicao);
        tvNome.setText("Nome: " + alunoNome);
        tvCidade.setText("Cidade: " + alunoCidade);
        tvSerie.setText("Série: " + alunoSerie);
        tvData.setText("Nascimento: " + formato.format(alunoData));
        imgAluno.setImageBitmap(alunoImagem);

// ----------------------------------------- VER A CONFIGURAÇÃO DO SWITCH E SETAR PARA SIM OU NÃO ----------------------------------------------


        if (switchnegocio.isChecked() && alunoComer.equals("Sim")) {
            Toast.makeText(view.getContext(), "O aluno já vai comer", Toast.LENGTH_SHORT).show();
        } else if (!switchnegocio.isChecked() && alunoComer.equals("Não")) {

            Toast.makeText(view.getContext(), "O alunão não vai comer", Toast.LENGTH_SHORT).show();
        }

        switchnegocio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (switchnegocio.isChecked() && alunoComer.equals("Não")) {

//            switchnegocio.setBackgroundColor(Color.GREEN);
                    Log.i("oi", "oi");

                    try {
                        //IP computador / nome_da_pasta / nome_web_service / nome_método
                        String url = "atualizaAlmoco";
                        String parametros[] = {"rm"};
                        String valores[] = {String.valueOf(alunoRm)};
                        String retorno = new ConectaHTTP(url, parametros, valores).execute("").get();

                        alunoComer = "Sim";

                        Log.i("Retorno XML ", retorno);


                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else if (!switchnegocio.isChecked() && alunoComer.equals("Sim")) {

                    try {

                        //IP computador / nome_da_pasta / nome_web_service / nome_método
                        String url = "atualizaAlmoconao";
                        String parametros[] = {"rm"};
                        String valores[] = {String.valueOf(alunoRm)};
                        String retorno = new ConectaHTTP(url, parametros, valores).execute("").get();

                        Log.i("Retorno XML ", retorno);

                        alunoComer = "Não";

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

            }
        });


        tvRm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int numero, numero2, numero3;


                Random a = new Random();
                numero = a.nextInt(255);
                numero2 = a.nextInt(255);
                numero3 = a.nextInt(255);


                tvRm.setTextColor(Color.rgb(numero, numero2, numero3));


            }
        });


        return view;
    }
}
