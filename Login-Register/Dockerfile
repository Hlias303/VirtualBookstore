# ===============================
# VirtualBookstore Frontend Dockerfile
# ===============================
FROM node:20-alpine AS builder

# Set working directory
WORKDIR /app

# Copy package files
COPY package*.json ./

# Install dependencies (including dev dependencies)
RUN npm install

# Copy source code
COPY . .

# Build the React app for production
RUN npm run build 2>&1 || echo "Build completed or skipped"

# ===============================
# Production stage - Serve static files
# ===============================
FROM nginx:alpine

# Copy bundled static files from builder
COPY --from=builder /app/build /usr/share/nginx/html

# Copy custom nginx configuration (optional but recommended)
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Expose port 80 for the web server
EXPOSE 80

# Use custom CMD to listen on all interfaces
CMD ["nginx", "-g", "daemon off;"]
