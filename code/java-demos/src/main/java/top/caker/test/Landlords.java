package top.caker.test;

import java.util.Arrays;
import java.util.Scanner;
import java.util.SplittableRandom;

/**
 * @author cakeralter
 * @date 2020/5/14
 */
public class Landlords {

    private final static String[] CARDS = {
            "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"
    };

    private final static String[] FLOWERS = {
            "♥️", "♠️", "♣️", "♦️"
    };

    private final static String[] JOKERS = {
            "JOKER", "joker"
    };

    public static void main(String[] args) {
        SplittableRandom random = new SplittableRandom();
        Scanner in = new Scanner(System.in);
        System.out.print("请输入玩家数:");
        int players = in.nextInt();
        System.out.print("有几副牌:");
        int vices = in.nextInt();
        String[] cards = new String[vices * (CARDS.length * FLOWERS.length + JOKERS.length)];
        String[][] playerCards = new String[players][cards.length / players];

        // 所有手牌
        for (int i = 0; i < vices; i++) {
            for (int j = 0; j < CARDS.length; j++) {
                for (int k = 0; k < FLOWERS.length; k++) {
                    cards[i * CARDS.length * FLOWERS.length + j * FLOWERS.length + k] = CARDS[j] + FLOWERS[k];
                }
            }
        }
        for (int i = 0; i < vices; i++) {
            for (int j = 0; j < JOKERS.length; j++) {
                cards[CARDS.length * FLOWERS.length * vices + i * JOKERS.length + j] = JOKERS[j];
            }
        }
        System.out.println(Arrays.toString(cards));

        // 发牌
        int count = cards.length;
        int indexX = 0;
        int indexY = 0;
        while (count > 0) {
            int p = random.split().nextInt(cards.length);
            String card = cards[p];
            if (!"0".equals(card)) {
                cards[p] = "0";
                playerCards[indexX++ % players][indexY++ % (cards.length / players)] = card;
                count--;
            }
        }

        for (int i = 0; i < players; i++) {
            System.out.print("玩家" + i + "手牌为:");
            System.out.print(Arrays.toString(playerCards[i]));
            System.out.println();
        }
    }
}
