const fs = require("fs");
const readline = require("readline");

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

console.log("=== LOW PERFORMING PRODUCTS DETECTION ===");
console.log("Please enter the FULL PATH to your dataset file (vgchartz-2024.csv).");
console.log("Example (Windows): C:\\Users\\YourUser\\Documents\\Midterm-Lab-1\\vgchartz-2024.csv");
console.log("Example (Mac/Linux): /Users/YourUser/Documents/Midterm-Lab-1/vgchartz-2024.csv\n");

function askFilePath() {
    rl.question("Enter dataset file path: ", (path) => {
        if (!fs.existsSync(path)) {
            console.log("Error: File not found. Ensure the path is correct.\n");
            askFilePath();
        } else if (!fs.statSync(path).isFile()) {
            console.log("Error: That is not a file. Please try again.\n");
            askFilePath();
        } else if (!path.toLowerCase().endsWith(".csv")) {
            console.log("Error: Invalid file type. Please enter a .csv file.\n");
            askFilePath();
        } else {
            console.log("File found. Processing dataset...\n");
            processFile(path);
        }
    });
}

function processFile(path) {
    try {
        const lines = fs.readFileSync(path, "utf8").trim().split("\n");
        const products = [];
        let totalSales = 0;

        // Skip header
        lines.slice(1).forEach(line => {
            const fields = line.split(",");
            if (fields.length >= 8) {
                const title = fields[1].trim();
                const sales = parseFloat(fields[7].trim());
                if (!isNaN(sales)) {
                    products.push({ title, sales });
                    totalSales += sales;
                }
            }
        });

        if (products.length === 0) {
            console.log("No valid sales data found. Please check dataset contents.");
            rl.close();
            return;
        }

        const averageSales = totalSales / products.length;
        console.log(`Average Total Sales: ${averageSales.toFixed(2)}\n`);
        console.log("LOW PERFORMING PRODUCTS (below average):");

        products
            .filter(p => p.sales < averageSales)
            .forEach(p => console.log(`• ${p.title} — Sales: ${p.sales}`));

        console.log("\nProcessing Complete.");

    } catch (err) {
        console.error("Error reading the file. Make sure it is readable and in correct CSV format.");
        console.error(err.message);
    } finally {
        rl.close();
    }
}

askFilePath();