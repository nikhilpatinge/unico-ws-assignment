package com.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the INTEGER_TABLE database table.
 * 
 */
@Entity
@Table(name="INTEGER_TABLE")
public class IntegerTable implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id  
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="INT_SEQ_GEN")
	@SequenceGenerator(name="INT_SEQ_GEN",sequenceName="INT_SEQ_GEN",allocationSize=500)
	private long id;

	private int integers;

    public IntegerTable() {
    }

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getIntegers() {
		return this.integers;
	}

	public void setIntegers(int integers) {
		this.integers = integers;
	}

}