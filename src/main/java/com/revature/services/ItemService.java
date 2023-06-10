package com.revature.services;

import com.revature.dao.interfaces.ItemDAOInterface;
import com.revature.models.Item;

import java.util.List;

public class ItemService {
  private final ItemDAOInterface iDAO;

  public ItemService(ItemDAOInterface iDAO) {
    this.iDAO = iDAO;
  }

  public Item getItem(int id) {
    if (id > 0) {
      return iDAO.getItem(id);
    }

    return null;
  }

  public List<Item> getAllItems() {
    return iDAO.getAllItems();
  }

  public Item addItem(Item i) {
    if (i != null) {
      return iDAO.addItem(i);
    }

    return null;
  }

  public Item updateItem(Item i) {
    if (i != null && i.getId() > 0) {
      return iDAO.updateItem(i);
    }

    return null;
  }
}
