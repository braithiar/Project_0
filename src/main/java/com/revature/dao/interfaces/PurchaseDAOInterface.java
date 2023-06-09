package com.revature.dao.interfaces;

import com.revature.models.Purchase;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface PurchaseDAOInterface {
  List<Purchase> getCustomerPurchases(int cid);

  List<Purchase> getCustomerPurchases(int cid, Connection conn) throws
    SQLException;

  boolean deleteCustomerPurchases(int cid);
}
