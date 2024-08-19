// package cr.ac.ucenfotec.dl;

// import cr.ac.ucenfotec.util.Utils;

// public class Conector {

//     private static AccesoBD conexionBD = null;

//     public static AccesoBD getConector() throws Exception {
//         String[] infoAccesoBD = Utils.getPropiedades();
//         String direccion = infoAccesoBD[0] + "//" + infoAccesoBD[1] + "/" + infoAccesoBD[2];
//         String usuario = infoAccesoBD[3];
//         String contrasenia = infoAccesoBD[4];
//         if(conexionBD == null){
//             conexionBD = new AccesoBD(direccion, usuario, contrasenia);
//         }
//         return conexionBD;
//     }

// }