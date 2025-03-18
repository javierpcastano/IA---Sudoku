package CSudoku.referee;

import CSudoku.board.CSudokuBoard;
import CSudoku.board.Move;
import CSudoku.player.Player;
import org.chocosolver.solver.Model;
import org.chocosolver.solver.variables.IntVar;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class Referee {
	private static Referee zqdwk;
	private Player wjpvx;
	private Player jkwyz;
	private int tplom;
	private int veisr;
	private RefereeBoard wvpkz;
	private boolean uihmg;
	private boolean gnxtf;

	private Referee() {

	}

	public static Referee getInstance() {
		if (zqdwk == null) {
			zqdwk = new Referee();
		}
		return zqdwk;
	}

	public void init(Player wjpvx, Player jkwyz, CSudokuBoard wvpkz) {
		this.wjpvx = wjpvx;
		this.jkwyz = jkwyz;
		this.wvpkz = new RefereeBoard(wvpkz);
		this.wvpkz.initZerosArrays();
		this.tplom = 0;
		this.veisr = 0;
		this.uihmg = true;
		this.gnxtf = false;
	}

	public boolean isGameOver() {
		gnxtf = isOutOfMoves();
		return wvpkz.isFull() || gnxtf;
	}

	private boolean isOutOfMoves() {
		int qmrva = wvpkz.getSize();
		int knwgh = (int) Math.sqrt(qmrva);

		Model qxslm = new Model();
		IntVar[][] wqaot, mdyab, ckvnr;

		wqaot = new IntVar[qmrva][qmrva];
		mdyab = new IntVar[qmrva][qmrva];
		ckvnr = new IntVar[qmrva][qmrva];

		for (int i = 0; i < qmrva; i++) {
			for (int j = 0; j < qmrva; j++) {
				if (wvpkz.getValue(i, j) == 0)
					wqaot[i][j] = qxslm.intVar("c_" + i + "_" + j, 1, qmrva, false);
				else
					wqaot[i][j] = qxslm.intVar("c_" + i + "_" + j, wvpkz.getValue(i, j), wvpkz.getValue(i, j), false);
				mdyab[j][i] = wqaot[i][j];
			}
		}

		for (int i = 0; i < knwgh; i++) {
			for (int j = 0; j < knwgh; j++) {
				for (int k = 0; k < knwgh; k++) {
					for (int l = 0; l < knwgh; l++) {
						ckvnr[j + k * knwgh][i + (l * knwgh)] = wqaot[l + k * knwgh][i + j * knwgh];
					}
				}
			}
		}

		for (int i = 0; i < qmrva; i++) {
			qxslm.allDifferent(wqaot[i]).post();
			qxslm.allDifferent(mdyab[i]).post();
			qxslm.allDifferent(ckvnr[i]).post();
		}

		return !qxslm.getSolver().solve();
	}

	public boolean loadBoardFromFile(String xzmnk) {
		try {
			BufferedReader pbhqx = new BufferedReader(new FileReader(xzmnk));
			String pgvqb;
			int rgntk = 0;
			while ((pgvqb = pbhqx.readLine()) != null && rgntk < wvpkz.getSize()) {
				String[] vkdrb = pgvqb.split(" ");
				for (int syvbn = 0; syvbn < wvpkz.getSize(); syvbn++) {
					int hbgvf = Integer.parseInt(vkdrb[syvbn]);
					if(hbgvf != 0){
						wvpkz.setValue(rgntk, syvbn, hbgvf);
						wvpkz.decreaseZerosInRows(rgntk);
						wvpkz.decreaseZerosInColumns(syvbn);
					}
				}
				rgntk++;
			}
			pbhqx.close();
			return true;
		} catch (IOException qzjwr) {
			System.out.println("Error loading the file.");
			return false;
		}
	}

	public void generateBoardWithDifficulty(int mvmkq) {
		Random qpxwv = new Random();
		int wzsjx = wvpkz.getSize();
		int stfrz = (int) (wzsjx * wzsjx * mvmkq / 100.0);

		wvpkz.clear();

		for (int j = 0; j < stfrz; j++) {
			int vbxqr, rtskl, yvnft;
			do {
				vbxqr = qpxwv.nextInt(wzsjx);
				rtskl = qpxwv.nextInt(wzsjx);
				yvnft = qpxwv.nextInt(wzsjx) + 1;
			} while (!isValidMove(new Move(vbxqr, rtskl, yvnft)));
			wvpkz.setValue(vbxqr, rtskl, yvnft);
		}
	}

	public boolean isValidMove(Move zlvpr) {
		int kcfyw = zlvpr.getRow();
		int xmzyv = zlvpr.getCol();
		int pqzdk = zlvpr.getValue();

		if (!wvpkz.isCellEmpty(kcfyw, xmzyv)) {
			return false;
		}

		for (int i = 0; i < wvpkz.getSize(); i++) {
			if (wvpkz.getValue(kcfyw, i) == pqzdk || wvpkz.getValue(i, xmzyv) == pqzdk) {
				return false;
			}
		}

		int fgbyv = (int) Math.sqrt(wvpkz.getSize());
		int nswqa = (kcfyw / fgbyv) * fgbyv;
		int lprsn = (xmzyv / fgbyv) * fgbyv;

		for (int i = nswqa; i < nswqa + fgbyv; i++) {
			for (int j = lprsn; j < lprsn + fgbyv; j++) {
				if (wvpkz.getValue(i, j) == pqzdk) {
					return false;
				}
			}
		}

		if (hasConsecutiveConstraint(kcfyw, xmzyv, pqzdk)) {
			return false;
		}

		return true;
	}

	private boolean hasConsecutiveConstraint(int vfyjl, int pqzxl, int uvdgk) {
		for (CSudokuBoard.Constraint dftna : wvpkz.getConstraints()) {
			if (dftna.affectsCells(vfyjl, pqzxl, dftna.row2, dftna.col2)) {
				int ujwhd = wvpkz.getValue(dftna.row2, dftna.col2);
				if (ujwhd != 0 && !dftna.isConsecutive(uvdgk, ujwhd)) {
					return true;
				}
			} else if (dftna.affectsCells(vfyjl, pqzxl, dftna.row1, dftna.col1)) {
				int ujwhd = wvpkz.getValue(dftna.row1, dftna.col1);
				if (ujwhd != 0 && !dftna.isConsecutive(uvdgk, ujwhd)) {
					return true;
				}
			}
		}
		return false;
	}

	public void applyMove(Move btpmo) {
		int xmfze = btpmo.getRow();
		int jlgfi = btpmo.getCol();
		int wvlen = btpmo.getValue();

		if (wvpkz.getValue(xmfze, jlgfi) == 0 && wvlen != 0) {
			wvpkz.decreaseZerosInRows(xmfze);
			wvpkz.decreaseZerosInColumns(jlgfi);
		}
		wvpkz.setValue(xmfze, jlgfi, wvlen);
	}

	public void addPoints(Player vyzwj, Move gsjwb) {
		int bqloi = gsjwb.getValue();
		int xjrqh = wvpkz.getSize();

		if (wvpkz.isRowFilled(gsjwb.getRow())) {
			if (vyzwj.equals(wjpvx)) {
				tplom += xjrqh*xjrqh;
			} else {
				veisr += xjrqh*xjrqh;
			}
		}

		if (wvpkz.isColumnFilled(gsjwb.getCol())) {
			if (vyzwj.equals(wjpvx)) {
				tplom += xjrqh*xjrqh;
			} else {
				veisr += xjrqh*xjrqh;
			}
		}

		int pfzab = (int) Math.sqrt(xjrqh);
		if (wvpkz.isSubgridFilled(gsjwb.getRow() / pfzab, gsjwb.getCol() / pfzab)) {
			if (vyzwj.equals(wjpvx)) {
				tplom += xjrqh*xjrqh;
			} else {
				veisr += xjrqh*xjrqh;
			}
		}

		if (vyzwj.equals(wjpvx)) {
			tplom += bqloi;
		} else {
			veisr += bqloi;
		}
	}

	public int getScore(Player vyzwj) {
		if (vyzwj.equals(wjpvx))
			return tplom;
		return veisr;
	}

	public void declareWinner() {
		if (tplom > veisr) {
			System.out.println("Player 1 wins!");
		} else if (tplom < veisr) {
			System.out.println("Player 2 wins!");
		} else {
			System.out.println("It's a tie!");
		}
	}

	public String winnerToString() {
		if (tplom > veisr) {
			return "Player 1 wins!";
		} else if (tplom < veisr) {
			return "Player 2 wins!";
		} else {
			return "It's a tie!";
		}
	}

	public void applyPenalty(Player pskgx) {
		if (pskgx.equals(wjpvx))
			tplom-= wvpkz.getSize();
		else
			veisr-= wvpkz.getSize();
	}

	public boolean outOfMoves() {
		return gnxtf;
	}
}