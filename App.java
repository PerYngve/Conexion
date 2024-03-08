import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class App extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App frame = new App();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	conexion db=new conexion();
	Connection cin = db.getConnection();
	String id, nombre_alumno, carrera, edad, matricula;
	PreparedStatement ps;
	
	public App() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 699, 562);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 681, 610);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CRUD MYSQL");
		lblNewLabel.setFont(new Font("Times New Roman", Font.ITALIC, 25));
		lblNewLabel.setBounds(216, 37, 180, 54);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(362, 144, 149, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(362, 175, 149, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(362, 206, 149, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(362, 237, 149, 20);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_1.setBounds(216, 147, 22, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nombre del alumno");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_2.setBounds(216, 178, 131, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Carrera");
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_3.setBounds(216, 209, 80, 14);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Edad");
		lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_4.setBounds(216, 240, 46, 14);
		panel.add(lblNewLabel_4);
		
		textField_4 = new JTextField();
		textField_4.setBounds(362, 268, 149, 20);
		panel.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Matricula");
		lblNewLabel_5.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_5.setBounds(216, 271, 91, 14);
		panel.add(lblNewLabel_5);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\USER\\Downloads\\embalaje.png"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				//id = textField.getText();
				nombre_alumno = textField_1.getText();
				carrera = textField_2.getText();
				edad = textField_3.getText();
				matricula = textField_4.getText();
				
				
				String insertar = "INSERT INTO alumno (nombre_alumno, carrera, edad, matricula) VALUES (?, ?, ?, ?)";
						
				try {
					
					ps=cin.prepareCall(insertar);
						System.out.println("Insertando");
						//ps.setString(1,  id);
						ps.setString(1,  nombre_alumno);
						ps.setString(2,  carrera);
						ps.setString(3,  edad);
						ps.setString(4,  matricula);
						
					int registro1 = ps.executeUpdate();
				if(registro1>0) {
					JOptionPane.showMessageDialog(null,  "Registro Guardado", "bien", JOptionPane.QUESTION_MESSAGE, null);
					//textField.setText("");
					textField_1.setText("");
					textField_2.setText("");
					textField_3.setText("");
					textField_4.setText("");
				}
				else
				{
					JOptionPane.showMessageDialog(null,  "Error al guardar el registro", "error", JOptionPane.ERROR_MESSAGE);
				}
				
				} 
				catch (SQLException el) {
					el.printStackTrace();
				}
					
		
			}
		});
		btnNewButton.setBounds(105, 385, 109, 73);
		panel.add(btnNewButton);
		btnNewButton.setOpaque(false);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBorderPainted(false);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\USER\\Downloads\\buscar.png"));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				id = textField.getText();
				
				String buscar = "SELECT * FROM alumno WHERE id = ?";
				
				try {
					
					ps=cin.prepareCall(buscar);
						System.out.println("Buscando");
						ps.setString(1,  id);
						
					ResultSet registro = ps.executeQuery();
						
				if(registro.next()) {
					textField.setText(registro.getString("id"));
					textField_1.setText(registro.getString("nombre_alumno"));
					textField_2.setText(registro.getString("carrera"));
					textField_3.setText(registro.getString("edad"));
					textField_4.setText(registro.getString("matricula"));
					
					JOptionPane.showMessageDialog(null,  "Registro Encontrado", "bien", JOptionPane.QUESTION_MESSAGE, null);
				
				}
				else
				{
					JOptionPane.showMessageDialog(null,  "Error al buscar el registro", "error", JOptionPane.ERROR_MESSAGE);
				}
				
				} 
				catch (SQLException el) {
					el.printStackTrace();
				}
				
				
			}
		});
		btnNewButton_1.setBounds(224, 385, 109, 73);
		panel.add(btnNewButton_1);
		btnNewButton_1.setOpaque(false);
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setBorderPainted(false);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setIcon(new ImageIcon("C:\\Users\\USER\\Downloads\\basura.png"));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				String eliminar = "DELETE FROM alumno WHERE id = ?";
						
				try {
					
					ps=cin.prepareCall(eliminar);
						System.out.println("Eliminando");
						ps.setString(1,  id);
						//ps.setString(1,  nombre_alumno);
						//ps.setString(2,  carrera);
						//ps.setString(3,  edad);
						//ps.setString(4,  matricula);
						
					int registro = ps.executeUpdate();
					
				if(registro>0) {
					JOptionPane.showMessageDialog(null,  "Registro Eliminado", "bien", JOptionPane.QUESTION_MESSAGE, null);
					textField.setText("");
					textField_1.setText("");
					textField_2.setText("");
					textField_3.setText("");
					textField_4.setText("");
				}
				else
				{
					JOptionPane.showMessageDialog(null,  "Error al eliminar el registro", "error", JOptionPane.ERROR_MESSAGE);
				}
				
				} 
				catch (SQLException el) {
					el.printStackTrace();
				}
			
			
			}
		});
		btnNewButton_2.setBounds(343, 385, 109, 73);
		panel.add(btnNewButton_2);
		btnNewButton_2.setOpaque(false);
		btnNewButton_2.setContentAreaFilled(false);
		btnNewButton_2.setBorderPainted(false);
		
		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.setIcon(new ImageIcon("C:\\Users\\USER\\Downloads\\actualizar.png"));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				id = textField.getText();
				nombre_alumno = textField_1.getText();
				carrera = textField_2.getText();
				edad = textField_3.getText();
				matricula = textField_4.getText();
				
				String actualizar = "UPDATE alumno SET id = ?, nombre_alumno = ?, carrera = ?, edad = ?, matricula = ?  WHERE id = ?";
				
try {
					
					ps=cin.prepareCall(actualizar);
						System.out.println("Actualizando");
						ps.setString(1,  id);
						ps.setString(2,  nombre_alumno);
						ps.setString(3,  carrera);
						ps.setString(4,  edad);
						ps.setString(5,  matricula);
						
					int registro = ps.executeUpdate();
					
				if(registro>0) {
					JOptionPane.showMessageDialog(null,  "Registro Modificado", "correcto", JOptionPane.QUESTION_MESSAGE, null);
					textField.setText("");
					textField_1.setText("");
					textField_2.setText("");
					textField_3.setText("");
					textField_4.setText("");
				}
				else
				{
					JOptionPane.showMessageDialog(null,  "Error al modificar el registro", "error", JOptionPane.ERROR_MESSAGE);
				}
				
				} 
				catch (SQLException el) {
					el.printStackTrace();
				}
			}
		});
		btnNewButton_3.setBounds(466, 385, 109, 73);
		panel.add(btnNewButton_3);
		btnNewButton_3.setOpaque(false);
		btnNewButton_3.setContentAreaFilled(false);
		btnNewButton_3.setBorderPainted(false);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setIcon(new ImageIcon("C:\\Users\\USER\\Downloads\\casa-kame-de-dragon-ball_3840x2160_xtrafondos.com (2).jpg"));
		lblNewLabel_6.setBounds(-56, -52, 783, 651);
		panel.add(lblNewLabel_6);
	}
}
