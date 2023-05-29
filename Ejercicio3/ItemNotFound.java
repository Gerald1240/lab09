package Ejercicio3;

//La clase ItemNotFound hereda de la clase Exception
//Esto se hace para crear una excepción personalizada en Java
@SuppressWarnings("serial")
public class ItemNotFound extends Exception {

// Constructor de ItemNotFound que acepta un mensaje de string como parámetro
// Cuando se lanza la excepción, este mensaje se pasa a la superclase Exception 
// y luego puede ser recuperado usando el método getMessage()
public ItemNotFound(String msg) {
  super(msg);  // Llama al constructor de la superclase (Exception) pasando el mensaje
}

// Constructor sin argumentos para la excepción ItemNotFound
// Cuando se lanza la excepción sin pasar un mensaje, este constructor es utilizado
// En este caso, se llama al constructor de la superclase Exception sin ningún argumento
public ItemNotFound() {
  super();  // Llama al constructor de la superclase (Exception) sin ningún argumento
}
}
