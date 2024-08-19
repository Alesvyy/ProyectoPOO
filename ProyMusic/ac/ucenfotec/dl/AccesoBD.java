// package cr.ac.ucenfotec.dl;

// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.sql.Statement;

// public class AccesoBD {

//     private Connection conexion = null;
//     private Statement statement = null;
//     private PreparedStatement preparedStatement = null;

//     public AccesoBD(String direccion, String usuario, String contrasenia) throws SQLException, ClassNotFoundException{
//         Class.forName("com.mysql.cj.jdbc.Driver");
//         conexion = DriverManager.getConnection(direccion, usuario, contrasenia);
//     }

//     public void ejecutarStatementSQL(String query) throws SQLException{
//         statement = conexion.createStatement();
//         statement.executeUpdate(query);
//     }

//     public void ejecutarStatementSQL(String query, String valor1, String valor2, String valor3) throws SQLException{
//         preparedStatement = conexion.prepareStatement(query);
//         preparedStatement.setString(1, valor2);
//         preparedStatement.setString(2, valor3);
//         preparedStatement.setString(3, valor1);
//         preparedStatement.executeUpdate();
//     }

//     public ResultSet ejecutarQuerySQL(String query) throws SQLException{
//         ResultSet resultado = null;
//         statement = conexion.createStatement();
//         resultado = statement.executeQuery(query);
//         return resultado;
//     }

//     public ResultSet ejecutarQuerySQL(String query, String valor) throws SQLException{
//         ResultSet resultado = null;
//         preparedStatement = conexion.prepareStatement(query);
//         preparedStatement.setString(1, valor);
//         resultado = preparedStatement.executeQuery();
//         return resultado;
//     }

//     public ResultSet ejecutarQuerySQL(String query, String valor1, String valor2) throws SQLException{
//         ResultSet resultado = null;
//         preparedStatement = conexion.prepareStatement(query);
//         preparedStatement.setString(1, valor1);
//         preparedStatement.setString(2, valor2);
//         resultado = preparedStatement.executeQuery();
//         return resultado;
//     }

//     public ResultSet ejecutarQuerySQL(String query, short valor1, String valor2) throws SQLException{
//         ResultSet resultado = null;
//         preparedStatement = conexion.prepareStatement(query);
//         preparedStatement.setShort(1, valor1);
//         preparedStatement.setString(2, valor2);
//         resultado = preparedStatement.executeQuery();
//         return resultado;
//     }

//     public ResultSet ejecutarQuerySQL(String query, String valor1, String valor2, String valor3) throws SQLException{
//         ResultSet resultado = null;
//         preparedStatement = conexion.prepareStatement(query);
//         preparedStatement.setString(1, valor1);
//         preparedStatement.setString(2, valor2);
//         preparedStatement.setString(3, valor3);
//         resultado = preparedStatement.executeQuery();
//         return resultado;
//     }

// }