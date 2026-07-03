Render deployment steps for GrameenCraft backend
===============================================

This file documents the exact Render UI options to deploy the backend Docker service from this repository.

1. Create a New Web Service
   - In Render dashboard click **New** → **Web Service**.
   - Select your Git provider and choose the `nafiswiths/Grameen_Craft` repository.

2. Use `render.yaml` (recommended)
   - Render will detect `render.yaml` and configure the service automatically.
   - Confirm the service name (e.g., `grameencraft-backend`) and branch `main`.

3. Manual configuration (if not using `render.yaml`)
   - Environment: **Docker**
   - Dockerfile Path: `backend/Dockerfile`
   - Build Command: leave blank (Docker handles build)
   - Start Command: leave blank (Docker image CMD handles start)
   - Health Check Path: `/api/db-status`
   - Auto-Deploy: **On** (recommended)

4. Environment variables / Secrets (Render > Environment)
   - Add the following as **Secret** values in Render:
     - `DATABASE_URL` = your PostgreSQL connection string (e.g. `postgresql://user:pass@host:5432/dbname`)
     - `GEMINI_API_KEY` = (optional) Google Gemini API key if using the heritage story feature

5. Instance Type
   - Starter (or Free) is OK for testing, but choose Standard/Pro for production traffic.

6. Post-deploy
   - Note the service URL (e.g., `https://grameencraft-backend.onrender.com`).
   - In Vercel, set `VITE_API_URL` to the Render URL (no trailing slash), e.g.:

```
VITE_API_URL=https://grameencraft-backend.onrender.com
```

7. Troubleshooting
   - If the build fails, open the build logs on Render — common issues:
     - Maven build errors (check `backend/pom.xml` and Java version)
     - Memory/timeouts on the Free plan — try Starter or Pro
   - Verify the health check `/api/db-status` returns 200 OK.

That's it — once Render finishes building, your backend will be live and your frontend can point to it.
