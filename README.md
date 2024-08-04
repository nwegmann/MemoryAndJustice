# MemoryAndJustice
## Model:
This project is an attempt at modeling how norms of justice could emerge between self-interested individuals under the following constraints:
  1. People are bound to interact multiple times.
  2. People may remember past interactions and adapt their strategies accordingly.

People have a certain number of neighbors with whom they can interact. At each round, one randomly chosen player is matched with one of their neighbors. They then play a prisoner's dilemma and each player's score gets incremented by their relative payoff. Players are defectors and play the _Defector_ strategy or grudgers and may either follow _Grudge_ or _Grudge*_. Defectors always defect. Players playing the _Grudge_ strategy cooperate unless they are facing a player who defected against them during their last _m_ interactions. Players following _Grudge*_ cooperate unless the player they are facing defected against them during the last _m_ times they got defected against.

## Usage:
There are two different ways to interact with this project.
### File Interaction:
One can use the file _SimulationValues.txt_ in the _Resources_ folder to play a single game. To run a simulation, uncomment line 30 calling **playFromFile("src/Resources/SimulationValues.txt")**. The desired values for the simulation must be specified inside the _SimulationValues.txt_ file. The results will be written in the file _results.txt_ in the _Resources_ folder
### Multiple simulations:
To run multiple simulations, the main file can be modified with the desired values:

![Screenshot 2024-08-03 at 17 48 27](https://github.com/user-attachments/assets/1872667b-4346-44a9-8f01-f9b23b363072)

The results will be written in the file _results.txt_ in the _Resources_ folder

### Results
Each line in the _results.txt_ file has the following form and presents the result of a given strategy for a given game:

_p_def_, _neighbors_, _memory_, _strategy_, _score_

Where:

- _p_def_ corresponds to the proportion of defectors in this game
- _neighbors_ corresponds to the number of neighbors in this game
- _memory_ corresponds to the _m_ value for the corresponding grudger strategy in this game
- _strategy_ corresponds to a strategy
- _score_ corresponds to the score of the players playing _strategy_ in this game

## Contact Information

For more information, please contact me at [nathan.wegmann@unifr.ch]
