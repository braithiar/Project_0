package com.revature.services;

import com.revature.dao.interfaces.PurchaseDAOInterface;
import com.revature.models.Purchase;

import java.util.List;

public class PurchaseService {
  private final PurchaseDAOInterface pDAO;

  public PurchaseService(PurchaseDAOInterface pDAO) {
    this.pDAO = pDAO;
  }

  public List<Purchase> getCustomerPurchases(int customerId) {
    if (customerId > 0) {
      return pDAO.getCustomerPurchases(customerId);
    }

    return null;
  }

  public boolean deletePurchases(int customerId) {
    if (customerId > 0) {
      return pDAO.deletePurchases(customerId);
    }

    return false;
  }
}
