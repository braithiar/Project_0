package com.revature.dao;

import com.revature.models.Customer;
import com.revature.services.CustomerService;
import com.revature.services.ProfessionService;
import com.revature.services.PurchaseService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestCustomerDAO {
  private CustomerDAO mockCustDAO;
  private ProfessionDAO mockProDAO;
  private PurchaseDAO mockPurDAO;
  private CustomerService cServ;
  private ProfessionService pServ;
  private PurchaseService purServ;

  @BeforeAll
  public void setup() {
    mockCustDAO = mock(CustomerDAO.class);
    cServ = new CustomerService(mockCustDAO);
    mockProDAO = mock(ProfessionDAO.class);
    pServ = new ProfessionService(mockProDAO);
    mockPurDAO = mock(PurchaseDAO.class);
    purServ = new PurchaseService(mockPurDAO);
  }

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
    Customer test = new Customer("Testy", "Testington", -1);

    assertNull(cServ.addNewCustomer(test));

    verify(mockCustDAO, times(0)).addNewCustomer(eq(test));
  }

  @Test
  public void createCustomerWithZeroProfessionId() {
    Customer test = new Customer("Testy", "Testington", 0);

    assertNull(cServ.addNewCustomer(test));

    verify(mockCustDAO, times(0));
  }

  @Test
  public void updateCustomerWithEmptyLastName() {
    Customer test = new Customer(1, "Test", "", 1);
    Customer expected = new Customer(1, "Test", "", pServ.getProfession(1),
                                     purServ.getCustomerPurchases(1));

    when(mockCustDAO.updateCustomer(any(Customer.class))).thenReturn(expected);

    cServ.updateCustomer(test);

    verify(mockCustDAO, times(1)).updateCustomer(eq(test));
  }

  @Test
  public void updateCustomerWithNullLastName() {
    Customer test = new Customer(1, "Test", null, 1);
    Customer expected = new Customer(1, "Test", null, pServ.getProfession(1),
                                     purServ.getCustomerPurchases(1));

    when(mockCustDAO.updateCustomer(any(Customer.class))).thenReturn(expected);

    assertEquals(expected, cServ.updateCustomer(test));
    verify(mockCustDAO, times(1)).updateCustomer(eq(test));
  }

  @Test
  public void updateCustomerWithNullNames() {
    Customer test = new Customer(1, null, null, 1);
    Customer expected = new Customer(1, null, null, pServ.getProfession(1),
                                     purServ.getCustomerPurchases(1));

    when(mockCustDAO.updateCustomer(any(Customer.class))).thenReturn(expected);

    assertEquals(expected, cServ.updateCustomer(test));
    verify(mockCustDAO, times(1)).updateCustomer(eq(test));
  }

  @Test
  public void updateCustomerWithNoId() {
    Customer test = new Customer("Test", "Testington", 1);
    ;

    assertNull(cServ.updateCustomer(test));
    verify(mockCustDAO, times(0)).updateCustomer(eq(test));
  }

  @Test
  public void deleteCustomerZeroId() {
    assertFalse(cServ.deleteCustomer(0));

    verify(mockCustDAO, times(0)).deleteCustomer(0);
  }
}
