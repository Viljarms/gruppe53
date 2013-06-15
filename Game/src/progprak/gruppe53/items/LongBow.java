package progprak.gruppe53.items;

import progprak.gruppe53.game.CollisionEvent;
import progprak.gruppe53.game.Game;

public class LongBow extends RangeWeapon{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4274376584235570942L;
	
	private Game game;

	public LongBow(int x, int y,Game game) {
		super(x, y, "",game);
		this.game = game;
		
		doInitalizations();

	}
	
	public LongBow(Game game){
		super(0,0, "",game);
		collisionEvent = new CollisionEvent(CollisionEvent.EVENT_NOTHING,this);
		draw = false;
		doInitalizations();
	}
	
	private void doInitalizations(){
		price = 100;
	}

	@Override
	public void doLogic(long delta) {

		
	}

}
