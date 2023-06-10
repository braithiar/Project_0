package com.revature.services;

import com.revature.dao.interfaces.ItemTypeDAOInterface;
import com.revature.models.ItemType;

import java.util.List;

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

  public List<ItemType> getAllItemTypes() {
    return itDAO.getAllItemTypes();
  }

  public ItemType createItemType(ItemType it) {
    if (it != null) {
      return itDAO.createItemType(it);
    }

    return null;
  }

  public ItemType updateItemType(ItemType it) {
    if (it != null) {
      return itDAO.updateItemType(it);
    }

    return null;
  }
}
