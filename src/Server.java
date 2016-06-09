
public class Server {
	public static void main(String[] args) {
		Thread thread = new Thread(new ServerThread());
		thread.start();
	}
}
