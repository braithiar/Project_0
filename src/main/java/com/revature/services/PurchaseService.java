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

  public Purchase createPurchase(Purchase p ) {
    if (p != null && p.getCustId() > 0 && p.getItemId() > 0) {
      return purDAO.createPurchase(p);
    }

    return null;
  }

  public Purchase updatePurchase(Purchase p) {
    if (p != null && p.getId() > 0) {
      return purDAO.updatePurchase(p);
    }

    return null;
  }

  public List<Purchase> deletePurchases(int customerId) {
    if (customerId > 0) {
      return purDAO.deleteCustomerPurchases(customerId);
    }

    return null;
  }

  public Purchase deletePurchase(Purchase p) {
    if (p != null && p.getId() > 0) {
      return purDAO.deletePurchase(p);
    }

    return null;
  }
}
