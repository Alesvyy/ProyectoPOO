// package cr.ac.ucenfotec.bl.logic;

// import java.util.ArrayList;
// import cr.ac.ucenfotec.bl.entities.cancion.Cancion;
// import cr.ac.ucenfotec.bl.entities.cancion.ICancionDAO;
// import cr.ac.ucenfotec.dao.DAOFactory;

// public class CancionGestor {

//     private DAOFactory factory;
//     private ICancionDAO daoCancion;

//     public CancionGestor() {
//         this.factory = DAOFactory.getDAOFactory(1);  // Suponiendo que MySQL es el factory con ID 1
//         this.daoCancion = factory.getCancionDAO();
//     }

//     public String agregarCancion(String titulo, String duracion, String artista, String genero, String compositor) throws Exception {
//         Cancion nuevaCancion = new Cancion(titulo, duracion, artista, genero, compositor);
//         return daoCancion.registrarCancion(nuevaCancion);
//     }

//     public Cancion buscarCancion(String tituloCancionBuscada) throws Exception {
//         Cancion cancionBuscada = daoCancion.buscarCancion(tituloCancionBuscada);
//         return cancionBuscada;
//     }

//     public ArrayList<Cancion> listarCanciones() throws Exception {
//         return daoCancion.listarCanciones();
//     }
// }
