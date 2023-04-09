package main;

import javax.swing.JFrame;

import Controller.MainController;
import util.DataTest;

public class Scan {

	private static MainController controller;

	public static void main(String[] args) {
		// Paneli içeren bir JFrame oluştur
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1920, 1080);
		// CrosshairPanel'i JFrame'e ekle
		controller = new MainController();

		DataTest t = new DataTest(controller);
		frame.add(controller.getMainPanel());
		frame.setVisible(true);
		t.start();
	}

}
