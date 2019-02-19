package com.example.aluno.tccappschoolll;

import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Base64;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class MenuManeiro extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{
    ImageView imCarro;
    CircleImageView imgAluno;
    TextView nomeUser, emailUser;

    Boolean loopmaneiro = true;
    private AlertDialog alerta;



    public static Context contexto;
    public static Context pegaContexto()
    {
        return contexto;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_maneiro);







        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        contexto = getApplicationContext();






       // imCarro = (ImageView) findViewById(R.id.imCarro);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //Cria o gerador do AlertDialog
                AlertDialog.Builder montarAlerta = new AlertDialog.Builder(MenuManeiro.this);
                //define o titulo
                montarAlerta.setTitle("Confirmação");
                //define a mensagem
                montarAlerta.setMessage("Deseja realmente sair?");
                //define um botão como positivo
                montarAlerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {

                        Intent it = new Intent(MenuManeiro.this, telaLogin.class);
                        startActivity(it);
                        finish();
                    }
                });

                //define um botão como negativo.
                montarAlerta.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });
                //cria o AlertDialog
                alerta = montarAlerta.create();
                //Exibe
                alerta.show();


            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);


//            carrosselManeiro();


//            imCarro.setImageResource(R.drawable.gradient);
//            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imCarro, View.ALPHA, 0);
//            objectAnimator.setStartDelay(3000);
//            imCarro.setImageResource(R.drawable.fundoinicial);


    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_maneiro, menu);

        nomeUser = (TextView) findViewById(R.id.tvNomeUser);
        emailUser = (TextView) findViewById(R.id.tvEmailNoUser);
        imgAluno = (CircleImageView) findViewById(R.id.fotoAluninho);

        SharedPreferences sharedPreferencess = MenuManeiro.contexto.getSharedPreferences("guardarDados", Context.MODE_PRIVATE);
        String nome = sharedPreferencess.getString("nomeUser", "");
        String email = sharedPreferencess.getString("emailUser", "");
        String foto = sharedPreferencess.getString("fotoUser", "");

        byte [] stringCodificada = Base64.decode(foto, Base64.DEFAULT);

        Bitmap fotinha = BitmapFactory.decodeByteArray(stringCodificada, 0, stringCodificada.length);

        nomeUser.setText(nome);
        emailUser.setText(email);
        imgAluno.setImageBitmap(fotinha);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
         Fragment fragment = null;


        if (id == R.id.nav_idescolar) {

            fragment = new IdEscolarFragment();

        } else if (id == R.id.nav_aulas) {

            fragment = new AulaFragment();

        } else if (id == R.id.nav_cardapio) {

            fragment = new CardapioFragment();

        }



            if (fragment != null){
               FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.screen_area, fragment);
                ft.commit();
            }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    public void carrosselManeiro() {


        while (loopmaneiro == true) {
            imCarro.setImageResource(R.drawable.logo);
            new Handler().postDelayed(new Runnable() {
                /*
                 * Exibindo splash com um timer.
                 */
                @Override
                public void run() {
                    imCarro.setImageResource(R.drawable.fundoinicial);


                }
            }, 1000);

        }


    }

}







//
//
//    }

