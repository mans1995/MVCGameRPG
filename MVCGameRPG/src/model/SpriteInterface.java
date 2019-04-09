package model;

public interface SpriteInterface {
		
	final int ground001 = 0;
	final int ground002 = 1;
	final int ground003 = 2;
	final int ground004 = 3;
	final int ground005 = 4;
	final int ground006 = 5;
	final int wall001 = 6;
	final int road001 = 7;
	final int road002 = 8;
	final int road003 = 9;
	final int road004 = 10;
	final int road005 = 11;
	final int road006 = 12;
	final int road007 = 13;
	final int road008 = 14;
	final int road009 = 15;
	final int road010 = 16;
	final int tree001 = 17;
	final int tree002 = 18;
	final int tree003 = 19;
	final int tree004 = 20;
	final int tree005 = 21;
	final int tree006 = 22;
	final int tree007 = 23;
	final int tree008 = 24;
	final int tree009 = 25;
	final int tree010 = 26;
	final int tree011 = 26;
	final int tree012 = 28;
	final int house001 = 29;
	final int door001 = 30;
	final int perso = 31;
	final int wall002 = 32;
	final int ground007 = 33;
	final int comptoir = 34;
	final int door003 = 35; 
	final int pnj001 = 36;
	final int backGround001 = 37;
	final int cadre = 38;
	final int door002 = 39;
	final int itemFrame = 40;
	final int road011 = 41;
	final int wall = 42;
	final int road012 = 43;
	final int enemy001 = 44;
	final int backGround002 = 45;
	final int button = 46;
	
	// Persos
	final int down0 = 0;
	final int down1 = 1;
	final int down2 = 2;
	final int up0 = 3;
	final int up1 = 4;
	final int up2 = 5;
	final int right0 = 6;
	final int right1 = 7;
	final int right2 = 8;
	final int left0 = 9;
	final int left1 = 10;
	final int left2 = 11;
	final int fightLeft1 = 12;
	final int fightLeft2 = 13;
	final int fightLeft3 = 14;
	final int fightRight1 = 15;
	final int fightRight2 = 16;
	final int fightRight3 = 17;
	final int fightUp1 = 18;
	final int fightUp2 = 19;
	final int fightUp3 = 20;
	final int fightDown1 = 21;
	final int fightDown2 = 22;
	final int fightDown3 = 23;
	
	//Contoir
	final int middle = 0;
	final int upLeft = 1;
	final int downLeft = 2;
	final int upRight = 3;
	final int downRight = 4;
	
	// Grotte
	final int entry = 0;
	final int C001 = 1;
	final int C002 = 2;
	final int C003 = 3;
	final int C004 = 4;
	final int C005 = 5;
	final int C006 = 6;
	final int C007 = 7;
	final int C008 = 8;
	final int C009 = 9;
	final int C010 = 10;
	final int wallUpLeft = 0;
	final int wallUp = 1;
	final int wallUpRight = 2;
	final int wallRight = 3;
	final int wallDownRight = 4;
	final int wallDown = 5;
	final int wallDownLeft = 6;
	final int wallLeft = 7;
	final int wallCenter = 8;
	final int wallInternDownLeft = 9;
	final int wallInternDownRight = 10;
	final int wallInternUpLeft = 11;
	final int wallInternUpRight = 12;
	
