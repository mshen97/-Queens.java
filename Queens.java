//Queens.java
//Michael Shen
//mjshen
//pa5
//Lists all possible outcomes for n-Queens

import java.util.*;

class Queens {
      static void printArrayLine(int[][] A, int position) {
        System.out.print("(");
          for (int i = 1; i <= A[position].length-1; i++) {
            System.out.print(A[position][i]);
            if (i < A[position].length-1) {
              System.out.print(", ");
            } else {
              System.out.println(")");
            }
          }
      }
    
      static boolean isSolution(int[] A){
        int x,y;
        boolean ok = true;
        for (int i = 1; i <= A.length-1; i++) {
          for (int a = 1; a <= A.length-1; a++){
            if (a == i) {
              continue;
            } else if (a != i) {
              x = Math.abs(i - a);
              y = Math.abs(A[i] - A[a]);
              if (x == y) {
                ok = false;
              }
            }
          }
        }
        if (ok == true) {
          return(true);
        } else {
          return(false);
        }
      }
      
      static void swap(int[] value, int i, int j){
        int temp;
        temp = value[i];
        value[i] = value[j];
        value[j] = temp;
      }
      
      public static boolean isInteger(String s) {
        try { 
          Integer.parseInt(s); 
        } catch(NumberFormatException e) { 
          return false; 
        }
        return true;
      }
      
      static void ErrorMessage() {
        System.out.println("Usage: Queens [-v] number");
        System.out.println("Option: -v verbose output, print all solutions");
        System.exit(0);
      }
      
            static void nextPermutation(int[] A){
        int temp1 = A[A.length-1];
        int pivot = 0;
        int successor = 0;
        int temp2 = 0;
        int temp3 = 0;
        int pivotPos = A.length-1;
        int successorPos = A.length-1;
        for (int i = A.length-1; i >= 1; i--) {
          if (A[i] < temp1) {
            pivot = A[i];
            pivotPos = i;
            break;
          } else {
            temp1 = A[i];
          }
        }
        if (pivot == 0) {
          temp2 = 1;
          temp3 = A.length-1;
          while (temp2 < temp3) {
            swap(A,temp2,temp3);
            temp2++;
            temp3--;
          }
          return;
        }
        for (int i = A.length-1; i >= 1; i--) {
          if (A[i] > pivot) {
            successor = A[i];
            successorPos = i;
            break;
          }
        }
        swap(A,pivotPos,successorPos);
        if (pivotPos != A.length-1) {
          temp2 = pivotPos+1;
          temp3 = A.length-1;
          while (temp2 < temp3) {
            swap(A,temp2,temp3);
            temp2++;
            temp3--;
          }
        }
      return;
      }
      
      public static void main(String[] args){
        int result;
        int queensNumber;
        int ArrayCounter;

        if (args.length>0) {
        if (args[0].equals("-v")) {
          if (isInteger(args[1]) == true) {
            queensNumber = Integer.parseInt(args[1]);

            int[] shiftQueens = new int[queensNumber+1];

            result = queensNumber;
            for (int i = queensNumber-1; i > 0; i--) {
              result =  result*i;
            }
            int[][] resultArray = new int[result][queensNumber+1];
            ArrayCounter = 0;

            for (int i = 1; i <= queensNumber; i++) {
              shiftQueens[i] = i;
            }

            if (isSolution(shiftQueens) == true) {
              for (int i = 1; i <= queensNumber; i++) {
                resultArray[ArrayCounter][i] = shiftQueens[i];
              }
              ArrayCounter++;
            }

            for (int i = result; i > 0; i--) {
              nextPermutation(shiftQueens);
              if (isSolution(shiftQueens) == true) {
                for (int a = 1; a <= queensNumber; a++) {
                  resultArray[ArrayCounter][a] = shiftQueens[a];
                }
                ArrayCounter++;
              }
            }

            if (ArrayCounter != 0) {
              for (int i = 0; i < ArrayCounter; i++) {
                printArrayLine(resultArray,i);
              }
              System.out.println(queensNumber + "-Queens has " + ArrayCounter + " solutions");
              System.exit(0);
            } else {
              System.out.println(queensNumber + "-Queens has no solutions");
              System.exit(0);
            }
          } else {
            ErrorMessage();
          }

        } else if (isInteger(args[0]) == true) { 
          queensNumber = Integer.parseInt(args[0]);

          int[] shiftQueens = new int[queensNumber+1];

          result = queensNumber;
          for (int i = queensNumber-1; i > 0; i--) {
            result =  result*i;
          }
          ArrayCounter = 0;

          for (int i = 1; i <= queensNumber; i++) {
            shiftQueens[i] = i;
          }

          if (isSolution(shiftQueens) == true) {
            ArrayCounter++;
          }
                
          for (int i = result; i > 0; i--) {
            nextPermutation(shiftQueens);
            if (isSolution(shiftQueens) == true) {
              ArrayCounter++;
            }
          }

          if (ArrayCounter != 0) {
            System.out.println(queensNumber + "-Queens has " + ArrayCounter + " solutions");
            System.exit(0);
          } else {
            System.out.println(queensNumber + "-Queens has no solutions.");
            System.exit(0);
          }

        } else {
          ErrorMessage();
        }
      } else {
        ErrorMessage();
      }
      }
}
