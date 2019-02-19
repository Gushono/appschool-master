package com.example.aluno.tccappschoolll;


import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.github.chrisbanes.photoview.PhotoView;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class CardapioFragment extends Fragment {

    View view;
    ImageView imagemTeste;
    String imagem;
    Bitmap imgCardapio;
    boolean isImageFitToScreen;
    private int REQUEST_CODE = 1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_cardapio, container, false);
        view.getContext();
        getActivity();
        getContext();



         imagemTeste = (ImageView) view.findViewById(R.id.ivCardapio);



        try {
            //IP computador / nome_da_pasta / nome_web_service / nome_método
            String url = "montaCardapio";
            String parametros[] = {};
            String valores[] = {};
            String retorno = new ConectaHTTP(url, parametros, valores).execute("").get();

            Log.i("Retorno XML ", retorno);

            //Método para ler um XML com categorias (quando o webservice retorna uma lista)
            BuscaConteudoXML xml = new BuscaConteudoXML(retorno, "Cardapio");
            NodeList elementos = xml.elementosXML();

            //Para cada item encontrado à partir do ponto principal
            for (int a = 0; a < elementos.getLength(); a++) {
                Node node = elementos.item(a);

                //Testando se existe um item dentro da estrutura "Produto"
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;


                    imagem = (xml.getValue("cardapinho", element).toString());

                    byte[] stringCodificada = Base64.decode(imagem, Base64.DEFAULT);

                    Bitmap decodedByte = BitmapFactory.decodeByteArray(stringCodificada, 0, stringCodificada.length);
                    imgCardapio = decodedByte;


                }
            }
        }catch (Exception e){
            e.getMessage();
            Toast.makeText(view.getContext(), ""+e.toString(), Toast.LENGTH_SHORT).show();
        }


            imagemTeste.setImageBitmap(imgCardapio);

        imagemTeste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Dialog dialog = new Dialog(view.getContext(), R.style.Theme_AppCompat_DialogWhenLarge);
                dialog.setContentView(R.layout.popup_cardapio);
               final PhotoView cardapinho = (PhotoView) dialog.findViewById(R.id.ivCardapioZoom);
                cardapinho.setImageBitmap(imgCardapio);

                dialog.show();


                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(cardapinho, View.ALPHA, 0, 1);
                //Tempo, em milisegundos, da sua animação. Caso não coloque nenhum, o default é 300.
                objectAnimator.setDuration(600);

                /*Aqui esta a mágica. Você define o tempo (em milisegundos) para sua animação começar.
                 * Ou seja, depois de 2 segundos, sua ImageView ira começar a desaparecer
                 */
                objectAnimator.setStartDelay(0);
                //Caso você queira um Listener para o termino da animação
                objectAnimator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        cardapinho.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                objectAnimator.start();


            }
        });



        return view;

    }


//    TERMINAR ESSA PORRA DE PEGAR A IMAGEM DA GALERIA E TACAR EM UM IMAGEVIEW DENTRO DE UM FRAGMENT
//
//
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

      //  Context applicationContext = CardapioFragment.getContextOfApplication();
        //applicationContext.getContentResolver();

        if(resultCode == RESULT_OK) {
            if(requestCode == 1000){
                Uri path = data.getData();



                imagemTeste.setImageURI(path);
            }
        }


    }



}
