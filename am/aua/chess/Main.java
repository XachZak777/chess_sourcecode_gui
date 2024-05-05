package am.aua.chess;

import am.aua.chess.cli.ChessConsole;
import am.aua.chess.core.IllegalArrangementException;
import am.aua.chess.ui.ChessUI;

public class Main {
public static void main(String[] args) throws IllegalArrangementException {
    if (args.length == 1 && args[0].equals("-console")) {
        ChessConsole console = new ChessConsole();
        console.run();
    } else {
        ChessUI gui = new ChessUI();
    }
}

}    



