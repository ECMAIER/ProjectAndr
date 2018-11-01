import java.util.Scanner;

public class Main {

    //this function takes in a base 10 integer, and returns a string representing
    //the number in the passed base
    //
    //for example if inNumber == 6 and base == 2, the return value would be "110", which is 6 in binary (base 2)

    //need to screen base for numbers greater than zero
    public static String BaseToDigit(int inNumber, int base){

        String baseRepresentation = "";
        char newChar;
        int newDigit;

        while(inNumber > 0){

            newDigit = inNumber % base;

            //for digits zero through nine, add ascii 48 (zero)
            if (newDigit< 10)
                newChar = (char) ((newDigit) + 48);
            //for digits >= 10, add  87, so that 10 +87 = 97, ascii lowercase a
            else
                newChar = (char) ((newDigit) + 87);

            baseRepresentation =  newChar + baseRepresentation;

            inNumber = inNumber / base;
        }

        return baseRepresentation;
    }

    //This function takes in a string representing a number in any base,
    //it converts the number to a (base 10) integer
    public static int fromDigits(String S, int base){

        int digit = 0;

        for (int i = 0; i < S.length(); i++){

            int temp;

            //convert character to an integer
            char c = S.charAt(i);
            if (Character.isDigit(c))
                temp = ((int) (c)) - 48;
            else
                temp = ((int) (c)) - 87;

            //
            digit += (int) (temp *  Math.pow(base,S.length()-i-1));


        }
        return digit;
    }

    public static void main(String[] args) {

        char more = 'a';
        boolean moreTesting = true;
        Scanner input = new Scanner(System.in);


        while (moreTesting) {
            System.out.println("\nEnter base 10 digit and base: \n");

            int n = input.nextInt();
            int b = input.nextInt();

            System.out.println(BaseToDigit(n, b));

            System.out.println("q to quit, any other to continue: ");
            more = input.next().charAt(0);
            if (more=='q')
                moreTesting = false;
        }

        moreTesting =true;

        while (moreTesting){
            System.out.println("\nEnter number: \n");

            String S = input.nextLine();

            System.out.println("\nEnter its base: \n");

            int base = Integer.parseInt(input.nextLine());

            System.out.println(fromDigits(S, base));


            System.out.println("q to quit, any other to continue: \n");

            more = input.next().charAt(0);
            if (more=='q')
                moreTesting = false;

            input.nextLine();

        }
    }
}
