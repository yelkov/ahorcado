package ahorcado;

public class Drawing {
    private StringBuilder drawing;
    private Game game;

    public Drawing(Game game){
        this.game = game;
        this.drawing = new StringBuilder();
    }

    public StringBuilder getDrawing(){
        return this.drawing;
    }

    public void setDrawing(){
        switch (game.getErrors()){
            case 0:
                drawing.setLength(0);
                drawing.append("\n");
                break;
            case 1:
                drawing.setLength(0);
                drawing.append("\n");
                drawing.append("=========");
                break;
            case 2:
                drawing.setLength(0);
                drawing.append("\n");
                drawing.append("      |  \n".repeat(5));
                drawing.append("=========");
                break;
            case 3:
                drawing.setLength(0);
                drawing.append("  +---+  \n");
                drawing.append("      |  \n".repeat(5));
                drawing.append("=========");
                break;
            case 4:
                drawing.setLength(0);
                drawing.append("  +---+  \n");
                drawing.append("  |   |  \n");
                drawing.append("      |  \n".repeat(4));
                drawing.append("=========");
                break;
            case 5:
                drawing.setLength(0);
                drawing.append("  +---+  \n");
                drawing.append("  |   |  \n");
                drawing.append("  O   |  \n");
                drawing.append("      |  \n".repeat(3));
                drawing.append("=========");
                break;
            case 6:
                drawing.setLength(0);
                drawing.append("  +---+  \n");
                drawing.append("  |   |  \n");
                drawing.append("  O   |  \n");
                drawing.append("  |   |  \n");
                drawing.append("      |  \n".repeat(2));
                drawing.append("=========");
                break;
            case 7:
                drawing.setLength(0);
                drawing.append("  +---+  \n");
                drawing.append("  |   |  \n");
                drawing.append("  O   |  \n");
                drawing.append(" /|   |  \n");
                drawing.append("      |  \n".repeat(2));
                drawing.append("=========");
                break;
            case 8:
                drawing.setLength(0);
                drawing.append("  +---+  \n");
                drawing.append("  |   |  \n");
                drawing.append("  O   |  \n");
                drawing.append(" /|\\  |  \n");
                drawing.append("      |  \n".repeat(2));
                drawing.append("=========");
                break;
            case 9:
                drawing.setLength(0);
                drawing.append("  +---+  \n");
                drawing.append("  |   |  \n");
                drawing.append("  O   |  \n");
                drawing.append(" /|\\  |  \n");
                drawing.append(" /    |  \n");
                drawing.append("      |  \n");
                drawing.append("=========");
                break;
            case 10:
                drawing.setLength(0);
                drawing.append("  +---+  \n");
                drawing.append("  |   |  \n");
                drawing.append("  O   |  \n");
                drawing.append(" /|\\  |  \n");
                drawing.append(" / \\  |  \n");
                drawing.append("      |  \n");
                drawing.append("=========");
                break;
            case 11:
                drawing.setLength(0);
                drawing.append("  +---+  \n");
                drawing.append("  |  \\|  \n");
                drawing.append("  O   |  \n");
                drawing.append(" /|\\  |  \n");
                drawing.append(" / \\  |  \n");
                drawing.append("      |  \n");
                drawing.append("=========");
                break;
        }
    }
}
