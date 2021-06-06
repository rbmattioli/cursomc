package br.com.mattioli.cursoms.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Estado implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer ig;
	private String nome;
    @JsonBackReference
	@OneToMany(mappedBy = "estado")
	private List<Cidade> cidades = new ArrayList<Cidade>();

	public Estado() {

	}

	public Estado(Integer ig, String nome) {
		super();
		this.ig = ig;
		this.nome = nome;
	}

	public Integer getIg() {
		return ig;
	}

	public void setIg(Integer ig) {
		this.ig = ig;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ig == null) ? 0 : ig.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Estado other = (Estado) obj;
		if (ig == null) {
			if (other.ig != null)
				return false;
		} else if (!ig.equals(other.ig))
			return false;
		return true;
	}
	
	

}
