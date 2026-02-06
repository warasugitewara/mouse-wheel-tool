# Changelog

All notable changes to this project will be documented in this file.

## [1.1.0] - 2026-02-07

### Added
- Number spinners for real-time parameter input (Speed, Amount)
- Maximum rotation amount increased to 20 (was 10)
- OS detection utilities (Windows/Linux/macOS)
- Linux support (with xbindkeys recommendation)
- Improved GUI with synchronized sliders and spinners
- Larger maximum rotation speed (10-500ms, was 10-200ms)
- System info display on startup (OS, Java version)

### Changed
- Enhanced GUI layout for better UX
- Rotation speed default: 50ms (was 3ms)
- Better thread logging and debugging output

### Fixed
- Mouse input blocking issue (improved thread management)

## [1.0.1] - 2026-02-06

### Added
- Global hotkey listener implementation (JNA + Windows API)
- Mouse wheel operation test program
- Launch scripts (run.bat, run.sh)
- Enhanced logging with debug output
- Test class for mouse wheel verification

### Changed
- Replaced GUI-only keyboard listener with global hotkey (Windows)
- Improved rotation controller with better thread handling

### Fixed
- Mouse wheel rotation now properly executes

## [1.0.0] - 2026-02-05

### Added
- Initial release
- Swing-based GUI with sliders
- Mouse wheel rotation control (java.awt.Robot)
- F9/F10 hotkey support (GUI focus)
- Real-time parameter adjustment
- Java 21/22/25 support
- Windows support
