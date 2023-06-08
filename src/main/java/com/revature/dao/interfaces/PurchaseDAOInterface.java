package com.revature.dao.interfaces;

import com.revature.models.Purchase;

import java.util.List;

public interface PurchaseDAOInterface {
  List<Purchase> getCustomerPurchases(int customerId);
  boolean deletePurchases(int customerId);
}
