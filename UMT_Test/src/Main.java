import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    /***
     * A pruning method to find faster if there is no way arrays B or C can exist under the condition they both have
     * the same average
     * @param sum : the sum of all elements in the array A
     * @param length : the length of the array A
     * @return false - if there cannot exist such arrays as B and C
     *         true  - if the pruning condition is satisfied; this does not guarantee there can exist B and C
     */
    static boolean prunning(int sum, int length) {
        for (int i = 1; i <= length / 2; i++) {
            // Condition based on the mathematical formula sum(B) = sum(A) * len(B) / len(A)
            if (sum * i % length == 0) {
                return true;
            }
        }
        return false;
    }

    /***
     *
     * @param A list of integers
     * @return true if A can be split in 2 arrays with the same average;
     *         false otherwise
     */
    static boolean sameAverageArrays(List<Integer> A) {
        int length = A.size();
        int necessary_length = length / 2;

        int sum = A.stream().reduce(0, Integer::sum);

        if (!prunning(sum, length))
            return false;

        //A list of sets where allSums[i] is a set of all the sums that can be formed with i elements
        List<Set<Integer>> allSums = new ArrayList<>();

        //We only need to find the sums with the half of total size, because the other ones are complementary
        for (int i = 0; i <= necessary_length; i++) {
            allSums.add(new HashSet<>());
        }

        //first set contains only the number 0
        allSums.get(0).add(0);

        //we create the set of sums
        for (int number : A) {
            for (int i = necessary_length; i >= 1; i--) {
                for (int sum_var : allSums.get(i - 1)) {
                    int new_sum = sum_var + number;
                    allSums.get(i).add(new_sum);
                    //When we find a sum that corresponds to the formula, we return true
                    if (new_sum == (double) sum * i / length) {
                        System.out.println("First array with length " + i +
                                " total sum " + new_sum +
                                " and average " + ((double) new_sum / i));
                        System.out.println("Second array with length " + (length - i) +
                                " total sum " + (sum - new_sum) +
                                " and average " + ((double) (sum - new_sum) / (length - i)));
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /***
     *
     * @param path the path of the input file - only one line, each number separated by exactly one space
     * @return a list with the integers from the file
     * @throws FileNotFoundException
     */
    static List<Integer> readFromFile(String path) throws FileNotFoundException {
        Scanner reader = new Scanner(new File(path));
        var line = reader.nextLine();
        var elems = line.split(" ");
        List<Integer> A = new ArrayList<>();
        for (var elem : elems) {
            A.add(Integer.parseInt(elem));
        }
        return A;
    }

    public static void main(String[] args) throws FileNotFoundException {

        System.out.println(sameAverageArrays(readFromFile("src/input.txt")));

    }
}