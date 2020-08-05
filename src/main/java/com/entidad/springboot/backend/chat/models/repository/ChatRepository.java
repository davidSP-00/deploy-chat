package com.entidad.springboot.backend.chat.models.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.entidad.springboot.backend.chat.models.documents.Mensaje;

public interface ChatRepository extends MongoRepository<Mensaje,String> {
	
	public List<Mensaje> findFirst10ByOrderByFechaDesc();
	
	@Query(value="{$or:[{ $and: [ { 'username1':?0}, { 'username2':?1}]},{ $and: [ { 'username1':?1}, { 'username2':?0}]}]}")
	public List<Mensaje> findByUsername(String username,String username2);

}
