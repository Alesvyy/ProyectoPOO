import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.regex.*;

public class SignUp extends JFrame {
    private JTextField idField;
    private JTextField nameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton togglePasswordButton;
    private boolean isPasswordVisible = false;

    public SignUp() {
        setTitle("Lelyfy Sign Up");
        setSize(380, 400); // Tamaño inicial de la ventana
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla

        ImageIcon icon = new ImageIcon("imagenes/iconos/iconoClancy.jpg");
        setIconImage(icon.getImage());

        // Configuración del panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.decode("#CD8166")); // Color de fondo

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espaciado entre componentes
        gbc.anchor = GridBagConstraints.CENTER;

        // Título
        JLabel titleLabel = new JLabel("Registro");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(titleLabel, gbc);

        // Etiquetas y campos de entrada con más espacio
        JLabel idLabel = new JLabel("ID:");
        idLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(idLabel, gbc);

        idField = new JTextField(22);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(idField, gbc);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(nameLabel, gbc);

        nameField = new JTextField(22);
        gbc.gridx = 1;
        panel.add(nameField, gbc);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(emailLabel, gbc);

        emailField = new JTextField(22);
        gbc.gridx = 1;
        panel.add(emailField, gbc);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(passwordLabel, gbc);

        // Panel para el campo de contraseña y el botón de mostrar/ocultar
        JPanel passwordPanel = new JPanel(new BorderLayout());
        passwordPanel.setOpaque(true); // Asegurarse de que el panel es opaco
        passwordPanel.setBackground(Color.WHITE); // Establecer fondo blanco para el panel

        // Campo de Contraseña
        passwordField = new JPasswordField(20);
        passwordPanel.add(passwordField, BorderLayout.CENTER);

        // Botón para mostrar/ocultar contraseña
        togglePasswordButton = new JButton();
        togglePasswordButton.setPreferredSize(new Dimension(20, 20)); // Tamaño pequeño
        togglePasswordButton.setBorder(BorderFactory.createEmptyBorder());
        togglePasswordButton.setContentAreaFilled(false); // El área del contenido no debe ser rellenada
        togglePasswordButton.setIcon(new ImageIcon("imagenes/iconos/ojoCerrado.png")); // Imagen inicial

        togglePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                togglePasswordVisibility();
            }
        });
        passwordPanel.add(togglePasswordButton, BorderLayout.EAST);

        gbc.gridx = 1;
        panel.add(passwordPanel, gbc);

        // Botón de registro centrado
        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setBackground(Color.decode("#F3C300")); // Color de fondo amarillo oscuro
        signUpButton.setForeground(Color.BLACK); // Color de texto negro
        gbc.gridy = 5;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(signUpButton, gbc);

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idText = idField.getText();
                String name = nameField.getText();
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());
        
                // Validaciones
                if (idText.isEmpty() || name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos los campos deben ser llenados");
                    return;
                }
        
                if (!isValidID(idText)) {
                    JOptionPane.showMessageDialog(null, "El ID debe tener entre 8 y 12 caracteres");
                    return;
                }
        
                if (!email.contains("@") || email.contains(" ")) {
                    JOptionPane.showMessageDialog(null, "El correo electrónico debe contener al menos un '@' y no debe contener espacios");
                    return;
                }
        
                if (password.contains(" ") || !isValidPassword(password)) {
                    JOptionPane.showMessageDialog(null, "La contraseña debe tener entre 8 y 16 caracteres, " +
                            "contener al menos una mayúscula, una minúscula, un número y no debe contener espacios");
                    return;
                }
        
                // ID como número entero
                int id;
                try {
                    id = Integer.parseInt(idText);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "ID debe ser un número entero");
                    return;
                }
        
                // Crear el usuario
                new User(id, name, email, password);
                JOptionPane.showMessageDialog(null, "Registro exitoso");
                dispose(); // Cierra la ventana de registro
            }
        });
        

        JScrollPane scrollPane = new JScrollPane(panel);
        add(scrollPane);
    }

    // Método para validar el ID
    private boolean isValidID(String id) {
        return id.length() >= 8 && id.length() <= 12;
    }

    // Método para validar la contraseña
    private boolean isValidPassword(String password) {
        if (password.length() < 8 || password.length() > 16) {
            return false;
        }
        boolean hasUpperCase = !password.equals(password.toLowerCase());
        boolean hasLowerCase = !password.equals(password.toUpperCase());
        boolean hasDigit = password.matches(".*\\d.*");
        return hasUpperCase && hasLowerCase && hasDigit && !password.contains(" ");
    }

    // Método para mostrar/ocultar la contraseña
    private void togglePasswordVisibility() {
        if (isPasswordVisible) {
            passwordField.setEchoChar('*');
            togglePasswordButton.setIcon(new ImageIcon("imagenes/iconos/ojoCerrado.png"));
        } else {
            passwordField.setEchoChar((char) 0);
            togglePasswordButton.setIcon(new ImageIcon("imagenes/iconos/ojoAbierto.png"));
        }
        isPasswordVisible = !isPasswordVisible;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SignUp().setVisible(true);
            }
        });
    }
}
