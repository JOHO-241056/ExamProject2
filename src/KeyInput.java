import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class KeyInput {
    public String readString() {
        String inputStr = "";
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
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
}
