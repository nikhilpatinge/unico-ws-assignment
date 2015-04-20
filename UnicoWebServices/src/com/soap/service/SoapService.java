package com.soap.service;

import javax.ejb.EJB;
import javax.jws.WebService;
import java.util.List;

import com.bean.BIBean;

@WebService
public class SoapService {

	@EJB
    BIBean bean;
	
	//1. Get GCD of the first 2 members of the queue
	public int getGCD(){
		System.out.println("soap:gcd called");
		int gcd=bean.getGCD();
		System.out.println("soap:gcd returned");
		return gcd;
	}
	
	//2. Get list of all the GCD's calculated till now 
	public List<Integer> gcdList(){
		System.out.println("soap:gcd list called");
		List<Integer> gcdList=bean.gcdList();
		System.out.println("soap:gcd list called");
		return gcdList;
		
	}

	//3. Get sum of all GCD's calculated till now
	 public int gcdSum(){
		System.out.println("soap:gcd sum called");
		int gcdSum=bean.gcdSum();
		System.out.println("soap:gcd sum called");
		return gcdSum;
	 }
}
