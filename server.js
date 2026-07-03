import express from "express";
import { spawn } from "child_process";
import path from "path";
import http from "http";
import { createServer as createViteServer } from "vite";

const app = express();
const PORT = 3000;
const BACKEND_PORT = 8080;

// Start Spring Boot backend process
console.log("Launching Spring Boot backend process via Maven under /backend...");
const springBoot = spawn("mvn", ["spring-boot:run"], {
  cwd: path.join(process.cwd(), "backend"),
  stdio: "inherit",
  shell: true
});

springBoot.on("close", (code) => {
  console.log(`Spring Boot process exited with code ${code}`);
});

// Proxy helper for API requests
app.all("/api/*", (req, res) => {
  const targetUrl = `http://127.0.0.1:${BACKEND_PORT}${req.originalUrl}`;
  
  const proxyReq = http.request(targetUrl, {
    method: req.method,
    headers: req.headers,
  }, (proxyRes) => {
    res.writeHead(proxyRes.statusCode, proxyRes.headers);
    proxyRes.pipe(res, { end: true });
  });

  proxyReq.on("error", (err) => {
    console.error("Proxy connection error to Spring Boot:", err.message);
    res.status(502).json({ 
      error: "Spring Boot backend is booting up. Please wait 10-15 seconds and refresh.",
      detail: err.message
    });
  });

  req.pipe(proxyReq, { end: true });
});

// Setup Vite middleware or static serving
async function startApp() {
  if (process.env.NODE_ENV !== "production") {
    const vite = await createViteServer({
      server: { middlewareMode: true },
      appType: "spa",
    });
    app.use(vite.middlewares);
  } else {
    const distPath = path.join(process.cwd(), "dist");
    app.use(express.static(distPath));
    app.get("*", (req, res) => {
      res.sendFile(path.join(distPath, "index.html"));
    });
  }

  app.listen(PORT, "0.0.0.0", () => {
    console.log(`GrameenCraft Full-Stack Proxy Server running on port ${PORT}`);
  });
}

startApp();

export default app;
