package com.example.aluno.tccappschoolll;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class SplashArt extends Activity {

    ImageView imgLogoSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_art);



        imgLogoSplash = (ImageView) findViewById(R.id.imagemSplash);

        animaLogo();



    }

    public void animaLogo(){

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imgLogoSplash, View.ALPHA, 0);
        //Tempo, em milisegundos, da sua animação. Caso não coloque nenhum, o default é 300.
        objectAnimator.setDuration(1000);
    /*Aqui esta a mágica. Você define o tempo (em milisegundos) para sua animação começar.
    * Ou seja, depois de 2 segundos, sua ImageView ira começar a desaparecer
    */
        objectAnimator.setStartDelay(2000);

        //Caso você queira um Listener para o termino da animação
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Intent it = new Intent(SplashArt.this, telaLogin.class);
                startActivity(it);
                SplashArt.this.finish();
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
