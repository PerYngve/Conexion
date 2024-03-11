
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

asdasd
public class conexion {
	
	
	private static String driver="com.mysql.jdbc.Driver";
	private static String usuario="root";
	private static String password="";
	private static String url="jdbc:mysql://localhost:3306/alumnos";
	
	static {
		try {
			Class.forName(driver);
			System.out.println("conexion con mysql" );
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("error en el driver" );
		}
		
	}
	
				Connection con=null;
		public Connection getConnection() {
	
			
			
			try {
			
				 con=DriverManager.getConnection(url, usuario, password);
				 System.out.println("conectado a mysql" );
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("error de conexion");
			
			}
			return con;
			
			
		}
		
		
		
		public Connection close() {
		

			try {
			
				 con.close();
				 System.out.println("se cerro la conexion exitosamente" );
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("error de conexion");
			
			}
			return con;
		}
		
		
		
		
		public static void main(String[] args) {
			
		
		conexion db=new conexion();
		db.getConnection();
		//db.close();
	
	
}  
		
		
		
		
		
		
		
		
		
		
}