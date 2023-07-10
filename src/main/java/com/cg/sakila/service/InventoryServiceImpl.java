package com.cg.sakila.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.sakila.entity.Film;
import com.cg.sakila.entity.Inventory;
import com.cg.sakila.repository.InventoryRepository;

@Service
public class InventoryServiceImpl implements InventoryService {

	private final InventoryRepository inventoryRepository;
	

    @Autowired
    public InventoryServiceImpl(InventoryRepository inventoryRepository ) {
        this.inventoryRepository = inventoryRepository;
    }
    
	// ----------------------------------------------------

	@Override
	public List<Inventory> getAllInventory() {
		return inventoryRepository.findAll();
	}

	@Override
	public int getInventoryCountForFilm(Film film) {
		return inventoryRepository.countByFilm(film);
	}

	// -------------------------------------------------------
	@Override
	public List<Map<String, Object>> getInventoryByStoreId(Byte storeId) {
		List<Inventory> inventoryList = inventoryRepository.findByStoreStoreId(storeId);
		List<Map<String, Object>> result = new ArrayList<>();

		for (Inventory inventory : inventoryList) {
			Film film = inventory.getFilm();
			String filmTitle = film.getTitle();
			int numberOfCopies = inventoryList.size();

			Map<String, Object> inventoryData = new HashMap<>();
			inventoryData.put("title", filmTitle);
			inventoryData.put("copies", numberOfCopies);

			result.add(inventoryData);
		}

		return result;
	}

	// -----------------------------------------------------------------------
	
    @Override
    public List<Map<String, Object>> getInventoryCountByFilmIdWithStoreAddress(Short filmId) {
        return inventoryRepository.getInventoryCountByFilmIdWithStoreAddress(filmId);
    }
    
    @Override
    public List<Object[]> getInventoryCountByFilmAndStore(Short filmId, Byte storeId) {
        return inventoryRepository.findInventoryCountByFilmAndStore(filmId, storeId);
    }
    @Override
    public Inventory addFilmToStores(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }
}