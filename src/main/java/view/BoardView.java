package view;

import com.codingame.gameengine.module.entities.BufferedGroup;
import com.codingame.gameengine.module.entities.GraphicEntityModule;
import com.codingame.gameengine.module.entities.Group;
import com.codingame.gameengine.module.entities.Sprite;

import TowerDefense.Attacker;
import TowerDefense.Board;
import TowerDefense.Tower;

public class BoardView {
	public static final int CELL_SIZE = 100;
	private Board board;
	private GraphicEntityModule graphics;
	private Group boardGroup;

	public BoardView(Board board, GraphicEntityModule graphics) {
		this.board = board;
		board.setView(this);
		this.graphics = graphics;

		boardGroup = graphics.createGroup();
		BufferedGroup gridGroup = graphics.createBufferedGroup();
		boardGroup.add(gridGroup);
		Group innerGroup = graphics.createGroup();
		gridGroup.add(innerGroup);

		for (int x = 0; x < board.getWidth(); x++) {
			for (int y = 0; y < board.getHeight(); y++) {
				if (board.getGrid()[x][y].canBuild()) {
					Sprite plateau = Utils.createBoardSprite(graphics, "plateau.png", x, y);
					innerGroup.add(plateau);
				} else if (board.getGrid()[x][y].canEnter()) {
					Sprite canyon = Utils.createBoardSprite(graphics, "canyon.png", x, y);
					innerGroup.add(canyon);
					if (x == 0) {
						Sprite headquarter = Utils.createBoardSprite(graphics, "headquarter.png", x, y);
						headquarter.setTint(board.getPlayer(0).getColor());
						innerGroup.add(headquarter);
					}
					if (x == board.getWidth() - 1) {
						Sprite headquarter = Utils.createBoardSprite(graphics, "headquarter.png", x, y);
						headquarter.setTint(board.getPlayer(1).getColor());
						innerGroup.add(headquarter);
					}
				}
			}
		}
	}

	public void addAttacker(Attacker attacker) {
		AttackerView view = new AttackerView(attacker, boardGroup, graphics);
	}

	public void addTower(Tower tower) {
		TowerView view = tower.createView(boardGroup, graphics);
	}
}
