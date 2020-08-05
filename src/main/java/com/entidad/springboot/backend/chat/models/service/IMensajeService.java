package com.entidad.springboot.backend.chat.models.service;

import java.util.List;

import com.entidad.springboot.backend.chat.models.documents.Mensaje;

public interface IMensajeService {

	
	public Mensaje guardar(Mensaje mensaje);
	public List<Mensaje> obtenerHistorial();
	public List<Mensaje> obtenerHistorialPorUsuario(String usuario,String usuario2);
}
