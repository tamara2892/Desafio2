package desafio4.demo.exceptions.kinds;


public class ContactoExistente extends RuntimeException {
    public ContactoExistente(String message){
        super(message);
    }
}

