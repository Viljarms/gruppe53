package progprak.gruppe53.items.meleeWeapons;

import progprak.gruppe53.game.CollisionEvent;
import progprak.gruppe53.game.DamageCollisionEvent;
import progprak.gruppe53.game.GameLogic;
import progprak.gruppe53.items.Weapon;
import progprak.gruppe53.sprites.characters.Hero;

abstract public class MeleeWeapons extends Weapon {

	

	
	private static final long serialVersionUID = -9179616171179500701L;
	protected transient CollisionEvent notAttackCe;
	protected transient CollisionEvent attackCe;
	protected int damage;
	
	public MeleeWeapons(int x, int y, String imagePath,GameLogic gameLogic) {
		super(x, y, imagePath, gameLogic);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doLogic(long delta) {
		// TODO Auto-generated method stub

	}


	@Override
	public void attack(boolean attack) {
		super.attack(attack);
		if(attack){
			//setAngle(Math.PI);
			collisionEvent = attackCe;
		}
		else {
			setWidth(setHeight(setX(setY(0))));
			collisionEvent = notAttackCe;
		}
	}
	@Override
	public void setOwner(Hero owner) {
		super.setOwner(owner);
		attackCe = new DamageCollisionEvent((int)(damage*(1+gameLogic.getHero().getTalentTree().getTalent(9)*0.3)), owner.getFaction(), owner);
	}


}
