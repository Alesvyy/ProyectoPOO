// package cr.ac.ucenfotec.bl.logic;

// import cr.ac.ucenfotec.bl.entities.artista.Artista;
// import cr.ac.ucenfotec.bl.entities.artista.IArtistaDAO;
// import cr.ac.ucenfotec.dao.DAOFactory;

// public class ArtistaGestor {

//     private DAOFactory factory;
//     private IArtistaDAO daoArtista;

//     public ArtistaGestor() {
//         this.factory = DAOFactory.getDAOFactory(1);
//         this.daoArtista = factory.getArtistaDAO();
//     }

//     public String agregarArtista(String nombreArtistaNuevo) throws Exception {
//         Artista nuevoArtista = new Artista(nombreArtistaNuevo);
//         return daoArtista.registrarArtista(nuevoArtista);
//     }

//     public Artista buscarArtista(String nombreArtistaBuscado) throws Exception {
//         return daoArtista.buscarArtista(nombreArtistaBuscado);
//     }
// }