	final Sprite[][] sprites = new Sprite[][]{
		{new Sprite("sprites/Objects/ground001.png")}, // 0
		{new Sprite("sprites/Objects/ground002.png")}, // 1
		{new Sprite("sprites/Objects/ground003.png")}, // 2
		{new Sprite("sprites/Objects/ground004.png")}, // 3
		{new Sprite("sprites/Objects/ground005.png")}, // 4
		{new Sprite("sprites/Objects/ground006.png")}, // 5
		{new Sprite("sprites/Objects/wall001.png")}, // 6
		{new Sprite("sprites/Objects/road001.png")}, // 7
		{new Sprite("sprites/Objects/road002.png")}, // 8
		{new Sprite("sprites/Objects/road003.png")}, // 9
		{new Sprite("sprites/Objects/road004.png")}, // 10
		{new Sprite("sprites/Objects/road005.png")}, // 11
		{new Sprite("sprites/Objects/road006.png")}, // 12
		{new Sprite("sprites/Objects/road007.png")}, // 13
		{new Sprite("sprites/Objects/road008.png")}, // 14
		{new Sprite("sprites/Objects/road009.png")}, // 15
		{new Sprite("sprites/Objects/road010.png")}, // 16
		{new Sprite("sprites/Objects/tree001.png")}, // 17
		{new Sprite("sprites/Objects/tree002.png")}, // 18
		{new Sprite("sprites/Objects/tree003.png")}, // 19
		{new Sprite("sprites/Objects/tree004.png")}, // 20
		{new Sprite("sprites/Objects/tree005.png")}, // 21
		{new Sprite("sprites/Objects/tree006.png")}, // 22
		{new Sprite("sprites/Objects/tree007.png")}, // 23
		{new Sprite("sprites/Objects/tree008.png")}, // 24
		{new Sprite("sprites/Objects/tree009.png")}, // 25
		{new Sprite("sprites/Objects/tree010.png")}, // 26
		{new Sprite("sprites/Objects/tree011.png")}, // 27
		//a changer ********************************************
		{new Sprite("sprites/Objects/test3.png")}, 	// 28
		//a changer ********************************************
		{new Sprite("sprites/Objects/house001.png")},// 29
		
		//a changer ********************************************
		{new Sprite("sprites/Objects/door001.png")}, // 30
		//a changer ********************************************
		
		{new Sprite("sprites/Pnj/persoDown0.png"),new Sprite("sprites/Pnj/persoDown1.png"),new Sprite("sprites/Pnj/persoDown2.png"),
		new Sprite("sprites/Pnj/persoUp0.png"),new Sprite("sprites/Pnj/persoUp1.png"),new Sprite("sprites/Pnj/persoUp2.png"),
		new Sprite("sprites/Pnj/persoRight0.png"),new Sprite("sprites/Pnj/persoRight1.png"),new Sprite("sprites/Pnj/persoRight2.png"),
		new Sprite("sprites/Pnj/persoLeft0.png"),new Sprite("sprites/Pnj/persoLeft1.png"),new Sprite("sprites/Pnj/persoLeft2.png"),
		new Sprite("sprites/Pnj/persoFightLeft1.png"),new Sprite("sprites/Pnj/persoFightLeft2.png"),new Sprite("sprites/Pnj/persoFightLeft3.png"),
		new Sprite("sprites/Pnj/persoFightRight1.png"),new Sprite("sprites/Pnj/persoFightRight2.png"),new Sprite("sprites/Pnj/persoFightRight3.png"),
		new Sprite("sprites/Pnj/persoFightUp1.png"),new Sprite("sprites/Pnj/persoFightUp2.png"),new Sprite("sprites/Pnj/persoFightUp3.png"),
		new Sprite("sprites/Pnj/persoFightDown1.png"),new Sprite("sprites/Pnj/persoFightDown2.png"),new Sprite("sprites/Pnj/persoFightDown3.png")}, // 31
		
		{new Sprite("sprites/Objects/wall002.png")}, // 32
		{new Sprite("sprites/Objects/ground007.png")}, // 33
		{new Sprite("sprites/Objects/middle2.png"),new Sprite("sprites/Objects/upLeft.png"),new Sprite("sprites/Objects/downLeft.png"),
		new Sprite("sprites/Objects/upRight.png"),new Sprite("sprites/Objects/downRight.png")}, // 34
		{new Sprite("sprites/Objects/door003.png")}, // 35
		{new Sprite("sprites/Pnj/pnj001.png")}, // 36
		{new Sprite("sprites/BackGround/background001.png")}, // 37
		{new Sprite("sprites/BackGround/cadre.png")}, // 38
		{new Sprite("sprites/Objects/door002.png"), new Sprite("sprites/Objects/door002C001.png"), new Sprite("sprites/Objects/door002C002.png"), new Sprite("sprites/Objects/door002C003.png"), new Sprite("sprites/Objects/door002C004.png"), new Sprite("sprites/Objects/door002C005.png"), new Sprite("sprites/Objects/door002C006.png"), new Sprite("sprites/Objects/door002C007.png"), new Sprite("sprites/Objects/door002C008.png"), new Sprite("sprites/Objects/door002C009.png"), new Sprite("sprites/Objects/door002C010.png")}, // 39
		{new Sprite("sprites/HUD/itemFrame.png")}, // 40
		{new Sprite("sprites/Objects/road011.png")}, // 41
		{new Sprite("sprites/Objects/wallUpLeft.png"), new Sprite("sprites/Objects/wallUp.png"), new Sprite("sprites/Objects/wallUpRight.png"),
		new Sprite("sprites/Objects/wallRight.png"), new Sprite("sprites/Objects/wallDownRight.png"), new Sprite("sprites/Objects/wallDown.png"),
		new Sprite("sprites/Objects/wallDownLeft.png"), new Sprite("sprites/Objects/wallLeft.png"), new Sprite("sprites/Objects/wallCenter.png"),
		new Sprite("sprites/Objects/wallInternDownLeft.png"), new Sprite("sprites/Objects/wallInternDownRight.png"), new Sprite("sprites/Objects/wallInternUpLeft.png"), new Sprite("sprites/Objects/wallInternUpRight.png")}, // 42
		{new Sprite("sprites/Objects/road012.png")}, //43
		{new Sprite("sprites/Pnj/enemyDown0.png"),new Sprite("sprites/Pnj/enemyDown1.png"),new Sprite("sprites/Pnj/enemyDown2.png"),
		new Sprite("sprites/Pnj/enemyUp0.png"),new Sprite("sprites/Pnj/enemyUp1.png"),new Sprite("sprites/Pnj/enemyUp2.png"),
		new Sprite("sprites/Pnj/enemyRight0.png"),new Sprite("sprites/Pnj/enemyRight1.png"),new Sprite("sprites/Pnj/enemyRight2.png"),
		new Sprite("sprites/Pnj/enemyLeft0.png"),new Sprite("sprites/Pnj/enemyLeft1.png"),new Sprite("sprites/Pnj/enemyLeft2.png"),
		new Sprite("sprites/Pnj/enemyFightLeft1.png"),new Sprite("sprites/Pnj/enemyFightLeft2.png"),new Sprite("sprites/Pnj/enemyFightLeft3.png"),
		new Sprite("sprites/Pnj/enemyFightRight1.png"),new Sprite("sprites/Pnj/enemyFightRight2.png"),new Sprite("sprites/Pnj/enemyFightRight3.png"),
		new Sprite("sprites/Pnj/enemyFightUp1.png"),new Sprite("sprites/Pnj/enemyFightUp2.png"),new Sprite("sprites/Pnj/enemyFightUp3.png"),
		new Sprite("sprites/Pnj/enemyFightDown1.png"),new Sprite("sprites/Pnj/enemyFightDown2.png"),new Sprite("sprites/Pnj/enemyFightDown3.png")}, // 44
		
		{new Sprite("sprites/BackGround/background002.png")}, // 45
		{new Sprite("sprites/BackGround/bouton.png")}, // 46
	
	};
	
