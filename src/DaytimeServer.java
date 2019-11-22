import java.net.*;
import java.io.*;
import java.util.Date;

public class DaytimeServer {
	public static int PORT;

	public DaytimeServer() {
	}

	public static int getPORT() {
		return PORT;
	}

	public static void setPORT(int PORT) {
		PORT = PORT;
	}

//	public static void main(String[] args) {
//	try (ServerSocket server = new ServerSocket(PORT)) {
//		while (true) {
//			try (Socket connection = server.accept()) {
//				Writer out = new OutputStreamWriter(connection.getOutputStream());
//				Date now = new Date();
//				out.write(now.toString() +"\r\n");
//				out.flush();
//				connection.close();
//			}catch (IOException ex) {
//			}
//		}
//	} catch (IOException ex) {
//		System.err.println(ex);
//	}
//	}
	
	public void onServer(int port) {
		port = PORT;
		try (ServerSocket server = new ServerSocket(PORT)) {
			while (true) {
				try (Socket connection = server.accept()) {
					Writer out = new OutputStreamWriter(connection.getOutputStream());
					Date now = new Date();
					out.write(now.toString() +"\r\n");
					out.flush();
					connection.close();
				}catch (IOException ex) {
				}
			}
		} catch (IOException ex) {
			System.err.println(ex);
		}
	}
	
	private void offServer() {

	}
}
