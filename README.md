MacMahonMosaic - JavaFX Puzzle Game

JavaFX implementation of the MacMahon Mosaic puzzle.
Users can solve, create, validate and store custom puzzles while following strict color-matching rules.
Developed as part of a university programming module.

Features:
  Drag & Drop tile placement,
  Tile rotation (right-click) & removal,
  Real-time rule validation,
  Editor mode (custom puzzle creation),
  Solvability check,
  Hint system,
  JSON-based save & load

Architecture:
gui → JavaFX UI & FXML,
logic → Game rules & validation,
JSON persistence for game state

Technologies:
  Java - JavaFX - FXML - JSON - OOP

Known Limitation: 
  Grid size resets when switching between editor and play mode due to current grid reconstruction logic.
