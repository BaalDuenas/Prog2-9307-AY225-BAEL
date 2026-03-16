// Hardcoded 3x3 matrix assigned to this student
const m = [
    [2, 0, 1],
    [3, 5, 2],
    [1, 4, 3]
];

// Print the matrix
console.log("Assigned Matrix:");
m.forEach(row => console.log("| " + row.join(" ") + " |"));

// Calculate the 2x2 minors
let minor1 = m[1][1] * m[2][2] - m[1][2] * m[2][1];
let minor2 = m[1][0] * m[2][2] - m[1][2] * m[2][0];
let minor3 = m[1][0] * m[2][1] - m[1][1] * m[2][0];

// Show steps for cofactor expansion
console.log("\nSteps:");
console.log("Step 1: Minor M₁₁ = det([5,2],[4,3]) = (5*3) - (2*4) = 15 - 8 = 7");
console.log("Step 2: Minor M₁₂ = det([3,2],[1,3]) = (3*3) - (2*1) = 9 - 2 = 7");
console.log("Step 3: Minor M₁₃ = det([3,5],[1,4]) = (3*4) - (5*1) = 12 - 5 = 7");

// Compute cofactor terms and determinant
let det = m[0][0] * minor1 - m[0][1] * minor2 + m[0][2] * minor3;

console.log("\nCofactors:");
console.log("C₁₁ = (+1) * 2 * 7 = 14");
console.log("C₁₂ = (-1) * 0 * 7 = 0");
console.log("C₁₃ = (+1) * 1 * 7 = 7");

// Final Determinant
console.log("\nDeterminant = 14 + 0 + 7 = 21");

if (det === 0) {
    console.log("The matrix is SINGULAR — it has no inverse.");
}