package com.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the GCD_TABLE database table.
 * 
 */
@Entity
@Table(name="GCD_TABLE")
public class GcdTable implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id  
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GCD_SEQ_GEN")
	@SequenceGenerator(name="GCD_SEQ_GEN",sequenceName="GCD_SEQ_GEN",allocationSize=500)
	private long id;

	private int gcd;

    public GcdTable() {
    }

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getGcd() {
		return this.gcd;
	}

	public void setGcd(int gcd) {
		this.gcd = gcd;
	}

}