package com.example.aluno.tccappschoolll;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ybq.android.spinkit.SpinKitView;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class telaLogin extends Activity {

    EditText txtMatricula, txtSenha;
    Button btnLogar;
    ConstraintLayout background;
    TextView esqueciSenha;
    ImageView logo, logoSesi;
    String login = "3", senha = "senha";
    SpinKitView spin;
    CheckBox chq;
    int testeUsuario;
    String testeSenha, testeCpf, testeEmail, testeNome, testeFoto, testeSala;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_tela);


        spin = (SpinKitView) findViewById(R.id.spin_kit);
        txtMatricula = (EditText) findViewById(R.id.txtNumeroMatricula);
        txtSenha = (EditText) findViewById(R.id.txtSenha);
        btnLogar = (Button) findViewById(R.id.btnLogar);
        background = (ConstraintLayout) findViewById(R.id.bakground);
        esqueciSenha = (TextView) findViewById(R.id.txtviewEsqueci);
        logo = (ImageView) findViewById(R.id.imgSesi);
        chq = (CheckBox) findViewById(R.id.checkboxConectado);


        //Teste Validação de Login Sarah Unicónio Azul


        //Função animação
        animacaoLogo();
        animaMatricula();
        animaSenha();
        animaBotao();
        animaEsquecisenhaa();

        txtMatricula.addTextChangedListener(Mascara.insert(Mascara.RM_MASK, txtMatricula));


        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnLogar.setVisibility(View.INVISIBLE);
                spin.setVisibility(View.VISIBLE);


                if (txtMatricula.getText().toString().equals("") || txtSenha.getText().toString().equals("")) {
                    Toast.makeText(telaLogin.this, "Não deixe campos em branco", Toast.LENGTH_SHORT).show();
                    btnLogar.setVisibility(View.VISIBLE);
                    spin.setVisibility(View.INVISIBLE);
                } else {


                    try {

                        String parametros[] = {"rm", "senha"};
                        String valores[] = {txtMatricula.getText().toString(), txtSenha.getText().toString()};
                        //IP computador / nome_da_pasta / nome_web_service / nome_método
                        String url = "Logar";
                        String retorno = new ConectaHTTP(url, parametros, valores).execute("").get();

                        //Método para ler um XML com categorias (quando o webservice retorna uma lista)
                        BuscaConteudoXML xml = new BuscaConteudoXML(retorno, "Usuario");
                        NodeList elementos = xml.elementosXML();


                        //Para cada item encontrado à partir do ponto principal
                        for (int i = 0; i < elementos.getLength(); i++) {
                            Node node = elementos.item(i);

                            //Testando se existe um item dentro da estrutura "Produto"
                            if (node.getNodeType() == Node.ELEMENT_NODE) {
                                Element element = (Element) node;
                                //Testes

//                            imgid[i] = decodedByte;
                                testeUsuario = Integer.parseInt((xml.getValue("Rm", element).toString()));
                                testeCpf = (xml.getValue("Cpf", element).toString());
                                testeSenha = (xml.getValue("Senha", element).toString());
                                testeNome = (xml.getValue("Nome", element).toString());
                                testeEmail = (xml.getValue("Email", element).toString());
                                testeFoto = (xml.getValue("Foto", element).toString());
                                testeSala = (xml.getValue("SaladeAula", element).toString());

                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
//                    Toast.makeText(telaLogin.this, ""+e, Toast.LENGTH_LONG).show();

                    }


                    SharedPreferences.Editor editor = getSharedPreferences("guardarDados", MODE_PRIVATE).edit();
                    if (testeCpf != null) {
                        editor.putString("cpfUser", testeCpf);
                        editor.putString("nomeUser", testeNome);
                        editor.putString("emailUser", testeEmail);
                        editor.putString("fotoUser", testeFoto);
                        editor.putString("salaUser", testeSala);
                        editor.putString("rmUser", String.valueOf(testeUsuario));
                        editor.putString("senhaUser", testeSenha);


                        editor.apply();
                    }


                    if (Integer.parseInt(txtMatricula.getText().toString()) == testeUsuario && txtSenha.getText().toString().equals(testeSenha)) {
                        new Handler().postDelayed(new Runnable() {
                            /*
                             * Exibindo splash com um timer.
                             */
                            @Override
                            public void run() {

                                Toast.makeText(telaLogin.this, "Logado", Toast.LENGTH_LONG).show();


                                txtMatricula.setText("");
                                txtSenha.setText("");
                                Intent telaEntrar = new Intent(telaLogin.this, MenuManeiro.class);
                                startActivity(telaEntrar);
                                finish();

                            }
                        }, 3000);
                    } else {
                        new Handler().postDelayed(new Runnable() {
                            /*
                             * Exibindo splash com um timer.
                             */
                            @Override
                            public void run() {

                                Toast.makeText(telaLogin.this, "Sem acesso", Toast.LENGTH_SHORT).show();
                                txtMatricula.setText("");
                                txtSenha.setText("");
                                btnLogar.setVisibility(View.VISIBLE);
                                spin.setVisibility(View.INVISIBLE);


                            }
                        }, 3000);

                    }

                }


//                Toast.makeText(telaLogin.this, testeUsuario+" Cpf retornado "+testeCpf+"Senha retornada " +testeSenha, Toast.LENGTH_LONG).show();


//
////                  PARA LOGIN NO CEL SAMUKA
//                if(login.equals("3") && senha.equals("senha")){
//                    Toast.makeText(telaLogin.this, "Logado", Toast.LENGTH_LONG).show();
//                    Intent telaEntrar = new Intent(telaLogin.this, MenuManeiro.class);
//                    startActivity(telaEntrar);
//                }


            }
        });


        esqueciSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent it = new Intent(telaLogin.this, telaEsqueciSenha.class);
                startActivity(it);

