package Ejercicio1;

//La clase ItemDuplicated hereda de la clase base Exception.
//Esta es una práctica común cuando se crean excepciones personalizadas en Java.
public class ItemDuplicated extends Exception {

// Este es un constructor de ItemDuplicated que toma un mensaje de string como parámetro.
// Cuando se lanza la excepción, este mensaje se pasa a la clase base Exception, 
// y se puede recuperar más tarde con el método getMessage().
public ItemDuplicated(String msg) {
  super(msg);  // Llama al constructor de la superclase (Exception) pasando el mensaje
}

// Este es un constructor sin argumentos para la excepción ItemDuplicated.
// Cuando se lanza la excepción sin pasar un mensaje, este constructor se usa.
// En este caso, se llama al constructor de la clase base Exception sin ningún argumento.
public ItemDuplicated() {
  super();  // Llama al constructor de la superclase (Exception) sin ningún argumento
}
}