# Mummy Maze Solver

## Project Overview
The Mummy Maze Solver is a Java-based application designed to navigate a treasure-hunting game where the player, as the hero, must traverse through various levels while avoiding enemies and traps. The objective is to reach the exit point while overcoming challenges posed by mummies and other obstacles.

### Game Elements
- **Hero**: Moves one square per turn in any direction, avoiding walls. The hero can choose to remain stationary.
- **White Mummy**: Moves up to two squares per turn, attempting to track down the hero by aligning its position first vertically and then horizontally.
- **Red Mummy**: Similar to the white mummy but prioritizes aligning with the hero's row before moving to the same column.
- **Scorpion**: Moves like the white mummy but can only move one square per turn.
- **Key**: When the hero steps on the key, it toggles the state of adjacent doors.
- **Door**: Acts as a barrier when closed and allows passage when open.
- **Trap**: Instantly defeats the hero upon contact.

### Game Mechanics
The game is played in turns, with the hero moving first, followed by the enemies. Players must strategically maneuver to avoid enemies and traps while seeking the exit.

### Algorithm Implementation
The application implements various search algorithms to solve the maze. Users can select the level, the algorithm, and applicable heuristics for navigation. A comparative analysis of the performance of different search algorithms and heuristics is also included.

### State Representation
Levels are represented in a 13x13 character matrix, where each character denotes different elements of the maze:
- `|` or `–`: Wall
- `H`: Hero
- `.`: Empty space
- `S`: Exit
- `M`: White mummy
- `V`: Red mummy
- `A`: Trap
- `E`: Scorpion
- `C`: Key
- `=`: Closed horizontal door
- `_`: Open horizontal door
- `”`: Closed vertical door
- `”)`: Open vertical door

### Solution Visualization
A graphical interface shows the solution path generated by the search algorithms, enhancing user interaction and understanding of the game's dynamics.

### Key Features
- Customizable levels defined via text files.
- Multiple search algorithms and heuristics to compare their effectiveness.
- Interactive solution visualization for better comprehension of the maze-solving process. 

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.


### Authors
- João Castro ([@jcastroo](https://github.com/jcastroo))
- Marco Gaspar ([@Sneuc](https://github.com/marco2201708))
