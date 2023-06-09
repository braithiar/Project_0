package com.revature.services;

import com.revature.dao.interfaces.PurchaseDAOInterface;
import com.revature.models.Purchase;

import java.util.List;

public class PurchaseService {
  private final PurchaseDAOInterface purDAO;

  public PurchaseService(PurchaseDAOInterface pDAO) {
    this.purDAO = pDAO;
  }

  public List<Purchase> getCustomerPurchases(int customerId) {
    if (customerId > 0) {
      return purDAO.getCustomerPurchases(customerId);
    }

    return null;
  }

  public List<Purchase> getAllPurchases() {
    return purDAO.getAllPurchases();
  }

  public Purchase getOnePurchase(int pid) {
    if (pid > 0) {
      return purDAO.getOnePurchase(pid);
    }

    return null;
  }

  public boolean deletePurchases(int customerId) {
    if (customerId > 0) {
      return purDAO.deleteCustomerPurchases(customerId);
    }

    return false;
  }
}
