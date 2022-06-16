Un cop descarregat el .zip, iniciem el IntelliJ i obrim el projecte ubicat a la carpeta Programari.
La base de dades l'hem fet amb el MySql Workbench, és necessari inicialitzar-la, cal executar l'arxiu "importsql.sql" que es troba ubicat a la carpeta BaseDeDades per a tenir correcte funcionament de la pràctica.

Abans de fer res, localitza a files el fitxer config.json, per a canviar/actualitza les dades, i inserta la base de dades del programa.

Un cop fets aquest dos passos, compilem el projecte del IntelliJ.

El primer que ens apareix és un menú amb dues opcions, si ets un usuari nou has de fer click a l'opció de register, a continuació se't mostrar una pantalla on hauràs d'inserir les dades que et demanen per a registrar-te, en el cas que hi hagi algun error a
l'hora de registrar-te, et sortirà l'error per pantalla indicant que has fet malament, i ho podràs tornar a intentar. En cas que no siguis un usuari nou hauràs de fer click a l'opció de login i se't mostrar la pantalla per a introduir les teves dades, igual que en el registre
si hi ha algun error, et sortirà l'error per pantalla indicant que has fet malament i ho podràs intentar de nou.

Un cop aquestes dades introduïdes correctament, prem el botó de submit per enviar la informació a la base de dades. Fet això accedirem al menú principal del joc i ja podràs fer la teva primera partida, consulta el ranking, l'historial o el menú d'opcions.

El menú principal es divideix en 4 apartats diferents:
	
	-Game: 	Iniciar una nova partida.
		Fes clic a les tropes i invoca-les sobre el taulell. Un cop acabi la partida, és a dir, que alguna de les torres principals hagi estat destruïda, apareixerà una finestra on hauràs de guardar la partida amb el nom que tu vulguis.

	-Ranking: 	JTable que mostra el ranking dels millors jugadors amb el nom de l'usuari, el nombre de partides que ha guanyat, i el winratio que tingui. Amb un JScorll per poder fer scroll en cas que la taula sigui molt extensa i finalment tres JButtons
		un que retorna al menú principal, l'altra et mostra l'historial del jugador seleccionat i un últim per a fer el logout del compte.
	
	-Reproduce:     En aquesta opció mostrem tot l'historial de l'usuari ordenat per dates de més propera a més llunyana. Per cada partida guardem la data, el nom de la partida i si hem guanyat o perdut.
		      En aquesta mateixa view cada usuari pot prémer qualsevol de les partides i reproduir-lo clicant el botó de reproduir. També si es vol pot tornar al menú principal prement el botó "Back" o sortir del compte amb el botó "Logout".

	-Options: 	Consta de tres botons principals, un per tancar sessió i esborrar el compte de la base de dades anomenat "Delete Account", un per tancar sessió anomenada "Logout" i finalment un que tira enrere cap al menú principal.
	
	

