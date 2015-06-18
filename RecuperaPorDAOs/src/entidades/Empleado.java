package entidades;

import java.sql.Date;

public class Empleado {
	private int employee_id;
	private String first_name;
	private String last_name;
	private String email;
	private String phone_number;
	private Date hire_date;
	private String job_id;
	private float salary;
	private String commission_pct;
	private int manager_id;
	private int department_id;

	public Empleado(int e_id, String f_name, String l_name, String email,
			String phone, Date h_date, String j_id, float salary, String c_pct,
			int m_id, int d_id) {
		this.employee_id = e_id;
		this.first_name = f_name;
		this.last_name = l_name;
		this.email = email;
		this.phone_number = phone;
		this.hire_date = h_date;
		this.job_id = j_id;
		this.salary = salary;
		this.commission_pct = c_pct;
		this.manager_id = m_id;
		this.department_id = d_id;
	}

	public int getEmployee_id() {
		return employee_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public Date getHire_date() {
		return hire_date;
	}

	public int getDepartment_id() {
		return department_id;
	}

	public String getEmail() {
		return email;
	}

	public String getJob_id() {
		return job_id;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public float getSalary() {
		return salary;
	}

	public String getCommission_pct() {
		return commission_pct;
	}

	public int getManager_id() {
		return manager_id;
	}

}
