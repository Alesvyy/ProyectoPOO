import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class User {
    private static final AtomicInteger idGenerator = new AtomicInteger(1);
    private int id;
    private String name;
    private String email;
    private String password;
    private List<Cancion> canciones = new ArrayList<>();

    private static List<User> users = new ArrayList<>();

    public User(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        users.add(this); // Agrega el usuario a la lista
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public List<Cancion> getCanciones() {
        return canciones;
    }

    public void addCancion(Cancion cancion) {
        canciones.add(cancion);
    }

    public static List<User> getUsers() {
        return users;
    }

    public static User getUser(String email, String password) {
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
    

    @Override
    public String toString() {
        return "ID: " + id + ", Nombre: " + name + ", Email: " + email;
    }
}
