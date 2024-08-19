// package cr.ac.ucenfotec.bl.logic;

// import cr.ac.ucenfotec.bl.entities.compositor.Compositor;
// import cr.ac.ucenfotec.bl.entities.compositor.ICompositorDAO;
// import cr.ac.ucenfotec.dao.DAOFactory;

// public class CompositorGestor {

//     private DAOFactory factory;
//     private ICompositorDAO daoCompositor;

//     public CompositorGestor() {
//         this.factory = DAOFactory.getDAOFactory(1);
//         this.daoCompositor = factory.getCompositorDAO();
//     }

//     public String agregarCompositor(String nombreCompositorNuevo) throws Exception {
//         Compositor nuevoCompositor = new Compositor(nombreCompositorNuevo);
//         return daoCompositor.registrarCompositor(nuevoCompositor);
//     }

//     public Compositor buscarCompositor(String nombreCompositorBuscado) throws Exception {
//         return daoCompositor.buscarCompositor(nombreCompositorBuscado);
//     }

//     public String asignarCompositorAArtista(String nombreCompositor, String nombreArtista) throws Exception {
//         return daoCompositor.asignarCompositorAArtista(nombreCompositor, nombreArtista);
//     }
// }
