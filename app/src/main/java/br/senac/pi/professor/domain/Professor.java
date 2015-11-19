package br.senac.pi.professor.domain;

/**
 * Created by Aluno on 19/11/2015.
 */
public class Professor {
    private int id;
    private String nome;
    private String disciplina;

    public Professor() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }
}

