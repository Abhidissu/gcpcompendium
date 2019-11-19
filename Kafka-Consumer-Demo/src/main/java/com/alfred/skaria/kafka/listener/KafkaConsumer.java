package com.alfred.skaria.kafka.listener;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.StringTokenizer;

import org.springframework.boot.SpringApplication;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.alfred.skaria.kafka.KafkaConsumerDemoApplication;
//import com.alfred.skaria.kafka.model.User;

import java.util.ArrayList;
// Added for Postgress DB
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;


@Service


public class KafkaConsumer {

public void EmployeeDaoImpl(NamedParameterJdbcTemplate template) {  
        this.template = template;  
}  
    NamedParameterJdbcTemplate template;  

	@KafkaListener(topics="MyFirstTopic" , groupId = "group_id")
	public void consume(String message) {
		String[] myStringArray = new String[9];
		StringTokenizer tokenizer = new StringTokenizer(message, ",");
		int i =0;
		while (tokenizer.hasMoreTokens()) {
			myStringArray[i] = String.valueOf(tokenizer.nextToken());
            i++;
        }
		int order_number = Integer.parseInt(myStringArray[0]);
		String sr_1=myStringArray[1];String sr_2=myStringArray[2];String sr_3=myStringArray[3];String sr_4=myStringArray[4];String sr_5=myStringArray[5];String sr_6=myStringArray[6];String sr_7=myStringArray[7];String sr_8=myStringArray[8];
		for(int k=0;k<9;k++) 
			System.out.println(myStringArray[k]);
		
		System.out.println("Cosumed msg_1: "+message);
		  Connection c = null;
	      //Statement stmt = null;
	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager.getConnection("jdbc:postgresql://35.228.147.37:5432/postgres","postgres", "welcome1");
	         c.setAutoCommit(false);
	         //String sql = "INSERT INTO public.order_header (order_id,order_number,customer_name,billing_address,shipping_address,order_status,order_date,order_type,reference_order) VALUES(11,'ord1','john','12, almhult','12, almhult','booked','2019-10-23 10:18:55.695','standard',NULL)";
	         //String sql = "INSERT INTO public.order_header (order_id,order_number,customer_name,billing_address,shipping_address,order_status,order_date,order_type,reference_order) VALUES(order_id,sr_1,sr_2,sr_3,sr_4,sr_5,sr_6,sr_7,sr_8)";
	         String sql = "INSERT INTO public.order_header (order_id,order_number,customer_name,billing_address,shipping_address,order_status,order_date,order_type,reference_order) VALUES(?,?,?,?,?,?,?,?,?)";
	         PreparedStatement st = c.prepareStatement(sql);
	         //st.setString(0,order_number);
	         System.out.println("entr");
	         st.setString(1,myStringArray[0]);
	         System.out.println(myStringArray[0]);
	         st.setString(2,myStringArray[1]);
	         System.out.println(myStringArray[1]);
	         st.setString(3,myStringArray[2]);
	         System.out.println(myStringArray[2]);
	         st.setString(4,myStringArray[3]);
	         System.out.println(myStringArray[3]);
	         st.setString(5,myStringArray[4]);
	         System.out.println(myStringArray[4]);
	         st.setString(6,myStringArray[5]);
	         System.out.println(myStringArray[5]);
	         st.setString(7,myStringArray[6]);
	         System.out.println(myStringArray[6]);
	         st.setString(8,myStringArray[7]);
	         System.out.println(myStringArray[7]);
	         st.setString(9,myStringArray[8]);
	         System.out.println(myStringArray[8]);
	         System.out.println("Opened database successfully_1");
	        // stmt = c.createStatement();
	         st.executeUpdate(sql);
	         st.close();
	         c.commit();
	         c.close();
	      } catch (Exception e) {
	         e.printStackTrace();
	         System.err.println(e.getClass().getName()+": "+e.getMessage());
	         System.exit(0);
	      }
	      System.out.println("Opened database successfully");
		
		 //final String sql = "insert into employee(employeeId, employeeName , employeeAddress,employeeEmail) values(:employeeId,:employeeName,:employeeEmail,:employeeAddress)";
//		final String sql = "insert into order_header(order_id,order_number,customer_name,billing_address,shipping_address,order_status,order_date,order_type,reference_order values(:order_id,:order_number,:customer_name,:billing_address,:shipping_address,:order_status,:order_date,:order_type,:reference_order)";
//	        KeyHolder holder = new GeneratedKeyHolder();
//	        SqlParameterSource param = new MapSqlParameterSource()
//	        		.addValue("order_id", Integer.parseInt(myStringArray[0]))
//					.addValue("order_number", myStringArray[1])
//					.addValue("customer_name", myStringArray[2])
//					.addValue("billing_address", myStringArray[3])
//					.addValue("shipping_address", myStringArray[4])
//					.addValue("order_status", myStringArray[5])
//					.addValue("order_date", myStringArray[6])
//					.addValue("order_type", myStringArray[7])
//					.addValue("reference_order", myStringArray[8]); 
//	        template.update(sql,param, holder);
//		System.out.println("Cosumed msg: "+message);
	}
	
//	public static void main(String[] args) {
//		KafkaConsumer test = new KafkaConsumer();
//		test.consume("1001,4321,'John','H.No 112 New Delhi','111-DelhiHatt','OPEN','2019-06-22 19:10:25-07','TakeAway','1233wwqwssde'");
//	}
}
