package progprak.gruppe53.sprites.projectils;

import progprak.gruppe53.game.Collidable;
import progprak.gruppe53.game.CollisionEvent;
import progprak.gruppe53.game.DamageCollisionEvent;
import progprak.gruppe53.game.GameLogic;
import progprak.gruppe53.game.Shooter;
import progprak.gruppe53.sprites.CombatObject;

public class SpiderWebBall extends Projectile implements Collidable{
	
	private static final long serialVersionUID = 1L;

	private double slowFactor;
	
	/**
	 * See class Projectile
	 * 
	 * @param x
	 * @param y
	 * @param gameLogic
	 * @param shooter
	 * @param dx
	 * @param dy
	 * @param faction
	 * @param slowFactor defines the slowFactor, if a target Object gets hit
	 */
	public SpiderWebBall(int x, int y, GameLogic gameLogic, Shooter shooter, double dx, double dy, int faction, double slowFactor){
		super(x,y,"images/spiderweb.png", gameLogic,shooter);
		this.setWidth(16);
		this.setHeight(16);
		this.dx = dx;
		this.dy = dy;
		this.faction = faction;
		this.slowFactor = slowFactor;
		doInitalizations();
	}
	
	@Override
	public void doLogic(long delta){
		

	}
	@Override
	protected void handleMassiveEvent(CollisionEvent ce) {
		super.handleMassiveEvent(ce);
		gameLogic.removeSprite(this);
		shooter.shootRemoved();
	}
	@Override
	protected void doneDamage() {
		super.doneDamage();
		gameLogic.getHero().setSlow(slowFactor);
		gameLogic.removeSprite(this);
		shooter.shootRemoved();
	}
	@Override
	protected void initCollisionEvent() {
		collisionEvent = new DamageCollisionEvent(5,faction,this);
	}
	@Override
	protected void doneKill(CombatObject combatObject) {
		shooter.doneKill(combatObject);
	}
	
}