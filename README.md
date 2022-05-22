# sample-spring-annotations
Ejemplo de como crear anotaciones personalizadas en spring boot con programación orientada a aspectos

*Usage*
## TrackTime
Mide la duracion de un metodo anotado
Ejemplo
```java

  @TrackTime
	public String goodbye() {
		return "Good bye  World";
	}
  
```
## BaseMail
Envia correos atravez de java mailer, para ello es necesario definir un metodo con los parametros estandar tambien se puede agregar un template 
de htlm pasada por parametro

Ejemplo
```java
  
  //Definicion
  
  @BaseMail
	public void sendMailGoodBye(String to, String sender, String subject, String text, Boolean isSimpleMail,
			String template) {
	}
  
  //Invocacion
  
  @TrackTime
	@GetMapping(value = "/users/goodbye", produces = MediaType.APPLICATION_JSON_VALUE)
	public String goodbye() {
//		var templateTemp = MailerService.getDefaultTemplate();
//		templateTemp = templateTemp.replace("{bodyMessage}", "Variables parametrizadas");
		userService.sendMailGoodBye("trabajos.daniel2018@gmail.com", "trabajos.daniel2018@gmail.com", "Despedida",
				"Adios amigo adios gracias por ver el tuto", true, null);
		return "Good bye  World";
	}
  
```
