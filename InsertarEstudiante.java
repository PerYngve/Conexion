import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertarEstudiante extends JFrame {
    private JTextField txtNombre, txtCarrera, txtEdad, txtMatricula;
    private JButton btnInsertar;

    public InsertarEstudiante() {
        super("Insertar Estudiante");
        setSize(300, 200);
        setLayout(new GridLayout(5, 2));

        add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        add(txtNombre);

        add(new JLabel("Carrera:"));
        txtCarrera = new JTextField();
        add(txtCarrera);

        add(new JLabel("Edad:"));
        txtEdad = new JTextField();
        add(txtEdad);

        add(new JLabel("Matrícula:"));
        txtMatricula = new JTextField();
        add(txtMatricula);

        btnInsertar = new JButton("Insertar");
        btnInsertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertarEstudiante();
            }
        });
        add(btnInsertar);

        setVisible(true);
    }

    private void insertarEstudiante() {
        try {
            Connection conexion = Conexion.obtenerConexion();
            String query = "INSERT INTO estudiante (nombre_estudiante, carrera, edad, matricula) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conexion.prepareStatement(query);

            statement.setString(1, txtNombre.getText());
            statement.setString(2, txtCarrera.getText());
            statement.setInt(3, Integer.parseInt(txtEdad.getText()));
            statement.setString(4, txtMatricula.getText());

            int filasAfectadas = statement.executeUpdate();

            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(this, "Estudiante ingresado correctamente");
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al ingresar estudiante");
            }

            statement.close();
            conexion.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtCarrera.setText("");
        txtEdad.setText("");
        txtMatricula.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new InsertarEstudiante();
            }
        });
    }
}
