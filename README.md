<!-- HEADER STYLE: COWBOY BANNER -->
<div align="center">

<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 800 200">
  <defs>
    <linearGradient id="bg" x1="0%" y1="0%" x2="100%" y2="0%">
      <stop offset="0%" style="stop-color:#8B4513;stop-opacity:1" />
      <stop offset="50%" style="stop-color:#CD853F;stop-opacity:1" />
      <stop offset="100%" style="stop-color:#DEB887;stop-opacity:1" />
    </linearGradient>
    <filter id="shadow">
      <feDropShadow dx="2.0" dy="2.0" stdDeviation="4.0" flood-opacity="0.5" />
    </filter>
    <pattern id="stars" width="30" height="30" patternUnits="userSpaceOnUse">
      <text x="5" y="20" font-size="24" fill="rgba(255,255,255,0.15)">✯</text>
    </pattern>
  </defs>
  <rect width="100%" height="100%" fill="url(#bg)" rx="5.0" />
  <rect width="100%" height="100%" fill="url(#stars)" />
  <text x="400.0" y="100.0" font-family="'Lucida Console', monospace" font-size="28" font-weight="bold" text-anchor="middle" fill="#fff" filter="url(#shadow)">
    Adventure Jones🤠
  </text>
</svg>

<p align="center">
  <img alt="Java" src="https://img.shields.io/badge/Java-17+-red?style=flat-square&logo=java&logoColor=white" />
  <img alt="JavaFX" src="https://img.shields.io/badge/JavaFX-SDK-blue?style=flat-square" />
  <img alt="Platform" src="https://img.shields.io/badge/Platform-Desktop-lightgrey?style=flat-square" />
  <img alt="License" src="https://img.shields.io/github/license/roland-front-back/Adventure-Jones-4?style=flat-square" />
  <img alt="Build" src="https://img.shields.io/badge/Build-Manual-orange?style=flat-square" />
</p>

</div>

---

## 🧭 Overview

**Adventure-Jones-4** is a top-down 2D action-adventure game developed in Java using JavaFX. Inspired by retro classics like _Zelda: A Link to the Past_ and _Hyper Light Drifter_, it blends exploration, combat, puzzles, and storytelling into one pixel-perfect experience.

You’ll traverse vibrant maps, talk to NPCs, fight enemies, and uncover lost secrets — all wrapped up in an extensible, object-oriented architecture.

---

## 🎮 Features

- 🗺️ **Multiple maps** with collision detection and transitions
- 🧍 **Player movement** and basic character mechanics
- 🪖 **Enemy AI** with pathfinding and aggro radius
- 💬 **NPC interactions** and dialog system
- 🔫 **Weapons system**: pistols, bows, whips, and more
- 🎶 **Custom sound & music manager**
- 🧱 **Tile-based collision and level loading**
- 🧰 Fully modular architecture for easy extension

---

## 🧱 Project Structure

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

## 🚀 How to Run

### Prerequisites

- Java 17+
- JavaFX SDK

---

### Running the Game

```bash
javac -cp "path/to/javafx-sdk/lib/*" -d bin src/main/Main.java
java -cp "bin:path/to/javafx-sdk/lib/*" main.Main
```

Replace "path/to/javafx-sdk/lib/\*" with the path to your JavaFX lib folder.

---

## 🧪 Testing

This project currently runs manually. You can test the gameplay features by compiling and launching the main class:

```bash
java -cp ./bin main.Main
```

Make sure your .class files are correctly compiled into the bin/ directory.

---

## 🛠️ Extending the Game

We encourage contributions and mods! Here's how you can extend core systems:

| Component    | How to Extend                                                |
| ------------ | ------------------------------------------------------------ |
| Weapons      | Create new class in `weapons/`, implement `Weapon` interface |
| Maps         | Add `.json` or `.txt` files in `assets/maps/`                |
| Enemies/NPCs | Create new entity classes and register in spawner            |
| UI           | Customize or extend the `ui/` components                     |

---

## ## 📸 Screenshots

<p align="center">
  <img src="screenshots/title_screen.png" alt="Title Screen" width="400"/>
  <img src="screenshots/gameplay_fight.gif" alt="Gameplay Fight" width="400"/>
</p>

<p align="center">
  <img src="screenshots/dialog_npc.png" alt="Dialog System" width="400"/>
  <img src="screenshots/map_transition.png" alt="Map Transition" width="400"/>
</p>

---

## 🧠 Credits

- **Programming**: Group-4

- **Concept & Design**: Retro game inspirations

- **Music**: Custom SFX or royalty-free music

- **Engine**: Java + JavaFX

---

## 📄 License

MIT License — Free to use, modify, and share.

---

## 🤠 Fun Fact

     The name “Adventure Jones” was a random inside joke that turned into an epic codebase. Yeehaw!
