package com.ronit.test;

import java.sql.Date;
import java.util.List;

import javax.swing.JOptionPane;

import com.ronit.beans.Company;
import com.ronit.beans.Coupon;
import com.ronit.beans.Customer;
import com.ronit.enums.Category;
import com.ronit.enums.ClientType;
import com.ronit.exceptions.CouponSystemException;
import com.ronit.facades.AdministratorFacade;
import com.ronit.facades.CompanyFacade;
import com.ronit.facades.CustomerFacade;
import com.ronit.job.CouponExpirationDailyIob;
import com.ronit.utils.ConnectionPool;
import com.ronit.utils.LoginManager;

public class Test {
	private CouponExpirationDailyIob job;
	private AdministratorFacade administratorFacade;
	private CustomerFacade customerFacade;
	private CompanyFacade companyFacade;

// _____________________________MAIN TEST____________________________________

	public void testAll() {
		try {
			this.systemStart();
			System.out.println();
			System.out.println("=================== TEST ADMIN");
//			this.testAdmin();
			System.out.println();
			System.out.println("=================== TEST COMPANY");
//			this.testCompany();
			System.out.println();
			System.out.println("=================== TEST CUSTOMER");
			this.testCustomer();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			this.systemStop();
		}

	}

//_____________________________ADMIN TEST_____________________________________
	private void testAdmin() throws CouponSystemException {
		this.doAdminLogin();
//		this.doAddCompany();
//		doDeleteCompany();
//		doaAddCustomer();
//		doaDeleteCustomer();
//		doUpdateCustomer();
//		doUpdateCompany();
//		doGetAllCompanies();
//		doGetAllCustomers();
//		doGetOneCompany();
//		doGetOneCustomer();

	}

//_____________________________COMPANY TEST____________________________________
	private void testCompany() throws CouponSystemException {
		doCompanyLogin();
//		doAddCoupon();
//		doDeleteCoupon();
//		doGetCompanyCoupons();
//		getCompanyCouponsByCatagoryAndId();		
	}

//_____________________________CUSTOMER TEST____________________________________
	private void testCustomer() throws CouponSystemException {
		this.doCustomerLogin();
//		this.doGetgetAllCustomerCoupons();
//		doGetAllCustomerDetails();
//		doGetPurchaseCoupon();
//		doGetCustomerCouponsbyCatagory();
//		 doGetCustomerCouponsByIdAndPrice();

	}

//_______________________INTERNAL METHODS - ADMIN TEST___________________________
	private void doAdminLogin() throws CouponSystemException {
		this.administratorFacade = (AdministratorFacade) LoginManager.getInstance().login("admin@admin.com", "admin",
				ClientType.ADMINISTRATOR);
		if (administratorFacade != null) {
			System.out.println("logged in as admin");
		} else {
			System.out.println("admin login failed");
		}
	}

	private void doAddCompany() throws CouponSystemException {
		Company company = new Company(1, "aaa", "aaa@gmail.com", "aaa29l5");
		int id = this.administratorFacade.addCompany(company);
		System.out.println("company added. id is " + id);
	}

	private void doDeleteCompany() throws CouponSystemException {
		administratorFacade.deleteCompany(12);
		System.out.print("company deleted");
	}

	private void doaAddCustomer() throws CouponSystemException {
		Customer customer = new Customer(8433, "Daniel", "Harvey", "Daniel@gmail.com", "Daniel123");
		int id = administratorFacade.addCustomer(customer);
		System.out.println("Customer added. id is " + id);

	}

	private void doaDeleteCustomer() throws CouponSystemException {
		administratorFacade.deleteCustomer(1);
		System.out.println("Customer Deleted");

	}

	private void doUpdateCustomer() throws CouponSystemException {
		Customer customer = new Customer(4, "Will", "Doe", "Will@gmail.com", "Will123");
		administratorFacade.updateCustomer(customer);
		System.out.println("Customer Updated");
	}

	// does not update company
	private void doUpdateCompany() throws CouponSystemException {
		Company company = new Company(7, "fff", "hhh@gmail.com", "HHH");
		this.administratorFacade.updateCompany(company);
		System.out.println("company Updated: " + company);
	}

	private void doGetAllCompanies() throws CouponSystemException {

		List<Company> list = administratorFacade.getAllCompanies();
		System.out.println("Get All Companies");
		for (Company company : list) {
			System.out.println(company);
		}
		System.out.println("=====================");
	}

	private void doGetAllCustomers() throws CouponSystemException {

		List<Customer> list = administratorFacade.getAllCustomers();
		System.out.println("Get All Customers");
		for (Customer customer : list) {
			System.out.println(customer);
		}
		System.out.println("=====================");
	}

