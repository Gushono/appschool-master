package com.example.aluno.tccappschoolll;

public class Usuario
{

    private int rm;
    private String cpf, senha;

    public int getRm() {
        return rm;
    }
    public String getCpf() {
        return cpf;
    }

    public String getSenha() {
        return senha;
    }


    public Usuario()
    {


    }

    public Usuario(int rm, String cpf, String senha)
    {

        this.rm = rm;
        this.cpf = cpf;
        this.senha = senha;
    }






}
