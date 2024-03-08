import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrincipalCRUD extends JFrame {
    private JButton btnInsertar, btnBuscar, btnActualizar, btnEliminar;

    public PrincipalCRUD() {
        super("CRUD Interfaz Gráfica");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        btnInsertar = new JButton("Insertar");
        btnBuscar = new JButton("Buscar");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");

        btnInsertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertarEstudiante();
            }
        });

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarEstudiante();
            }
        });

        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarEstudiante();
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarEstudiante();
            }
        });

        add(btnInsertar);
        add(btnBuscar);
        add(btnActualizar);
        add(btnEliminar);

        setVisible(true);
    }

    private void insertarEstudiante() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new InsertarEstudiante();
            }
        });
    }

    private void buscarEstudiante() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BuscarEstudiante();
            }
        });
    }

    private void actualizarEstudiante() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ActualizarEstudiante();
            }
        });
    }

    private void eliminarEstudiante() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EliminarEstudiante();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PrincipalCRUD();
            }
        });
    }
}
