import fs from 'fs';
import { ARTISANS, PRODUCTS } from './src/data.js';

fs.writeFileSync('./backend/src/main/resources/artisans.json', JSON.stringify(ARTISANS, null, 2));
fs.writeFileSync('./backend/src/main/resources/products.json', JSON.stringify(PRODUCTS, null, 2));
console.log("Artisans and Products successfully exported to JSON for Spring Boot backend.");
