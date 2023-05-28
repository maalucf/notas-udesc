package apresentacao;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import dados.Aluno;
import dados.Disciplina;
import org.slf4j.*;

import javax.swing.table.TableColumn;

public class ResultadoPdf {
    private List<Aluno> alunos;
    private List<Disciplina> disciplinas;

    public ResultadoPdf(List<Aluno> alunos, List<Disciplina> disciplinas) {
        this.alunos = alunos;
        this.disciplinas = disciplinas;
    }

    public void gerarPdf() {
        Document doc = new Document();
        String arquivoPDF = "relatorio.pdf";

        try {
            // Criando um objeto PdfWriter
            PdfWriter.getInstance(doc, new FileOutputStream(arquivoPDF));
            doc.open();

            Paragraph p = new Paragraph("Relatório Geral");
            p.setAlignment(1);
            doc.add(p);

            p = new Paragraph("\n");

            doc.add(p);

            // Criando uma tabela
            PdfPTable table = new PdfPTable(3);

            // Adicionando células à tabela
            PdfPCell cel1 = new PdfPCell(new Paragraph("Nome Aluno"));
            PdfPCell cel2 = new PdfPCell(new Paragraph("Disciplina"));
            PdfPCell cel3 = new PdfPCell(new Paragraph("Média Final"));

            table.addCell(cel1);
            table.addCell(cel2);
            table.addCell(cel3);

            for(Disciplina disc : disciplinas) {
                for(int i = 0; i < alunos.size(); i++) {
                    if (alunos.get(i).getDisciplinas().contains(disc)) {
                        table.addCell(new PdfPCell(new Paragraph(alunos.get(i).getNome())));
                        table.addCell(new PdfPCell(new Paragraph(disc.getNome() + " (" + disc.getCodigo() + ")")));
                        float mediaFinal = alunos.get(i).calcularMediaFinal(disc);
                        float mediaExame = alunos.get(i).calcularMediaExame(disc);
                        String s = "";
                        if (mediaFinal >= 7) {
                            s += String.format("%.2f", mediaFinal) + " - APROVADO";
                        } else if (mediaFinal < 1.7) {
                            s += String.format("%.2f", mediaFinal) + " - REPROVADO";
                        } else if (mediaFinal >= 1.7) {
                            s += String.format("%.2f", mediaFinal) + " - EXAME (nota: " + String.format("%.2f", mediaExame) + ")";
                        }
                        table.addCell(new PdfPCell(new Paragraph(s)));
                    }
                }
            }

            // Adicionando a tabela ao documento
            doc.add(table);

            // Fechando o documento
            doc.close();

            Desktop.getDesktop().open(new File(arquivoPDF));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
