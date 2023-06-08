package com.revature.services;

import com.revature.dao.interfaces.ItemDAOInterface;
import com.revature.models.Item;

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
}
