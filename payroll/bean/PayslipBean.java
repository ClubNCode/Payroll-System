package com.raj.payroll.bean;

public class PayslipBean {
	
	String empID;
	String month;
	String year;
	double basic;
	double commutation;
	double hrAllowance;
	double providentFund;
	double totalSalary;
	double incomeTax;
	double payableAmount;
	
	
	public String getEmpID() {
		return empID;
	}
	public void setEmpID(String empID) {
		this.empID = empID;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public double getBasic() {
		return basic;
	}
	public void setBasic(double basic) {
		this.basic = basic;
	}
	public double getCommutation() {
		return commutation;
	}
	public void setCommutation(double commutation) {
		this.commutation = commutation;
	}
	public double getHrAllowance() {
		return hrAllowance;
	}
	public void setHrAllowance(double hrAllowance) {
		this.hrAllowance = hrAllowance;
	}
	public double getProvidentFund() {
		return providentFund;
	}
	public void setProvidentFund(double providentFund) {
		this.providentFund = providentFund;
	}
	public double getTotalSalary() {
		return totalSalary;
	}
	public void setTotalSalary(double totalSalary) {
		this.totalSalary = totalSalary;
	}
	public double getIncomeTax() {
		return incomeTax;
	}
	public void setIncomeTax(double incomeTax) {
		this.incomeTax = incomeTax;
	}
	public double getPayableAmount() {
		return payableAmount;
	}
	public void setPayableAmount(double payableAmount) {
		this.payableAmount = payableAmount;
	}
	
	
	

}
