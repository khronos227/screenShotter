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
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import jp.dip.netherworld.main.ScreenShot;

public class MainFrame extends JFrame {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4419550151693231401L;

	private ScreenShot logic;
	private MainFrame self;

	public MainFrame() throws HeadlessException {
		super("スクリーンショットメーカー");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.self = this;

		this.logic = new ScreenShot();

		this.setMenuBar();
		this.setContents(this.getContentPane());
		this.setSize(300, 100);

		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			SwingUtilities.updateComponentTreeUI(this);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		this.setResizable(false);
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
					JOptionPane.showMessageDialog(self, "画像を出力できませんでした。\n"
							+ "ディレクトリ： " + logic.getOutputDir() + "\n"
							+ "が存在することを確認してください。");
					e.printStackTrace();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(self, "画像を出力できませんでした。\n"
							+ "ディレクトリ： " + logic.getOutputDir() + "\n"
							+ "が存在することを確認してください。");
					e.printStackTrace();
				}
			}
		});

		container.add(ssButton);
	}

	private void setMenuBar() {
		MenuSet menues = new MenuSet(self, logic);
		this.setMenuBar(menues.createMenuBar());
	}
}
