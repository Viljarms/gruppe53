package progprak.gruppe53.items;

import progprak.gruppe53.game.Collidable;
import progprak.gruppe53.game.Game;

abstract public class Weapon extends Item implements Collidable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected int damage;

	protected int manaCost = 0;
	
	protected int yOffset;

	protected int xOffset;

	
	protected int ww = 0;
	protected int wh = 0;

	private Game game;
	
	public Weapon(int x, int y, String imagePath,Game game) {
		super(x, y, imagePath);
		this.game = game;
	}

	public int getdamage(){
		return damage;
	}
	public void attack(boolean attack){
		if(attack){
			game.getGameLogic().getHero().drainMana(manaCost);
		}
		drawWeapon(attack);
	}
	
	protected void drawWeapon(boolean draw) {
		this.draw = draw;
		if(draw){
			x = owner.getX()+xOffset;
			y = owner.getY()+yOffset; 
			width = ww;
			height = wh;
		}
		else {
			width = height = x = y = 0;
		}
	}
	
}