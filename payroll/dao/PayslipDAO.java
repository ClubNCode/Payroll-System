package com.raj.payroll.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.wipro.payroll.bean.PayslipBean;
import com.wipro.payroll.util.DBUtil;

public class PayslipDAO {
	
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	String query = "";
	
      
	
	public PayslipDAO(){
		
		con = DBUtil.getDBConnection();
		
	}
	
	public String createPayslip(PayslipBean payslipBean){
		
		query = "insert into PAYSLIP_TB values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, payslipBean.getEmpID());
			ps.setString(2, payslipBean.getMonth());
			ps.setString(3, payslipBean.getYear());
			ps.setDouble(4, payslipBean.getBasic());
			ps.setDouble(5, payslipBean.getCommutation());
			ps.setDouble(6, payslipBean.getHrAllowance());
			ps.setDouble(7, payslipBean.getProvidentFund());
			ps.setDouble(8, payslipBean.getTotalSalary());
			ps.setDouble(9, payslipBean.getIncomeTax());
			ps.setDouble(10, payslipBean.getPayableAmount());
			int result = ps.executeUpdate();
			
			if(result == 1){
				return "SUCCESS";
			}else{
				return "FAIL";
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}
		
		
		
	}
	
	
	public PayslipBean fetchPayslip(String empID,String month,String year){
		
		query = "select * from PAYSLIP_TB where EMPID = ? AND Month = ? AND Year = ?";
		
		PayslipBean bean = null;
		try{
		ps = con.prepareStatement(query);
		ps.setString(1, empID);
		ps.setString(2, month);
		ps.setString(3, year);
		rs = ps.executeQuery();
		
		while(rs.next()){
			bean = new PayslipBean();
			bean.setEmpID(rs.getString(1));
			bean.setMonth(rs.getString(2));
			bean.setYear(rs.getString(3));
			bean.setBasic(rs.getDouble(4));
			bean.setCommutation(rs.getDouble(5));
			bean.setHrAllowance(rs.getDouble(6));
			bean.setProvidentFund(rs.getDouble(7));
			bean.setTotalSalary(rs.getDouble(8));
			bean.setIncomeTax(rs.getDouble(9));
			bean.setPayableAmount(rs.getDouble(10));
			
			
		}
		
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return bean;
		
	}
	
	public boolean payslipExists(String empID,String month,String year){
		
		query = "select * from PAYSLIP_TB where EMPID = ? AND Month = ? AND Year = ?";
		
		try{
			
			ps = con.prepareStatement(query);
			ps.setString(1, empID);
			ps.setString(2, month);
			ps.setString(3, year);
			rs = ps.executeQuery();
			
			if(rs.next()){
				return true;
			}
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
		return false;
		
	}

}
