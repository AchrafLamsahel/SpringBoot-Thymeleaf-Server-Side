package ma.cigma.springsecurity.domaine;

import lombok.Data;

@Data
public class EmpVo {
	private Long id;
	private String name;
	private Double salary;
	private String fonction;
	public EmpVo() {
		super();
	}
	public EmpVo(Long id, String name, Double salary, String fonction) {
		this(name,salary,fonction);
		this.id = id;
	}
	
	public EmpVo(String name, Double salary, String fonction) {
		super();
		this.name = name;
		this.salary = salary;
		this.fonction = fonction;
	}
}