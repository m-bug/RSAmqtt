import java.util.Arrays;

public class App {

    public static final String MESSAGE = "Ich bin eine Message";

    public static void main(String[] args){
       RsaClient client = new RsaClient();
       client.generateKeyPair();
       System.out.println(MESSAGE);
       byte[] encryptedText = client.encrypt(MESSAGE, client.getPublicKey());
       System.out.println(client.decrypt(encryptedText, client.getPrivateKey()));
       System.out.println(client.getPublicKey());
       client.exportPublicKey();

    }

}
