package model;

public interface ItemInterface {
	
	// Positions
	final static int hammerPos = 0;
	final static int potionPos = 1;
	final static int spellPos = 2;
	
	// SubPositions	
	// Swords
	final static int ironHammer = 0;
	final static int goldHammer = 1;
	// Potions
	final static int healPotion = 0;
	final static int regenPotion = 1;
	// Spells
	final static int damageSpell = 0;
	final static int otherSpell = 1;
	
	// 2 hammers, 2 kinds of potions, spells 
	final static int itemsPerCategory = 2;
}
