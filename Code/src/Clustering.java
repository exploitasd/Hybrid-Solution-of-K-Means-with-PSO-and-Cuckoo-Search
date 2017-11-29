import javax.swing.JFrame;
import javax.swing.JOptionPane;
import weka.core.Instances;

public class Clustering extends JFrame {

	// Store the Dataset
	public static Instances classData;
	public static Instances searchData;

	// Open File Object
	public OpenFile opf;

	// CSPSOSearch
	public CSPSOSearch cspso;

	// Classification
	public Kmean kmean;
	public CSPSOKmean cspsokmean;

	// Datatype Numeric
	public static boolean dataType[];

	// Constructor
	public Clustering() {
		// Initilaize the Java Swing Component.
		initComponents();

		// Maximize the Windows
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		// Set the tile of Application.
		this.setTitle("Data Clustering with ");

		// Call constructor for OpenDataFile class
		opf = new OpenFile();

		// call Constructor for Classify Class
		kmean = new Kmean();
		cspsokmean = new CSPSOKmean();
		cspso = new CSPSOSearch();
	}

	// OutFile path
	public String outpath(String inPath) {
		String outpath = inPath;
		if (inPath.contains(".")) {
			String name = inPath.substring(0, inPath.lastIndexOf("."));
			String ext = inPath.substring(inPath.lastIndexOf("."));
			outpath = name + "_CSPSO" + ext;
		}
		return outpath;
	}

	private void initComponents() {
		btnPanel = new javax.swing.JPanel();
		classifyBtn = new javax.swing.JButton();
		inpLbl = new javax.swing.JLabel();
		inpText = new javax.swing.JTextField();
		openBtn = new javax.swing.JButton();
		outLbl = new javax.swing.JLabel();
		outText = new javax.swing.JTextField();
		saveBtn = new javax.swing.JButton();
		cluLbl = new javax.swing.JLabel();
		numBox = new javax.swing.JComboBox<>();
		scrollPane = new javax.swing.JScrollPane();
		textPane = new javax.swing.JTextPane();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		classifyBtn.setText("Classify");
		classifyBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				classifyBtnActionPerformed(evt);
			}
		});

		inpLbl.setText("Select Input Data File");

		inpText.setEditable(false);

		openBtn.setText("...");
		openBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				openBtnActionPerformed(evt);
			}
		});

		outLbl.setText("Select Output Data File");

		outText.setEditable(false);

		saveBtn.setText("...");
		saveBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				saveBtnActionPerformed(evt);
			}
		});

		cluLbl.setText("Select No. of Cluster");

		numBox.setModel(
				new javax.swing.DefaultComboBoxModel<>(new String[] { "2", "3", "4", "5", "6", "7", "8", "9", "10" }));

		javax.swing.GroupLayout btnPanelLayout = new javax.swing.GroupLayout(btnPanel);
		btnPanel.setLayout(btnPanelLayout);
		btnPanelLayout.setHorizontalGroup(btnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(btnPanelLayout.createSequentialGroup().addContainerGap()
						.addGroup(btnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(btnPanelLayout.createSequentialGroup()
										.addComponent(inpLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 159,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(inpText, javax.swing.GroupLayout.DEFAULT_SIZE, 553,
												Short.MAX_VALUE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(openBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 38,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGroup(btnPanelLayout.createSequentialGroup()
								.addComponent(outLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 159,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(outText)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 38,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
								btnPanelLayout.createSequentialGroup()
										.addComponent(cluLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 159,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(numBox, javax.swing.GroupLayout.PREFERRED_SIZE, 77,
												javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(classifyBtn,
												javax.swing.GroupLayout.PREFERRED_SIZE, 104,
												javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addContainerGap()));
		btnPanelLayout.setVerticalGroup(btnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(btnPanelLayout.createSequentialGroup().addContainerGap()
						.addGroup(btnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(inpLbl)
								.addComponent(inpText, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(openBtn))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(btnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(outLbl)
								.addComponent(outText, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(saveBtn))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(btnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(classifyBtn)
								.addGroup(btnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(cluLbl).addComponent(numBox,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		btnPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL,
				new java.awt.Component[] { inpLbl, inpText, openBtn });

		textPane.setEditable(false);
		scrollPane.setViewportView(textPane);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(btnPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addContainerGap()
								.addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 768, Short.MAX_VALUE)
								.addContainerGap())));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(btnPanel, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(0, 330, Short.MAX_VALUE))
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
								layout.createSequentialGroup()
										.addGap(121, 121, 121).addComponent(scrollPane,
												javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
								.addContainerGap())));

		pack();
	}

	private void classifyBtnActionPerformed(java.awt.event.ActionEvent evt) {
		// Classify the data
		if (classData != null) {
			String inFile = inpText.getText();
			String outFile = outText.getText();
			String searchCSV = outpath(inFile);
			String resultCSV = outpath(outFile);
			String data = kmean.classify(classData, outFile, numBox.getSelectedIndex() + 2);
			cspso.convertCSV(inFile, searchCSV, classData.numAttributes(), classData.numInstances());
			opf.loadSearch(searchCSV);
			data += cspsokmean.classify(searchData, resultCSV, numBox.getSelectedIndex() + 2);
			textPane.setText(data);
		} else {
			JOptionPane.showMessageDialog(null, "Please Select Input Data File", "Set File Path",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void openBtnActionPerformed(java.awt.event.ActionEvent evt) {
		opf.openFile(this);
	}

	private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {
		opf.saveFile(this);
	}

	public static void main(String args[]) {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(Clustering.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Clustering.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Clustering.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Clustering.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		}
		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				// Call the constructor.
				new Clustering().setVisible(true);
			}
		});
	}

	private javax.swing.JPanel btnPanel;
	private javax.swing.JButton classifyBtn;
	private javax.swing.JLabel cluLbl;
	private javax.swing.JLabel inpLbl;
	public javax.swing.JTextField inpText;
	public static javax.swing.JComboBox<String> numBox;
	private javax.swing.JButton openBtn;
	private javax.swing.JLabel outLbl;
	public javax.swing.JTextField outText;
	private javax.swing.JButton saveBtn;
	private javax.swing.JScrollPane scrollPane;
	public javax.swing.JTextPane textPane;
}