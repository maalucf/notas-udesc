package apresentacao;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import negocio.*;
import dados.*;

public class TelaSecundaria {

	public JFrame frame;
	private Sistema sistema;
	private JTextField textFieldDepartDisc;
	private JTextField textFieldCodigoDisc;
	private JTextField textFieldNomeDisc;
	private JTextField textFieldNomeDiscEditar;
	private JTextField textFieldCodigoDiscEditar;
	private JTextField textFieldDepartDiscEditar;
	private Professor professorLogado;
	private Tabela tabela = new Tabela();
	private JTable tableAvalAlunos;
	private JTable tableResultados;
	private TabelaResultados tabelaResultados = new TabelaResultados();
	private JTextField textFieldNomeAv;
	private JTextField textFieldDataAv;
	private JTextField textFieldPesoAv;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaSecundaria window = new TelaSecundaria();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public TelaSecundaria(Professor professorLogado, Sistema sistema) {
		this.professorLogado = professorLogado;
		this.sistema = sistema;
		initialize();
	}

	/**
	 * Create the application.
	 */
	public TelaSecundaria() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 689, 445);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setForeground(new Color(19, 80, 130));
		tabbedPane.setFont(new Font("Roboto Cn", Font.BOLD, 14));
		tabbedPane.setBackground(new Color(255, 255, 255));
		tabbedPane.setBounds(0, 24, 675, 384);
		frame.getContentPane().add(tabbedPane);

		JPanel tabDisciplinas = new JPanel();
		tabbedPane.addTab("Disciplina - Avaliação", null, tabDisciplinas, null);
		tabDisciplinas.setLayout(null);

		JPanel tabAlunoDisc = new JPanel();
		tabbedPane.addTab("Disciplinas Aluno", null, tabAlunoDisc, null);
		tabAlunoDisc.setLayout(null);

		JPanel backgroundExclusaoDiscAluno = new JPanel();
		backgroundExclusaoDiscAluno.setBackground(new Color(174, 211, 242));
		backgroundExclusaoDiscAluno.setBounds(333, -2, 337, 356);
		tabAlunoDisc.add(backgroundExclusaoDiscAluno);
		backgroundExclusaoDiscAluno.setLayout(null);

		JLabel labelExcDiscAluno = new JLabel("EXCLUSÃO DISCIPLINA");
		labelExcDiscAluno.setHorizontalAlignment(SwingConstants.CENTER);
		labelExcDiscAluno.setBounds(0, 38, 337, 26);
		backgroundExclusaoDiscAluno.add(labelExcDiscAluno);
		labelExcDiscAluno.setForeground(new Color(19, 80, 130));
		labelExcDiscAluno.setFont(new Font("Roboto Cn", Font.BOLD, 24));

		JButton btnExcluirDisciplinaAlunos = new JButton("Excluir Disciplina");
		btnExcluirDisciplinaAlunos.setBounds(89, 268, 166, 21);
		backgroundExclusaoDiscAluno.add(btnExcluirDisciplinaAlunos);
		btnExcluirDisciplinaAlunos.setForeground(new Color(19, 80, 130));
		btnExcluirDisciplinaAlunos.setFont(new Font("Roboto Cn", Font.BOLD, 14));

		JLabel labelDisciplinaAlunoExc = new JLabel("Disciplina:");
		labelDisciplinaAlunoExc.setHorizontalAlignment(SwingConstants.RIGHT);
		labelDisciplinaAlunoExc.setBounds(56, 115, 69, 13);
		backgroundExclusaoDiscAluno.add(labelDisciplinaAlunoExc);
		labelDisciplinaAlunoExc.setForeground(new Color(19, 80, 130));
		labelDisciplinaAlunoExc.setFont(new Font("Roboto Cn", Font.PLAIN, 14));

		JComboBox comboBoxDisciplinasAlunoExc = new JComboBox();
		comboBoxDisciplinasAlunoExc.setBackground(new Color(255, 255, 255));
		comboBoxDisciplinasAlunoExc.setBounds(130, 111, 125, 21);
		backgroundExclusaoDiscAluno.add(comboBoxDisciplinasAlunoExc);
		comboBoxDisciplinasAlunoExc.setForeground(new Color(19, 80, 130));
		comboBoxDisciplinasAlunoExc.setFont(new Font("Roboto Cn", Font.PLAIN, 14));

		JLabel lblAlunosCadastrados = new JLabel("Alunos:");
		lblAlunosCadastrados.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAlunosCadastrados.setBounds(75, 156, 45, 13);
		backgroundExclusaoDiscAluno.add(lblAlunosCadastrados);
		lblAlunosCadastrados.setForeground(new Color(19, 80, 130));
		lblAlunosCadastrados.setFont(new Font("Roboto Cn", Font.PLAIN, 14));

		JLabel lblAluno_1 = new JLabel("ALUNO");
		lblAluno_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblAluno_1.setForeground(new Color(19, 80, 130));
		lblAluno_1.setFont(new Font("Roboto Cn", Font.BOLD, 24));
		lblAluno_1.setBounds(0, 61, 337, 26);
		backgroundExclusaoDiscAluno.add(lblAluno_1);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBounds(130, 151, 125, 90);
		backgroundExclusaoDiscAluno.add(panel_2);

		DefaultListModel<Aluno> alunoListModel = new DefaultListModel<>();
		DefaultListModel<Aluno> alunoListModelCad = new DefaultListModel<>();

		JList listAlunosDiscCad = new JList<>(alunoListModelCad);
		listAlunosDiscCad.setForeground(new Color(19, 80, 130));
		listAlunosDiscCad.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		listAlunosDiscCad.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		JScrollPane scrollPaneAlunosDiscCad = new JScrollPane(listAlunosDiscCad);
		scrollPaneAlunosDiscCad.setBounds(0, 0, 125, 90);
		scrollPaneAlunosDiscCad.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		panel_2.add(scrollPaneAlunosDiscCad);
		scrollPaneAlunosDiscCad.setViewportView(listAlunosDiscCad);

		JPanel backgroundCadastroDiscAluno = new JPanel();
		backgroundCadastroDiscAluno.setBackground(new Color(214, 233, 248));
		backgroundCadastroDiscAluno.setBounds(-1, -1, 337, 355);
		tabAlunoDisc.add(backgroundCadastroDiscAluno);
		backgroundCadastroDiscAluno.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(115, 151, 125, 90);
		backgroundCadastroDiscAluno.add(panel);
		panel.setLayout(null);

		JList listAlunosDiscNC = new JList<>(alunoListModel);
		listAlunosDiscNC.setForeground(new Color(19, 80, 130));
		listAlunosDiscNC.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		listAlunosDiscNC.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		JScrollPane scrollPaneAlunosDiscNC = new JScrollPane(listAlunosDiscNC);
		scrollPaneAlunosDiscNC.setBounds(0, 0, 125, 90);
		scrollPaneAlunosDiscNC.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		panel.add(scrollPaneAlunosDiscNC);
		scrollPaneAlunosDiscNC.setViewportView(listAlunosDiscNC);

		JComboBox comboBoxDisciplinasAluno = new JComboBox();
		comboBoxDisciplinasAluno.setBackground(new Color(255, 255, 255));
		comboBoxDisciplinasAluno.setBounds(115, 111, 125, 21);
		backgroundCadastroDiscAluno.add(comboBoxDisciplinasAluno);
		comboBoxDisciplinasAluno.setForeground(new Color(19, 80, 130));
		comboBoxDisciplinasAluno.setFont(new Font("Roboto Cn", Font.PLAIN, 14));

		JLabel labelDisciplinaAluno = new JLabel("Disciplina:");
		labelDisciplinaAluno.setHorizontalAlignment(SwingConstants.RIGHT);
		labelDisciplinaAluno.setBounds(29, 115, 84, 13);
		backgroundCadastroDiscAluno.add(labelDisciplinaAluno);
		labelDisciplinaAluno.setForeground(new Color(19, 80, 130));
		labelDisciplinaAluno.setFont(new Font("Roboto Cn", Font.PLAIN, 14));

		JLabel lblAlunosNoCadastrados = new JLabel("Alunos: ");
		lblAlunosNoCadastrados.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAlunosNoCadastrados.setBounds(54, 156, 59, 13);
		backgroundCadastroDiscAluno.add(lblAlunosNoCadastrados);
		lblAlunosNoCadastrados.setForeground(new Color(19, 80, 130));
		lblAlunosNoCadastrados.setFont(new Font("Roboto Cn", Font.PLAIN, 14));

		JButton btnCadastrarDisciplinaAluno = new JButton("Cadastrar Disciplina");
		btnCadastrarDisciplinaAluno.setBounds(86, 268, 170, 21);
		backgroundCadastroDiscAluno.add(btnCadastrarDisciplinaAluno);
		btnCadastrarDisciplinaAluno.setForeground(new Color(19, 80, 130));
		btnCadastrarDisciplinaAluno.setFont(new Font("Roboto Cn", Font.BOLD, 14));

		JLabel lblAluno = new JLabel("ALUNO");
		lblAluno.setHorizontalAlignment(SwingConstants.CENTER);
		lblAluno.setForeground(new Color(19, 80, 130));
		lblAluno.setFont(new Font("Roboto Cn", Font.BOLD, 24));
		lblAluno.setBounds(0, 64, 335, 26);
		backgroundCadastroDiscAluno.add(lblAluno);

		JLabel labelCadastroDiscAluno = new JLabel("CADASTRO DISCIPLINA");
		labelCadastroDiscAluno.setBounds(5, 39, 330, 26);
		backgroundCadastroDiscAluno.add(labelCadastroDiscAluno);
		labelCadastroDiscAluno.setHorizontalAlignment(SwingConstants.CENTER);
		labelCadastroDiscAluno.setForeground(new Color(19, 80, 130));
		labelCadastroDiscAluno.setFont(new Font("Roboto Cn", Font.BOLD, 24));

		JPanel tabAlunoAval = new JPanel();
		tabbedPane.addTab("Avaliações Aluno", null, tabAlunoAval, null);
		tabAlunoAval.setLayout(null);

		JPanel backgroundCadastroAvalAlunos = new JPanel();
		backgroundCadastroAvalAlunos.setLayout(null);
		backgroundCadastroAvalAlunos.setBackground(new Color(214, 233, 248));
		backgroundCadastroAvalAlunos.setBounds(0, 0, 670, 355);
		tabAlunoAval.add(backgroundCadastroAvalAlunos);

		JLabel labelCadastro_1_1 = new JLabel("CADASTRO AVALIAÇÃO ALUNOS");
		labelCadastro_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		labelCadastro_1_1.setForeground(new Color(19, 80, 130));
		labelCadastro_1_1.setFont(new Font("Roboto Cn", Font.BOLD, 24));
		labelCadastro_1_1.setBounds(0, 23, 670, 26);
		backgroundCadastroAvalAlunos.add(labelCadastro_1_1);

		JButton btnCadastrarAvAlunos = new JButton("Cadastrar Avaliação");
		btnCadastrarAvAlunos.setForeground(new Color(19, 80, 130));
		btnCadastrarAvAlunos.setFont(new Font("Roboto Cn", Font.BOLD, 14));
		btnCadastrarAvAlunos.setBounds(251, 307, 171, 21);
		backgroundCadastroAvalAlunos.add(btnCadastrarAvAlunos);

		JComboBox comboBoxDisciplinasAvAluno = new JComboBox();
		comboBoxDisciplinasAvAluno.setBackground(new Color(255, 255, 255));
		comboBoxDisciplinasAvAluno.setForeground(new Color(19, 80, 130));
		comboBoxDisciplinasAvAluno.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		comboBoxDisciplinasAvAluno.setBounds(277, 69, 129, 21);
		backgroundCadastroAvalAlunos.add(comboBoxDisciplinasAvAluno);

		JLabel labelDisciplinaAv_1 = new JLabel("Disciplina: ");
		labelDisciplinaAv_1.setHorizontalAlignment(SwingConstants.RIGHT);
		labelDisciplinaAv_1.setForeground(new Color(19, 80, 130));
		labelDisciplinaAv_1.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		labelDisciplinaAv_1.setBounds(173, 72, 104, 13);
		backgroundCadastroAvalAlunos.add(labelDisciplinaAv_1);

		JPanel tabResultados = new JPanel();
		tabbedPane.addTab("Resultado", null, tabResultados, null);
		tabResultados.setLayout(null);

		JPanel backgroundResultado = new JPanel();
		backgroundResultado.setLayout(null);
		backgroundResultado.setBackground(new Color(214, 233, 248));
		backgroundResultado.setBounds(0, 0, 670, 355);
		tabResultados.add(backgroundResultado);

		JLabel labelCadastro_1_1_1 = new JLabel("RESULTADOS FINAIS");
		labelCadastro_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		labelCadastro_1_1_1.setForeground(new Color(19, 80, 130));
		labelCadastro_1_1_1.setFont(new Font("Roboto Cn", Font.BOLD, 24));
		labelCadastro_1_1_1.setBounds(0, 23, 670, 26);
		backgroundResultado.add(labelCadastro_1_1_1);

		JButton btnGerarPdf = new JButton("Gerar PDF");
		btnGerarPdf.setForeground(new Color(19, 80, 130));
		btnGerarPdf.setFont(new Font("Roboto Cn", Font.BOLD, 14));
		btnGerarPdf.setBounds(274, 314, 117, 21);
		backgroundResultado.add(btnGerarPdf);

		JPanel panelTableResultados = new JPanel();
		panelTableResultados.setBounds(43, 59, 585, 238);
		backgroundResultado.add(panelTableResultados);
		panelTableResultados.setLayout(null);

		tableResultados = new JTable();
		tableResultados.setBackground(new Color(255, 255, 255));
		tableResultados.setForeground(new Color(19, 80, 130));
		tableResultados.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		tableResultados.setModel(tabelaResultados);
		tableResultados.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		JScrollPane scrollPaneResultados = new JScrollPane(tableResultados);
		scrollPaneResultados.setBounds(0, 0, 585, 238);
		panelTableResultados.add(scrollPaneResultados);

		tableResultados.setPreferredScrollableViewportSize(new Dimension(585, 238));

		JLabel lblBemVindo = new JLabel("Bem-vindo(a), " + professorLogado.getNome() + "!");
		lblBemVindo.setForeground(new Color(19, 80, 130));
		lblBemVindo.setFont(new Font("Roboto Cn", Font.BOLD, 15));
		lblBemVindo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBemVindo.setBounds(349, 10, 240, 13);
		frame.getContentPane().add(lblBemVindo);

		JPanel backgroundCadastroDisc = new JPanel();
		backgroundCadastroDisc.setBackground(new Color(214, 233, 248));
		backgroundCadastroDisc.setBounds(1, 1, 222, 355);
		tabDisciplinas.add(backgroundCadastroDisc);
		backgroundCadastroDisc.setLayout(null);

		JLabel labelCadastro = new JLabel("CADASTRO DISCIPLINA");
		labelCadastro.setHorizontalAlignment(SwingConstants.CENTER);
		labelCadastro.setBounds(4, 34, 220, 26);
		backgroundCadastroDisc.add(labelCadastro);
		labelCadastro.setForeground(new Color(19, 80, 130));
		labelCadastro.setFont(new Font("Roboto Cn", Font.BOLD, 20));

		JLabel labelNomeAluno = new JLabel("Nome: ");
		labelNomeAluno.setBounds(58, 95, 45, 13);
		backgroundCadastroDisc.add(labelNomeAluno);
		labelNomeAluno.setHorizontalAlignment(SwingConstants.RIGHT);
		labelNomeAluno.setForeground(new Color(19, 80, 130));
		labelNomeAluno.setFont(new Font("Roboto Cn", Font.PLAIN, 14));

		JLabel lblDepartamento = new JLabel("Departamento:");
		lblDepartamento.setBounds(5, 188, 98, 13);
		backgroundCadastroDisc.add(lblDepartamento);
		lblDepartamento.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDepartamento.setForeground(new Color(19, 80, 130));
		lblDepartamento.setFont(new Font("Roboto Cn", Font.PLAIN, 14));

		JLabel lblCodigo = new JLabel("Código: ");
		lblCodigo.setBounds(12, 141, 91, 13);
		backgroundCadastroDisc.add(lblCodigo);
		lblCodigo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCodigo.setForeground(new Color(19, 80, 130));
		lblCodigo.setFont(new Font("Roboto Cn", Font.PLAIN, 14));

		textFieldNomeDisc = new JTextField();
		textFieldNomeDisc.setBounds(106, 93, 96, 19);
		backgroundCadastroDisc.add(textFieldNomeDisc);
		textFieldNomeDisc.setForeground(new Color(19, 80, 130));
		textFieldNomeDisc.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		textFieldNomeDisc.setColumns(10);
		textFieldNomeDisc.setText("");

		textFieldCodigoDisc = new JTextField();
		textFieldCodigoDisc.setBounds(106, 139, 96, 19);
		backgroundCadastroDisc.add(textFieldCodigoDisc);
		textFieldCodigoDisc.setForeground(new Color(19, 80, 130));
		textFieldCodigoDisc.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		textFieldCodigoDisc.setColumns(10);
		textFieldCodigoDisc.setText("");

		textFieldDepartDisc = new JTextField();
		textFieldDepartDisc.setBounds(106, 186, 96, 19);
		backgroundCadastroDisc.add(textFieldDepartDisc);
		textFieldDepartDisc.setForeground(new Color(19, 80, 130));
		textFieldDepartDisc.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		textFieldDepartDisc.setColumns(10);
		textFieldDepartDisc.setText("");

		JButton btnCadastrarDisciplina = new JButton("Cadastrar Disciplina");
		btnCadastrarDisciplina.setBounds(27, 231, 171, 21);
		backgroundCadastroDisc.add(btnCadastrarDisciplina);
		btnCadastrarDisciplina.setForeground(new Color(19, 80, 130));
		btnCadastrarDisciplina.setFont(new Font("Roboto Cn", Font.BOLD, 14));

		JPanel backgroundEditarDisc = new JPanel();
		backgroundEditarDisc.setBackground(new Color(174, 211, 242));
		backgroundEditarDisc.setBounds(223, 0, 222, 356);
		tabDisciplinas.add(backgroundEditarDisc);
		backgroundEditarDisc.setLayout(null);

		JLabel labelEditarExcluir = new JLabel("EDITAR/EXCLUIR DISC");
		labelEditarExcluir.setHorizontalAlignment(SwingConstants.CENTER);
		labelEditarExcluir.setBounds(1, 34, 224, 26);
		backgroundEditarDisc.add(labelEditarExcluir);
		labelEditarExcluir.setForeground(new Color(19, 80, 130));
		labelEditarExcluir.setFont(new Font("Roboto Cn", Font.BOLD, 20));

		JLabel labelNomeAluno_1_1 = new JLabel("Disciplina:");
		labelNomeAluno_1_1.setBounds(25, 88, 76, 13);
		backgroundEditarDisc.add(labelNomeAluno_1_1);
		labelNomeAluno_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		labelNomeAluno_1_1.setForeground(new Color(19, 80, 130));
		labelNomeAluno_1_1.setFont(new Font("Roboto Cn", Font.PLAIN, 14));

		JLabel labelNomeAluno_1 = new JLabel("Nome: ");
		labelNomeAluno_1.setBounds(60, 127, 45, 13);
		backgroundEditarDisc.add(labelNomeAluno_1);
		labelNomeAluno_1.setHorizontalAlignment(SwingConstants.RIGHT);
		labelNomeAluno_1.setForeground(new Color(19, 80, 130));
		labelNomeAluno_1.setFont(new Font("Roboto Cn", Font.PLAIN, 14));

		JLabel lblCodigo_1 = new JLabel("Código: ");
		lblCodigo_1.setBounds(29, 167, 76, 13);
		backgroundEditarDisc.add(lblCodigo_1);
		lblCodigo_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCodigo_1.setForeground(new Color(19, 80, 130));
		lblCodigo_1.setFont(new Font("Roboto Cn", Font.PLAIN, 14));

		textFieldDepartDiscEditar = new JTextField();
		textFieldDepartDiscEditar.setBounds(105, 204, 96, 19);
		backgroundEditarDisc.add(textFieldDepartDiscEditar);
		textFieldDepartDiscEditar.setForeground(new Color(19, 80, 130));
		textFieldDepartDiscEditar.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		textFieldDepartDiscEditar.setColumns(10);
		textFieldDepartDiscEditar.setText("");

		JLabel lblDepartamento_1 = new JLabel("Departamento:");
		lblDepartamento_1.setBounds(-3, 206, 108, 13);
		backgroundEditarDisc.add(lblDepartamento_1);
		lblDepartamento_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDepartamento_1.setForeground(new Color(19, 80, 130));
		lblDepartamento_1.setFont(new Font("Roboto Cn", Font.PLAIN, 14));

		JButton btnEditarDisciplina = new JButton("Editar Disciplina");
		btnEditarDisciplina.setBounds(36, 252, 164, 21);
		backgroundEditarDisc.add(btnEditarDisciplina);
		btnEditarDisciplina.setForeground(new Color(19, 80, 130));
		btnEditarDisciplina.setFont(new Font("Roboto Cn", Font.BOLD, 14));

		JButton btnExcluirDisciplina = new JButton("Excluir Disciplina");
		btnExcluirDisciplina.setBounds(36, 283, 164, 21);
		backgroundEditarDisc.add(btnExcluirDisciplina);
		btnExcluirDisciplina.setForeground(new Color(19, 80, 130));
		btnExcluirDisciplina.setFont(new Font("Roboto Cn", Font.BOLD, 14));

		textFieldCodigoDiscEditar = new JTextField();
		textFieldCodigoDiscEditar.setBounds(105, 165, 96, 19);
		backgroundEditarDisc.add(textFieldCodigoDiscEditar);
		textFieldCodigoDiscEditar.setForeground(new Color(19, 80, 130));
		textFieldCodigoDiscEditar.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		textFieldCodigoDiscEditar.setColumns(10);
		textFieldCodigoDiscEditar.setText("");

		textFieldNomeDiscEditar = new JTextField();
		textFieldNomeDiscEditar.setBounds(105, 125, 96, 19);
		backgroundEditarDisc.add(textFieldNomeDiscEditar);
		textFieldNomeDiscEditar.setForeground(new Color(19, 80, 130));
		textFieldNomeDiscEditar.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		textFieldNomeDiscEditar.setColumns(10);
		textFieldNomeDiscEditar.setText("");

		JComboBox comboBoxDisciplinas = new JComboBox();
		comboBoxDisciplinas.setBackground(new Color(255, 255, 255));
		comboBoxDisciplinas.setBounds(105, 83, 96, 21);
		backgroundEditarDisc.add(comboBoxDisciplinas);
		comboBoxDisciplinas.setForeground(new Color(19, 80, 130));
		comboBoxDisciplinas.setFont(new Font("Roboto Cn", Font.PLAIN, 14));

		JButton btnSair = new JButton("Sair");
		btnSair.setForeground(new Color(19, 80, 130));
		btnSair.setFont(new Font("Roboto Cn", Font.BOLD, 14));
		btnSair.setBounds(594, 6, 71, 21);
		frame.getContentPane().add(btnSair);

		JPanel panelTableAval = new JPanel();
		panelTableAval.setBounds(128, 121, 407, 162);
		backgroundCadastroAvalAlunos.add(panelTableAval);
		panelTableAval.setLayout(null);

		tableAvalAlunos = new JTable();
		tableAvalAlunos.setBackground(new Color(255, 255, 255));
		tableAvalAlunos.setForeground(new Color(19, 80, 130));
		tableAvalAlunos.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		tableAvalAlunos.setModel(tabela);
		tableAvalAlunos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		JScrollPane scrollPaneAval = new JScrollPane(tableAvalAlunos);
		scrollPaneAval.setBounds(0, 0, 407, 162);
		panelTableAval.add(scrollPaneAval);

		tableAvalAlunos.setPreferredScrollableViewportSize(new Dimension(407, 300));

		JPanel backgroundCadastroAval = new JPanel();
		backgroundCadastroAval.setLayout(null);
		backgroundCadastroAval.setBackground(new Color(214, 233, 248));
		backgroundCadastroAval.setBounds(445, 1, 221, 355);
		tabDisciplinas.add(backgroundCadastroAval);

		JLabel labelCadastro_1 = new JLabel("CADASTRO AVALIAÇÃO");
		labelCadastro_1.setHorizontalAlignment(SwingConstants.CENTER);
		labelCadastro_1.setForeground(new Color(19, 80, 130));
		labelCadastro_1.setFont(new Font("Roboto Cn", Font.BOLD, 20));
		labelCadastro_1.setBounds(0, 34, 225, 26);
		backgroundCadastroAval.add(labelCadastro_1);

		JLabel labelNomeAv = new JLabel("Nome: ");
		labelNomeAv.setHorizontalAlignment(SwingConstants.RIGHT);
		labelNomeAv.setForeground(new Color(19, 80, 130));
		labelNomeAv.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		labelNomeAv.setBounds(46, 127, 45, 13);
		backgroundCadastroAval.add(labelNomeAv);

		textFieldNomeAv = new JTextField();
		textFieldNomeAv.setForeground(new Color(19, 80, 130));
		textFieldNomeAv.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		textFieldNomeAv.setColumns(10);
		textFieldNomeAv.setBounds(91, 125, 103, 19);
		backgroundCadastroAval.add(textFieldNomeAv);

		JLabel lblDataAv = new JLabel("Data: ");
		lblDataAv.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDataAv.setForeground(new Color(19, 80, 130));
		lblDataAv.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		lblDataAv.setBounds(46, 167, 45, 13);
		backgroundCadastroAval.add(lblDataAv);

		textFieldDataAv = new JTextField();
		textFieldDataAv.setForeground(new Color(19, 80, 130));
		textFieldDataAv.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		textFieldDataAv.setColumns(10);
		textFieldDataAv.setBounds(91, 165, 103, 19);
		backgroundCadastroAval.add(textFieldDataAv);

		JLabel lblPesoAv = new JLabel("Peso: ");
		lblPesoAv.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPesoAv.setForeground(new Color(19, 80, 130));
		lblPesoAv.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		lblPesoAv.setBounds(46, 208, 45, 13);
		backgroundCadastroAval.add(lblPesoAv);

		textFieldPesoAv = new JTextField();
		textFieldPesoAv.setForeground(new Color(19, 80, 130));
		textFieldPesoAv.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		textFieldPesoAv.setColumns(10);
		textFieldPesoAv.setBounds(91, 204, 103, 19);
		backgroundCadastroAval.add(textFieldPesoAv);

		JButton btnCadastrarAv = new JButton("Cadastrar Avaliação");
		btnCadastrarAv.setForeground(new Color(19, 80, 130));
		btnCadastrarAv.setFont(new Font("Roboto Cn", Font.BOLD, 14));
		btnCadastrarAv.setBounds(27, 252, 171, 21);
		backgroundCadastroAval.add(btnCadastrarAv);

		JComboBox comboBoxDisciplinasAv = new JComboBox();
		comboBoxDisciplinasAv.setBackground(new Color(255, 255, 255));
		comboBoxDisciplinasAv.setForeground(new Color(19, 80, 130));
		comboBoxDisciplinasAv.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		comboBoxDisciplinasAv.setBounds(91, 83, 103, 21);
		backgroundCadastroAval.add(comboBoxDisciplinasAv);

		JLabel labelDisciplinaAv = new JLabel("Disciplina: ");
		labelDisciplinaAv.setHorizontalAlignment(SwingConstants.RIGHT);
		labelDisciplinaAv.setForeground(new Color(19, 80, 130));
		labelDisciplinaAv.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		labelDisciplinaAv.setBounds(15, 88, 76, 13);
		backgroundCadastroAval.add(labelDisciplinaAv);

		JButton btnOkDiscCad = new JButton("Ok");
		btnOkDiscCad.setForeground(new Color(19, 80, 130));
		btnOkDiscCad.setFont(new Font("Roboto Cn", Font.BOLD, 14));
		btnOkDiscCad.setBounds(247, 111, 55, 21);
		backgroundCadastroDiscAluno.add(btnOkDiscCad);

		JButton btnOkDiscExc = new JButton("Ok");
		btnOkDiscExc.setForeground(new Color(19, 80, 130));
		btnOkDiscExc.setFont(new Font("Roboto Cn", Font.BOLD, 14));
		btnOkDiscExc.setBounds(265, 111, 55, 20);
		backgroundExclusaoDiscAluno.add(btnOkDiscExc);

		JButton btnOkTabelaCad = new JButton("Ok");
		btnOkTabelaCad.setForeground(new Color(19, 80, 130));
		btnOkTabelaCad.setFont(new Font("Roboto Cn", Font.BOLD, 14));
		btnOkTabelaCad.setBounds(416, 69, 55, 21);
		backgroundCadastroAvalAlunos.add(btnOkTabelaCad);

		Disciplina disc = new Disciplina("POO", "111", "BCC");
		if(!sistema.existeCodigoDisciplina(disc.getCodigo())) {
			sistema.cadastrarDisciplinaProfessor(disc, professorLogado);
		}
		for(int i = 0; i < 10; i++) {
			Avaliacao aval = new Avaliacao(disc.getCodigo(), "P"+i, 10, "1", 0);
			List<Avaliacao> avaliacoes = new ArrayList<>();
			avaliacoes.add(aval);
			if(!sistema.existeAvaliacao(disc, aval.getNome()))
				sistema.cadastrarAvaliacao(disc, aval, professorLogado, avaliacoes);
		}

		//Atualizar comboBox disciplinas do professor
		List<Disciplina> disciplinasCadastradas = sistema.getListaDisciplinasProfessor();

		DefaultComboBoxModel<Disciplina> model = new DefaultComboBoxModel<>();
		DefaultComboBoxModel<Disciplina> model1 = new DefaultComboBoxModel<>();
		DefaultComboBoxModel<Disciplina> model2 = new DefaultComboBoxModel<>();
		DefaultComboBoxModel<Disciplina> model3 = new DefaultComboBoxModel<>();
		DefaultComboBoxModel<Disciplina> model4 = new DefaultComboBoxModel<>();

		comboBoxDisciplinas.setModel(model);
		comboBoxDisciplinasAv.setModel(model1);
		comboBoxDisciplinasAvAluno.setModel(model2);
		comboBoxDisciplinasAluno.setModel(model3);
		comboBoxDisciplinasAlunoExc.setModel(model4);

		Collections.sort(disciplinasCadastradas, new Comparator<Disciplina>() {
			public int compare(Disciplina disciplina1, Disciplina disciplina2) {
				return disciplina1.getNome().compareToIgnoreCase(disciplina2.getNome());
			}
		});

		model.removeAllElements();
		model1.removeAllElements();
		model2.removeAllElements();
		model3.removeAllElements();
		model4.removeAllElements();

		for (Disciplina d : disciplinasCadastradas) {
			model.addElement(d);
			model1.addElement(d);
			model2.addElement(d);
			model3.addElement(d);
			model4.addElement(d);
		}
		comboBoxDisciplinas.setSelectedIndex(-1);
		comboBoxDisciplinasAv.setSelectedIndex(-1);
		comboBoxDisciplinasAvAluno.setSelectedIndex(-1);
		comboBoxDisciplinasAluno.setSelectedIndex(-1);
		comboBoxDisciplinasAlunoExc.setSelectedIndex(-1);
		//

		//ABA DISCIPLINA
		btnCadastrarDisciplina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nome = textFieldNomeDisc.getText();
				String codigo = textFieldCodigoDisc.getText();
				String departamento = textFieldDepartDisc.getText();

				Disciplina disciplina = new Disciplina(nome, codigo, departamento);

				if (sistema.existeCodigoDisciplina(codigo)) {
					JOptionPane.showMessageDialog(frame, "Código já cadastrado!");
				} else {
					sistema.cadastrarDisciplinaProfessor(disciplina, professorLogado);
					JOptionPane.showMessageDialog(frame, "Disciplina cadastrada com sucesso!");

					List<Disciplina> disciplinasCadastradas = sistema.getListaDisciplinasProfessor();

					Collections.sort(disciplinasCadastradas, new Comparator<Disciplina>() {
						public int compare(Disciplina disciplina1, Disciplina disciplina2) {
							return disciplina1.getNome().compareToIgnoreCase(disciplina2.getNome());
						}
					});

					model.removeAllElements();
					model1.removeAllElements();
					model2.removeAllElements();
					model3.removeAllElements();
					model4.removeAllElements();

					for (Disciplina d : disciplinasCadastradas) {
						model.addElement(d);
						model1.addElement(d);
						model2.addElement(d);
						model3.addElement(d);
						model4.addElement(d);
					}
				}

				textFieldDepartDiscEditar.setText("");
				textFieldCodigoDiscEditar.setText("");
				textFieldNomeDiscEditar.setText("");
				textFieldDepartDisc.setText("");
				textFieldCodigoDisc.setText("");
				textFieldNomeDisc.setText("");
				comboBoxDisciplinas.setSelectedIndex(-1);
				comboBoxDisciplinasAv.setSelectedIndex(-1);
				comboBoxDisciplinasAluno.setSelectedIndex(-1);
				comboBoxDisciplinasAvAluno.setSelectedIndex(-1);
				comboBoxDisciplinasAlunoExc.setSelectedIndex(-1);
			}
		});

		comboBoxDisciplinas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Disciplina disciplinaSelecionado = (Disciplina) comboBoxDisciplinas.getSelectedItem();

				if (disciplinaSelecionado != null) {
					textFieldNomeDiscEditar.setText(disciplinaSelecionado.getNome());
					textFieldCodigoDiscEditar.setText(String.valueOf(disciplinaSelecionado.getCodigo()));
					textFieldDepartDiscEditar.setText(disciplinaSelecionado.getDepartamento());
				}
			}
		});

		btnExcluirDisciplina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Disciplina disciplinaSelecionado = (Disciplina) comboBoxDisciplinas.getSelectedItem();

				if (disciplinaSelecionado != null) {
					sistema.removerDisciplinaProfessor(disciplinaSelecionado, professorLogado);

					List<Disciplina> disciplinasCadastradas = sistema.getListaDisciplinasProfessor();

					Collections.sort(disciplinasCadastradas, new Comparator<Disciplina>() {
						public int compare(Disciplina disciplina1, Disciplina disciplina2) {
							return disciplina1.getNome().compareToIgnoreCase(disciplina2.getNome());
						}
					});

					model.removeAllElements();
					model1.removeAllElements();
					model2.removeAllElements();
					model3.removeAllElements();
					model4.removeAllElements();

					for (Disciplina d : disciplinasCadastradas) {
						model.addElement(d);
						model1.addElement(d);
						model2.addElement(d);
						model3.addElement(d);
						model4.addElement(d);
					}

					textFieldDepartDiscEditar.setText("");
					textFieldCodigoDiscEditar.setText("");
					textFieldNomeDiscEditar.setText("");
					textFieldDepartDisc.setText("");
					textFieldCodigoDisc.setText("");
					textFieldNomeDisc.setText("");
					comboBoxDisciplinas.setSelectedIndex(-1);
					comboBoxDisciplinasAv.setSelectedIndex(-1);
					comboBoxDisciplinasAluno.setSelectedIndex(-1);
					comboBoxDisciplinasAvAluno.setSelectedIndex(-1);
					comboBoxDisciplinasAlunoExc.setSelectedIndex(-1);
				}
			}
		});

		btnEditarDisciplina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Disciplina disciplinaSelecionado = (Disciplina) comboBoxDisciplinas.getSelectedItem();

				if (disciplinaSelecionado != null) {
					String nome = textFieldNomeDiscEditar.getText();
					String codigo = textFieldCodigoDiscEditar.getText();
					String departamento = textFieldDepartDiscEditar.getText();

					Disciplina disciplina = new Disciplina(nome, codigo, departamento);

					if(sistema.existeCodigoDisciplina(codigo) && !codigo.equals(disciplinaSelecionado.getCodigo())) {
						JOptionPane.showMessageDialog(frame, "Código já cadastrado!");
					} else {
						sistema.editarDisciplina(disciplinaSelecionado, nome, codigo, departamento, professorLogado);

						if(sistema.existeCodigoDisciplina(codigo)) {
							JOptionPane.showMessageDialog(frame, "Disciplina editada com sucesso!");

							List<Disciplina> disciplinasCadastradas = sistema.getListaDisciplinasProfessor();

							Collections.sort(disciplinasCadastradas, new Comparator<Disciplina>() {
								public int compare(Disciplina disciplina1, Disciplina disciplina2) {
									return disciplina1.getNome().compareToIgnoreCase(disciplina2.getNome());
								}
							});

							model.removeAllElements();
							model1.removeAllElements();
							model2.removeAllElements();
							model3.removeAllElements();
							model4.removeAllElements();

							for (Disciplina d : disciplinasCadastradas) {
								model.addElement(d);
								model1.addElement(d);
								model2.addElement(d);
								model3.addElement(d);
								model4.addElement(d);
							}
						} else {
							JOptionPane.showMessageDialog(frame, "Erro ao editar disciplina!");
						}
					}
				}
				textFieldDepartDiscEditar.setText("");
				textFieldCodigoDiscEditar.setText("");
				textFieldNomeDiscEditar.setText("");
				textFieldDepartDisc.setText("");
				textFieldCodigoDisc.setText("");
				textFieldNomeDisc.setText("");
				comboBoxDisciplinas.setSelectedIndex(-1);
				comboBoxDisciplinasAv.setSelectedIndex(-1);
				comboBoxDisciplinasAluno.setSelectedIndex(-1);
				comboBoxDisciplinasAvAluno.setSelectedIndex(-1);
				comboBoxDisciplinasAlunoExc.setSelectedIndex(-1);
			}
		});

		btnCadastrarAv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nome = textFieldNomeAv.getText();
				String data = textFieldDataAv.getText();
				Float peso = Float.parseFloat(textFieldPesoAv.getText());
				Disciplina disciplinaSelecionado = (Disciplina) comboBoxDisciplinasAv.getSelectedItem();
				String codigo = disciplinaSelecionado.getCodigo();

				Avaliacao avaliacao = new Avaliacao(codigo, nome, peso, data, 0);
				List<Avaliacao> avaliacoesEnviadas = new LinkedList<>();

				if(disciplinaSelecionado == null) {
					JOptionPane.showMessageDialog(frame, "Selecione uma disciplina!");
				}
				else {
					if (sistema.existeAvaliacao(disciplinaSelecionado, nome)) {
						JOptionPane.showMessageDialog(frame, "Avaliação já cadastrada!");
					} else {
						for (int i = 0; i < sistema.getListaAlunos().size(); i++) {
							if (sistema.getListaAlunos().get(i).getDisciplinas().contains(disciplinaSelecionado)) {
								Avaliacao aval = new Avaliacao(codigo, nome, peso, data, 0);
								avaliacoesEnviadas.add(aval);
							}
						}
						sistema.cadastrarAvaliacao(disciplinaSelecionado, avaliacao, professorLogado, avaliacoesEnviadas);

						if (sistema.getDisciplina(codigo).getAvaliacoes().contains(avaliacao)) {
							JOptionPane.showMessageDialog(frame, "Avaliação cadastrada com sucesso!");

							List<Aluno> todosAlunos = sistema.getListaAlunos();
							List<Disciplina> disciplinas = sistema.getListaDisciplinasProfessor();

							tabelaResultados = new TabelaResultados(todosAlunos, disciplinas);
							tableResultados.setModel(tabelaResultados);

							int coluna = tabelaResultados.getColumnCount();
							int linha = tabelaResultados.getRowCount();

							for (int i = 0; i < linha; i++) {
								for (int j = 1; j < coluna; j++) {
									tabelaResultados.isCellEditable(i, j);
									TableColumn col = tableResultados.getColumnModel().getColumn(j);
									col.setMinWidth(150);
								}
							}

							TableColumn firstColumn = tableResultados.getColumnModel().getColumn(0);
							firstColumn.setMinWidth(75);
							firstColumn.setMaxWidth(100);
							firstColumn.setResizable(false);

							int viewportHeight = scrollPaneResultados.getViewport().getHeight();
							int tableHeight = tableResultados.getPreferredSize().height;
							int viewportWidth = scrollPaneResultados.getViewport().getWidth();
							int tableWidth = tableResultados.getPreferredSize().width;

							if (tableHeight > viewportHeight) {
								scrollPaneResultados.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
							} else {
								scrollPaneResultados.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
							}
							if (tableWidth > viewportWidth) {
								scrollPaneResultados.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
							} else {
								scrollPaneResultados.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
							}

						} else {
							JOptionPane.showMessageDialog(frame, "Erro ao cadastrar avaliação!");
						}
					}
				}

				textFieldNomeAv.setText("");
				textFieldDataAv.setText("");
				textFieldPesoAv.setText("");
				comboBoxDisciplinasAv.setSelectedIndex(-1);
				comboBoxDisciplinas.setSelectedIndex(-1);
				sistema.mostrarAlunos();
			}
		});
		//FIM ABA DISCIPLINA

		//ABA DISCIPLINA ALUNO
		btnOkDiscCad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Aluno> todosAlunos = sistema.getListaAlunos();
				List<Aluno> alunosNaoCadastrados = new ArrayList<Aluno>();
				alunoListModel.removeAllElements();

				Disciplina disciplinaSelecionada = (Disciplina) comboBoxDisciplinasAluno.getSelectedItem();

				if (disciplinaSelecionada == null) {
					JOptionPane.showMessageDialog(frame, "Selecione uma disciplina!");
				} else {
					for (Aluno aluno : todosAlunos) {
						if (!aluno.getDisciplinas().contains(disciplinaSelecionada)) {
							alunosNaoCadastrados.add(aluno);
						}
					}

					Collections.sort(alunosNaoCadastrados, new Comparator<Aluno>() {
						public int compare(Aluno aluno1, Aluno aluno2) {
							return aluno1.getNome().compareToIgnoreCase(aluno2.getNome());
						}
					});

					for (Aluno aluno : alunosNaoCadastrados) {
						alunoListModel.addElement(aluno);
					}
				}
				listAlunosDiscNC.setModel(alunoListModel);
				listAlunosDiscNC.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
				scrollPaneAlunosDiscNC.revalidate();
				listAlunosDiscNC.revalidate();
			}
		});

		btnCadastrarDisciplinaAluno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Disciplina disciplinaSelecionada = (Disciplina) comboBoxDisciplinasAluno.getSelectedItem();
				List<Aluno> alunosSelecionados = listAlunosDiscNC.getSelectedValuesList();

				if(disciplinaSelecionada == null) {
					JOptionPane.showMessageDialog(frame, "Selecione uma disciplina!");
				} else if(alunosSelecionados.isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Selecione um ou mais alunos!");
				} else {
					for (Aluno aluno : alunosSelecionados) {
						sistema.cadastrarDisciplinaAluno(disciplinaSelecionada, aluno);
					}
				}
				listAlunosDiscNC.setModel(alunoListModel);
				listAlunosDiscNC.clearSelection();
				alunoListModel.removeAllElements();
				listAlunosDiscNC.revalidate();
				scrollPaneAlunosDiscNC.revalidate();
				comboBoxDisciplinasAluno.setSelectedIndex(-1);
				comboBoxDisciplinasAlunoExc.setSelectedIndex(-1);

				sistema.mostrarAlunos();
			}
		});

		btnOkDiscExc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Aluno> todosAlunos = sistema.getListaAlunos();
				List<Aluno> alunosCadastrados = new ArrayList<Aluno>();
				alunoListModelCad.removeAllElements();

				Disciplina disciplinaSelecionada = (Disciplina) comboBoxDisciplinasAlunoExc.getSelectedItem();

				if(disciplinaSelecionada == null) {
					JOptionPane.showMessageDialog(frame, "Selecione uma disciplina!");
				} else {
					for (Aluno aluno : todosAlunos) {
						if(aluno.getDisciplinas().contains(disciplinaSelecionada)) {
							alunosCadastrados.add(aluno);
						}
					}

					Collections.sort(alunosCadastrados, new Comparator<Aluno>() {
						public int compare(Aluno aluno1, Aluno aluno2) {
							return aluno1.getNome().compareToIgnoreCase(aluno2.getNome());
						}
					});

					for (Aluno aluno : alunosCadastrados) {
						alunoListModelCad.addElement(aluno);
					}
				}

				listAlunosDiscCad.setModel(alunoListModelCad);
				listAlunosDiscCad.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
				scrollPaneAlunosDiscCad.revalidate();
				listAlunosDiscCad.revalidate();
			}
		});

		btnExcluirDisciplinaAlunos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Disciplina disciplinaSelecionada = (Disciplina) comboBoxDisciplinasAlunoExc.getSelectedItem();
				List<Aluno> alunosSelecionados = listAlunosDiscCad.getSelectedValuesList();

				if(disciplinaSelecionada == null) {
					JOptionPane.showMessageDialog(frame, "Selecione uma disciplina!");
				} else if(alunosSelecionados.isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Selecione um ou mais alunos!");
				} else {
					for (Aluno aluno : alunosSelecionados) {
						sistema.removerDisciplinaAluno(disciplinaSelecionada, aluno);
					}
				}

				listAlunosDiscCad.setModel(alunoListModelCad);
				listAlunosDiscCad.clearSelection();
				alunoListModelCad.removeAllElements();
				listAlunosDiscCad.revalidate();
				scrollPaneAlunosDiscCad.revalidate();
				comboBoxDisciplinasAlunoExc.setSelectedIndex(-1);
				comboBoxDisciplinasAlunoExc.setSelectedIndex(-1);

				sistema.mostrarAlunos();
			}
		});
		//FIM ABA DISCIPLINA ALUNO

		//ABA AVALIAÇÕES ALUNO
		btnOkTabelaCad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Disciplina disciplinaSelecionada = (Disciplina) comboBoxDisciplinasAvAluno.getSelectedItem();

				if(disciplinaSelecionada != null) {
					List<Aluno> todosAlunos = sistema.getListaAlunos();
					List<Aluno> alunosCadastrados = new ArrayList<Aluno>();
					int quantidadeAvaliacoes = sistema.getAvaliacoesDisciplina(disciplinaSelecionada).size();

					for (Aluno aluno : todosAlunos) {
						if (aluno.getDisciplinas().contains(disciplinaSelecionada)) {
							alunosCadastrados.add(aluno);
						}
					}

					Collections.sort(alunosCadastrados, new Comparator<Aluno>() {
						public int compare(Aluno aluno1, Aluno aluno2) {
							return aluno1.getNome().compareToIgnoreCase(aluno2.getNome());
						}
					});

					if (quantidadeAvaliacoes == 0) {
						JOptionPane.showMessageDialog(frame, "Não há avaliações cadastradas para essa disciplina!");
					} else if (alunosCadastrados.size() == 0) {
						JOptionPane.showMessageDialog(frame, "Não há alunos cadastrados para essa disciplina!");
					} else {

						tabela = new Tabela(alunosCadastrados, disciplinaSelecionada);
						tableAvalAlunos.setModel(tabela);

						int coluna = tabela.getColumnCount();
						int linha = tabela.getRowCount();

						for(int i = 0; i < linha; i++) {
							for (int j = 1; j < coluna; j++) {
								tabela.isCellEditable(i, j);
								TableColumn col = tableAvalAlunos.getColumnModel().getColumn(j);
								col.setMinWidth(30);
							}
						}

						TableColumn firstColumn = tableAvalAlunos.getColumnModel().getColumn(0);
						firstColumn.setMinWidth(75);

						int viewportHeight = scrollPaneAval.getViewport().getHeight();
						int tableHeight = tableAvalAlunos.getPreferredSize().height;
						int viewportWidth = scrollPaneAval.getViewport().getWidth();
						int tableWidth = tableAvalAlunos.getPreferredSize().width;

						if (tableHeight > viewportHeight) {
							scrollPaneAval.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
						} else {
							scrollPaneAval.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
						}
						if(tableWidth > viewportWidth) {
							scrollPaneAval.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
						} else {
							scrollPaneAval.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
						}
					}
				} else {
					JOptionPane.showMessageDialog(frame, "Selecione uma disciplina!");
				}
				sistema.mostrarAlunos();
			}
		});

		btnCadastrarAvAlunos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, "Avaliações cadastradas com sucesso!");
				sistema.mostrarAlunos();
				tabela = new Tabela();
				tableAvalAlunos.setModel(tabela);
				tableAvalAlunos.repaint();
				tableAvalAlunos.revalidate();
				comboBoxDisciplinasAvAluno.setSelectedIndex(-1);
			}
		});

		//ABA RESULTADOS
		btnGerarPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Aluno> todosAlunos = sistema.getListaAlunos();
				List<Disciplina> disciplinas = sistema.getListaDisciplinasProfessor();
				Collections.sort(todosAlunos, new Comparator<Aluno>() {
					public int compare(Aluno aluno1, Aluno aluno2) {
						return aluno1.getNome().compareToIgnoreCase(aluno2.getNome());
					}
				});

				Collections.sort(disciplinas, new Comparator<Disciplina>() {
					public int compare(Disciplina disciplina1, Disciplina disciplina2) {
						return disciplina1.getNome().compareToIgnoreCase(disciplina2.getNome());
					}
				});

				ResultadoPdf pdf = new ResultadoPdf(todosAlunos, disciplinas);
				pdf.gerarPdf();
			}
		});
		//FIM ABA RESULTADOS

		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				professorLogado = null;
				JOptionPane.showMessageDialog(frame, "Professor deslogado com sucesso!");

				TelaPrincipal princ = new TelaPrincipal(sistema);
				princ.frame.setVisible(true);

				sistema.deslogarProfessor();

				frame.dispose();
			}
		});

	}
}
