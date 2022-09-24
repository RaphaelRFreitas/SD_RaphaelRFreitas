package br.inatel.labs.labrest.client.model;

public class Curso {
    private long id;
    private String descricao;
    private int cargaHoraria;

    public Curso() {
    }

    public Curso(long id, String descricao, int cargaHoraria) {
        this.id = id;
        this.descricao = descricao;
        this.cargaHoraria = cargaHoraria;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", cargaHoraria=" + cargaHoraria +
                '}';
    }
}
