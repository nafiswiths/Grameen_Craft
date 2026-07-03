import axios from "axios";

// Create Axios Instance
const api = axios.create({
  baseURL: import.meta.env.VITE_API_URL || "/api",
  headers: {
    "Content-Type": "application/json",
  },
});

// Request Interceptor to add JWT authorization token dynamically
api.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem("grameen_jwt_token");
    if (token) {
      config.headers.Authorization = token; // This is 'Bearer <token>'
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

// Response Interceptor for cleaner error handling
api.interceptors.response.use(
  (response) => response.data,
  (error) => {
    const message = error.response?.data?.error || error.response?.data?.message || error.message || "An API error occurred";
    return Promise.reject(new Error(message));
  }
);

// Define core API services
export const authApi = {
  signup: (data) => api.post("/auth/signup", data),
  verify: (data) => api.post("/auth/verify", data),
  login: (data) => api.post("/auth/login", data),
};

export const productApi = {
  getAll: () => api.get("/products"),
  getById: (id) => api.get(`/products/${id}`),
  create: (data) => api.post("/products", data),
};

export const artisanApi = {
  create: (data) => api.post("/artisans", data),
};

export const orderApi = {
  create: (data) => api.post("/orders", data),
  getByUserEmail: (email) => api.get(`/orders/user/${email}`),
};

export const dbApi = {
  getStatus: () => api.get("/db-status"),
  sync: () => api.get("/sync"),
};

export const aiApi = {
  getHeritageStory: (data) => api.post("/heritage-story", data),
};

export const adminApi = {
  getUsers: () => api.get("/admin/users"),
  changeUserRole: (email, role) => api.post("/admin/users/role", { email, role }),
  deleteUser: (email) => api.delete(`/admin/users/${email}`),
  getOrders: () => api.get("/admin/orders"),
  changeOrderStatus: (id, status) => api.post("/admin/orders/status", { id, status }),
  deleteProduct: (id) => api.delete(`/admin/products/${id}`),
  toggleProductStock: (id, inStock) => api.post("/admin/products/toggle-stock", { id, inStock }),
};

export default api;
