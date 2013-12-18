package cons;

import java.awt.Image;

class Person {

	private Map place;
	private int xPos;
	private int yPos;
	private String text;
	private Main main;
	private int dialogNr; 				// 0 ... Spricht nicht, n ... Spricht Text n (text wird noch Array)
	private PersonType personType;
	private int direction = 2;
	public int stepX; 					// Bruchteil des Schrittes der X-Position
	public int stepY; 					// Bruchteil des Schrittes der Y-Position
	private int turnBack = 5; 			// Richtung, in die sich Personen nach dem Ansprechen drehen
	private Fighter fighter;
	
	
	Person(Map place, int xPos, int yPos, String text, PersonType personType, Main main)
	{
		this.place = place;
		this.xPos = xPos;
		this.yPos = yPos;
		this.text = text;
		this.main = main;
		this.personType = personType;
		
		place.addPerson(this);
		dialogNr = 0;
	}
	
	Person setDirection(int direction)
	{
		this.direction = direction;
		return this;
	}
	
	void removeFromMap()
	{
		place.removePerson(this);
		place = null;
	}
	
	Person setTurnBack(int turnBack)
	{
		this.turnBack = turnBack;
		return this;
	}
	
	void setOnMap(Map place, int xPos, int yPos)
	{
		this.place = place;
		this.xPos = xPos;
		this.yPos = yPos;
		
		place.addPerson(this);
	}
		    	
	void onClick(int direction)
	{
		if(fighter == null)
		{
			SoundPlayer.soundBeep.play();
			dialogNr = (dialogNr + 1) % 2;
			
			if(dialogNr == 1)
			{
				this.direction = direction;
				place.drawImageP(main);
				main.drawMap();
				main.printDialog(text);
				main.setWalkingEnabled(false);
			}
			else
			{
				if(turnBack < 5)
				{
					this.direction = this.turnBack;
					place.drawImageP(main);
				}
				main.setWalkingEnabled(true);
				main.drawMap();
			}
		}
		else
		{
			main.setInBattle(true);
			main.getPlayer().setEnemy(fighter);
			main.drawBattle(main.getPlayer().fighter, fighter, "\"Lass uns kämpfen!\"");
		}
	}

	void walkOnX(int length)
	{
		if(length > 0)
		{
			for(int step = 0; step < length; step++)
			{
				direction = 1;
		    	if(!(main.getPlayer().xPos == xPos+1 && main.getPlayer().yPos == yPos))
		    	{
		        	for(stepX = 0; stepX > -16; stepX--)
		        	{
		        		place.drawImageP(main);
		        		main.drawMap();
		        	}
		        	stepX = 0;
			    	xPos++;
		    		place.getPersons()[xPos-1][yPos] = null;
		    		place.getPersons()[xPos][yPos] = this;
	        		place.drawImageP(main);
	        		main.drawMap();
		    	}
		    }
		}
		else
		{
			for(int step = 0; step > length; step--)
			{
				direction = 3;
		    	if(!(main.getPlayer().xPos == xPos-1 && main.getPlayer().yPos == yPos))
		    	{
		        	for(stepX = 0; stepX < 16; stepX++)
		        	{
		        		place.drawImageP(main);
		        		main.drawMap();
		        	}
		        	stepX = 0;
			    	xPos--;
		    		place.getPersons()[xPos+1][yPos] = null;
		    		place.getPersons()[xPos][yPos] = this;
	        		place.drawImageP(main);
	        		main.drawMap();
		    	}
		    }
		}
	}
		    	
	void walkOnY(int length)
	{
		if(length > 0)
		{
			for(int step = 0; step < length; step++)
			{
				direction = 2;
		    	if(!(main.getPlayer().xPos == xPos && main.getPlayer().yPos == yPos+1))
		    	{
		        	for(stepY = 0; stepY > -16; stepY--)
		        	{
		        		place.drawImageP(main);
		        		main.drawMap();
		        	}
		        	stepY = 0;
			    	yPos++;
		    		place.getPersons()[xPos][yPos-1] = null;
		    		place.getPersons()[xPos][yPos] = this;
	        		place.drawImageP(main);
	        		main.drawMap();
		    	}
			}
		}
		else
		{
			for(int step = 0; step > length; step--)
			{
				direction = 0;
		    	if(!(main.getPlayer().xPos == xPos && main.getPlayer().yPos == yPos-1))
		    	{
		        	for(stepY = 0; stepY < 16; stepY++)
		        	{
		        		place.drawImageP(main);
		        		main.drawMap();
		        	}
		        	stepY = 0;
			    	yPos--;
		    		place.getPersons()[xPos][yPos+1] = null;
		    		place.getPersons()[xPos][yPos] = this;
	        		place.drawImageP(main);
	        		main.drawMap();
		    	}
		    }
		}
	}
	
	Image getImage()
	{
		if(Math.abs(stepY + stepX) / 4 < 1)
			return personType.getImg()[direction * 3];
		else if(Math.abs(stepY + stepX) / 4 < 2)
			return personType.getImg()[direction * 3 + 1];
		else if(Math.abs(stepY + stepX) / 4 < 3)
			return personType.getImg()[direction * 3];
		else
			return personType.getImg()[direction * 3 + 2];
	}
	
	
		Map getPlace() {
			return place;
		}

		void setPlace(Map place) {
			this.place = place;
		}

		int getxPos() {
			return xPos;
		}

		void setxPos(int xPos) {
			this.xPos = xPos;
		}

		int getyPos() {
			return yPos;
		}

		void setyPos(int yPos) {
			this.yPos = yPos;
		}

		String getText() {
			return text;
		}

		void setText(String text) {
			this.text = text;
		}

		Main getM() {
			return main;
		}

		void setM(Main m) {
			this.main = m;
		}

		int getDialogNr() {
			return dialogNr;
		}

		void setDialogNr(int dialogNr) {
			this.dialogNr = dialogNr;
		}

		PersonType getPersonType() {
			return personType;
		}

		void setPersonType(PersonType personType) {
			this.personType = personType;
		}

		int getStepX() {
			return stepX;
		}

		void setStepX(int stepX) {
			this.stepX = stepX;
		}

		int getStepY() {
			return stepY;
		}

		void setStepY(int stepY) {
			this.stepY = stepY;
		}

		int getDirection() {
			return direction;
		}

		int getTurnBack() {
			return turnBack;
		}

		public Fighter getFighter() {
			return fighter;
		}

		public Person setFighter(Fighter fighter) {
			this.fighter = fighter;
			return this;
		}		
}