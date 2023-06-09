package com.revature.dao.interfaces;

import com.revature.models.Purchase;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface PurchaseDAOInterface {
  List<Purchase> getCustomerPurchases(int cid);

  List<Purchase> getCustomerPurchases(int cid, Connection conn) throws
    SQLException;

  List<Purchase> getAllPurchases();

  Purchase getOnePurchase(int pid);

  Purchase createPurchase(Purchase p);

  Purchase updatePurchase(Purchase p);

  List<Purchase> deleteCustomerPurchases(int cid);

  Purchase deletePurchase(Purchase p);
}
