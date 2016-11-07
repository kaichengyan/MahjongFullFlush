import java.util.Scanner;

/**
 * Created by Kyle on 11/7/2016.
 */
public class MahjongTest {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Mahjong mj = new Mahjong(input.nextLine());
        System.out.println(mj);
        System.out.println(mj.isFullFlush());
    }

}
