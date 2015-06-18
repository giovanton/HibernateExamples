package dAOS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entidades.Empleado;

public class EmpJDBCDAO implements Recuperable {

	@Override
	public Empleado leerEmpleado(int n) {
		
		Connection con = null;
		Empleado emp = null;
		ResultSet rs = null;
		PreparedStatement pstm = null;
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "HR", "hr");
			con.setAutoCommit(false);
			pstm = con.prepareStatement("Select * from employees where employee_id = ?");
			pstm.setInt(1, n);
			rs = pstm.executeQuery();
			
			//emp = rs;
			rs.next();
			emp = new Empleado(rs.getInt(1), rs.getString(2),
					rs.getString(3), rs.getString(4),
					rs.getString(5), rs.getDate(6), rs.getString(7),
					rs.getFloat(8), rs.getString(9), rs.getInt(10),
					rs.getInt(11));
			pstm.close();
			con.close();
	
		} catch (SQLException e) {
			System.out.println("Fallo al recuperar registro");
				e.printStackTrace();
		}
		
		return emp;
	}

}
