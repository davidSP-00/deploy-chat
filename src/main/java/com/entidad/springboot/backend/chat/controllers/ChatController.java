package com.entidad.springboot.backend.chat.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.entidad.springboot.backend.chat.models.documents.Mensaje;
import com.entidad.springboot.backend.chat.models.service.IMensajeService;
import com.entidad.springboot.backend.chat.models.service.MensajeServiceImpl;

@Controller
public class ChatController {
	
	@Autowired
	private IMensajeService chatService;
	
	@Autowired
	SimpMessagingTemplate webSocket;
	
//	@MessageMapping("/mensaje")
//	@SendTo("/chat/mensaje")
//	public Mensaje recibirMensaje(Mensaje mensaje) {
//		
//		return this.chatService.guardar(mensaje);
//	}
	
	@MessageMapping("/historial")
	public void historial() {
		System.out.print(this.chatService.obtenerHistorial());
		webSocket.convertAndSend("/chat/historial",this.chatService.obtenerHistorial());
	}
	@MessageMapping("/historial_usuario")
	public void historialUsuario(String usuario) {
		String[]usuarios=usuario.split("--;--");
		System.out.print(this.chatService.obtenerHistorialPorUsuario(usuarios[0],usuarios[1]));
		webSocket.convertAndSend("/chat/historial",this.chatService.obtenerHistorialPorUsuario(usuarios[0],usuarios[1]));
	}
	
	@MessageMapping("/mensaje")
	public void recibirMensaje(Mensaje mensaje) {
		mensaje.setFecha(new Date());
		Mensaje mensajeGuardado=this.chatService.guardar(mensaje);
		webSocket.convertAndSend("/chat/mensaje/"+mensaje.getUsername1()+mensaje.getUsername2(),mensajeGuardado);
		webSocket.convertAndSend("/chat/mensaje/"+mensaje.getUsername2()+mensaje.getUsername1(),mensajeGuardado);
	}
	
	@MessageMapping("/escribiendo")
	public void estaEscribiendo(Mensaje mensaje) {
		webSocket.convertAndSend("/chat/escribiendo/"+mensaje.getUsername1()+mensaje.getUsername2(),"escribiendo ...");
		
	}
	
	@EventListener
	public void onDisconnectEvent(SessionDisconnectEvent event) {
		System.err.println(event.getSource());
	}

}
