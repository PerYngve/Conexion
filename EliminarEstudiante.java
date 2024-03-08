import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EliminarEstudiante extends JFrame {
    private JTextField txtIdEliminar;
    private JButton btnEliminar;

    public EliminarEstudiante() {
        super("Eliminar Estudiante");
        setSize(300, 100);
        setLayout(new GridLayout(2, 2));

        add(new JLabel("ID a eliminar:"));
        txtIdEliminar = new JTextField();
        add(txtIdEliminar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarEstudiante();
            }
        });
        add(btnEliminar);

        setVisible(true);
    }

    private void eliminarEstudiante() {
        try {
            Connection conexion = Conexion.obtenerConexion();
            String query = "DELETE FROM estudiante WHERE id = ?";
            PreparedStatement statement = conexion.prepareStatement(query);

            int id = Integer.parseInt(txtIdEliminar.getText());
            statement.setInt(1, id);

            int filasAfectadas = statement.executeUpdate();

            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(this, "Estudiante eliminado correctamente");
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar estudiante");
            }

            statement.close();
            conexion.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void limpiarCampos() {
        txtIdEliminar.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EliminarEstudiante();
            }
        });
    }
}
