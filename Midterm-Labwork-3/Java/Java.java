public class Java {

    public static void main(String[] args) {

        // Hardcoded 3x3 matrix that is assigned to me
        int[][] m = {
            {2, 0, 1},
            {3, 5, 2},
            {1, 4, 3}
        };

        // To display the matrix
        System.out.println("Assigned Matrix:");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(m[i][j] + " ");
            }
            System.out.println("|");
        }

        // Calculate each specialized 2x2 column after the inclusion of the value of the 1st row
        int minor1 = (m[1][1] * m[2][2]) - (m[1][2] * m[2][1]);
        int minor2 = (m[1][0] * m[2][2]) - (m[1][2] * m[2][0]);
        int minor3 = (m[1][0] * m[2][1]) - (m[1][1] * m[2][0]);

        // Showing steps for cofactor expansion
        System.out.println("\nSteps:");
        System.out.println("Step 1: Minor M₁₁ = det([5,2],[4,3]) = (5*3) - (2*4) = 15 - 8 = 7");
        System.out.println("Step 2: Minor M₁₂ = det([3,2],[1,3]) = (3*3) - (2*1) = 9 - 2 = 7");
        System.out.println("Step 3: Minor M₁₃ = det([3,5],[1,4]) = (3*4) - (5*1) = 12 - 5 = 7");

        // Compute cofactor terms and determinant
        int det = m[0][0] * minor1 - m[0][1] * minor2 + m[0][2] * minor3;

        System.out.println("\nCofactors:");
        System.out.println("C₁₁ = (+1) * 2 * 7 = 14");
        System.out.println("C₁₂ = (-1) * 0 * 7 = 0");
        System.out.println("C₁₃ = (+1) * 1 * 7 = 7");

        // To determine the Determinant
        System.out.println("\nDeterminant = 14 + 0 + 7 = 21");

        if (det == 0) {
            System.out.println("The matrix is SINGULAR — it has no inverse.");
        }
    }
}