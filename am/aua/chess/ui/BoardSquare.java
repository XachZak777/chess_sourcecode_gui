package am.aua.chess.ui;

import javax.swing.*;
import java.awt.*;

public class BoardSquare extends JButton {
    
    private static final Color LIGHT_COLOR = new Color(255, 206, 158); // Light wood color
    private static final Color DARK_COLOR = new Color(209, 139, 71);   // Dark wood color
    private static final Color HIGHLIGHT_COLOR = new Color(255, 255, 100); // Bright yellow for highlight
    private final int x, y;
    private final Color originalColor;

    public BoardSquare(boolean isLight, int x, int y) {
        this.x = x;
        this.y = y;
        this.originalColor = isLight ? LIGHT_COLOR : DARK_COLOR;
        setBackground(originalColor);
        setOpaque(true);
        setBorderPainted(false);
        setPreferredSize(new Dimension(60, 60)); // Adjust size as needed
    }

    public int[] getCoordinates() {
        return new int[]{x, y};
    }

    public void setPiece(String pieceType) {
        String fileName;
        switch (pieceType) {
            case "K":
                fileName = "KingW.png";
                break;
            case "k":
                fileName = "KingB.png";
                break;
            case "L":
                fileName = "KingW.png";
                break;
            case "l":
                fileName = "KingB.png";
                break;
            case "B":
                fileName = "BishopW.png";
                break;
            case "b":
                fileName = "BishopB.png";
                break;
            case "N":
                fileName = "KnightW.png";
                break;
            case "n":
                fileName = "KnightB.png";
                break;
            case "P":
                fileName = "PawnW.png";
                break;
            case "p":
                fileName = "PawnB.png";
                break;
            case "Q":
                fileName = "QueenW.png";
                break;
            case "q":
                fileName = "QueenB.png";
                break;
            case "R":
                fileName = "RookW.png";
                break;
            case "r":
                fileName = "RookB.png";
                break;
            case "S":
                fileName = "RookW.png";
                break;
            case "s":
                fileName = "RookB.png";
                break;
            default:
                return;
        }
        ImageIcon icon = new ImageIcon(getClass().getResource(fileName));
        setIcon(icon);
    }

    public void setPiece() {
        setIcon(null); // Clear the icon
    }

    public void setHighlight(boolean highlight) {
        setBackground(highlight ? HIGHLIGHT_COLOR : originalColor);
    }
}
