package progprak.gruppe53.game.inventory;

import java.io.Serializable;

import progprak.gruppe53.game.GameLogic;
import progprak.gruppe53.items.Item;
import progprak.gruppe53.items.Weapon;
import progprak.gruppe53.items.armor.Armor;
import progprak.gruppe53.items.potions.Potion;
import progprak.gruppe53.sprites.characters.Hero;

public class Inventory implements Serializable {

	private static final long serialVersionUID = 2765576774412774911L;
	private Weapon weapon;
	private Armor armor;
	private Item[] items;
	private int inventorySlots = 10;
	private Hero hero;
	private transient GameLogic gameLogic;

	/**
	 * @param hero
	 *            : Requires a Hero
	 * @param gameLogic
	 *            : Requires the gameLogic Initialises the Inventory of the Hero
	 */
	public Inventory(Hero hero, GameLogic gameLogic) {
		items = new Item[inventorySlots + 1];
		this.hero = hero;
		this.gameLogic = gameLogic;
	}

	/**
	 * @return returns Weapon from Weaponslot
	 */
	public Weapon getWeapon() {
		return weapon;
	}

	/**
	 * @return returns Armor from Armorslot
	 */
	public Armor getArmor() {
		return armor;
	}

	/**
	 * @return returns true, if the Inventory has a empty Slot
	 */
	public boolean hasFreeSlot() {
		for (int i = 0; i < inventorySlots; i++) {
			if (items[i] == null) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @param item
	 *            : Required new Item for Inventory Adds the new Item to an
	 *            empty InventorySlot
	 */
	public void addItem(Item item) {
		for (int i = 0; i < inventorySlots; i++) {
			if (items[i] == null) {
				items[i] = item;
				return;
			}
		}
	}

	/**
	 * @return Returns all Items
	 */
	public Item[] getItems() {
		return items;
	}

	/**
	 * @param slotNumber
	 *            : Required the SlotNumber Uses the Item in the Inventoryslot
	 *            with the SlotNumber
	 */
	public void slotClicked(int slotNumber) {
		if (items[slotNumber] != null) {
			// if the Slot is empty, the click has no effect
			if (hero.isShopOpen()) {
				if (items[slotNumber] instanceof Potion) {
					hero.setMoney(hero.getMoney()
							+ (int) items[slotNumber].getPrice() / 2);
					items[slotNumber] = null;
				} else {
					hero.setMoney(hero.getMoney()
							+ (int) items[slotNumber].getPrice() / 2);
					hero.getShop().newItem(items[slotNumber]);
					items[slotNumber] = null;
				}
			} else {
				// if the Slot contains a Weapon, it is added to the Weaponslot
				if (items[slotNumber] instanceof Weapon) {
					items[inventorySlots] = weapon;
					weapon = (Weapon) items[slotNumber];
					items[slotNumber] = items[inventorySlots];
					gameLogic.addSprite(weapon);
				}
				// if the Slot contains an Armor , it is added to the Armorslot
				else if (items[slotNumber] instanceof Armor) {
					items[inventorySlots] = armor;
					armor = (Armor) items[slotNumber];
					items[slotNumber] = items[inventorySlots];
					gameLogic.addSprite(armor);
				}
				// if the Slot contains a useable Item, it is used and removed
				// from the Inventory
				else {
					items[slotNumber].use();
					items[slotNumber] = null;
				}
			}
		}
	}

}
