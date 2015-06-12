package hiberDAO;

import java.util.List;

import entities.Employees;

public interface InterfaceDAO {

	public abstract List<Employees> consultarTodo();

	public abstract void modificar(Employees e);

}