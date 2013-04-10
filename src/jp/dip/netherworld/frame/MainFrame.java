package jp.dip.netherworld.frame;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.HeadlessException;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
		MenuBar mb = new MenuBar();
		// メニューの生成
		Menu mn = new Menu("ファイル");
		mb.add(mn);
		// メニュー項目の生成
		MenuItem mi = new MenuItem("出力先の変更");
		mi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fileChooser.setMultiSelectionEnabled(false);
				int selected = fileChooser.showOpenDialog(self);
				if (selected == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					if (file.isDirectory()) {
						logic.setOutputDir(file.getAbsolutePath());
					}
				}
			}
		});

		mn.add(mi);
		this.setMenuBar(mb);
	}
}
