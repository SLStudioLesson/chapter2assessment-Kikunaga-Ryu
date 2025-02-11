package data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import ui.RecipeUI;

public class RecipeFileHandler {
    private String filePath;

    public RecipeFileHandler() {
        filePath = "app/src/main/resources/recipes.txt";
    }

    public RecipeFileHandler(String filePath) {
        this.filePath = filePath;
    }

    /**
     * 設問1: 一覧表示機能
     * recipes.txtからレシピデータを読み込み、それをリスト形式で返します。 <br>
     * IOExceptionが発生したときは<i>Error reading file: 例外のメッセージ</i>とコンソールに表示します。
     *
     * @return レシピデータ
     */

    /*
     * recipes.txtのデータを読み込む
     * BufferedReaderを使って一行ずつデータ取得し、ArrayListの配列に代入していく
     * 代入された配列をdisplayRecipesメソッドに返す
     * エラーが出た場合エラーメッセージを出力
     */
    public ArrayList<String> readRecipes() {
        ArrayList<String> dataList = new ArrayList<>();
        File dataFile = new File(filePath);

        // recipes.txtからデータを読み込む
        if (dataFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(dataFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    dataList.add(line);
                }
            } catch (IOException e) {
                System.out.println("Error reading file:" + e.getMessage());
            }
        }
        return dataList;
    }

    /**
     * 設問2: 新規登録機能
     * 新しいレシピをrecipes.txtに追加します。<br>
     * レシピ名と材料はカンマ区切りで1行としてファイルに書き込まれます。
     *
     * @param recipeName  レシピ名
     * @param ingredients 材料名
     */
    //
    /*
     * 元のデータをリストに移しておく(データを追加するときに消えてしまうため)
     * 入力されたデータを取得しリストに追加する
     * recipe.txtどうりにデータを入れる
     */
    public void addRecipe(String recipeName, String ingredients) {
        File dataFile = new File(filePath);
        // 元のデータを格納するためのArrayList
        ArrayList<String> array = new ArrayList<>();

        // 元のデータを格納
        try (BufferedReader reader = new BufferedReader(new FileReader(dataFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                array.add(line);
            }
        } catch (IOException ex) {
            System.out.println("Error reading file: " + ex.getMessage());
        }

        // 入力されたデータを変数に入れArrayListに追加
        String newRecipe = recipeName + ", " + ingredients;
        array.add(newRecipe);

        // recipes.txtの表記どうりにデータを入れる
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dataFile))) {
            for (String line : array) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
