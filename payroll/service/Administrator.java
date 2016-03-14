package com.wipro.payroll.service;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import org.eclipse.jdt.core.compiler.InvalidInputException;

import com.wipro.payroll.bean.PayslipBean;
import com.wipro.payroll.dao.PayslipDAO;

public class Administrator {
	
	Date d = new Date();
	
	public String addPayslip(PayslipBean payslipBean){
		
		if(payslipBean == null ||(payslipBean.getEmpID()==null||payslipBean.getEmpID().equals(""))||
				(payslipBean.getMonth()==null||payslipBean.getMonth().equals(""))
				||(payslipBean.getYear()==null||payslipBean.getYear().equals(""))
				||(payslipBean.getBasic()<=0.0)||(payslipBean.getCommutation()<=0.0)
				||(payslipBean.getHrAllowance()<=0.0)){
			
			return "INVALID INPUT";
					
		}
		
		
		Calendar c = Calendar.getInstance();
		if(!payslipBean.getMonth().equals(c.get(Calendar.MONTH)+1)|| (!payslipBean.getYear().equals(c.get(Calendar.YEAR)))){
			
			return "INVALID MONTH OR YEAR";
			
		}
		
			/*if(payslipBean.getMonth() != d.getMonth()||payslipBean.getYear() != d.getYear()){
				
			     return "INVALID MONTH OR YEAR";
				
			}*/
		
		
		if(new PayslipDAO().payslipExists(payslipBean.getEmpID(), payslipBean.getMonth(), payslipBean.getYear())){
			return "ALREADY EXISTS";
		}
		
		
		payslipBean.setProvidentFund(payslipBean.getBasic()*0.12);
		
		
		
		if(payslipBean.getBasic() >= 1.0 && payslipBean.getBasic() <= 21000.0){
			payslipBean.setIncomeTax(payslipBean.getBasic()*0.0);
		}
		
		else if(payslipBean.getBasic() >= 21001.0 && payslipBean.getBasic() <= 42000.0){
			
			payslipBean.setIncomeTax(payslipBean.getBasic()*(0.1));
			
		}else if(payslipBean.getBasic() >= 42001.0 && payslipBean.getBasic() <= 84000.0){
			
			payslipBean.setIncomeTax(payslipBean.getBasic()*(0.2));
			
		}else if(payslipBean.getBasic() > 84000.0){
			
			payslipBean.setIncomeTax(payslipBean.getBasic()*(0.3));
			
		}
		
		
		payslipBean.setTotalSalary(payslipBean.getBasic()+payslipBean.getCommutation()+payslipBean.getHrAllowance()+payslipBean.getProvidentFund());
		
		payslipBean.setPayableAmount(payslipBean.getTotalSalary()-payslipBean.getIncomeTax()-payslipBean.getProvidentFund());
		
		return new PayslipDAO().createPayslip(payslipBean);
		
	}
	
	public PayslipBean viewPayslip(String empID,String month,String year){
		
		if(new PayslipDAO().payslipExists(empID, month, year)){
		return new PayslipDAO().fetchPayslip(empID, month, year);
		}
		return null;
		
	}

}
