// package cr.ac.ucenfotec.bl.logic;

// import cr.ac.ucenfotec.bl.entities.genero.Genero;
// import cr.ac.ucenfotec.bl.entities.genero.IGeneroDAO;
// import cr.ac.ucenfotec.dao.DAOFactory;

// public class GeneroGestor {

//     private DAOFactory factory;
//     private IGeneroDAO daoGenero;

//     public GeneroGestor() {
//         this.factory = DAOFactory.getDAOFactory(1);
//         this.daoGenero = factory.getGeneroDAO();
//     }

//     public String agregarGenero(String nombreGeneroNuevo) throws Exception {
//         Genero nuevoGenero = new Genero(nombreGeneroNuevo);
//         return daoGenero.registrarGenero(nuevoGenero);
//     }

//     public Genero buscarGenero(String nombreGeneroBuscado) throws Exception {
//         return daoGenero.buscarGenero(nombreGeneroBuscado);
//     }
// }
