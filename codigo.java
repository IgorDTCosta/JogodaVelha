import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class codigo {
	public static int jogador = 1;
	public static int tabuleiro[][] = new int[3][3];

	public static void main(String[] args) {
		JFrame window = new JFrame();
		window.setBounds(300, 300, 625, 690);
		window.getContentPane().setBackground(Color.gray);
		window.setLayout(null);
		window.setVisible(true);

		JLabel Player = new JLabel("Player 1");
		Player.setFont(new Font("calibri", Font.BOLD, 25));
		Player.setBounds(15, 298, 256, 650);
		Player.setForeground(Color.white);
		window.add(Player);

		JButton botao[][] = new JButton[3][3];
		for (int i = 0; i < botao.length; i++) {
			for (int j = 0; j < botao[i].length; j++) {
				int x, y;
				x = i;
				y = j;

				botao[x][y] = new JButton("");
				botao[x][y].setBounds(10 + (196 * j), 10 + (196 * i), 196, 196);
				botao[x][y].setFont(new Font("Calibri", Font.PLAIN, 150));
				window.add(botao[x][y]);

				botao[x][y].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						tabuleiro[x][y] = jogador;
						
						String simbolo = "";
						if (jogador == 1)
							simbolo = "O";
						else
							simbolo = "X";
						botao[x][y].setText(simbolo);

						jogador *= -1;
						if (jogador == 1)
							Player.setText("Player 1");
						else if (jogador == -1)
							Player.setText("Player 2");
						botao[x][y].setEnabled(false);

						int verificar = 0;

						for (int i = 0; i < tabuleiro[0].length; i++) {
							for (int j = 0; j < tabuleiro.length; j++) {
								verificar += tabuleiro[j][i];
							}

							if (verificar == 3 || verificar == -3)
								break;
							else
								verificar = 0;
						}

						if (verificar == 0) {
							for (int i = 0; i < tabuleiro.length; i++) {
								for (int j = 0; j < tabuleiro[i].length; j++) {
									verificar += tabuleiro[i][j];
								}

								if (verificar == 3 || verificar == -3)
									break;
								else
									verificar = 0;
							}
						}

						if (verificar == 0) {
							if ((tabuleiro[0][0] + tabuleiro[1][1] + tabuleiro[2][2] == 3)
									|| (tabuleiro[0][2] + tabuleiro[1][1] + tabuleiro[2][0] == 3))
								verificar = 3;

							if ((tabuleiro[0][0] + tabuleiro[1][1] + tabuleiro[2][2] == -3)
									|| (tabuleiro[0][2] + tabuleiro[1][1] + tabuleiro[2][0] == -3))
								verificar = -3;
						}

						if (verificar == 0) {
							for (int ii = 0; ii < tabuleiro.length; ii++) {
								for (int jj = 0; jj < tabuleiro[ii].length; jj++) {
									if (tabuleiro[ii][jj] != 0)
										verificar++;
								}
							}
							if (verificar != 9)
								verificar = 0;
						}

						if (verificar == 3 || verificar == -3 || verificar == 9) {
							if (verificar == 3)
								JOptionPane.showMessageDialog(null, "Player 1 Ganhou");
							else if (verificar == -3)
								JOptionPane.showMessageDialog(null, "Player 2 Ganhou");
							else if (verificar == 9)
								JOptionPane.showMessageDialog(null, "Velha");

							for (int ii = 0; ii < botao.length; ii++) {
								for (int jj = 0; jj < botao[ii].length; jj++) {
									botao[ii][jj].setText("");
									botao[ii][jj].setEnabled(true);
									tabuleiro[ii][jj] = 0;
								}
							}

						}

					}
				});
			}
		}
	}

}
