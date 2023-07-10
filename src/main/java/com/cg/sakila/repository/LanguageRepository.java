package com.cg.sakila.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.sakila.entity.Language;


@Repository
public interface LanguageRepository extends JpaRepository<Language, Byte>{

	Language findByName(String languageName);

}
