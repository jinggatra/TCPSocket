package Server;
import java.net.*;
import java.io.*;
import java.util.Date;


public class DaytimeServer {
	public static int PORT;
	private boolean keepgoing;

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
			if (true) {
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
		DatagramSocket serverSocket;
		try {
			
			serverSocket = new DatagramSocket();
			System.out.println("Server telah siap....");
			
			while (true) {
				boolean isQUIT = false;
				while (!isQUIT) {
					byte[] byteFromClient = new byte[1024];
					byte[] byteToClient = new byte[1024];
					DatagramPacket receivePacket = new DatagramPacket(byteFromClient, byteFromClient.length);
					serverSocket.receive(receivePacket);
					
					InetAddress IPAddress = receivePacket.getAddress();
					int port = receivePacket.getPort();
					
					
					String data = new String(receivePacket.getData());
					
					if (data.startsWith("TIME")) {
						String DateNow = new String(new Date().toString());
						byteToClient = DateNow.getBytes();
					}else if (data.startsWith("NET")) {
						String hostname = new String();
						byteToClient = hostname.getBytes();
					}else if (data.startsWith("QUIT")) {
						isQUIT = true;
						String thanks =	new String("Terima kasih!");
						byteToClient = thanks.getBytes();
					}
					
					DatagramPacket sendPacket = new DatagramPacket(byteToClient,byteToClient.length, IPAddress, port);
					serverSocket.send(sendPacket);
				}
				System.out.println("Hub. client tertutup...");
			}
		} catch (IOException ioe) {
			// TODO: handle exception
			System.out.println("error: "+ioe);
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("error: "+e);
		}
	}
}
