package com.cg.sakila.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.sakila.entity.Film;
import com.cg.sakila.entity.Inventory;
import com.cg.sakila.repository.FilmRepository;
import com.cg.sakila.repository.InventoryRepository;
import com.cg.sakila.service.FilmService;
import com.cg.sakila.service.InventoryService;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
	
	private final FilmService filmService;
	private final InventoryService inventoryService;
	 
	@Autowired
	FilmRepository filmRepository;
	@Autowired
	InventoryRepository inventoryRepository;
	 

	@Autowired
	public InventoryController(InventoryService inventoryService,FilmService filmService) {
	        this.inventoryService = inventoryService;
	        this.filmService=filmService;
	} 	
	
	//working
	@GetMapping("/films")
	public List<Map<String, Object>> getAllInventoryOfFilms() {
		List<Inventory> inventoryList = inventoryService.getAllInventory();
	 	List<Map<String, Object>> result = new ArrayList<>();
	 	Map<Film, Integer> filmInventoryMap = new HashMap<>();
	 	    for(Inventory inventory : inventoryList) {
	 	        Film film = inventory.getFilm();
	 	        int numberOfCopies = inventoryService.getInventoryCountForFilm(film);
	 	        filmInventoryMap.put(film, filmInventoryMap.getOrDefault(film, 0) + numberOfCopies);
	 	    }

	 	    for(Map.Entry<Film, Integer> entry : filmInventoryMap.entrySet()) {
	 	        Film film = entry.getKey();
	 	        Integer totalCount = entry.getValue();
	 	        Map<String, Object> inventoryData = new HashMap<>();
	 	        inventoryData.put("title", film.getTitle());
	 	        inventoryData.put("totalCopies", totalCount);
	 	        result.add(inventoryData);
	 	    }

	 	    return result;
	 	}
	
	 @PostMapping("/add")
	    public ResponseEntity<String> addFilmToStores(@RequestBody Inventory inventory) {
	        Inventory addedInventory = inventoryService.addFilmToStores(inventory);
	        if (addedInventory != null) {
	            return ResponseEntity.status(HttpStatus.OK).body("Record Created Successfully");
	        } else {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to Create Record");
	        }
	    }
	
	
	
	 @GetMapping("/store/{id}")
	 public ResponseEntity<List<Map<String, Object>>> getInventoryByStoreId(@PathVariable("id") Byte storeId) {
	     List<Map<String, Object>> inventoryData = inventoryService.getInventoryByStoreId(storeId);
	     return ResponseEntity.ok(inventoryData);
	 }

	
	
	
	

	    /*----------------------------------------------------------
		//working
	    @GetMapping("/store/{id}")
	    public ResponseEntity<List<Map<String, Object>>> getInventoryByStoreId(@PathVariable("id") Byte storeId) {
	        List<Map<String, Object>> inventoryData = inventoryService.getInventoryByStoreId(storeId);
	        return ResponseEntity.ok(inventoryData);
	    }
	 	*/
	 
	 //------------------------------------------------------------
	    //working
	    @GetMapping("/film/{id}")
	    public ResponseEntity<List<Map<String, Object>>> getInventoryCountByFilmIdWithStoreAddress(
	            @PathVariable("id") Short filmId) {
	        List<Map<String, Object>> inventoryData = inventoryService.getInventoryCountByFilmIdWithStoreAddress(filmId);
	        return ResponseEntity.ok(inventoryData);
	    }

	    
	   @GetMapping
	    public List<Inventory> getAllInventory() {
	        return inventoryService.getAllInventory();
	    }
	    
	   
//	 	@PostMapping("/add")
//	    public ResponseEntity<String> addFilmToStore(@RequestBody Inventory inventory) {
//	        inventoryService.addFilmToStores(inventory);
//	        return ResponseEntity.ok("Record Created Successfully");
//	    }
	 	//working
	 	@GetMapping("/film/{filmId}/store/{storeId}")
	 	public ResponseEntity<Map<String, String>> getInventoryCount(@PathVariable Short filmId, @PathVariable Byte storeId) {
	 	    List<Object[]> inventoryCount = inventoryService.getInventoryCountByFilmAndStore(filmId, storeId);
	 	    if (inventoryCount.isEmpty()) {
	 	        return ResponseEntity.notFound().build();
	 	    }
	 	    String storeAddress = inventoryCount.get(0)[0].toString();
	 	    String count = inventoryCount.get(0)[1].toString();
	 	    
	 	    Map<String, String> response = new HashMap<>();
	 	    response.put("storeAddress", storeAddress);
	 	    response.put("count", count);
	 	    
	 	    return ResponseEntity.ok(response);
	 	}
	 
	    
}