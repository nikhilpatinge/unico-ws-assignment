package com.bean;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.codehaus.jettison.json.JSONArray;

import com.model.GcdSum;
import com.model.GcdTable;
import com.model.IntegerTable;

/**
 * Session Bean implementation class BIBean
 */
@Stateless
@LocalBean
public class BIBean {

	@PersistenceUnit
    EntityManagerFactory emf;
    
    @Resource(mappedName="jms/opsQueueConnectionFactory") 
	private ConnectionFactory connectionFactory;
	
	@Resource(mappedName="jms/opsQueue")
	private Queue queue;

    
    /**
     * Default constructor. 
     */
    public BIBean() {
        // TODO Auto-generated constructor stub
    }
    
    //Rest-1: Method for inserting in Queue and DB
    public int pushInQueue(int i1,int i2){
    	int status=200;
    	//System.out.println("i1="+i1+" | i2:"+i2);
    	//Pushing into the queue
    	try{
			Connection connection=connectionFactory.createConnection();
			Session session=connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			MessageProducer producer=session.createProducer(queue);
			//System.out.println("Getting the messages ready..");
			
			List<TextMessage> txtMessage=new ArrayList<TextMessage>();
			txtMessage.add(session.createTextMessage(i1+""));
			txtMessage.add(session.createTextMessage(i2+""));
			
			//System.out.println("Sending messages to JMS..");
			for(TextMessage msgUnit: txtMessage)
			producer.send(msgUnit);
			
			//System.out.println("Messages sent..");
			session.close();
			connection.close();
			}catch(JMSException jmsEx ){
				status=500;
				jmsEx.printStackTrace();
			}
    	
    	//Insert into DB
    	try{
    		IntegerTable intEntry1=new IntegerTable();
    		intEntry1.setIntegers(i1);
    		insertInTable(intEntry1);
    	
    		IntegerTable intEntry2=new IntegerTable();
    		intEntry2.setIntegers(i2);
    		insertInTable(intEntry2);
    	
    	}catch(Exception e){
    		status=500;
    		e.printStackTrace();
    	}
    	
    	return status;
    }
    
    //Method for persisting in DB
    public void insertInTable(Object persistenceObj){
		EntityManager em=emf.createEntityManager();
		em.persist(persistenceObj);
    }
    
    //Rest-2: Method to get the JSON list of all integers entered till now from DB in order of insertion 
    public String getList(){
    	
    	List<IntegerTable> listObj=emf.createEntityManager().createQuery("select a from IntegerTable a").getResultList();
    	List<Integer> listInt=new ArrayList<Integer>();
    	
    	for (IntegerTable unit:listObj)
    		listInt.add(unit.getIntegers());

    	JSONArray jsonResponse=new JSONArray(listInt);
    	return (jsonResponse !=null?jsonResponse.toString():"");
    }
    
    //Soap-1: Calculating the GCD of 2 numbers
    public int getGCD(){
    	
    	//1. consume the 1st two integers from the queue-head
    	int i1,i2,gcd=0;
    	try{
    	Connection connection=connectionFactory.createConnection();
		Session session=connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		MessageConsumer consumer=session.createConsumer(queue);
		connection.start();
		Message intMessage1=consumer.receive();
		Message intMessage2=consumer.receive();
		i1=Integer.parseInt(((TextMessage)intMessage1).getText());
		i2=Integer.parseInt(((TextMessage)intMessage2).getText());
		session.close();
		connection.close();
		
		
    	//2. Calculate their GCD
    	gcd=calcGCD(i1,i2);
    	System.out.println("The GCD of "+ i1 + " and " + i2 + " is " + gcd);

    	//3. push this GCD in the DB
    	GcdTable gcdObj=new GcdTable();
    	gcdObj.setGcd(gcd);
    	insertInTable(gcdObj);
		
		}catch(JMSException jmsEx ){
			jmsEx.printStackTrace();
		}catch(Exception ex){
			ex.printStackTrace();
		}    	
		
    	//4. update the GCDsum in the table with this new Gcd
    	updateInDb(gcd);
    	
    	return gcd;
    }
    
    //Method to calculate GCD of 2 Integers
    public int calcGCD(int x,int y){
		return(y==0?x:calcGCD(y, x%y));
	}
    
    //Method to merge in table 
    public void updateInDb(int newGCD){
    	EntityManager em=emf.createEntityManager();
    	GcdSum gcdSum=(GcdSum)em.createQuery("select a from GcdSum a").getResultList().get(0);
    	int oldSum=gcdSum.getGcdSum();
    	gcdSum.setGcdSum(oldSum+newGCD);
    	em.merge(gcdSum);
    }
    
    
  //Soap-2: displaying the GCD list from DB
    public List<Integer> gcdList(){
    	List<GcdTable> listObj=emf.createEntityManager().createQuery("select a from GcdTable a").getResultList();
    	List<Integer> listGCD=new ArrayList<Integer>();
    	
    	for (GcdTable unit:listObj)
    		listGCD.add(unit.getGcd());   	
    	
    	return listGCD;
    }
    
    
  //Soap-3: displaying the GCD Sum from DB
    public int gcdSum(){
    	GcdSum gcdSum=(GcdSum)emf.createEntityManager().createQuery("select a from GcdSum a").getResultList().get(0);
    	return gcdSum.getGcdSum();
    }
    
    //Method for testing - Queue Browser
	public String browseQueue(){
		
		Enumeration browser;
    	List<String> messageList=new ArrayList<String>();
    	try{
    	Connection connection=connectionFactory.createConnection();
    	Session session=connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    	QueueBrowser queueBrowser=session.createBrowser(queue);
    	browser=queueBrowser.getEnumeration();
    	while(browser.hasMoreElements())
    		messageList.add(((TextMessage)browser.nextElement()).getText());
    	}catch(JMSException jmsEx){
    		jmsEx.printStackTrace();
    	}
    	 JSONArray jsonResponse=new JSONArray(messageList);
    	 return (jsonResponse !=null?jsonResponse.toString():"Error in JSON creation");
	}
    
}
