package cons;

final class MapUtils {
	
	private static Map[] maps = new Map[10];
	
	private MapUtils() {	
	}
	
	static Map[] getMaps() {
		return maps;
	}

	static void setMaps(final Map[] maps) {
		MapUtils.maps = maps;
	}
	
	
	static void load(final Main main)
	{
		maps[0] = new Map(32, 32, SoundPlayer.soundCity3);
		maps[1] = new Map(16, 16, SoundPlayer.soundCity3);
		maps[2] = new Map(16, 16, SoundPlayer.soundCity1);
		
		// === Objekte ===

		maps[0].setRect(Field.meadow_light, 0, 1, 0, 32, 31);
		maps[0].setRect(Field.busch, 8, 8, 1, 1, 1);
		maps[0].setRect(Field.blumentopf, 9, 8, 1, 1, 1);
		maps[0].setRect(Field.flower, 13, 5, 1, 0, 1);
		
		// === Pfade ===

		maps[0].setHorizontalPath(Trail.path2, 9, 12, 19);
		maps[0].setFence(Fence.fence1, 0, 2, 1, 31, 29);

		// === Landschaften ===

		maps[0].setMountain(Mountain.mountain3, 3, 18, 24, 8);
		maps[0].setWater(5, 19, 7, 6);
		maps[0].setIsland(Field.meadow_light, 6, 20, 4, 4);
		maps[0].setPcdesk(7, 21);
		maps[0].setRect(Field.shadowstairs, 15, 26, 0, 1, 1);
		maps[0].setRect(Field.stairs, 16, 26, 0, 1, 1);
		maps[0].setRect(null, 15, 26, 1, 2, 1);
		maps[0].setTree1(4, 4);

		maps[0].setKühlschrank(15, 22);
		maps[0].setHugeGrass(17, 20, 9, 5);
		
		maps[1].setRect(Field.dielen, 0, 2, 0, 16, 14);
		maps[1].setRect(Teleporter.t1, 7, 15, 0, 1, 1);
		maps[1].setRect(Teleporter.t2, 8, 15, 0, 1, 1);
		maps[1].setRect(Field.wall1_m, 0, 0, 1, 15, 1);
		maps[1].setRect(null, 7, 0, 1, 4, 1);
		maps[1].setRect(null, 7, 0, 0, 4, 4);
		maps[1].setRect(Field.wall1_l, 7, 2, 1, 1, 1);
		maps[1].setRect(Field.wall1_m, 8, 2, 1, 2, 1);
		maps[1].setRect(Field.window, 3, 2, 2, 1, 1);
		maps[1].setKühlschrank(0, 2);
		maps[1].setRect(Field.wall1_r, 10, 2, 1, 1, 1);
		maps[1].setRect(Field.stairs_t, 15, 0, 0, 1, 1);
		maps[1].setRect(Teleporter.t3, 15, 1, 0, 1, 1);

		maps[2].setRect(Field.fliesen, 0, 2, 0, 16, 14);
		maps[2].setRect(Field.stairs_b, 15, 0, 0, 1, 1);
		maps[2].setRect(Teleporter.t4, 15, 1, 0, 1, 1);
		maps[2].setRect(Field.wall1_m, 0, 0, 1, 15, 1);
		maps[2].setPcdesk(0, 2);
		maps[2].setPcdesk(3, 2);
		maps[2].setPcdesk(6, 2);
		maps[2].setPcdesk(9, 2);
		maps[2].setPcdesk(0, 5);
		maps[2].setPcdesk(3, 5);
		maps[2].setPcdesk(6, 5);
		maps[2].setPcdesk(9, 5);
		maps[2].setPcdesk(0, 8);
		maps[2].setPcdesk(3, 8);
		maps[2].setPcdesk(6, 8);
		maps[2].setPcdesk(9, 8);
		maps[2].setSofa(1, 11);
		maps[2].setRoomplant(3, 11);
		maps[2].setSofa(6, 11);
		maps[2].setSofa(9, 11);
		maps[2].setRoomplant(0, 11);
		maps[2].setRedCouch(0, 12);
		maps[2].setRedCouch(0, 14);
		maps[2].setRoomplant(11, 2);
		maps[2].setRoomplant(11, 5);
		maps[2].setRoomplant(11, 8);
		maps[2].setRoomplant(11, 11);
		
		// === Bäume ===
		
		maps[0].setRect(Field.sign2, 8, 15, 1, 1, 1);
		
		maps[0].setTree2(0, 1);
		maps[0].setTree2(2, 1);
		maps[0].setTree2(4, 1);
		maps[0].setTree2(6, 1);
		maps[0].setTree2(8, 1);
		maps[0].setTree2(10, 1);
		maps[0].setTree2(12, 1);
		maps[0].setTree2(14, 1);
		maps[0].setTree2(16, 1);
		maps[0].setTree2(18, 1);
		maps[0].setTree2(20, 1);
		maps[0].setTree2(22, 1);
		maps[0].setTree2(24, 1);
		maps[0].setTree2(26, 1);
		maps[0].setTree2(28, 1);
		maps[0].setTree2(30, 1);
		
		// === Häuser ===

		maps[0].setLetterbox(9, 10);
		maps[0].setHouse(House.house3, 10, 10);;
		maps[0].setHouse(House.house7, 18, 9);
		maps[0].setHouse(House.house8, 2, 10);

		maps[0].setStatue2(16, 9);
		maps[0].setStatue1(29, 9);
		maps[0].setFountain(18, 16);
		
		// === Teleporter ===
		
		maps[0].setRect(null, 11, 10, 1, 1, 1);
		maps[0].setRect(Teleporter.t0, 11, 10, 0, 1, 1);
		
		// === Personen ===

		Person p = new Person(maps[0], 7, 4, "Einen der Bäume hier hinten habe ich in jungen Jahren mit eigenen Händen gepflanzt. Wie schnell die Zeit hier doch vergeht ...", PersonType.getType()[0], main).setDirection(2);
		p.setFighter(new Player(0, 0, null, main).fighter);
		
		maps[0].addPerson(p);
		maps[0].addPerson(new Person(maps[0], 6, 4, "Ich lebe hier schon, seitdem ich ein Kind bin. Weil dieser Ort von großen Städten abgeschieden liegt, ist es hier so wunderbar ruhig.", PersonType.getType()[1], main).setDirection(2));
		maps[0].addPerson(new Person(maps[0], 12, 22, "Ahoi Matrose! Ich würde ja gerne auf's tosende Meer hinausfahren, aber die Fische verhalten sich so, als wäre ein Unheil in Anmarsch.", PersonType.getType()[2], main).setDirection(3).setTurnBack(3));
		maps[0].addPerson(new Person(maps[0], 5, 12, "Ich hab dich hier schon öfters vorbeilaufen sehen. Wohnst du hier etwa in der Nähe?", PersonType.getType()[3], main).setDirection(2));
		maps[0].addPerson(new Person(maps[0], 25, 15, "Früher hatten wir noch Hobbys, da fuhren wir jeden Tag Fahrrad. Heute ist das anders ...", PersonType.getType()[4], main).setDirection(0));
		maps[2].addPerson(new Person(maps[2], 3, 6, "Ich habe bei der Programmierung dieses Spiels beigetragen.", PersonType.getType()[5], main).setDirection(0).setTurnBack(0));
		maps[2].addPerson(new Person(maps[2], 9, 3, "Ich entwickle Technologien, damit Zyans immer auf dem neusten Stand bleibt.", PersonType.getType()[6], main).setDirection(0).setTurnBack(0));
		maps[2].addPerson(new Person(maps[2], 8, 13, "Ich verfolge die Entwicklung des Spieles schon seit der Planung. Mit viel Glück werde ich Beta-Tester und kann es kostenlos als Erster testen!", PersonType.getType()[7], main).setDirection(2));
		maps[1].addPerson(new Person(maps[1], 5, 14, "Oben findest du einige wichtige Leute für dieses Spiel. Wenn ich einmal groß bin, will ich auch Mitarbeiter von Zyans sein.", PersonType.getType()[8], main).setDirection(0));
		maps[1].addPerson(new Person(maps[1], 4, 14, "Ich bin mit meinem Sohn hier. Er besucht die Programmierer jeden Sonntag, um nichts zu verpassen.", PersonType.getType()[9], main).setDirection(1).setTurnBack(1));
		maps[1].addPerson(new Person(maps[1], 9, 4, "Viele Leute besuchen Zyans, ich bin hier für die Sicherheit zuständig, obwohl es hier noch keine Zwischenfälle gab.", PersonType.getType()[10], main).setDirection(2).setTurnBack(2));
		maps[0].addPerson(new Person(maps[0], 7, 22, "Ich wohne hier, weil mir meine Mutter hier nicht vorschreiben kann, wie lange ich am PC bin.", PersonType.getType()[7], main).setDirection(0).setTurnBack(0));
	}
}