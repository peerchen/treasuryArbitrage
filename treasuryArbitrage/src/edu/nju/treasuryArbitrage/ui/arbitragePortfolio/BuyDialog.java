package edu.nju.treasuryArbitrage.ui.arbitragePortfolio;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import edu.nju.treasuryArbitrage.factory.DataInterfaceFactory;
import edu.nju.treasuryArbitrage.logic.dataInterface.DataInterface;
import edu.nju.treasuryArbitrage.model.Arb_detail;
import edu.nju.treasuryArbitrage.ui.navigater.Navigater;

public class BuyDialog extends JDialog {
	private static final long serialVersionUID = 3595675326727853779L;

	private static final Color BACKGROUND_COLOR = Navigater.BACKGROUND_COLOR;
	private static final Color FOREGROUND_COLOR = Color.BLACK;

	private static final Font NORMAL_FONT = new Font("΢���ź�", Font.PLAIN, 18);

	private JLabel name = new BordedLabel("��Լ����", JLabel.CENTER);
	private JLabel name1 = new BordedLabel("", JLabel.CENTER);
	private JLabel name2 = new BordedLabel("", JLabel.CENTER);

	private JLabel direction = new BordedLabel("��������", JLabel.CENTER);
	private JLabel dir1 = new BordedLabel("", JLabel.CENTER);
	private JLabel dir2 = new BordedLabel("", JLabel.CENTER);

	private JLabel price = new BordedLabel("��Լ�۸�", JLabel.CENTER);
	private JLabel price1 = new BordedLabel("", JLabel.CENTER);
	private JLabel price2 = new BordedLabel("", JLabel.CENTER);

	private JLabel holdings = new BordedLabel("����", JLabel.CENTER);
	private JLabel hld = new BordedLabel("��", JLabel.CENTER);

	private JLabel money = new BordedLabel("Ͷ�뱣֤��", JLabel.CENTER);
	private JLabel mny = new BordedLabel("", JLabel.CENTER);

	private JButton confirm = new JButton("ȷ���µ�");
	
	private Arb_detail[] arbGroup;
	private boolean isRecentBuy;
	private int hold;
	private double guar;
	
	public BuyDialog(Arb_detail[] arbGroup, boolean isRecentBuy, int hold, double guar) {
		this.arbGroup = arbGroup;
		this.isRecentBuy = isRecentBuy;
		this.hold = hold;
		this.guar = guar;
		
		this.setBackground(BACKGROUND_COLOR);
		this.setLayout(null);
		this.setSize(700, 380);
		this.setResizable(false);
		this.setModal(true);
		this.setTitle("�µ�");
		this.setLocationRelativeTo(null);
		
		name.setBounds(30, 50, 200, 40);
		name.setFont(NORMAL_FONT);
		name.setForeground(FOREGROUND_COLOR);
		name1.setBounds(230, 50, 200, 40);
		name2.setBounds(430, 50, 200, 40);
		name1.setFont(NORMAL_FONT);
		name1.setForeground(FOREGROUND_COLOR);
		name2.setFont(NORMAL_FONT);
		name2.setForeground(FOREGROUND_COLOR);
		this.add(name);
		this.add(name1);
		this.add(name2);

		direction.setBounds(30, 90, 200, 40);
		direction.setFont(NORMAL_FONT);
		direction.setForeground(FOREGROUND_COLOR);
		dir1.setBounds(230, 90, 200, 40);
		dir2.setBounds(430, 90, 200, 40);
		dir1.setFont(NORMAL_FONT);
		dir1.setForeground(FOREGROUND_COLOR);
		dir2.setFont(NORMAL_FONT);
		dir2.setForeground(FOREGROUND_COLOR);
		this.add(direction);
		this.add(dir1);
		this.add(dir2);

		price.setBounds(30, 130, 200, 40);
		price.setFont(NORMAL_FONT);
		price.setForeground(FOREGROUND_COLOR);
		price1.setBounds(230, 130, 200, 40);
		price2.setBounds(430, 130, 200, 40);
		price1.setFont(NORMAL_FONT);
		price1.setForeground(FOREGROUND_COLOR);
		price2.setFont(NORMAL_FONT);
		price2.setForeground(FOREGROUND_COLOR);
		this.add(price);
		this.add(price1);
		this.add(price2);

		holdings.setBounds(30, 170, 200, 40);
		holdings.setFont(NORMAL_FONT);
		holdings.setForeground(FOREGROUND_COLOR);
		hld.setBounds(230, 170, 400, 40);
		hld.setFont(NORMAL_FONT);
		hld.setForeground(FOREGROUND_COLOR);
		this.add(holdings);
		this.add(hld);

		money.setBounds(30, 210, 200, 40);
		money.setFont(NORMAL_FONT);
		money.setForeground(FOREGROUND_COLOR);
		mny.setBounds(230, 210, 400, 40);
		mny.setFont(NORMAL_FONT);
		mny.setForeground(FOREGROUND_COLOR);
		this.add(money);
		this.add(mny);


		confirm.setBounds(180, 300, 300, 30);
		confirm.setContentAreaFilled(false);
		confirm.setBorderPainted(true);
		confirm.setFocusPainted(false);
		confirm.setFont(NORMAL_FONT);
		confirm.setForeground(FOREGROUND_COLOR);
		confirm.addActionListener(new ConfirmListener());
		this.add(confirm);
		
		name1.setText(arbGroup[0].getSymbol());
		name2.setText(arbGroup[1].getSymbol());
		if (isRecentBuy) {
			dir1.setText("��ͷ");
			dir2.setText("��ͷ");
		} else {
			dir1.setText("��ͷ");
			dir2.setText("��ͷ");
		}
		price1.setText(String.valueOf(arbGroup[0].getPresentPrice()));
		price2.setText(String.valueOf(arbGroup[1].getPresentPrice()));
		hld.setText(hold + "��");
		mny.setText(guar + "Ԫ");
	}

	public class ConfirmListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			BuyDialog.this.setVisible(false);
			JOptionPane.showMessageDialog(null, "�µ��ɹ���");
			
			Arb_detail buy, sell;
			if (isRecentBuy) {
				buy = arbGroup[0];
				sell = arbGroup[1];
			} else {
				buy = arbGroup[1];
				sell = arbGroup[0];
			}
			
			DataInterface di = DataInterfaceFactory.getInstance().getDataInterfaceToServer();
			di.Order(buy.getSymbol(), sell.getSymbol(), buy.getPresentPrice(), sell.getPresentPrice(), 
					hold, guar);
			BuyDialog.this.dispose();
		}
	}

	private class BordedLabel extends JLabel {
		private static final long serialVersionUID = -7586450516168737600L;

		public BordedLabel(String text, int location) {
			super(text, location);
			setBorder(BorderFactory.createLineBorder(new Color(122, 138, 153)));
		}
	}

}