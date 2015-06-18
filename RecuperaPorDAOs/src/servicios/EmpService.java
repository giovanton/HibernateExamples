package servicios;

import dAOS.Recuperable;

public class EmpService {

	Recuperable recuperable;
	
	public void setRecuperable(Recuperable recuperable) {
		this.recuperable = recuperable;
	}
	
	public Object leerEmpleado(int i) {
		Object o = null;
		o = recuperable.leerEmpleado(i);
		return o;
	}
}
