package com.entidad.springboot.backend.chat.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entidad.springboot.backend.chat.models.documents.Mensaje;
import com.entidad.springboot.backend.chat.models.repository.ChatRepository;

@Service
public class MensajeServiceImpl implements IMensajeService{
	@Autowired
	private ChatRepository chatRepository;

	@Override
	public Mensaje guardar(Mensaje mensaje) {
		// TODO Auto-generated method stub
		
		return chatRepository.save(mensaje);
	}

	@Override
	public List<Mensaje> obtenerHistorial() {
		// TODO Auto-generated method stub
		return chatRepository.findFirst10ByOrderByFechaDesc();
	}

	@Override
	public List<Mensaje> obtenerHistorialPorUsuario(String usuario, String usuario2) {
		// TODO Auto-generated method stub
		return chatRepository.findByUsername(usuario, usuario2);
	}

	
	

}
