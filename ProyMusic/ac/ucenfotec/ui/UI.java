// package cr.ac.ucenfotec.ui;

// import cr.ac.ucenfotec.tl.Controller;
// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStreamReader;

// public class UI {

//     private static BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));

//     public void mostrarMenu() {
//         int seleccion = -1;
//         do {
//             try {
//                 System.out.println("\nAplicación - Gestión de Música\n" +
//                                    "\tSeleccione una opción:\n" +
//                                    "\t\t1 - Agregar una nueva canción\n" +
//                                    "\t\t2 - Agregar un nuevo género\n" +
//                                    "\t\t3 - Agregar un nuevo artista\n" +
//                                    "\t\t4 - Agregar un nuevo compositor\n" +
//                                    "\t\t5 - Ver información de una canción\n" +
//                                    "\t\t6 - Ver todas las canciones por género\n" +
//                                    "\t\t7 - Ver todas las canciones por artista\n" +
//                                    "\t\t8 - Ver todas las canciones por compositor\n" +
//                                    "\t\t0 - Salir de la aplicación\n");
//                 seleccion = Integer.parseInt(entrada.readLine());
//                 Controller.procesarSeleccion(seleccion);
//             } catch (Exception e) {
//                 System.out.println("Lo sentimos, sucedió un error inesperado.\n");
//                 System.out.println(e.getMessage());
//             }
//         } while (seleccion != 0);
//     }

//     public String leerTexto() throws IOException {
//         return entrada.readLine();
//     }
// }
