package dados;

public class Avaliacao {
    private String nome;
    private float peso;
    private float nota;
    private String data;

    private String codDisciplina;

    public Avaliacao(String codDisciplina, String nome, float peso, String data, float nota) {
        this.codDisciplina = codDisciplina;
        this.nome = nome;
        this.peso = peso;
        this.data = data;
        this.nota = nota;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCodDisciplina() {
        return codDisciplina;
    }

    public void setCodDisciplina(String codDisciplina) {
        this.codDisciplina = codDisciplina;
    }
}
