package am.aua.chess.ui;

import am.aua.chess.core.Chess;
import am.aua.chess.core.IllegalArrangementException;
import am.aua.chess.core.Move;
import am.aua.chess.core.Piece;
import am.aua.chess.core.Position;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChessUI extends JFrame {

    private Chess game;
    private BoardSquare[][] boardSquares;
    private Position originPosition;

    public ChessUI() throws IllegalArrangementException {
        game = new Chess();
        setTitle("Chess Game");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(Chess.BOARD_RANKS, Chess.BOARD_FILES));

        // Initialize the matrix of BoardSquare objects
        boardSquares = new BoardSquare[Chess.BOARD_RANKS][Chess.BOARD_FILES];
        for (int rank = 0; rank < Chess.BOARD_RANKS; rank++) {
            for (int file = 0; file < Chess.BOARD_FILES; file++) {
                boolean isLightColor = (rank + file) % 2 == 0;
                boardSquares[rank][file] = new BoardSquare(isLightColor, rank, file);
                boardSquares[rank][file].setPreferredSize(new Dimension(60, 60));
                boardSquares[rank][file].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        BoardSquare clickedSquare = (BoardSquare) e.getSource();
                        int[] coordinates = clickedSquare.getCoordinates();
                        boardClicked(coordinates);
                    }
                });
                boardPanel.add(boardSquares[rank][file]);
            }
        }

        add(boardPanel, BorderLayout.CENTER);
        setVisible(true);
        updatePieces();
    }

    private void boardClicked(int[] coordinates) {
        int rank = coordinates[0];
        int file = coordinates[1];
        Position selectedPosition = new Position(rank, file);
    
        if (originPosition == null) {
            if (!game.isEmpty(selectedPosition) && game.getPieceAt(selectedPosition).getPieceColor() == game.getTurn()) {
                originPosition = selectedPosition;
                highlightReachableSquares(originPosition);
            }
        } else {
            Position destinationPosition = selectedPosition;
            Move move = new Move(originPosition, destinationPosition);
            if (game.isValidMove(move)) {
                game.performMove(move);
                updatePieces();
                clearHighlights();
                originPosition = null;
            } else {
                clearHighlights();
                originPosition = null;
            }
        }
    }
    
    private void highlightReachableSquares(Position origin) {
        clearHighlights();
        Position[] reachable = game.reachableFrom(origin);
        if (reachable != null) {
            for (Position pos : reachable) {
                int rank = pos.getRank();
                int file = pos.getFile();
                boardSquares[rank][file].setHighlight(true);
            }
        }
    }
    
    private void clearHighlights() {
        for (int rank = 0; rank < Chess.BOARD_RANKS; rank++) {
            for (int file = 0; file < Chess.BOARD_FILES; file++) {
                boardSquares[rank][file].setHighlight(false);
            }
        }
    }
    
    private void updatePieces() {
        for (int rank = 0; rank < Chess.BOARD_RANKS; rank++) {
            for (int file = 0; file < Chess.BOARD_FILES; file++) {
                Position pos = new Position(rank, file);
                Piece piece = game.getPieceAt(pos);
                if (piece != null) {
                    boardSquares[rank][file].setPiece(piece.toString()); // Assume toString() returns the correct icon name
                } else {
                    boardSquares[rank][file].setPiece();
                }
            }
        }
    }
}
