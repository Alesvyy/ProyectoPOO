// package cr.ac.ucenfotec.util;

// import java.io.FileInputStream;
// import java.util.Properties;

// public class Utils {

//     public static String[] getPropiedades() throws Exception{

//         String[] propiedades = new String[6];
//         Properties propiedadesAccesoBD = new Properties();
//         String ruta = "src\\cr\\ac\\ucenfotec\\bd.properties";

//         try {
//             propiedadesAccesoBD.load(new FileInputStream(ruta));
//             propiedades[0] = propiedadesAccesoBD.getProperty("driver");
//             propiedades[1] = propiedadesAccesoBD.getProperty("server");
//             propiedades[2] = propiedadesAccesoBD.getProperty("dataBase");
//             propiedades[3] = propiedadesAccesoBD.getProperty("user");
//             propiedades[4] = propiedadesAccesoBD.getProperty("password");
//             return propiedades;
//         } catch(Exception e){
//             throw e;
//         }

//     }

// }