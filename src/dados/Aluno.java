package dados;
import java.util.*;
public class Aluno {
    private String nome;
    private String cpf;
    private String curso;
    private List<Disciplina> disciplinas = new ArrayList<Disciplina>();
    private List<Avaliacao> avaliacoes = new ArrayList<Avaliacao>();

    public Aluno (String nome, String cpf, String curso) {
        this.nome = nome;
        this.cpf = cpf;
        this.curso = curso;
        disciplinas = new ArrayList<Disciplina>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public void adicionarDisciplina(Disciplina disciplina) {
        disciplinas.add(disciplina);
    }

    public void removerDisciplina(Disciplina disciplina) {
        disciplinas.remove(disciplina);
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public Avaliacao getAvaliacaoPorNomeDisciplina(String nome, Disciplina disciplina) {
        for (Avaliacao avaliacao : avaliacoes) {
            if (avaliacao.getNome().equals(nome) && avaliacao.getCodDisciplina().equals(disciplina.getCodigo())) {
                return avaliacao;
            }
        }
        return null;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacao) {
        this.avaliacoes.addAll(avaliacao);
    }

    public void alterarAvaliacoes(List<Avaliacao> novaLista, Disciplina disciplina) {
        List<Avaliacao> listaAtualizada = new ArrayList<>();
        for (Avaliacao avaliacaoExistente : avaliacoes) {
            boolean encontrada = false;
            for (Avaliacao novaAvaliacao : novaLista) {
                if (avaliacaoExistente.getNome().equals(novaAvaliacao.getNome()) && avaliacaoExistente.getCodDisciplina().equals(disciplina.getCodigo())) {
                    avaliacaoExistente.setNota(novaAvaliacao.getNota());
                    listaAtualizada.add(avaliacaoExistente);
                    encontrada = true;
                    break;
                }
            }
            if (!encontrada) {
                listaAtualizada.add(avaliacaoExistente);
            }
        }

        avaliacoes = listaAtualizada;
    }

    public float getNotaAvaliacao(Disciplina disciplina, Avaliacao avaliacao) {
        for(Disciplina disc : disciplinas) {
            if(disc.equals(disciplina)) {
                for (Avaliacao aval : avaliacoes) {
                    if (aval.equals(avaliacao) && aval.getCodDisciplina().equals(disciplina.getCodigo())) {
                        return aval.getNota();
                    }
                }
            }
        }

        return 0;
    }

    public void setNotaAvaliacao(Disciplina disciplina, Avaliacao avaliacao, float nota) {
        for(Disciplina disc : disciplinas) {
            if(disc.equals(disciplina)) {
                for (Avaliacao aval : avaliacoes) {
                    if (aval.getNome().equals(avaliacao.getNome()) && aval.getCodDisciplina().equals(disc.getCodigo())) {
                        aval.setNota(nota);
                    }
                }
            }
        }
    }

    public void removerAvaliacao(Disciplina disciplina, Avaliacao avaliacao) {
        for(Disciplina disc : disciplinas) {
            if(disciplina.getCodigo().equals(disc.getCodigo())) {
                avaliacoes.remove(avaliacao);
            }
        }
    }

    public float calcularMediaFinal(Disciplina disciplina) {
        float soma = 0;
        float peso = 0;

        for(Disciplina disc : disciplinas){
            if(disc.equals(disciplina)) {
                for (Avaliacao avaliacao : getAvaliacoes()) {
                    if (avaliacao.getCodDisciplina().equals(disciplina.getCodigo())) {
                        soma += getNotaAvaliacao(disciplina, avaliacao) * avaliacao.getPeso();
                        peso += avaliacao.getPeso();
                    }
                }
            }
        }

        return (float) (soma / peso);
    }

    public float calcularMediaExame(Disciplina disciplina) {
        float media_final = calcularMediaFinal(disciplina);
        float media_exame = 0;
        if(media_final >= 1.7 && media_final < 7.0)
            media_exame = (float) ((-1.5 * media_final) + 12.5);
        else media_exame = 0;

        return media_exame;
    }

    public String toString() {
        return nome;
    }
}
