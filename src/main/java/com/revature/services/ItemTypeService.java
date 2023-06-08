package com.revature.services;

import com.revature.dao.interfaces.ItemTypeDAOInterface;
import com.revature.models.ItemType;

public class ItemTypeService {
  private final ItemTypeDAOInterface itDAO;

  public ItemTypeService(ItemTypeDAOInterface itDAO) {
    this.itDAO = itDAO;
  }

  public ItemType getItemType(int id) {
    if (id > 0) {
      return itDAO.getItemType(id);
    }

    return null;
  }
}
