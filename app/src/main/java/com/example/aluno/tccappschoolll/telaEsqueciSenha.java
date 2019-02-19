package com.example.aluno.tccappschoolll;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class telaEsqueciSenha extends Activity {

    EditText txtCpf, txtCodigo, txtNovaSenha, txtRepeteSenha;
    TextView tvCpf, tvCodigoSenha, tvSenhaNova;
    Button btnEnviarCpf, btnEnviarCodigo, btnEnviarSenha;

    String cpfGuardado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_esqueci_senha);


        txtCpf = (EditText) findViewById(R.id.txtDigiteCpf);
        txtCodigo = (EditText) findViewById(R.id.txtCodigoSenha);
        txtNovaSenha = (EditText) findViewById(R.id.txtNovaSenha);
        txtRepeteSenha = (EditText) findViewById(R.id.txtConfirmaNovaSenha);


        tvCpf = (TextView) findViewById(R.id.tvCpf);
        tvCodigoSenha = (TextView) findViewById(R.id.tvCodigoSenha);
        tvSenhaNova = (TextView) findViewById(R.id.tvSenhaNova);

        btnEnviarCodigo = (Button) findViewById(R.id.btnEnviaCodigo);
        btnEnviarCpf = (Button) findViewById(R.id.btnConfirmaEnviar);
        btnEnviarSenha = (Button) findViewById(R.id.btnEnviarSenha);

//        String parametros[] = {"email", "codigo"};
//        String valores[] = {txtEmail.getText().toString(), txtCodigo.getText().toString()};


        txtCpf.addTextChangedListener(Mascara.insert(Mascara.CPF_MASK, txtCpf));


//        Toast.makeText(telaEsqueciSenha.this, "seu cpf é: " + txtCpf.getText().toString(), Toast.LENGTH_SHORT).show();


        btnEnviarCpf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//
//                Toast.makeText(telaEsqueciSenha.this, "seu cpf é: " + txtCpf.getText().toString(), Toast.LENGTH_SHORT).show();
                String parametros[] = {"cpf"};
                String valores[] = {txtCpf.getText().toString()};

//                Toast.makeText(telaEsqueciSenha.this, "seu cpf é: " +txtCpf.getText().toString(), Toast.LENGTH_SHORT).show();

                try {
                    //IP computador / nome_da_pasta / nome_web_service / nome_método

                    String url = "recuperaSenha";

                    String retorno = new ConectaHTTP(url, parametros, valores).execute("").get();

                    if (retorno.contains("true"))
                    {
                        cpfGuardado = txtCpf.getText().toString();
                        Toast.makeText(telaEsqueciSenha.this, "seu cpf é: " +cpfGuardado, Toast.LENGTH_SHORT).show();

                        tvCpf.setVisibility(View.INVISIBLE);
                        txtCpf.setVisibility(View.INVISIBLE);
                        btnEnviarCpf.setVisibility(View.INVISIBLE);
                        btnEnviarCpf.setEnabled(false);


                        tvCodigoSenha.setVisibility(View.VISIBLE);
                        txtCodigo.setVisibility(View.VISIBLE);
                        btnEnviarCodigo.setVisibility(View.VISIBLE);
                        btnEnviarCodigo.setEnabled(true);


                    } else {
                        Toast.makeText(telaEsqueciSenha.this, "n tem cpf", Toast.LENGTH_SHORT).show();
//                txtEmail.setText("");
//                txtSenha.setText("");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });


        btnEnviarCodigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String parametros[] = {"codigo", "cpf"};
                String valores[] = {txtCodigo.getText().toString(), cpfGuardado};


                try {
                    //IP computador / nome_da_pasta / nome_web_service / nome_método

                    String url = "confirmaCodigo";

                    String retorno = new ConectaHTTP(url, parametros, valores).execute("").get();

//                      Toast.makeText(telaEsqueciSenha.this, ""+retorno, Toast.LENGTH_LONG).show();

//                    Toast.makeText(telaEsqueciSenha.this, "Código autenticado, por favor, escolha uma nova senha", Toast.LENGTH_LONG).show();

                    if (retorno.contains("true")) {
                        tvCodigoSenha.setVisibility(View.INVISIBLE);
                        txtCodigo.setVisibility(View.INVISIBLE);
                        btnEnviarCodigo.setVisibility(View.INVISIBLE);
                        btnEnviarCodigo.setEnabled(false);

                        Toast.makeText(telaEsqueciSenha.this, "Código autenticado, por favor, escolha uma nova senha", Toast.LENGTH_LONG).show();

                        tvSenhaNova.setVisibility(View.VISIBLE);
                        txtNovaSenha.setVisibility(View.VISIBLE);
                        txtRepeteSenha.setVisibility(View.VISIBLE);
                        btnEnviarSenha.setEnabled(true);
                        btnEnviarSenha.setVisibility(View.VISIBLE);


                    } else {
//                        Toast.makeText(telaEsqueciSenha.this, "Codigo incorreto", Toast.LENGTH_SHORT).show();
//                txtEmail.setText("");
//                txtSenha.setText("");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });


        btnEnviarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtNovaSenha.getText().toString().isEmpty() || txtRepeteSenha.getText().toString().isEmpty()) {
                    Toast.makeText(telaEsqueciSenha.this, "Não deixe nada em branco", Toast.LENGTH_SHORT).show();
                } else {


                    if (txtNovaSenha.getText().toString().equals(txtRepeteSenha.getText().toString())) {

                        String parametros[] = {"senha", "cpf"};
                        String valores[] = {txtNovaSenha.getText().toString(), cpfGuardado};


                        try {
                            //IP computador / nome_da_pasta / nome_web_service / nome_método

                            String url = "atualizaSenha";

                            String retorno = new ConectaHTTP(url, parametros, valores).execute("").get();


                            if (retorno.contains("true")) {
                                Toast.makeText(telaEsqueciSenha.this, "Sua senha foi alterado com sucesso", Toast.LENGTH_SHORT).show();

                                Intent it = new Intent(telaEsqueciSenha.this, telaLogin.class);
                                startActivity(it);
                                finish();


                            } else {
                                Toast.makeText(telaEsqueciSenha.this, "Codigo incorreto", Toast.LENGTH_SHORT).show();


                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    } else {

                        Toast.makeText(telaEsqueciSenha.this, "Senhas não são iguais", Toast.LENGTH_SHORT).show();

                    }
                }


            }
        });


    }


}