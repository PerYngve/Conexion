import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BuscarEstudiante extends JFrame {
    private JTextField txtIdBuscar;
    private JButton btnBuscar;

    public BuscarEstudiante() {
        super("Buscar Estudiante");
        setSize(300, 100);
        setLayout(new GridLayout(2, 2));

        add(new JLabel("Buscar por nombre:"));
        txtIdBuscar = new JTextField();
        add(txtIdBuscar);

        btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarEstudiante();
            }
        });
        add(btnBuscar);

        setVisible(true);
    }

    private void buscarEstudiante() {
        try {
            Connection conexion = Conexion.obtenerConexion();
            String query = "SELECT * FROM estudiante WHERE nombre_estudiante = ?";
            PreparedStatement statement = conexion.prepareStatement(query);

            String nombre_estudiante = (txtIdBuscar.getText());
            statement.setString(1, nombre_estudiante);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String nombre = resultSet.getString("nombre_estudiante");
                String carrera = resultSet.getString("carrera");
                int edad = resultSet.getInt("edad");
                int id = resultSet.getInt("id");
                String matricula = resultSet.getString("matricula");

                JOptionPane.showMessageDialog(this, "ID: " + id + "\nNombre: " + nombre +
                        "\nCarrera: " + carrera + "\nEdad: " + edad + "\nMatrícula: " + matricula);
            } else {
                JOptionPane.showMessageDialog(this, "Estudiante no encontrado");
            }

            statement.close();
            conexion.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BuscarEstudiante();
            }
        });
    }
}
