package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UI implements Runnable{

	private Client client;
	private boolean quit;
	
	public UI(Client client){
		this.client = client;
		this.quit = false;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String cmd;
			while(!quit) {
				cmd = br.readLine();
				switch(cmd){
					case "quit":
						cmdQuit();
						break;
					case "connect":
						cmdConnect("127.0.0.1", 1000);
						break;
					case "send":
						cmdSend("Hello");
						break;
					default:
						System.out.println("Unknown command: "+cmd);
						break;
				}
			}
		}
		catch(IOException ex){
			ex.printStackTrace();
		}
	}
	
	private void cmdQuit(){
		quit = true;
		client.quit();
	}
	private void cmdConnect(String IP, int port){
		client.connect(IP, port);
	}
	private void cmdDisconnect(){
		client.disconnect();
	}
	private void cmdSend(String message){
		client.sendMsg(message);
	}
	
}
