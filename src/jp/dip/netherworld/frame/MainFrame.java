package jp.dip.netherworld.frame;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;

import jp.dip.netherworld.main.ScreenShot;

public class MainFrame extends JFrame {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4419550151693231401L;

	private ScreenShot logic;

	public MainFrame() throws HeadlessException {
		super("スクリーンショットメーカー");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.logic = new ScreenShot();

		this.setContents(this.getContentPane());
		this.setSize(300, 100);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new MainFrame();
	}

	private void setContents(Container container) {
		container.setLayout(new BorderLayout());

		JButton ssButton = new JButton("スクリーンショット！！");
		ssButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					logic.shot();
				} catch (AWTException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		container.add(ssButton);
	}
}
