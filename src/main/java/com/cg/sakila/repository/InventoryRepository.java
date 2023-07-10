package com.cg.sakila.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.sakila.entity.Film;
import com.cg.sakila.entity.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Integer>{

	int countByFilm(Film film);
	List<Inventory> findByStoreStoreId(Byte storeId);
	
	
    @Query("SELECT i FROM Inventory i WHERE i.film.id = :filmId AND i.store.id = :storeId")
    List<Inventory> getInventoryByFilmIdAndStoreId(@Param("filmId") Short filmId, @Param("storeId") Byte storeId);
    
    @Query("SELECT i.film.title AS filmTitle, COUNT(i) AS totalCount, s.address.address AS storeAddress "
            + "FROM Inventory i JOIN i.store s "
            + "WHERE i.film.id = :filmId "
            + "GROUP BY i.film.title, s.address.address")
    List<Map<String, Object>> getInventoryCountByFilmIdWithStoreAddress(@Param("filmId") Short filmId);

    @Query("SELECT s.address.address, COUNT(i) FROM Inventory i " +
            "JOIN i.store s " +
            "JOIN i.film f " +
            "WHERE f.filmId = :filmId AND s.storeId = :storeId " +
            "GROUP BY s.address.address")
	List<Object[]> findInventoryCountByFilmAndStore(Short filmId, Byte storeId);


}
