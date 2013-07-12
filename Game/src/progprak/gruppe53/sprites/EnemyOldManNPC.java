package progprak.gruppe53.sprites;

import progprak.gruppe53.game.CollisionEvent;
import progprak.gruppe53.game.GameLogic;

public class EnemyOldManNPC extends Enemy {


	private static final long serialVersionUID = 2838843254158087591L;
	
	private double speed = 1;
	
	
	public EnemyOldManNPC(int x, int y, GameLogic gameLogic) {
		super(x, y, "images/OldManNPC.png",gameLogic);
		dx = speed;
		health = 20;
		damageType = 3;
	}
	@Override
	protected void handleMassiveEvent(CollisionEvent ce){
		if(ce.getDirection() == CollisionEvent.DIRECTION_VERTICAL && Math.signum(dy) == -1){
			dx = speed;
			dy = 0;
		}
		if(ce.getDirection() == CollisionEvent.DIRECTION_VERTICAL && Math.signum(dy) == 1){
			dx = -speed;
			dy = 0;
		}
		if(ce.getDirection() == CollisionEvent.DIRECTION_HORIZONTAL && Math.signum(dx) == 1 ){
			dx = 0;
			dy = speed;
		}
		if(ce.getDirection() == CollisionEvent.DIRECTION_HORIZONTAL && Math.signum(dx) == -1 ){
			dx = 0;
			dy = -speed;
		}
		handleEvents = false;
	}
	
	@Override
	protected void handleDie() {
		gameLogic.addSprite(new EnemyGhost((int)getX(),(int)getY(),gameLogic));
		gameLogic.removeSprite(this);
	}
}
