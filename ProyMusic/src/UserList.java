import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.text.*;

public class UserList extends JFrame {
    public UserList() {
        setTitle("Lelyfy Lista de Usuarios");
        setSize(360, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla

        ImageIcon icon = new ImageIcon("imagenes/iconos/iconoClancy.jpg");
        setIconImage(icon.getImage());

        List<User> usuarios = User.getUsers();

        JTextPane textPane = new JTextPane();
        textPane.setEditable(false);

        StyledDocument doc = textPane.getStyledDocument();
        SimpleAttributeSet bold = new SimpleAttributeSet();
        StyleConstants.setBold(bold, true);
        SimpleAttributeSet normal = new SimpleAttributeSet();
        StyleConstants.setFontSize(normal, 12);

        int count = 1;
        for (User usuario : usuarios) {
            try {
                doc.insertString(doc.getLength(), "Usuario " + count + ":\n", bold);
                doc.insertString(doc.getLength(), "ID: " + usuario.getId() + "\n", normal);
                doc.insertString(doc.getLength(), "Nombre: " + usuario.getName() + "\n", normal);
                doc.insertString(doc.getLength(), "Email: " + usuario.getEmail() + "\n\n", normal);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
            count++;
        }

        JScrollPane scrollPane = new JScrollPane(textPane);
        scrollPane.setPreferredSize(new Dimension(250, 300));

        add(scrollPane);
    }
}
