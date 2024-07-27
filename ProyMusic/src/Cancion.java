public class Cancion {
    private String nombre;
    private Artista artista;
    private String rutaArchivo;

    public Cancion(String nombre, Artista artista, String rutaArchivo) {
        this.nombre = nombre;
        this.artista = artista;
        this.rutaArchivo = rutaArchivo;
    }

    public String getNombre() {
        return nombre;
    }

    public Artista getArtista() {
        return artista;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    @Override
    public String toString() {
        return nombre + " - " + artista.getNombre();
    }
}
