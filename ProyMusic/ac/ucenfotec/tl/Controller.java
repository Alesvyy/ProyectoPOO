// package cr.ac.ucenfotec.tl;

// import cr.ac.ucenfotec.bl.logic.ArtistaGestor;
// import cr.ac.ucenfotec.bl.logic.CompositorGestor;
// import cr.ac.ucenfotec.bl.logic.GeneroGestor;
// import cr.ac.ucenfotec.bl.logic.CancionGestor;
// import cr.ac.ucenfotec.ui.UI;

// public class Controller {

//     private static UI interfaz;
//     private static ArtistaGestor gestoraArtistaGestor;
//     private static CompositorGestor gestorCompositorGestor;
//     private static GeneroGestor gestorGeneroGestor;
//     private static CancionGestor gestorCancionGestor;

//     public Controller() {
//         interfaz = new UI();
//         gestoraArtistaGestor = new ArtistaGestor();
//         gestorCompositorGestor = new CompositorGestor();
//         gestorGeneroGestor = new GeneroGestor();
//         gestorCancionGestor = new CancionGestor();
//     }

//     public UI getInterfaz() {
//         return Controller.interfaz;
//     }

//     public static void procesarSeleccion(int seleccion) throws Exception {
//         switch(seleccion) {
//             case 1:
//                 agregarCancion();
//                 break;
//             case 2:
//                 agregarGenero();
//                 break;
//             case 3:
//                 agregarArtista();
//                 break;
//             case 4:
//                 agregarCompositor();
//                 break;
//             case 5:
//                 imprimirInfoCancion();
//                 break;
//             case 6:
//                 imprimirCancionesPorGenero();
//                 break;
//             case 7:
//                 imprimirCancionesPorArtista();
//                 break;
//             case 8:
//                 imprimirCancionesPorCompositor();
//                 break;
//             case 0:
//                 System.out.println("¡Gracias por utilizar la aplicación de música! :)");
//                 break;
//             default:
//                 System.out.println("La opción digitada es inválida.\n");
//                 break;
//         }
//     }

//     public static void agregarCancion() throws Exception {
//         System.out.println("\n- Registro de una nueva canción -");
//         System.out.println("Por favor digite el título de la canción:");
//         String tituloCancion = interfaz.leerTexto();
//         System.out.println("Por favor digite el nombre del género:");
//         String nombreGenero = interfaz.leerTexto();
//         System.out.println("Por favor digite el nombre del artista:");
//         String nombreArtista = interfaz.leerTexto();
//         System.out.println("Por favor digite el nombre del compositor:");
//         String nombreCompositor = interfaz.leerTexto();

//         String mensaje = gestorCancionGestor.agregarCancion(tituloCancion, nombreGenero, nombreArtista, nombreCompositor);
//         System.out.println(mensaje);
//     }

//     public static void agregarGenero() throws Exception {
//         System.out.println("\n- Registro de un nuevo género musical -");
//         System.out.println("Por favor digite el nombre del género:");
//         String nombreGeneroNuevo = interfaz.leerTexto();
//         if (gestorGeneroGestor.buscarGenero(nombreGeneroNuevo) == null) {
//             String mensaje = gestorGeneroGestor.agregarGenero(nombreGeneroNuevo);
//             System.out.println(mensaje);
//         } else {
//             System.out.println("Ya existe un género registrado con ese nombre.");
//         }
//     }

//     public static void agregarArtista() throws Exception {
//         System.out.println("\n- Registro de un nuevo artista -");
//         System.out.println("Por favor digite el nombre del artista:");
//         String nombreArtistaNuevo = interfaz.leerTexto();
//         if (gestoraArtistaGestor.buscarArtista(nombreArtistaNuevo) == null) {
//             String mensaje = gestoraArtistaGestor.agregarArtista(nombreArtistaNuevo);
//             System.out.println(mensaje);
//         } else {
//             System.out.println("Ya existe un artista registrado con ese nombre.");
//         }
//     }

//     public static void agregarCompositor() throws Exception {
//         System.out.println("\n- Registro de un nuevo compositor -");
//         System.out.println("Por favor digite el nombre del compositor:");
//         String nombreCompositorNuevo = interfaz.leerTexto();
//         if (gestorCompositorGestor.buscarCompositor(nombreCompositorNuevo) == null) {
//             String mensaje = gestorCompositorGestor.agregarCompositor(nombreCompositorNuevo);
//             System.out.println(mensaje);
//         } else {
//             System.out.println("Ya existe un compositor registrado con ese nombre.");
//         }
//     }

//     public static void imprimirInfoCancion() throws Exception {
//         System.out.println("\n- Información de una canción -");
//         System.out.println("Por favor digite el título de la canción:");
//         String tituloCancion = interfaz.leerTexto();
//         String infoCancion = gestorCancionGestor.obtenerInfoCancion(tituloCancion);
//         if (infoCancion != null) {
//             System.out.println(infoCancion);
//         } else {
//             System.out.println("La canción no existe.");
//         }
//     }

//     public static void imprimirCancionesPorGenero() throws Exception {
//         System.out.println("\n- Canciones por género -");
//         System.out.println("Por favor digite el nombre del género:");
//         String nombreGenero = interfaz.leerTexto();
//         gestorCancionGestor.imprimirCancionesPorGenero(nombreGenero);
//     }

//     public static void imprimirCancionesPorArtista() throws Exception {
//         System.out.println("\n- Canciones por artista -");
//         System.out.println("Por favor digite el nombre del artista:");
//         String nombreArtista = interfaz.leerTexto();
//         gestorCancionGestor.imprimirCancionesPorArtista(nombreArtista);
//     }

//     public static void imprimirCancionesPorCompositor() throws Exception {
//         System.out.println("\n- Canciones por compositor -");
//         System.out.println("Por favor digite el nombre del compositor:");
//         String nombreCompositor = interfaz.leerTexto();
//         gestorCancionGestor.imprimirCancionesPorCompositor(nombreCompositor);
//     }
// }