	private void doGetOneCompany() throws CouponSystemException {
		administratorFacade.getOneCompany(11);
	}

	private void doGetOneCustomer() throws CouponSystemException {
		administratorFacade.getOneCustomer(7);
	}

// ___________________INTERNAL METHODS - COMPANY TEST___________________________
	private void doCompanyLogin() throws CouponSystemException {
		this.companyFacade = (CompanyFacade) LoginManager.getInstance().login("coffeebakeryl@gmail.com", "gfggfh453",
				ClientType.COMPANY);
		if (companyFacade != null) {
			System.out.println("logged in as company");
		} else {
			System.out.println("company login failed");
		}
	}

	private void doAddCoupon() throws CouponSystemException {
		Date startDate = java.sql.Date.valueOf("2021-9-20");
		Date endDate = java.sql.Date.valueOf("2021-9-27");
		Coupon coupon = new Coupon(27, 9, Category.ELECTRICITY, "HelloWorld", "description", 20, 5000, "image", endDate,
				startDate);
		companyFacade.addCoupon(coupon);
		System.out.println("Coupon Added");
	}

	private void doDeleteCoupon() throws CouponSystemException {
		companyFacade.deleteCoupon(26);
		System.out.print("Coupon deleted");
	}

	private void doGetCompanyCoupons() throws CouponSystemException {
		List<Coupon> list = companyFacade.getCompanyCoupons(6);
		System.out.println("get Company Coupons");
		for (Coupon coupon : list) {
			System.out.println(coupon);
		}
		System.out.println("=====================");
	}

	private void getCompanyCouponsByCatagoryAndId() throws CouponSystemException {
		List<Coupon> list = companyFacade.getCompanyCoupons(6, 2);
		System.out.println("Get list");
		for (Coupon coupon : list) {
			System.out.println(coupon);
		}
		System.out.println("=====================");

	}

	private void getCompanyCouponsByPrice() throws CouponSystemException {
		companyFacade.getCompanyCoupons(0, 0);
	}

	private void doGetCompanyDetails() throws CouponSystemException {
		companyFacade.getCompanyDetails();
	}

	private void doUpdateCoupon() throws CouponSystemException {
		companyFacade.UpdateCoupon(null);
	}
//  __________________INTERNAL METHODS - CUSTOMER TEST___________________________

	private void doCustomerLogin() throws CouponSystemException {
//		String email = JOptionPane.showInputDialog("enter customer email");
//		String password = JOptionPane.showInputDialog("enter customer password");
		this.customerFacade = (CustomerFacade) LoginManager.getInstance().login("Daniella@gmail.com", "Daniella123",
				ClientType.CUSTOMER);
		if (this.customerFacade != null) {
			System.out.println("logged in as customer");
		} else {
			System.out.println("customer login failed");
		}
	}

	private void doGetgetAllCustomerCoupons() throws CouponSystemException {
		List<Coupon> list = customerFacade.getAllCustomerCoupons();

		System.out.println("customer coupons");
		for (Coupon coupon : list) {
			System.out.println(coupon);
		}
		System.out.println("=====================");

	}

	private void doGetAllCustomerDetails() throws CouponSystemException {
		customerFacade.getAllCustomerDetails();
	}

	private void doGetPurchaseCoupon() throws CouponSystemException {
		customerFacade.PurchaseCoupon(20);
		System.out.println("Purchase Coupon");
	}

	private void doGetCustomerCouponsbyCatagory() throws CouponSystemException {
		List<Coupon> list = customerFacade.getCustomerCoupons(Category.FOOD);
		System.out.println("Get Customer Coupons by Catagory");
		for (Coupon coupon : list) {
			System.out.println(coupon);
		}
		System.out.println("=====================");

	}

	private void doGetCustomerCouponsByIdAndPrice() throws CouponSystemException {
		List<Coupon> list = customerFacade.getCustomerCoupons(2, 5000);
		System.out.println("Customer Coupons By Id And Price");
		for (Coupon coupon : list) {
			System.out.println(coupon);
		}
		System.out.println("=====================");
	}

//___________________________INTERNAL METHODS____________________________________
	private void systemStart() {
		// 1. start the pool
		ConnectionPool.getInstance(); // load connection pool
		// 2. start the job
		job = new CouponExpirationDailyIob();
		job.start();
	}

	private void systemStop() {
		System.out.println("test ended. system will be down in 5 seconds");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
//	 1. stop the job
		if (job != null) {
			job.stop();
			try {
				job.getThread().join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
//	 2. stop the pool
		ConnectionPool.getInstance().closeConnections();
	}

}
