import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;
import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;


public class Menu extends JFrame {

    private Clip clip;
    private boolean isPlaying = false;
    private long pausedPosition = 0;
    private JList<Song> songList;
    private Song currentPlayingSong;
    private DefaultListModel<Song> songListModel;
    private JSlider progressBar;
    private JSlider volumeSlider;
    private JLabel durationLabel;
    private Timer timer;
    private File songsFolder = new File("canciones");
    private File imagesFolder = new File("imagenes/portadas");
    private JPanel volumePanel;
    private JToggleButton volumeButton;
    private JButton playPauseButton;
    private JButton previousButton;
    private JButton nextButton;
    private JButton addButton;
    private JButton deleteButton;
    private JButton logoutButton; // Agregar esta línea
    private Song currentSong = null;
    private int currentIndex = -1;
    private float currentVolume = 0.5f; // Valor predeterminado
    private final String IMAGE_PATH = "imagenes/iconos/";
    

    public Menu(String userName) {
        // Configuración de la ventana
        setTitle("Lelyfy Player");
        setSize(800, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Crear el modelo y la lista de canciones
        songListModel = new DefaultListModel<>();
        songList = new JList<>(songListModel);
        songList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        songList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    playSelectedSong();
                }
            }
        });
        songList.setCellRenderer(new SongListRenderer());

    // Cargar el ícono desde un archivo de imagen
    ImageIcon icon = new ImageIcon("imagenes/iconos/iconoClancy.jpg");

    // Configurar el ícono de la aplicación
    setIconImage(icon.getImage());

   // Crear un panel para la lista de reproducción con fondo verde
   JPanel playlistPanel = new JPanel(new BorderLayout());
   playlistPanel.setBackground(Color.GREEN); // Establecer el fondo a verde

   // Crear un título para la lista de reproducción
   JLabel playlistTitle = new JLabel("Lista de Reproducción (" + userName + ")", JLabel.CENTER);
   playlistTitle.setFont(new Font("Arial", Font.BOLD, 18));
   playlistTitle.setPreferredSize(new Dimension(getWidth(), 30));
   playlistTitle.setOpaque(true);
   playlistTitle.setBackground(new Color(249, 145, 109)); // Naranja

        // Añadir el título y la lista de canciones al panel de lista de reproducción
        playlistPanel.add(playlistTitle, BorderLayout.NORTH);
        playlistPanel.add(new JScrollPane(songList), BorderLayout.CENTER);

        add(playlistPanel, BorderLayout.CENTER);

        // Crear el panel de controles
        JPanel controlPanel = new JPanel(new BorderLayout());

        // Panel para controles de reproducción
        JPanel playbackPanel = new JPanel();
        playbackPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0)); // Centrar los controles

        previousButton = new JButton(resizeIcon("anterior.png", 20, 20));
        previousButton.addActionListener(e -> playPreviousSong());
        playbackPanel.add(previousButton);

        playPauseButton = new JButton(resizeIcon("play-button.png", 20, 20));
        playPauseButton.addActionListener(e -> togglePlayPause());
        playbackPanel.add(playPauseButton);

        nextButton = new JButton(resizeIcon("proximo.png", 20, 20));
        nextButton.addActionListener(e -> playNextSong());
        playbackPanel.add(nextButton);

        progressBar = new JSlider(0, 1000, 0);
        progressBar.setPreferredSize(new Dimension(200, 20));
        progressBar.addChangeListener(e -> {
            if (clip != null && !progressBar.getValueIsAdjusting()) {
                long newPosition = progressBar.getValue();
                clip.setMicrosecondPosition(newPosition);
            }
        });
        progressBar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (clip != null) {
                    int x = e.getX();
                    int sliderWidth = progressBar.getWidth();
                    long duration = clip.getMicrosecondLength();
                    long newPosition = (long) (x / (double) sliderWidth * duration);
                    clip.setMicrosecondPosition(newPosition);
                    progressBar.setValue((int) newPosition);
                }
            }
        });

        durationLabel = new JLabel("00:00 / 00:00");

        // Panel para la barra de progreso y duración
        JPanel progressPanel = new JPanel();
        progressPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0)); // Centrar la barra de progreso y la etiqueta

        progressPanel.add(durationLabel);
        progressPanel.add(progressBar);
        progressPanel.add(durationLabel);

        playbackPanel.add(progressPanel);

        // Panel para controles adicionales (volumen, agregar, eliminar, cerrar sesión)
        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 0));

        volumeButton = new JToggleButton(resizeIcon("volumen.png", 20, 20));
        volumeButton.addActionListener(e -> {
            if (volumeButton.isSelected()) {
                volumePanel.setVisible(true);
            } else {
                volumePanel.setVisible(false);
            }
        });
        sidePanel.add(volumeButton);

        addButton = new JButton(resizeIcon("boton-mas.png", 20, 20));
        addButton.addActionListener(e -> addSongs());
        sidePanel.add(addButton);

        deleteButton = new JButton(resizeIcon("eliminar.png", 20, 20));
        deleteButton.addActionListener(e -> deleteSelectedSong());
        sidePanel.add(deleteButton);

        logoutButton = new JButton(resizeIcon("cerrar-sesion.png", 20, 20));
        logoutButton.addActionListener(e -> {
            // Crear y mostrar la ventana de Login
            Login loginFrame = new Login();
            loginFrame.setVisible(true);

            // Cerrar la ventana actual
            dispose();
        });
        sidePanel.add(logoutButton);

        controlPanel.add(playbackPanel, BorderLayout.CENTER);
        controlPanel.add(sidePanel, BorderLayout.EAST);

        // Configurar el panel de volumen
        volumeSlider = new JSlider(0, 100, (int) (currentVolume * 100));
        volumeSlider.setPreferredSize(new Dimension(20, 100));
        volumeSlider.setOrientation(JSlider.VERTICAL);
        volumeSlider.addChangeListener(e -> {
            if (clip != null) {
                currentVolume = (float) volumeSlider.getValue() / 100;
                FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                volumeControl.setValue(20f * (float) Math.log10(currentVolume));
            }
        });

        volumePanel = new JPanel();
        volumePanel.setLayout(new BorderLayout());
        volumePanel.add(volumeSlider, BorderLayout.CENTER);
        volumePanel.setVisible(false); // Inicialmente oculto

        add(controlPanel, BorderLayout.SOUTH);
        add(volumePanel, BorderLayout.EAST);

        // Cargar canciones existentes
        loadSongs();
    }
    
    

    private ImageIcon resizeIcon(String fileName, int width, int height) {
        try {
            BufferedImage img = ImageIO.read(new File(IMAGE_PATH + fileName));
            Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImg);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void loadSongs() {
        songListModel.clear();
        if (songsFolder.exists() && songsFolder.isDirectory()) {
            File[] files = songsFolder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.getName().endsWith(".wav")) {
                        String name = removeExtension(file.getName());
                        File imageFile = new File(imagesFolder, name + ".png");
                        ImageIcon icon = null;
                        if (imageFile.exists()) {
                            try {
                                BufferedImage img = ImageIO.read(imageFile);
                                icon = new ImageIcon(img.getScaledInstance(50, 50, Image.SCALE_SMOOTH));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        songListModel.addElement(new Song(name, icon)); // Agregar canciones
                    }
                }
            }
        }
    }

    private void deleteSelectedSong() {
        int selectedIndex = songList.getSelectedIndex();
        if (selectedIndex != -1) {
            Song selectedSong = songListModel.getElementAt(selectedIndex);
            String songName = selectedSong.getName();
            
            // Delete the song file
            File songFile = new File(songsFolder, songName + ".wav");
            if (songFile.exists() && songFile.delete()) {
                // Remove the song from the list model
                songListModel.removeElementAt(selectedIndex);
                
                // Delete the associated image file if it exists
                File imageFile = new File(imagesFolder, songName + ".png");
                if (imageFile.exists()) {
                    imageFile.delete();
                }
    
                // Stop playing the song if it is currently playing
                if (currentSong != null && currentSong.getName().equals(songName)) {
                    clip.stop();
                    clip.close();
                    isPlaying = false;
                    playPauseButton.setIcon(resizeIcon("play-button.png", 20, 20));
                    progressBar.setValue(0);
                    durationLabel.setText("00:00 / 00:00");
                    currentSong = null;
                    currentIndex = -1;
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una canción para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }
    


    private void addSongs() {
        // Mostrar mensaje inicial para seleccionar una canción
        JOptionPane.showMessageDialog(this, "Seleccione una canción en formato .wav", "Seleccionar Canción", JOptionPane.INFORMATION_MESSAGE);
    
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(true);
        int result = fileChooser.showOpenDialog(this);
    
        if (result == JFileChooser.APPROVE_OPTION) {
            File[] selectedFiles = fileChooser.getSelectedFiles();
            for (File file : selectedFiles) {
                if (file.getName().endsWith(".wav")) {
                    // Mostrar mensaje para seleccionar una portada
                    JOptionPane.showMessageDialog(this, "Ahora seleccione una portada para la canción", "Seleccionar Portada", JOptionPane.INFORMATION_MESSAGE);
    
                    // Seleccionar portada
                    JFileChooser imageChooser = new JFileChooser();
                    imageChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes()));
                    int imageResult = imageChooser.showOpenDialog(this);
    
                    File imageFile = null;
                    if (imageResult == JFileChooser.APPROVE_OPTION) {
                        imageFile = imageChooser.getSelectedFile();
                    }
    
                    // Ventana de diálogo para ingresar información de la canción
                    JTextField nameField = new JTextField(20);
                    JTextField artistField = new JTextField(20);
                    JTextField composerField = new JTextField(20);
                    JTextField genreField = new JTextField(20);
    
                    JPanel inputPanel = new JPanel(new GridLayout(0, 2));
                    inputPanel.add(new JLabel("Nombre:"));
                    inputPanel.add(nameField);
                    inputPanel.add(new JLabel("Artista:"));
                    inputPanel.add(artistField);
                    inputPanel.add(new JLabel("Compositor:"));
                    inputPanel.add(composerField);
                    inputPanel.add(new JLabel("Género:"));
                    inputPanel.add(genreField);
    
                    int option = JOptionPane.showConfirmDialog(this, inputPanel, "Ingresar información de la canción", JOptionPane.OK_CANCEL_OPTION);
    
                    if (option == JOptionPane.OK_OPTION) {
                        String name = nameField.getText();
                        String artist = artistField.getText();
                        String composer = composerField.getText();
                        String genre = genreField.getText();
    
                        File destination = new File(songsFolder, name + ".wav");
                        try {
                            Files.copy(file.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
    
                            // Guardar la imagen
                            if (imageFile != null) {
                                File imageDestination = new File(imagesFolder, name + ".png");
                                Files.copy(imageFile.toPath(), imageDestination.toPath(), StandardCopyOption.REPLACE_EXISTING);
                            }
    
                            // Insertar la canción al principio
                            ImageIcon icon = null;
                            File imageDest = new File(imagesFolder, name + ".png");
                            if (imageDest.exists()) {
                                try {
                                    BufferedImage img = ImageIO.read(imageDest);
                                    icon = new ImageIcon(img.getScaledInstance(50, 50, Image.SCALE_SMOOTH));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            songListModel.add(0, new Song(name, icon)); // Agregar al principio
    
                            // Actualizar la lista de canciones y seleccionarla
                            songList.setSelectedIndex(0);
                            playSong(name);
    
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
    

    private void playSelectedSong() {
        Song selectedSong = songList.getSelectedValue();
        if (selectedSong != null) {
            playSong(selectedSong.getName());
        }
    }

    private void playSong(String songName) {
        try {
            if (clip != null) {
                clip.stop();
                clip.close();
            }
    
            File audioFile = new File(songsFolder, songName + ".wav"); 
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.setMicrosecondPosition(pausedPosition);
            clip.start();
            isPlaying = true;
            playPauseButton.setIcon(resizeIcon("pause-button.png", 20, 20));
            setupProgressBarAndTimer(clip.getMicrosecondLength());
    
            // Actualizar canción actual
            currentSong = getSongByName(songName);
            currentIndex = songListModel.indexOf(currentSong);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    // Cierra el clip y libera recursos
private void closeClip() {
    if (clip != null) {
        clip.stop();
        clip.close();
        clip = null;
    }
}

    private Song getSongByName(String songName) {
    return Collections.list(songListModel.elements()).stream()
            .filter(song -> song.getName().equals(songName))
            .findFirst()
            .orElse(null);
}


    private void togglePlayPause() {
        if (isPlaying) {
            if (clip != null) {
                clip.stop();
                pausedPosition = clip.getMicrosecondPosition(); // Guardar la posición actual
            }
            playPauseButton.setIcon(resizeIcon("play-button.png", 20, 20));
        } else {
            if (clip != null) {
                clip.setMicrosecondPosition(pausedPosition); // Reanudar desde la posición guardada
                clip.start();
            }
            playPauseButton.setIcon(resizeIcon("pause-button.png", 20, 20));
        }
        isPlaying = !isPlaying;
    }

    private void playPreviousSong() {
        if (currentIndex > 0) {
            currentIndex--;
        } else {
            currentIndex = songListModel.getSize() - 1;
        }
        Song previousSong = songListModel.getElementAt(currentIndex);
        playSong(previousSong.getName());
    }

    private void playNextSong() {
        if (currentIndex < songListModel.getSize() - 1) {
            currentIndex++;
        } else {
            currentIndex = 0;
        }
        Song nextSong = songListModel.getElementAt(currentIndex);
        playSong(nextSong.getName());
    }

    private void setupProgressBarAndTimer(long duration) {
        progressBar.setMaximum((int) duration);
        progressBar.setValue(0);

        if (timer != null) {
            timer.cancel();
        }

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (clip != null && isPlaying) {
                    long position = clip.getMicrosecondPosition();
                    progressBar.setValue((int) position);
                    durationLabel.setText(formatTime(position) + " / " + formatTime(duration));
                }
            }
        }, 0, 1000);
    }

    private String removeExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0) {
            return fileName.substring(0, dotIndex);
        }
        return fileName;
    }

    private String formatTime(long microseconds) {
        long seconds = (microseconds / 1000000) % 60;
        long minutes = (microseconds / (1000000 * 60)) % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    private class Song {
        private String name;
        private ImageIcon icon;

        public Song(String name, ImageIcon icon) {
            this.name = name;
            this.icon = icon;
        }

        public String getName() {
            return name;
        }

        public ImageIcon getIcon() {
            return icon;
        }

        @Override
        public String toString() {
            return name;
        }
    }


    public void setCurrentPlayingSong(Song song) {
        this.currentPlayingSong = song;
        songList.repaint(); // Vuelve a pintar la lista para reflejar el cambio
    }


    private class SongListRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            Song song = (Song) value;
            Component component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            JLabel label = (JLabel) component;
    
            // Configurar espaciado entre número y título
            label.setText((index + 1) + ". " + song.getName());
            label.setIcon(song.getIcon()); // Mostrar la imagen
    
            if (isSelected) {
                label.setText("<html><b>" + label.getText() + "</b></html>"); // Resaltar el texto
                label.setBackground(new Color(173, 216, 230)); // Color para la canción seleccionada (color azul claro)
            } else {
                label.setBackground(Color.WHITE); // Color de fondo para elementos no seleccionados
            }
    
            // Establecer el color de primer plano (texto) y el color de fondo
            if (isSelected) {
                label.setForeground(Color.BLACK); // Color del texto para la canción seleccionada
            } else {
                label.setForeground(Color.GRAY); // Color del texto para canciones no seleccionadas
            }
    
            return label;
        }
    }
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Login().setVisible(true);
            }
        });
    }
    
}
