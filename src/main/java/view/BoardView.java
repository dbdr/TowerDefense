package view;

import TowerDefense.Attacker;
import TowerDefense.Board;
import TowerDefense.Tower;
import com.codingame.gameengine.module.entities.GraphicEntityModule;
import com.codingame.gameengine.module.entities.Group;
import com.codingame.gameengine.module.entities.Sprite;
import com.codingame.gameengine.module.tooltip.TooltipModule;

public class BoardView {
	public static final int CELL_SIZE = 100;
	private Board board;
	private GraphicEntityModule graphics;
	private Group boardGroup;
	private TooltipModule tooltips;

	public BoardView(Board board, GraphicEntityModule graphics, TooltipModule tooltips) {
		this.board = board;
		board.setView(this);
		this.graphics = graphics;
		this.tooltips = tooltips;

		boardGroup = graphics.createGroup();
		//boardGroup.setScale(1080.0 / (board.getHeight() * CELL_SIZE));
        // TODO: switch gridgroup back to BufferedGroup
		Group gridGroup = graphics.createGroup();
		boardGroup.add(gridGroup);
		Group innerGroup = graphics.createGroup();
		gridGroup.add(innerGroup);

		for (int x = 0; x < board.getWidth(); x++) {
			for (int y = 0; y < board.getHeight(); y++) {
				if (board.getGrid()[x][y].canBuild()) {
					Sprite plateau = Utils.createBoardSprite(graphics, "plateau.png", x, y);
					tooltips.setTooltipText(plateau, "x: " + x + "\ny: " + y);
					innerGroup.add(plateau);
				} else if (board.getGrid()[x][y].canEnter()) {
					Sprite canyon = Utils.createBoardSprite(graphics, "canyon.png", x, y);
                    tooltips.setTooltipText(canyon, "x: " + x + "\ny: " + y);
					innerGroup.add(canyon);
				}
			}
		}
	}

	public void addAttacker(Attacker attacker) {
		AttackerView view = new AttackerView(attacker, boardGroup, graphics, tooltips);
	}

	public void addTower(Tower tower) {
		TowerView view = tower.createView(boardGroup, graphics, tooltips);
	}
}
