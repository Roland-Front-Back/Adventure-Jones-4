<div id="top">

<!-- HEADER STYLE: WILD WEST BANNER -->
<div align="center">
<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 800 200">
	<defs>
		 <linearGradient id="desert" x1="0" y1="0" x2="0" y2="1">
            <stop offset="0%" stop-color="#33170d" />
            <stop offset="50%" stop-color="#b13d1f" />
            <stop offset="100%" stop-color="#e89c4a" />
        </linearGradient>
		<filter id="shadow">
            <feDropShadow dx="2" dy="2" stdDeviation="4" flood-opacity="0.6" />
        </filter>
	</defs>
     <!-- Background -->
  <rect width="100%" height="100%" fill="url(#desert)" rx="10" />
    <!-- Horizon silhouette -->
  <path d="M0,160 Q200,130 400,160 T800,160 L800,200 L0,200 Z" fill="#2e1a14" />
      <!-- Cactus -->
  <g transform="translate(100,100)">
    <rect x="8" y="20" width="8" height="50" fill="#1d5b3f" />
    <rect x="0" y="30" width="8" height="20" fill="#1d5b3f" />
    <rect x="16" y="40" width="8" height="15" fill="#1d5b3f" />
  </g>
  <!-- Sun -->
  <circle cx="700" cy="50" r="30" fill="#fdd76e" opacity="0.8" />
	 <!-- Title -->
  <text x="400" y="110" font-family="'Georgia', serif" font-size="34" font-weight="bold" text-anchor="middle" fill="#fffbe6" filter="url(#shadow)">
    Adventure Jones
  </text>
  <br>
	<text x="400" y="145" font-family="'Georgia', serif" font-size="26" text-anchor="middle" fill="rgba(255,255,255,0.85)">
    A Retro Western Quest in Java
  </text>
</svg>

<!-- BADGES -->
<img src="https://img.shields.io/github/license/roland-front-back/Adventure-Jones-4?style=plastic&logo=opensourceinitiative&logoColor=white&color=43a047" alt="license">
<img src="https://img.shields.io/github/last-commit/roland-front-back/Adventure-Jones-4?style=plastic&logo=git&logoColor=white&color=43a047" alt="last-commit">
<img src="https://img.shields.io/github/languages/top/roland-front-back/Adventure-Jones-4?style=plastic&color=43a047" alt="repo-top-language">
<img src="https://img.shields.io/github/languages/count/roland-front-back/Adventure-Jones-4?style=plastic&color=43a047" alt="repo-language-count">

<em>Built with the tools and technologies:</em> Java, Swing, OOP, and game asset management.

</div>

---

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Project Structure](#project-structure)
  - [Project Index](#project-index)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
  - [Usage](#usage)
  - [Testing](#testing)
- [Roadmap](#roadmap)
- [Contributing](#contributing)
- [License](#license)
- [Acknowledgments](#acknowledgments)

---

## Overview

**Adventure-Jones-4** is a retro-inspired 2D adventure game where you explore dungeons, solve puzzles, and battle monsters using classic weapons and abilities. The goal: collect all ancient artifacts and uncover the final secret of the Jones legacy.

> A homage to classic pixel art RPGs with a modern codebase built in Java.

---

## Features

- 🎮 Top-down 2D gameplay with grid-based tile interaction
- 🧠 AI pathfinding for monster and NPC movement
- 🔊 Original sound effects and background music
- 🗺️ Multi-map exploration system
- 💬 NPC dialog interactions
- 🧰 Configurable settings via `config.txt`
- ⚔️ Inventory: swords, whips, bows, medkits, and artifacts
- 🧟 Unique enemies: Mecha, Tyanak, Dwarf, Death, and more
- 🔥 Destructible environment tiles: bushes, barrels, etc.

---

## Project Structure

```css
📁 Adventure-Jones-4/
│
├── 📁 src/
│ ├── 📁 entities/ # Player, NPCs, Enemies, Projectiles
│ ├── 📁 weapons/ # Weapon classes (Pistol, Whip, Bow, etc.)
│ ├── 📁 maps/ # Level loading, transitions, tilesets
│ ├── 📁 ui/ # Dialog boxes, health bars, overlays
│ ├── 📁 audio/ # Music and sound effect managers
│ ├── 📁 main/ # Main entry point and core loop
│ └── 📁 util/ # Helpers, game timer, collision logic
│
├── 📁 assets/ # Sprites, audio files, maps, fonts
├── 📁 bin/ # Compiled class files
├── README.md
└── .gitignore
```

---

## Getting Started

### Prerequisites

- Java 17+
- Git (for cloning)
- IDE (e.g., IntelliJ, Eclipse) or use `javac` in terminal

---

### Installation

```bash
git clone https://github.com/Roland-Front-Back/Adventure-Jones-4.git
cd Adventure-Jones-4
```

---

## Usage

Compile and run the game using your IDE or via terminal:

```bash
javac -d bin src/\*_/_.java
java -cp bin main.Main
```

You can modify config.txt to tweak game behavior.

---

## Testing

Manual testing is recommended. Automated unit tests (optional) can be added using JUnit if desired.

---

## Roadmap

- [ ] Add save/load game functionality

- [ ] Implement boss attack phases

- [ ] Add controller support

- [ ] Create more unique maps and puzzles

- [ ] Refactor AI for better pathfinding performance

---

## Contributing

Contributions are welcome! To contribute:

1. Fork the repo

2. Create a feature branch: git checkout -b feature/my-feature

3. Commit your changes: git commit -m 'Add some feature'

4. Push to the branch: git push origin feature/my-feature

5. Open a Pull Request

6. Please follow consistent code style and include brief summaries for major changes.

---

## License

Distributed under the MIT License.
See LICENSE for more information.
Acknowledgments

- Sound effects by <a href="https://freesound.org">freesound.org</a>

- Fonts from <a href="https://www.dafont.com">dafont.com</a>

- Inspired by The Legend of Zelda, Indiana Jones, and Secret of Mana

---

## Acknowledgments

- <a href="https://freesound.org">freesound.org</a> for open sound effects

- <a href="https://www.dafont.com">dafont.com</a> for custom pixel fonts

- Inspiration from The Legend of Zelda, Indiana Jones, and other classic action-adventure games

- Java/Swing documentation for GUI components and event handling

---

## 🧠 Credits

- Programming: Group 4

- Concept & Design: Retro game inspirations

- Music: Custom SFX or royalty-free music

- Engine: Java + JavaFX

---

<div align="right"><a href="#top">Back to top</a></div>

</div>
