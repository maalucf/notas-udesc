package dados;

import java.util.*;

public class Disciplina {
    private String nome;
    private String codigo;
    private String departamento;
    private List<Avaliacao> avaliacoes = new ArrayList<Avaliacao>();

    public Disciplina(String nome, String codigo, String departamento) {
        this.nome = nome;
        this.codigo = codigo;
        this.departamento = departamento;
        avaliacoes = new ArrayList<Avaliacao>();
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public List<Avaliacao> adicionarAvaliacao(Avaliacao avaliacao) {
        avaliacoes.add(avaliacao);
        return avaliacoes;
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public String toString() {
        return nome + " (" + codigo + ")";
    }

    public void removerAvaliacao(Avaliacao avaliacao) {
        avaliacoes.remove(avaliacao);
    }
}
