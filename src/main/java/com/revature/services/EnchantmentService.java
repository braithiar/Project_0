package com.revature.services;

import com.revature.dao.interfaces.EnchantmentDAOInterface;
import com.revature.models.Enchantment;

public class EnchantmentService {
  private final EnchantmentDAOInterface eDAO;

  public EnchantmentService(EnchantmentDAOInterface eDAO) {
    this.eDAO = eDAO;
  }
  public Enchantment getEnchantment(int id) {
    if (id > 0) {
      return eDAO.getEnchantment(id);
    }

    return null;
  }
}
