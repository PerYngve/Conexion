import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ActualizarEstudiante extends JFrame {
    private JTextField txtIdActualizar, txtNuevoNombre, txtNuevaCarrera, txtNuevaEdad, txtNuevaMatricula;
    private JButton btnActualizar;

    public ActualizarEstudiante() {
        super("Actualizar Estudiante");
        setSize(400, 200);
        setLayout(new GridLayout(6, 2));

        add(new JLabel("ID a actualizar:"));
        txtIdActualizar = new JTextField();
        add(txtIdActualizar);

        add(new JLabel("Nuevo Nombre:"));
        txtNuevoNombre = new JTextField();
        add(txtNuevoNombre);

        add(new JLabel("Nueva Carrera:"));
        txtNuevaCarrera = new JTextField();
        add(txtNuevaCarrera);

        add(new JLabel("Nueva Edad:"));
        txtNuevaEdad = new JTextField();
        add(txtNuevaEdad);

        add(new JLabel("Nueva Matrícula:"));
        txtNuevaMatricula = new JTextField();
        add(txtNuevaMatricula);

        btnActualizar = new JButton("Actualizar");
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarEstudiante();
            }
        });
        add(btnActualizar);

        setVisible(true);
    }

    private void actualizarEstudiante() {
        try {
            Connection conexion = Conexion.obtenerConexion();
            String query = "UPDATE estudiante SET nombre_estudiante = ?, carrera = ?, edad = ?, matricula = ? WHERE id = ?";
            PreparedStatement statement = conexion.prepareStatement(query);

            statement.setString(1, txtNuevoNombre.getText());
            statement.setString(2, txtNuevaCarrera.getText());
            statement.setInt(3, Integer.parseInt(txtNuevaEdad.getText()));
            statement.setString(4, txtNuevaMatricula.getText());
            statement.setInt(5, Integer.parseInt(txtIdActualizar.getText()));

            int filasAfectadas = statement.executeUpdate();

            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(this, "Estudiante actualizado correctamente");
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar estudiante");
            }

            statement.close();
            conexion.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void limpiarCampos() {
        txtIdActualizar.setText("");
        txtNuevoNombre.setText("");
        txtNuevaCarrera.setText("");
        txtNuevaEdad.setText("");
        txtNuevaMatricula.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ActualizarEstudiante();
            }
        });
    }
}
