import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerThread implements Runnable {

	public static final int port = 9999;
	
	public void run() {
		try {
			System.out.println("Waiting for connection.");
			ServerSocket ss = new ServerSocket(port);
			
			while(true) {
				Socket socket = ss.accept();
				System.out.println("The transfer of the file.");
				
				try {
					InputStream is = socket.getInputStream();

					//String filename = "MegaSender_";
					//SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyyMMddHHmmss", Locale.KOREA );
					//Date currentTime = new Date ();
					//String mTime = mSimpleDateFormat.format(currentTime);
					//FileOutputStream fos = new FileOutputStream("C:\\Users\\Mr.K\\Downloads\\MegaSender\\" + filename + mTime + ".jpg");
					
					BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					String filename = in.readLine();
					System.out.println(filename);
					FileOutputStream fos = new FileOutputStream("C:\\Users\\Mr.K\\Downloads\\MegaSender\\" + filename);

					System.out.println("socket conn : " + socket.isConnected());

					byte[] buffer = new byte[4096];

					System.out.println("Wating...");

					while (is.available() <= 0) {
						try {
							System.out.println(".");
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

					int len = 0;

					while ((len = is.read(buffer)) > 0) {
						fos.write(buffer, 0, len);
						System.out.println("Transfer data : " + is.available());
					}
					
					fos.close();
					socket.close();
					
				}catch (Exception e) {
					System.out.println("Error");
					e.printStackTrace();
				}finally {
					
					System.out.println("File transfer completed.");
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
