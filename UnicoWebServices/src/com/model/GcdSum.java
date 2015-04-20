package com.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the GCD_SUM database table.
 * 
 */
@Entity
@Table(name="GCD_SUM")
public class GcdSum implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="GCD_SUM")
	private int gcdSum;

    public GcdSum() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGcdSum() {
		return this.gcdSum;
	}

	public void setGcdSum(int gcdSum) {
		this.gcdSum = gcdSum;
	}

}