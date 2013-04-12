package jp.dip.netherworld.frame;

import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

import jp.dip.netherworld.main.ScreenShot;

public class MenuSet implements ActionListener {
	private final static String CHANGE_OUTPUT_DIR = "change-output-dir";

	private ScreenShot logic;
	private MainFrame mainFrame;

	public MenuSet(MainFrame mainFrame, ScreenShot logic) {
		this.mainFrame = mainFrame;
		this.logic = logic;
	}

	MenuBar createMenuBar() {
		// メニューバー本体
		MenuBar res = new MenuBar();
		res.add(createFileMenu(logic));
		return res;
	}

	private Menu createFileMenu(ScreenShot logic) {
		Menu res = new Menu("ファイル");
		// メニュー項目の生成
		MenuItem mi = new MenuItem("出力先の変更");
		mi.setActionCommand(MenuSet.CHANGE_OUTPUT_DIR);
		mi.addActionListener(this);
		res.add(mi);

		// mi = new MenuItem("出力先ディレクトリを開く");
		// mi.setActionCommand(MenuSet.OPEN_OUTPUT_DIR);
		// mi.addActionListener(this);
		// res.add(mi);

		return res;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(MenuSet.CHANGE_OUTPUT_DIR)) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			fileChooser.setMultiSelectionEnabled(false);
			int selected = fileChooser.showOpenDialog(this.mainFrame);
			if (selected == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				if (file.isDirectory()) {
					this.logic.setOutputDir(file.getAbsolutePath());
				}
			}
			// } else if (e.getActionCommand().equals(MenuSet.OPEN_OUTPUT_DIR))
			// {
		}
	}
}
