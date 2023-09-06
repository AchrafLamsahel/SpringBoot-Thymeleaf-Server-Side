package ma.cigma.springsecurity.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Emp {
	@Id
	@GeneratedValue
	private Long id;
	private String name2;
	private Double salary;
	private String fonction;

	public Emp(String name, Double salary, String fonction) {
		super();
		this.name2 = name;
		this.salary = salary;
		this.fonction = fonction;
	}
}