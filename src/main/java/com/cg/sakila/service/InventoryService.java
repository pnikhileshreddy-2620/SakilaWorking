package com.cg.sakila.service;

import java.util.List;
import java.util.Map;

import com.cg.sakila.entity.Film;
import com.cg.sakila.entity.Inventory;

public interface InventoryService {

	List<Inventory> getAllInventory();
	int getInventoryCountForFilm(Film film);
	List<Map<String, Object>> getInventoryByStoreId(Byte storeId);
	List<Map<String, Object>> getInventoryCountByFilmIdWithStoreAddress(Short filmId);
	List<Object[]> getInventoryCountByFilmAndStore(Short filmId, Byte storeId);
	  Inventory addFilmToStores(Inventory inventory);
	
	  
}