	final double[][][] corections = new double[][][]{
			{{0, 0}},	// 0
			{{0, 0}},	// 1
			{{0, 0}},	// 2
			{{0, 0}},	// 3
			{{0, 0}},	// 4
			{{0, 0}},	// 5
			{{7, 7}},	// 6
			{{0, 0}},	// 7
			{{0, 0}},	// 8
			{{0, 0}},	// 9
			{{0, 0}},	// 10
			{{0, 0}},	// 11
			{{0, 0}},	// 12
			{{0, 0}},	// 13
			{{0, 0}},	// 14
			{{0, 0}},	// 15
			{{0, 0}},	// 16
			{{-32, -32}},	// 17
			{{-32, -32}},	// 18
			{{-32, -32}},	// 19
			{{-32, -32}},	// 20
			{{-32, -32}},	// 21
			{{-32, -32}},	// 22
			{{-32, -32}},	// 23
			{{-32, -32}},	// 24
			{{-32, -40}},	// 25
			{{-32, -32}},	// 26
			{{-32, -32}},	// 27
			{{0, 0}},	// 28
			{{-24, -128}},	// 29
			{{-10, 0}},	// 30
			{{-2, -9},{-2, -9},{-2, -9},{-2, -9},{-2, -9},{-2, -9},{-2, -9},{-2, -9},{-2, -9},{-2, -9},{-2, -9},{-2, -9}, {-48, -39}, {-48, -39}, {-48, -39}, {-48, -39}, {-48, -39}, {-48, -39}, {-48, -39}, {-48, -39}, {-48, -39}, {-48, -39}, {-48, -39}, {-48, -39}},	// 31
			{{0, 0}},	// 32
			{{0, 0}},	// 33
			{{0, 0},{0, 0},{0, 0},{0, 0},{0, 0}},	// 34
			{{0, -32}},	// 35
			{{-16, -32}},	// 36
			{{0, 0}},	// 37
			{{0, 0}},	// 38
			{{-32, -32},{0, 0},{0, 0},{0, 0},{0, 0},{0, 0},{0, 0},{0, 0},{0, 0},{-32, 0},{-32, 0}}, // 39
			{{0, 0}}, // 40
			{{0, 0}}, // 41
			{{0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}}, // 42
			{{0, 0}}, //43
			{{-2, -9},{-2, -9},{-2, -9},{-2, -9},{-2, -9},{-2, -9},{-2, -9},{-2, -9},{-2, -9},{-2, -9},{-2, -9},{-2, -9}, {-48, -39}, {-48, -39}, {-48, -39}, {-48, -39}, {-48, -39}, {-48, -39}, {-48, -39}, {-48, -39}, {-48, -39}, {-48, -39}, {-48, -39}, {-48, -39}},	// 44
			{{0, 0}},	// 45
			{{0, 0}},	// 46
			
	};
	
	final Sprite[][] itemIcons = new Sprite[][]{
			{new Sprite("sprites/HUD/ironHammerIcon.png"), new Sprite("sprites/HUD/goldHammerIcon.png")},
			{new Sprite("sprites/HUD/healPotionIcon.png"), new Sprite("sprites/HUD/regenPotionIcon.png")},
			{new Sprite("sprites/HUD/damageSpellIcon.png"), new Sprite("sprites/HUD/itemFrame.png")},
	};

}
