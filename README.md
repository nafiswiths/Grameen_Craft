# GrameenCraft — Authentic Handmade Bangladesh Heritage Platform

GrameenCraft is a premium, cultural e-commerce and storytelling platform connecting rural master artisans in Bangladesh directly with global buyers. By eliminating exploitative middlemen, 100% of the platform's direct profits flow back to the artisan families, preserving ancestral craftsmanship and sustaining rural livelihoods.

This project is a modern, high-performance, full-stack application migrated to **React (Vite) with Tailwind CSS** for the frontend, and **Express (Node.js)** for the backend server, featuring seamless integration with a **PostgreSQL database** and the **Google Gemini API**.

---

## ✨ Features

- 🎨 **Visual & Cultural Identity**: Highly responsive and eye-safe design with smooth page transitions and bilingual language support (English & বাংলা) celebrating Bangladeshi heritage.
- 🧺 **Authentic Handcrafted Categories**: Explore Nakshi Katha, Jute Creations, Clay Crafts, Bamboo & Cane Crafts, Handloom Clothing, Homemade Food, and traditional Jewellery.
- 👩‍🎨 **Master Artisan Showcases**: Deep dive into the lives, experiences, specialties, and stories of individual rural creators.
- 📜 **AI-Powered Heritage Storytelling**: Harnesses the **Google Gemini API** to generate beautiful, poetic, and culturally accurate storytelling narratives for products.
- 🛒 **Interactive Checkout & Shopping Cart**: Fully featured cart management, offline fallbacks, secure checkout simulations (Cash on Delivery, bKash, Card), and user dashboards.
- 🗄️ **Durable Cloud Database**: PostgreSQL schema support with automatic seeding of products, artisans, default users, and sample order history.
- 🔒 **Secure Admin Operations**: Control center for managing listings, updating inventory stock, toggling stock availability, updating roles, or deleting accounts.

---

## 🏗️ Architecture

The application operates as a unified, full-stack Node.js application perfectly optimized for modern cloud container platforms:

```text
/
├── server.js               # Express server containing database pools, API endpoints & Vite middleware
├── index.html              # Frontend single-page application entry point
├── package.json            # Node.js dependencies, linting, and build scripts
├── vite.config.js          # Vite configuration with React and Tailwind CSS integration
├── api/
│   └── index.ts            # Serverless deployment entry point
└── src/
    ├── main.tsx            # React application entry point
    ├── App.jsx             # React component managing views, state, checkout, and translation keys
    ├── index.css           # Global Tailwind CSS utility rules and typography settings
    └── data.js             # Local fallback datasets and translation resources
```

---

## 🚀 Getting Started & Local Setup

### 1. Prerequisites
- **Node.js** v22 or higher
- **npm** (comes packaged with Node.js)
- *(Optional)* A running **PostgreSQL** database (e.g., Supabase, Neon, local server)

### 2. Environment Variables
Configure your variables inside a `.env` file at the root of the project (see `.env.example` as a template):

```env
# Google Gemini API Key for automated storytelling
GEMINI_API_KEY="your_gemini_api_key"

# (Optional) PostgreSQL Connection String
DATABASE_URL="postgresql://user:password@host:port/dbname"
```

If `DATABASE_URL` is not provided, the application automatically runs in a **high-performance, fully persistent offline client/server mode** using structured in-memory datasets as safe fallbacks.

### 3. Installation
Install all dependencies from the root directory:
```bash
npm install
```

### 4. Running in Development
Start the full-stack development server on port `3000`:
```bash
npm run dev
```

### 5. Production Build & Start
Compile the client static files and bundle the backend server:
```bash
npm run build
npm start
```

---

## 🔒 Security & Admin Controls

Administrators (`ROLE_ADMIN`) can access administrative views in the dashboard to perform core services securely:
- **Update User Roles**: Change buyer/seller status.
- **Manage Users**: Securely remove spam or inactive accounts.
- **Product Management**: Add new crafts or remove retired listings.
- **Inventory Stock Toggle**: Instantly mark items as "In Stock" or "Sold Out".
- **Order Tracking**: Review and update delivery statuses (e.g., Shipped, Pending).

---

## 🌸 Cultural Heritage Preserved

GrameenCraft keeps traditional stitches, clay molds, bamboo splices, and handlooms alive for the next generation. Thank you for supporting sustainable rural craftsmanship!
