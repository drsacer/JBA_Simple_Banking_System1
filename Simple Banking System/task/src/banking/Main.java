package banking;

import com.sun.security.jgss.GSSUtil;

import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static Random rnd = new Random();
    static String bin = "";
    static int nCard = 0, nPin =0;
    static String accNum = "", cardNum="", pin ="";
    static long cardNumber = 0;
    static int i = 0;
    static boolean menuShow = false;
    static String accountNumber ="";

    static List<String> accountList=new ArrayList<String>();

    public static void main(String[] args) {

        int ch = 3;

        do {
            if (menuShow==false) { // da se ne pokazuje početni meni kad se izlazi iz drugog menia
                                   // samo da se prikaže tekst bye
                menu();
            } else {
                break;
            }

            ch = sc.nextInt();
            if (ch == 1) {

                cardNum = generateCardNumber(/*bin,nCard,accNum,*/ cardNum);
                System.out.println("Your card has been created\n" +
                        "Your card number:");
                System.out.println(cardNum);
                System.out.println("Your card PIN:");
                pin = generatePIN(nPin,pin);
                System.out.println(pin);

                setCardsAndPins(cardNum,pin);


            } else if (ch==2){
                pinCheck();
            }
        }while(ch != 0);

    }

    public static void menu(){
        System.out.println("1. Create an account\n" +
                "2. Log into account\n" +
                "0. Exit");
    }

    public static String generateCardNumber (/*String bin, int nCard, String accNum, */ String cardNum ) {
         //bin = "40000";
         //nCard = 100000000 + rnd.nextInt(900000000);
        // accNum = Integer.toString(nCard);
        //cardNum = bin + nCard + 5;
        //cardNumber = Long.parseLong(cardNum);
        accountNumber = String.format("%09d", (long) (Math.random() * 999999999L));
        cardNum = "400000" + accountNumber;
        cardNum += generateLuhnAlgorithm(cardNum);
        return cardNum;
    }

    public static String generatePIN (int nPin, String pin){
        nPin = 1000 + rnd.nextInt(9000);
        pin= Integer.toString(nPin);
        return pin;
    }

    public static void pinCheck(){
        boolean menu = false;
        System.out.println("Enter your card number:");
        String cn = sc.next();
        System.out.println("Enter your PIN:");
        String p = sc.next();

        // u listi su račun kartice i pin upisani na sljedeći način :
        // 4000001628590620 8753 4000006739034817 3155 .......
        
        for (String num: accountList){          // indexOf daje poziciju  objekta (num)
            if (num.equals(cn) && accountList.get(accountList.indexOf(num)+1).equals(p)) {
                System.out.println("You have successfully logged in!\n");
                menu = true;
            }
        }
        if (menu == true){
            menu1();
        } else {
            System.out.println("Wrong card number or PIN!");
        }
    }
    // u listi su račun kartice i pin upisani na sljedeći način :
    // 4000001628590620 8753 4000006739034817 3155
    public static void setCardsAndPins(String cardNum, String pin){
        accountList.add(cardNum);
        accountList.add(pin);
       /*  for (String s:accountList){
            System.out.println(s);
        } */
    }

    public static void menu1(){
        int ch = 3;
        System.out.println("1. Balance\n" +
                "2. Log out\n" +
                "0. Exit");
        do {
            ch = sc.nextInt();
            if (ch == 1){
                System.out.println("Balance: 0");
                System.out.println("1. Balance\n" +
                        "2. Log out\n" +
                        "0. Exit");
            }
            else if (ch == 2) {
                System.out.println("You have successfully logged out!\n");
                //menu();
                break;
            }
            else if (ch == 0){
                System.out.println("Bye!");
                menuShow = true;
                break;
            }
        }while(ch!=0);
    }

    public static int generateLuhnAlgorithm(String cardNum){
        int[] cardIntArray=new int[cardNum.length()];
        int sum =0;
        for (int i = 0; i< cardNum.length(); i++) {
            char c = cardNum.charAt(i);
            cardIntArray[i] = Integer.parseInt("" + c);
        }
        for (int i = 0; i< cardNum.length()-2; i++) {
            int num = cardIntArray[i];
            num = num * 2;
            if(num>9) {
                num-= 9;
            }
            cardIntArray[i] = num;
            sum+=cardIntArray[i];
        }
        int digit = sum % 10;
        digit = 10 - digit;
        return digit;
    }

    public static void  print (){
        System.out.println("Comit");
    }
}