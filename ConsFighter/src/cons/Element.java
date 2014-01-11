package cons;

import java.awt.Color;

class Element
{
    private Element schaden;
    private Element schwaechung;
    private Element kontrolle; 
    private Element naehrung;
    private Color color;

    static Element fire, water,  metal, wood, earth;
   
    Element(Element schaden, Element schwaechung, Element kontrolle, Element naehrung, Color color)
    {
    	setSchaden(schaden);
    	setSchwaechung(schwaechung);
    	setKontrolle(kontrolle);
    	setNaehrung(naehrung);
    	setColor(color);
    }
    
    static void load()
    {
    	fire = new Element(water, wood, metal, earth, new Color(255, 0, 0));
    	water = new Element(earth, metal, fire, wood, new Color(0, 255, 0));
    	metal = new Element(fire, earth, wood, water, new Color(127, 127, 127));
    	wood = new Element(metal, water, earth, fire, new Color(0, 255, 0));
    	earth = new Element(wood, fire, water, metal, new Color(255, 127, 0));
    }

	Element getSchaden() {
		return schaden;
	}

	void setSchaden(Element schaden) {
		this.schaden = schaden;
	}

	Element getSchwaechung() {
		return schwaechung;
	}

	void setSchwaechung(Element schwaechung) {
		this.schwaechung = schwaechung;
	}

	Element getKontrolle() {
		return kontrolle;
	}

	void setKontrolle(Element kontrolle) {
		this.kontrolle = kontrolle;
	}

	Element getNaehrung() {
		return naehrung;
	}

	void setNaehrung(Element naehrung) {
		this.naehrung = naehrung;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	} 
}