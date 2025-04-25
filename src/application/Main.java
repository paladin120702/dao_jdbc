package application;

import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Main {

	public static void main(String[] args) {
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		DepartmentDao departmentdao = DaoFactory.createDepartmentDao();
		
		System.out.println("Finding id in seller ");
		Seller seller = sellerDao.findById(3);
		System.out.println(seller);
		
		System.out.println("Finding by department in seller ");
		Department department = new Department(2, null);
		List<Seller> list = sellerDao.findByDepartment(department);
		for (Seller obj : list) {
			System.out.println(obj);
		}
		
		System.out.println("Finding all in seller ");
		list = sellerDao.findAll();
		for (Seller obj : list) {
			System.out.println(obj);
		}
		
		System.out.println("Insert in seller ");
		Seller newSeller = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.0, department);
		sellerDao.insert(newSeller);
		System.out.println("Inserted! New id = " + newSeller.getId());
		
		System.out.println("Updaete in seller ");
		seller = sellerDao.findById(11);
		seller.setName("Sam Whinchester");
		seller.setEmail("sam@gmail.com");
		seller.setBirthDate(new Date());
		sellerDao.update(seller);
		System.out.println("Update completed");
		
		System.out.println("Delete in seller");
		int id = 12;
		sellerDao.deleteById(id);
		System.out.println("Delete completed");
		
		System.out.println("Inset in department ");
		Department newDepartment = new Department(null, "TI");
		departmentdao.insert(newDepartment);
		System.out.println("Inserted! New id = " + newDepartment.getId());
		 
		System.out.println("Finding id in department ");
 		Department dep = departmentdao.findById(2);
 		System.out.println(dep);
		
 		System.out.println("Update in department ");
 		Department dep2 = departmentdao.findById(1);
 		dep2.setName("Enginer");
 		departmentdao.update(dep2);
		System.out.println("Update completed");
 		
 		System.out.println("Finding all in department ");
 		List<Department> list2 = departmentdao.findAll();
 		for (Department d : list2) {
 			System.out.println(d);
 		}
 		
 		System.out.println("Delete in department ");
 		departmentdao.deleteById(8);
 		System.out.println("Delete completed");
	}
}