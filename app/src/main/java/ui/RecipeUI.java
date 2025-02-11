package ui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;

import data.RecipeFileHandler;

public class RecipeUI {
    private BufferedReader reader;
    private RecipeFileHandler fileHandler;

    public RecipeUI() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        fileHandler = new RecipeFileHandler();
    }

    public RecipeUI(BufferedReader reader, RecipeFileHandler fileHandler) {
        this.reader = reader;
        this.fileHandler = fileHandler;
    }

    public void displayMenu() {
        while (true) {
            try {
                System.out.println();
                System.out.println("Main Menu:");
                System.out.println("1: Display Recipes");
                System.out.println("2: Add New Recipe");
                System.out.println("3: Search Recipe");
                System.out.println("4: Exit Application");
                System.out.print("Please choose an option: ");

                String choice = reader.readLine();

                switch (choice) {
                    case "1":
                        // 設問1: 一覧表示機能
                        displayRecipes();
                        break;
                    case "2":
                        // 設問2: 新規登録機能
                        addNewRecipe();
                        break;
                    case "3":
                        // 設問3: 検索機能
                        break;
                    case "4":
                        System.out.println("Exit the application.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please select again.");
                        break;
                }
            } catch (IOException e) {
                System.out.println("Error reading input from user: " + e.getMessage());
            }
        }
    }

    /**
     * 設問1: 一覧表示機能
     * RecipeFileHandlerから読み込んだレシピデータを整形してコンソールに表示します。
     */

    /*
     * データが入っているかの確認
     * データが入っている場合、出力形式に合して出力
     * データが入っていなければエラー表示
     */
    private void displayRecipes() {
        /*
         * recipe変数にreadRecipesメソッドから送られたデータを入れる
         * 名前と材料で分けるためのリストを準備
         */
        ArrayList<String> recipe = this.fileHandler.readRecipes();
        ArrayList<String> name = new ArrayList<>();
        ArrayList<String> Ingredients = new ArrayList<>();

        // nameとIngrredientsに名前と材料を分けて代入
        if (recipe.size() != 0) {
            for (int i = 0; i < recipe.size(); i++) {
                String[] recipes = recipe.get(i).split(",", 2);
                name.add(recipes[0]);
                Ingredients.add(recipes[1]);
            }

            // System.out.println(recipe.get(0));
            System.out.println("Recipes:");
            System.out.println("-----------------------------------");

            // name配列とIngredients配列から出力
            for (int i = 0; i < name.size(); i++) {
                System.out.println("Recipe Name: " + name.get(i));
                System.out.println("Main Ingredients: " + Ingredients.get(i));
                System.out.println("-----------------------------------");
            }
        } else {
            System.out.println("No recipes available.");
        }
    }

    /**
     * 設問2: 新規登録機能
     * ユーザーからレシピ名と主な材料を入力させ、RecipeFileHandlerを使用してrecipes.txtに新しいレシピを追加します。
     *
     * @throws java.io.IOException 入出力が受け付けられない
     */
    /*
     * 料理名の入力を受け付ける
     * 材料の入力を受け付ける
     * 入力されたデータをrecipes.txtに追加
     */
    private void addNewRecipe() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // レシピ名の入力受付
        System.out.print("Enter recipe name: ");
        String recipeName = reader.readLine();

        // 材料の入力受付
        System.out.print("Enter main ingredients (comma separated): ");
        String ingredients = reader.readLine();

        System.out.println("Recipe added successfully. ");

        fileHandler.addRecipe(recipeName, ingredients);
    }

    /**
     * 設問3: 検索機能
     * ユーザーから検索クエリを入力させ、そのクエリに基づいてレシピを検索し、一致するレシピをコンソールに表示します。
     *
     * @throws java.io.IOException 入出力が受け付けられない
     */
    private void searchRecipe() throws IOException {

    }

}
