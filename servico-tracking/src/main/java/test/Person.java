package test;

import javax.persistence.*;

@Entity
@Cacheable
@NamedQuery(name = "byNameLike", query = "SELECT p FROM Person p WHERE name like ?1",
	hints = { @QueryHint(name = "org.hibernate.cacheable", value = "true"), @QueryHint(name = "org.hibernate.cacheRegion", value = "Query") })
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	public Person() {
	}

	public Person(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return String.format("id=%s, name=%s", id, name);
	}
}
