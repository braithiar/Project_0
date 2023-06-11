package com.revature.dao;

import com.revature.models.Customer;
import com.revature.services.CustomerService;
import com.revature.services.ProfessionService;
import com.revature.services.PurchaseService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TestCustomerDAO {
  CustomerDAO mockCustDAO = mock(CustomerDAO.class);
  CustomerService cServ = new CustomerService(mockCustDAO);

  @Test
  public void getCustomerByZeroId() {
    assertNull(cServ.getCustomer(0));
  }

  @Test
  public void getCustomerByNegativeId() {
    assertNull(cServ.getCustomer(-1));
  }

  @Test
  public void createCustomerWithNegativeProfessionId() {
    Customer test = new Customer("Test", "McTestington", -1);

    assertNull(cServ.addNewCustomer(test));
  }

  @Test
  public void createCustomerWithZeroProfessionId() {
    Customer test = new Customer("Test", "McTestington", 0);

    assertNull(cServ.addNewCustomer(test));
  }

  @Test
  public void updateCustomerWithEmptyLastName() {
    Customer test = new Customer("Test", "", 1);

    assertNotSame(cServ.addNewCustomer(test), test);
  }

  @Test
  public void deleteCustomerZeroId() {
    assertFalse(cServ.deleteCustomer(0));
  }
}
