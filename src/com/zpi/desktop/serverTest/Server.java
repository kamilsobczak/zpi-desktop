package Server;

public class Server {
	private boolean quit;
	private int port;
	private ListenConnection lConn;
	
	public Server(){
		
		quit = false;
		
		Thread ui = new Thread(new UI(this));
		ui.start();
		System.out.println("Server");
		
		port = 0;
		
//		Thread test = new Thread() {
//		    public void run() {
//		    	int c = 0;
//		    	while(!quit){
//		    		System.out.println("test"+c);
//		    		c++;
//		    		c = c % 10;
//		    		try{
//		    			Thread.sleep(1000);
//		    		}catch(Exception ex){ex.printStackTrace();}
//		    	}
//		    }  
//		};
//		test.start();		
	}
	public void quit(){
		stopListening();
		quit = true;
	}
	public void setPort(int port){
		this.port = port;
	}
	public void startListening(){
		if(port==0)
			return;
		lConn = new ListenConnection(port, 8);
		Thread listenThread = new Thread(lConn);
		listenThread.start();
	}
	public void stopListening(){
		if(lConn!=null)
			lConn.stop();
	}
	public void sendMsg(String message){
		lConn.sendMsg(message);
	}
}
