# AgeRoyale
A tower rush strategy game similar to Clash Royale. It is implemented using the MVC architecture. Made using Java and MySQL.

STEPS TO BE ABLE TO APPRECIATE THE CORRECT FUNCTIONING OF THE PROJECT:

Once the .zip is downloaded, we start IntelliJ and open the project in the "Programari" folder.
The database has been made with MySql Workbench, it needs to be initialized and compiled for the correct functioning of the practice.

Before doing anything, place the config.json file on assets, open it and change/update all data, and insert those from your own database.

Once you do this two step, we compile the IntelliJ project.

The first one that appears to us is a menu with two options, if you are a new user you must click on the register option then you will be opened a screen where you'll need to insert the data you're asked for, in case there's an error at the time to registrate or log in, the error will come out indicating you've done wrong, and you can try again.

Once these introduced, press the submit button to send the information to the database. By doing this we will access the main menu of the game and you will already be able to make your first game, see ranking, history or the options menu.

In case you're not the first time you've entered the game, jumped past steps and went directly to login, and enter your data to access your session, if there's a problem, the game will notify you.

In both cases, once you have entered the data you are asked for at login, click on "submit", if the data is correct you will access the main menu.

The main menu is divided into four separate sections:

- New Game: Press this option if you want to start a new game. Click the troop and invoke it on the counter and start the game. Once the game is over, that is, some of the main towers have been destroyed will show a window where you will have to save the game with the name you want.

- Career: In this option show all user history sorted by dates closer to further away. For each game we keep the date, the name of the game and whether we have won or lost.
In this same view each user can press any of the games and play them by clicking the play button. Also if desired you can return to the main menu by pressing the "Atras" button

- Options: It consists of three main buttons, one to log out and discard the database account called "Delete Account", one to log out called "Logout" and finally one to back down to the main menu. Both the delete account button and logout button return to the main menu of the program.

- Ranking: It consists of a JTable with all the information we need to show as required in the practice statement, a JScroll to be able to scroll in case the table is very extensive and finally two JButtons, one that returns to the main menu and the other shows you the history of the selected player.
