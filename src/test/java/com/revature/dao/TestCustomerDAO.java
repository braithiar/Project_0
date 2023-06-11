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
  ProfessionDAO mockProDAO = mock(ProfessionDAO.class);
  PurchaseDAO mockPurDAO = mock(PurchaseDAO.class);
  CustomerService cServ = new CustomerService(mockCustDAO);
  ProfessionService pServ = new ProfessionService(mockProDAO);
  PurchaseService purServ = new PurchaseService(mockPurDAO);

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
    Customer test = new Customer(1, "Test", "", 1);
    Customer expected = new Customer(1, "Test", "", pServ.getProfession(1), purServ.getCustomerPurchases(1));

    when(mockCustDAO.updateCustomer(any(Customer.class))).thenReturn(expected);

    assertEquals(cServ.updateCustomer(test), expected);
    verify(mockCustDAO, times(1)).updateCustomer(eq(test));
  }

  @Test
  public void updateCustomerWithOnlyFirstName() {
    Customer test = new Customer(1, "Test", null, 1);
    Customer expected = new Customer(1, "Test", "Testington", pServ.getProfession(1), purServ.getCustomerPurchases(1));

    when(mockCustDAO.updateCustomer(any(Customer.class))).thenReturn(expected);

    assertEquals(cServ.updateCustomer(test), expected);
    verify(mockCustDAO, times(1)).updateCustomer(eq(test));
  }

  @Test
  public void updateCustomerWithOnlyProfessionId() {
    Customer test = new Customer(1, null, null, 2);
    Customer expected = new Customer(1, "Test", "Testington", pServ.getProfession(2), purServ.getCustomerPurchases(1));

    when(mockCustDAO.updateCustomer(any(Customer.class))).thenReturn(expected);

    assertEquals(cServ.updateCustomer(test), expected);
    verify(mockCustDAO, times(1)).updateCustomer(eq(test));
  }

  @Test
  public void updateCustomerWithNoId() {
    Customer test = new Customer("Test", "Testington", 1);
    Customer expected = null;

    when(mockCustDAO.updateCustomer(any(Customer.class))).thenReturn(expected);

    assertEquals(cServ.updateCustomer(test), expected);
    verify(mockCustDAO, times(1)).updateCustomer(eq(test));
  }

  @Test
  public void deleteCustomerZeroId() {
    assertFalse(cServ.deleteCustomer(0));
  }
}
