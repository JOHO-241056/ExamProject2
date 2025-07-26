import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class KeyInput {
    private final BufferedReader br;

    public KeyInput() {
        this.br = new BufferedReader(new InputStreamReader(System.in));
    }

    public String readString() {
        String inputStr = "";
        try {
            inputStr = br.readLine();
        } catch (IOException e) {
            System.out.println("エラー: " + e.getMessage());
        }
        return inputStr;
    }

    public String readString(String inputMsg) {
        String inputStr;
        while (true) {
            System.out.print(inputMsg + "を入力>");
            inputStr = readString();
            if(!inputStr.isEmpty()) return inputStr;
            System.out.println(inputMsg + "が入力されていません。再入力してください。");
        }
    }

    public int readInt(String inputMsg) {
        while (true) {
            try {
                return Integer.parseInt(readString(inputMsg));
            } catch (NumberFormatException e) {
                System.out.println("整数値を入力してください。再入力してください。");
            }
        }
    }
}
