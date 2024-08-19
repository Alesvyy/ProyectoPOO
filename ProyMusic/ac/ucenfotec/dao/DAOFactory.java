// package cr.ac.ucenfotec.dao;

// import cr.ac.ucenfotec.bl.entities.artista.IArtistaDAO;
// import cr.ac.ucenfotec.bl.entities.compositor.ICompositorDAO;
// import cr.ac.ucenfotec.bl.entities.genero.IGeneroDAO;
// import cr.ac.ucenfotec.bl.entities.cancion.ICancionDAO;

// public abstract class DAOFactory {

//     public static final int mySQL = 1;

//     public static DAOFactory getDAOFactory(int seleccion){
//         switch(seleccion){
//             case mySQL:
//                 return new MySQLDAOFactory();
//             default:
//                 return null;
//         }
//     }

//     public abstract IArtistaDAO getArtistaDAO();
//     public abstract ICompositorDAO getCompositorDAO();
//     public abstract IGeneroDAO getGeneroDAO();
//     public abstract ICancionDAO getCancionDAO();
// }
