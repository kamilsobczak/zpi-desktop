package com.zpi.desktop.serverTest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import com.zpi.desktop.gameLogic.Player;
import com.zpi.desktop.gameLogic.Shape;
import com.zpi.desktop.gamePanel.GameFrame;

public class ClientConnection implements Runnable {

	//private ListenConnection listeningThread;
	private Socket socket;

	private PrintWriter out;
	private BufferedReader in;
	private Player player;
	private String msg;
	private GameFrame gameFrame;

	public ClientConnection(ListenConnection listeningThread, Socket socket,
			Player player, GameFrame gameFrame) {
		//this.listeningThread = listeningThread;
		this.socket = socket;
		this.player = player;
		this.gameFrame = gameFrame;
	}

	@Override
	public void run() {
		try {
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
					socket.getOutputStream(), "UTF-8")), true);

			in = new BufferedReader(new InputStreamReader(
					socket.getInputStream(), "UTF-8"));
			
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! sle id");
			
			out.println(player.getId()+"");
			
			while (true) {
				msg = in.readLine();

				if (!this.gameFrame.gamePanel.isCountdownDone())
					continue;
				

				if (msg != null) {

					Shape shape = null;

					switch (msg) {

					case "kwadrat":
						shape = Shape.SQUARE;
						break;
					case "kolko":
						shape = Shape.CIRCLE;
						break;
					case "trojkat":
						shape = Shape.TRIANGLE;
						break;
					case "krzyzyk":
						shape = Shape.CROSS;
						break;
					}

					if (shape != null) {

						if (!player.isFinish() && player != null) {
							player.selectShape(shape);
						} else {
							System.out.println("KONIEC!");
						}
						gameFrame.repaint();

					} else {
						System.out.println("nie moja wina");
					}

				}
				msg = null;
			}

		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void sendMsg(String message) {
		out.write(message + "\n");
		out.flush();
	}

}
