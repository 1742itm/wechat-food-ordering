package my.food.tool;

public class Tool {
    public static String padString(String input, int size, char padChar, boolean leftPad) {
        if (leftPad) {
            return String.format("%1$" + size + "s", input).replace(' ', padChar);
        } else {
            return String.format("%1$-" + size + "s", input).replace(' ', padChar);
        }
    }
}