//                Toast.makeText(telaLogin.this, "Foda-se irmão", Toast.LENGTH_LONG).show();

            }
        });


    }


    void animacaoLogo() {

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(logo, View.ALPHA, 0, 1);
        //Tempo, em milisegundos, da sua animação. Caso não coloque nenhum, o default é 300.
        objectAnimator.setDuration(1500);

        /*Aqui esta a mágica. Você define o tempo (em milisegundos) para sua animação começar.
         * Ou seja, depois de 2 segundos, sua ImageView ira começar a desaparecer
         */
        objectAnimator.setStartDelay(0);
        //Caso você queira um Listener para o termino da animação
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                logo.setVisibility(View.VISIBLE);
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

    public void animaMatricula() {

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(txtMatricula, View.ALPHA, 0, 1);
        //Tempo, em milisegundos, da sua animação. Caso não coloque nenhum, o default é 300.
        objectAnimator.setDuration(1000);
        /*Aqui esta a mágica. Você define o tempo (em milisegundos) para sua animação começar.
         * Ou seja, depois de 2 segundos, sua ImageView ira começar a desaparecer
         */
        objectAnimator.setStartDelay(0);
        //Caso você queira um Listener para o termino da animação
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                logo.setVisibility(View.VISIBLE);


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

    public void animaSenha() {

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(txtSenha, View.ALPHA, 0, 1);
        //Tempo, em milisegundos, da sua animação. Caso não coloque nenhum, o default é 300.
        objectAnimator.setDuration(1000);
        /*Aqui esta a mágica. Você define o tempo (em milisegundos) para sua animação começar.
         * Ou seja, depois de 2 segundos, sua ImageView ira começar a desaparecer
         */
        objectAnimator.setStartDelay(0);
        //Caso você queira um Listener para o termino da animação
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

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

    public void animaBotao() {

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(btnLogar, View.ALPHA, 0, 1);
        //Tempo, em milisegundos, da sua animação. Caso não coloque nenhum, o default é 300.
        objectAnimator.setDuration(1000);
        /*Aqui esta a mágica. Você define o tempo (em milisegundos) para sua animação começar.
         * Ou seja, depois de 2 segundos, sua ImageView ira começar a desaparecer
         */
        objectAnimator.setStartDelay(0);
        //Caso você queira um Listener para o termino da animação
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

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

    public void animaEsquecisenhaa() {

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(esqueciSenha, View.ALPHA, 0, 1);
        //Tempo, em milisegundos, da sua animação. Caso não coloque nenhum, o default é 300.
        objectAnimator.setDuration(1000);
        /*Aqui esta a mágica. Você define o tempo (em milisegundos) para sua animação começar.
         * Ou seja, depois de 2 segundos, sua ImageView ira começar a desaparecer
         */
        objectAnimator.setStartDelay(0);
        //Caso você queira um Listener para o termino da animação
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

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

}
