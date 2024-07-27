import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Login extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton togglePasswordButton;
    private List<User> usuarios;
    private boolean isPasswordVisible = false;

    // Instancia actual de la ventana Login
    public  static Login loginInstance;

    public  Login() {
        setTitle("Lelyfy Login");
        setSize(470, 370); // Tamaño inicial de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla

        usuarios = User.getUsers(); // Obtener la lista de usuarios desde User.java 

        ImageIcon icon = new ImageIcon("imagenes/iconos/iconoClancy.jpg");
        setIconImage(icon.getImage());

        // Panel para los componentes
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(0xEC8663)); // Establecer el color de fondo
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Título
        JLabel titleLabel = new JLabel("Inicia sesión");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE); // Color de texto blanco
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER; // Centrar el título
        panel.add(titleLabel, gbc);

        // Etiqueta de Email
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setForeground(Color.WHITE); // Color de texto blanco
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(emailLabel, gbc);

        // Campo de Email
        emailField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(emailField, gbc);

        // Etiqueta de Contraseña
        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordLabel.setForeground(Color.WHITE); // Color de texto blanco
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
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
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(passwordPanel, gbc);

        // Panel para los botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(new Color(0xEC8663)); // Color de fondo para el panel de botones

        JButton signUpButton = new JButton("Sign Up");
        JButton loginButton = new JButton("Login");
        JButton showUsersButton = new JButton("Users");

        // Colores de los botones
        Color buttonColor = new Color(0xF7E7A0); // Amarillo oscuro
        Color textColor = new Color(0x333333); // Gris oscuro

        signUpButton.setBackground(buttonColor);
        signUpButton.setForeground(textColor); // Color del texto gris oscuro
        loginButton.setBackground(buttonColor);
        loginButton.setForeground(textColor); // Color del texto gris oscuro
        showUsersButton.setBackground(buttonColor);
        showUsersButton.setForeground(textColor); // Color del texto gris oscuro

        // Añadir botones al panel de botones
        buttonPanel.add(signUpButton);
        buttonPanel.add(loginButton);
        buttonPanel.add(showUsersButton);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(buttonPanel, gbc);

        // Acción para el botón de Login
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());
        
                boolean loginSuccessful = false;
                String username = ""; // Variable para almacenar el nombre del usuario
        
                // Verificar el email y la contraseña
                for (User usuario : usuarios) {
                    if (usuario.getEmail().equals(email) && usuario.getPassword().equals(password)) {
                        loginSuccessful = true;
                        username = usuario.getName(); // Obtener el nombre del usuario
                        break;
                    }
                }
        
                if (loginSuccessful) {
                    JOptionPane.showMessageDialog(null, "Login exitoso");
                    dispose(); // Cierra la ventana de login
                    Menu menu = new Menu(username); // Pasa el nombre del usuario al constructor de Menu
                    menu.setVisible(true); // Muestra la ventana del menú
                } else {
                    JOptionPane.showMessageDialog(null, "Email o contraseña incorrectos");
                }
            }
        });
        
        

        // Acción para el botón de SignUp
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SignUp().setVisible(true); // Muestra la ventana de registro
            }
        });

        // Acción para el botón de Users
        showUsersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UserList().setVisible(true); // Muestra la ventana de lista de usuarios
            }
        });

        // Añadir el panel a un JScrollPane para permitir el desplazamiento
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setPreferredSize(new Dimension(470, 370));
        add(scrollPane);

        // Establecer la instancia de la ventana de Login
        loginInstance = this;
    }

    private void togglePasswordVisibility() {
        if (isPasswordVisible) {
            passwordField.setEchoChar('•');
            togglePasswordButton.setIcon(new ImageIcon("imagenes/iconos/ojoCerrado.png"));
        } else {
            passwordField.setEchoChar((char) 0); // Mostrar la contraseña
            togglePasswordButton.setIcon(new ImageIcon("imagenes/iconos/ojoAbierto.png"));
        }
        isPasswordVisible = !isPasswordVisible;
    }

    // Método para obtener la instancia actual de Login
    public static Login getInstance() {
        return loginInstance;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                if (loginInstance == null || !loginInstance.isVisible()) {
                    new Login().setVisible(true);
                }
            }
        });
    }
}
